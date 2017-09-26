function initDB(){
	$.ajax({
	    url: 'rest/file/init',
	    type: 'POST',
	    cache: false,
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		if(!res.status){
			alert("导入出错！");
		}else{
			alert("初始化数据库成功！");
		}
		loadEnd();
	}).fail(function(res) {
		alert("导入出错！");
		loadEnd();
	});
}