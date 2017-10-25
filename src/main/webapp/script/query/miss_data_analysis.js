$(function () {
	$('#select_file').filebox({    
	    buttonText: '选择文件', 
	    buttonAlign: 'left' 
	});
//    loadData();
    loadDropDownList();
//    queryMissData();
    $('#search').searchbox({ 
		searcher:function(value,name){ 
			$('#miss_result_table').datagrid('load', {    
			    filter: encodeURI(value)   
			});
		}, 
		width:200,
		prompt:'请输入值' 
	});
});

//加载下拉框
function loadDropDownList(){
	var arraysMap = {2:'二星组合',3:'三星组合',4:'四星组合',5:'五星组合'};
	var defaultGroup = 2;
	var data = [];
	for(var key in arraysMap){
		data.push({"text":arraysMap[key], "id":key});
	}
	$('#miss_group').combobox({    
	    valueField:'id',    
	    textField:'text',
	    data:data	
	});
    $('#miss_group').combobox({  
        onChange:function(){  
        	queryMissData();
        }
    });
	$('#miss_group').combobox('setValue',defaultGroup);

}
function queryMissData(){
	var pageSize = 30;
	var missGroup = $("#miss_group").combobox('getValue');
	var params = {digit : missGroup};
	$.ajax({
	    url: 'rest/miss/queryMissResult',
	    type: 'GET',
	    cache: false,
	    data: params,
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		loadEnd();
		var total = 0;
		$('#pagination').empty();
		loadData(res);
	}).fail(function(res) {
		alert("查询出错！");
		loadEnd();
		//清空数据!
		loadData([]);
	});
}

//重新计算遗漏结果
function recountMissData()
{
	$.ajax({
	    url: 'rest/miss/saveMissResult',
	    type: 'POST',
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		loadEnd();
		if(res.status){
			queryMissData();
		}
		else{
			alert("重新计算出错！");
		}
	}).fail(function(res) {
		alert("查询出错！");
		loadEnd();
		//清空数据!
		loadData([]);
	});
}

//加载数据
function loadData(loadData) {
	var columnsData = [{field: 'group', title: '组合', align: 'center', width: 25},
	                   {field: 'digit', title: '位数', align: 'center', width: 25,hidden:true }];
	//重新组装数据
	var showData = [];
	//只取一次值
	var isExists = false;
	loadData.forEach(function(item,index){
		var showItem = {};
		showItem.group = item.group;
		showItem.digit = item.digit;
		item.missList.forEach(function(missItem,index){
			var fileld = 'miss_'+missItem.type;
			var fileldValue;
			var isCurrent = missItem.end === 0;
			//本期遗漏
			if(isCurrent){
				fileld += '_current';
				fileldValue = missItem.missPeriods;
			}else{
				fileld += '_max';
				fileldValue = missItem.missPeriods+'('+missItem.begin+'~'+missItem.end+')';
			}
			if(!isExists){
				var columnsWidth = isCurrent ? 30 : 90;
				var columnsItem = {align: 'center', width: columnsWidth};
				columnsItem.field = fileld;
				columnsItem.title = missItem.desc;
				columnsData.push(columnsItem);
			}
			showItem[fileld] = fileldValue;
		});
		isExists = true;
		showData.push(showItem);
	});
    $('#miss_result_table').datagrid({
    	data:showData,        
    	width: ($(window).width()*0.98), 
        height:($(window).height()*0.83)-100, 
        fitColumns: true, //列自适应
        nowrap: false,
        idField: 'group',//主键列的列名
        loadMsg: '正在加载信息...',
        pagination: false,//是否有分页
        columns: [columnsData]
    });
}
