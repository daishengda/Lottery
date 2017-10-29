function initDB(){
	$.ajax({
	    url: 'rest/upgrade/upgradeDatabase/',
	    type: 'POST',
	    cache: false,
	    beforeSend: function () {
	    	loading();                    
        }
	}).done(function(res) {
		if(!res.status){
			alert("升级出错！");
		}else{
			alert("升级数据库成功！");
		}
		loadEnd();
	}).fail(function(res) {
		alert("升级出错！");
		loadEnd();
	});
}