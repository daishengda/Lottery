$(function () {
	$('#select_file').filebox({    
	    buttonText: '选择文件', 
	    buttonAlign: 'left' 
	});
    loadData();
    loadDiffStage();
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

//加载下拉框
function loadDiffStage(){
	var startPeriods = 1;
	var endPeriods = 9;
	var defaultPeriods = 1;
	var data=[];
	for(var i=startPeriods;i<= endPeriods;i++)
	{
		data.push({"text":i, "id":i});				
	}
	$('#diffStage').combobox({    
	    valueField:'id',    
	    textField:'text',
	    data:data	
	});
	$('#diffStage').combobox('setValue',defaultPeriods);
}
function importData(){
	var diffStage = $("#diffStage").combobox('getValue');
	var form = new FormData();
	var file = $('#filebox_file_id_1')[0].files[0];
	if(!file)
		alert("请选择文件！");
	form.append("file",file);
	form.append("fileName",file.name);
	form.append("fileSize",file.size);  
	form.append("diffStage",diffStage);  
	$.ajax({
	    url: 'rest/excel/parse',
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
			//下载文件
			downloadFile(res.status);
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
        url: 'rest/excel/searchResult',
        method: 'GET',
        width: ($(window).width() * 0.90),
        height: ($(window).height() * 0.75),
        fitColumns: true, //列自适应
        nowrap: false,
        idField: 'fileName',//主键列的列名
        loadMsg: '正在加载信息...',
        pagination: true,//是否有分页
        //singleSelect: false,//是否单行选择
        pageSize: 15,//每页多少条数据
        pageNumber: 1,//默认的页
        pageList: [15, 30, 50],
        //queryParams: pars,//往后台传递参数
        toolbar: [/*{
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
    	},'-',*/{
    		id:"search"
    	}],

        columns: [[
            { field: 'generateTime', title: '分析日期', align: 'generateTime', width: 100,editor:{type:"text",options:{required:true}}},
            { field: 'fileName', title: '结果文件', align: 'fileName', width: 100,editor:{type:"text",options:{required:true}}},
            {
                field: 'operation', title: '操作', align: 'center', width: 100,
                formatter: function (value, row, index) {
                	var str = "";
                	str += "<a href='#' onclick=downloadRow('"+row.fileName+"') class='easyui-linkbutton' style='width:50px'>下载</a> ";                              
                    str += "<a href='#' onclick=deleteRow('"+row.fileName+"') class='easyui-linkbutton' tyle='width:50px'>删除</a>";
                    return str;
                }
            }
        ]],
        onBeforeEdit:function(index,row){
        	$('#record_table').datagrid('refreshRow', index);
        },
        onAfterEdit:function(index,row){
        	$('#record_table').datagrid('refreshRow', index);
        },
        onCancelEdit:function(index,row){
        	$('#record_table').datagrid('refreshRow', index);
        },
        onLoadSuccess:function(data){
        	
        }
    });
}

function downloadRow(id){
	downloadFile(id);
}

function deleteRow(id){
	var index = $('#record_table').datagrid('getRowIndex', id);
	$.messager.confirm('提示框', '你确定要删除吗?',function(res){
		if(res){
			var url = 'rest/excel/delete/'+id;
			$.ajax({
			    url: url,
			    type: 'GET',
			    cache: false
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

function downloadFile(fileName)
{
	var url = 'rest/excel/download/'+fileName;
    jQuery('<form action="'+url+'" method="GET">'+ // action请求路径及推送方法
        '</form>').appendTo('body').submit().remove();
}

function downloadTemplate()
{
	var url = 'rest/excel/downloadTemplate';
    jQuery('<form action="'+url+'" method="GET">'+ // action请求路径及推送方法
        '</form>').appendTo('body').submit().remove();
}

