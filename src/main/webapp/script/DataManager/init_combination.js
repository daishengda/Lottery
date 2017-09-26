$(function () {
    loadData();
});
function saveCombination(){
	$.ajax({
	    url: 'rest/file/saveCombination',
	    type: 'POST',
	    cache: false,
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		if(!res.status){
			alert("导入出错！");
		}else{
			loadData();
		}
		loadEnd();
	}).fail(function(res) {
		alert("导入出错！");
		loadEnd();
	});
}
function loadData() {
    $('#combination_table').datagrid({
        url: 'rest/file/searchCombination',
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
        columns: [[
            { field: 'id', checkbox: true, align: 'left', width: 30 },
            { field: 'left', title: '前5位', align: 'center', width: 100 },
            { field: 'right', title: '后5位', align: 'center', width: 100 }
        ]]
    });
}