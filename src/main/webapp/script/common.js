function loading(){
	var a = $("<div/>",{id:"loading"});
	a.dialog({    
	    title: '',    
	    width:416,    
	    height: 316,
	    top:150,
	    left:300,
	    closable:false,
	    closed: false,    
	    cache: false, 
	    content:"<image src='images/loading021.gif'>",
	    //href: 'images/loading021.gif',    
	    modal: true   
	}); 
}

function loadEnd(){
	$('#loading').dialog("destroy");
}