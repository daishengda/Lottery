$(function () {
	$('#select_file').filebox({    
	    buttonText: '选择文件', 
	    buttonAlign: 'left' 
	});
    loadData();
    $('#search').searchbox({ 
		searcher:function(value,name){ 
			$('#record_table').datagrid('load', {    
			    filter: encodeURI(value)   
			});
		}, 
		width:200,
		prompt:'请输入值' 
	});
});
function importData(){
	var form = new FormData();
	var file = $('#filebox_file_id_1')[0].files[0];
	if(!file)
		alert("请选择文件！");
	form.append("file",file);
	form.append("fileName",file.name);
	form.append("fileSize",file.size);  
	$.ajax({
	    url: 'rest/file/upload',
	    type: 'POST',
	    cache: false,
	    data: form,
	    processData: false,
	    contentType: false,
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		if(!res.status){
			alert("导入出错！");
		}else{
			$('#record_table').datagrid('reload');
			$('#select_file').filebox('clear');	
		}
		loadEnd();
	}).fail(function(res) {
		alert("导入出错！");
		loadEnd();
		
	});
}

var editor = 0;
function loadData() {
    $('#record_table').datagrid({
        url: 'rest/file/searchRecord',
        method: 'GET',
        width: ($(window).width() * 0.90),
        height: ($(window).height() * 0.75),
        fitColumns: true, //列自适应
        nowrap: false,
        idField: 'id',//主键列的列名
        loadMsg: '正在加载信息...',
        pagination: true,//是否有分页
        //singleSelect: false,//是否单行选择
        pageSize: 15,//每页多少条数据
        pageNumber: 1,//默认的页
        pageList: [15, 30, 50],
        //queryParams: pars,//往后台传递参数
        toolbar: [{
    		iconCls: 'icon-add',
    		text:"添加",
    		handler: function(){
    			$('#record_table').datagrid('insertRow',{
    				index: 0,	// 索引从0开始
    				row: {
    					number: '',
    					id: "my"+editor ,
    					code: ''
    				}

    			});
    			$('#record_table').datagrid('beginEdit', 0);
    			editor++;
    		}
    	},'-',{
    		id:"search"
    	}],

        columns: [[
            { field: 'number', title: '序列号', align: 'number', width: 100,editor:{type:"text",options:{required:true}}},
            { field: 'code', title: '开奖号', align: 'code', width: 100,editor:{type:"text",options:{required:true}}},
            {
                field: 'operation', title: '操作', align: 'center', width: 100,
                formatter: function (value, row, index) {
                	var str = "";
                	if (row.editing){
                        str += "<a href='#' onclick=saveRow('"+row.id+"') class='easyui-linkbutton' style='width:50px'>保存</a> ";                              
                        str += "<a href='#' onclick=cancelRow('"+row.id+"') class='easyui-linkbutton' tyle='width:50px' >取消</a>";
                	} else{
                        str += "<a href='#' onclick=editRow('"+row.id+"') class='easyui-linkbutton' style='width:50px'>修改</a> ";                              
                        str += "<a href='#' onclick=deleteRow('"+row.id+"') class='easyui-linkbutton' tyle='width:50px'>删除</a>";
                	}
                    return str;
                }
            }
        ]],
        onBeforeEdit:function(index,row){
        	row.editing = true;
        	$('#record_table').datagrid('refreshRow', index);
        },
        onAfterEdit:function(index,row){
        	row.editing = false;
        	$('#record_table').datagrid('refreshRow', index);
        },
        onCancelEdit:function(index,row){
        	row.editing = false;
        	$('#record_table').datagrid('refreshRow', index);
        },
        onLoadSuccess:function(data){
        	
        }
    });
}

function editRow(id){
	var index = $('#record_table').datagrid('getRowIndex', id);
	$('#record_table').datagrid('beginEdit', index); 
}

function deleteRow(id){
	var index = $('#record_table').datagrid('getRowIndex', id);
	$.messager.confirm('提示框', '你确定要删除吗?',function(res){
		if(res){
			$.ajax({
			    url: 'rest/file/deleteRecord',
			    type: 'POST',
			    cache: false,
			    data:{id:id}
			}).done(function(res) {
				if(!res){
					alert("删除失败！");
				}else{
					$('#record_table').datagrid('deleteRow', index); 
				}
			}).fail(function(res) {
				alert("删除错误！");
			});
		}
	});
}

function saveRow(id){
	var obj={};
	var index = $('#record_table').datagrid('getRowIndex', id);
	var number = $($('#record_table').datagrid('getEditor', {index:index,field:'number'}).target).val();
	var code = $($('#record_table').datagrid('getEditor', {index:index,field:'code'}).target).val();
	obj.id = id;
	obj.number = number;
	obj.code = code;
	obj.index = index;
	if(!number || !code)
	{
		alert('序列号或者开奖号不允许为空！');
		return;
	}
	if(id.indexOf('my')>=0)
	{
		ajaxPost('rest/file/saveRecord', obj);
	} else{
		ajaxPost('rest/file/updateRecord', obj);
	}
}

function cancelRow(id){
	 var index = $('#record_table').datagrid('getRowIndex', id);
	if(id.indexOf('my')>=0){
		$('#record_table').datagrid('deleteRow', index);
	}else{
		$('#record_table').datagrid('cancelEdit', index);
	}
	
}

function ajaxPost(url,param)
{
	$.ajax({
	    url: url,
	    type: 'POST',
	    cache: false,
	    data:param,
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		if(res == -1){
			alert("保存错误！");
		}else{
			$('#record_table').datagrid('updateRow',{
				index: param.index,
				row: {
					id: res,
					number: param.number,
					code: param.code
				}
			});
			 var index = $('#record_table').datagrid('getRowIndex', res);
			$('#record_table').datagrid('endEdit', index);
		}
		loadEnd();
	}).fail(function(res) {
		alert("保存错误！");
		loadEnd();
	});
}

