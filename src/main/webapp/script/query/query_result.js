 $(function () {
	loadForecastStage();
	loadData(dataArrays);
    $("#btnSerach").click(function () {
        var pars = {
            startStage:$("#startStage").val(),
            endStage: $("#endStage").val(),
            forecastStage: $("#forecastStage").combobox('getValue')             
        };
        searchData(pars);
    });
});
var dataArrays =[];
function searchData(params){
	$.ajax({
	    url: 'rest/file/searchForecastCode',
	    type: 'GET',
	    cache: false,
	    data: params,
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		loadEnd();
		var total = 0;
		$('#pp').empty();
		//清空数据!
		dataArrays = [];
		if(res.length>0){
			for(var index = 0;index < res.length;index++){
				dataArray = dataArrays.push(res[index].list);
				total +=res[index].list.length;
			}
			loadData(dataArrays[0]);
			loadPagination(total, dataArrays[0].length);
		}else{
			loadData(dataArrays);
			loadPagination(0, 0);
		}

	}).fail(function(res) {
		alert("查询出错！");
		loadEnd();
		//清空数据!
		dataArrays = [];
		loadData(dataArrays);
		loadPagination(0, 0);
	});
}

function loadPagination(total,pageSize){
    $('#pp').pagination({ 
    	total:total, 
    	pageSize:pageSize,
    	pageNumber:1,
    	displayMsg:"",
    	showPageList:false,
    	showRefresh:false,
    	afterPageText:"组  共{pages}组",
    	onSelectPage:function(pageNumber, pageSize){
    		loadData(dataArrays[pageNumber-1]);
    	}
    }); 
}

function setOptionDiv(id,name){
	return "<option value='"+id+"'>"+name+"</option>";
}

//加载下拉框
function loadForecastStage(){
	var startPeriods = 8;
	var endPeriods = 50;
	var defaultPeriods = 25;
	var data=[];
	for(var i=startPeriods;i<= endPeriods;i++)
	{
		data.push({"text":i, "id":i});				
	}
	$('#forecastStage').combobox({    
	    valueField:'id',    
	    textField:'text',
	    data:data	
	});
	$('#forecastStage').combobox('setValue',defaultPeriods);
}
//加载数据
function loadData(loadData) {
	var stage = $("#forecastStage").combobox('getValue');
    $('#tableResult').datagrid({
    	data:loadData,        
    	width: ($(window).width()*0.90), 
        height:($(window).height()*0.83)-100, 
        fitColumns: true, //列自适应
        nowrap: false,
        idField: 'number',//主键列的列名
        loadMsg: '正在加载信息...',
        pagination: false,//是否有分页
        onLoadSuccess: function(data){
        	$('#tableResult').datagrid('unselectAll');
        	$('#tableResult').datagrid('clearSelections');
        	if(data.total>0){
        		while(--stage >= 0){
        			$('#tableResult').datagrid('selectRow', stage);
        		}
        	}
        },
        columns: [[
            {field: 'number', title: '序列号', align: 'center', width: 80 },
            {field: 'code', title: '开奖号', align: 'center', width: 80 },
            {field: 'groupDesc', title: '所属组合', align: 'center', width: 240 }
        ]]
    });
}