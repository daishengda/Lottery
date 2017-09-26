<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<script type="text/javascript"
	src="js/common/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="js/common/util.js"></script>
<head>
<style type="text/css">
body {
	background: #ebebeb;
}

ul {
	margin: 0px;
	padding: 0px;
}

ul li {
	list-style-type: none;
	padding-top: 5px;
	padding-bottom: 5px;
	padding-left: 5px;
	padding-right: 0px;
	background-color: #99CCFF;
	border-bottom: #ffffff solid 2px;
	padding-left: 5px;;
}

a {
	text-decoration: none;
	font-size: 23px;
	width: 100%;
	font-weight: bold;
}

a:link, a:visited {
	color: #008edd;
}

a:hover {
	color: #dd3409;
}

.selected {
	background-color: #33CCFF;
}

.normal {
	border-top: #ffffff solid 2px;
	border-bottom: #99CCFF solid 2px;
	background-color: #cde6e3;
	padding-left: 10px;
}
.selected2 {
	border-top: #ffffff solid 2px;
	border-bottom: #99CCFF solid 2px;
	background-color: #33CCFF;
	padding-left: 10px;
}
</style>
</head>
<body>
	<ul>
		<li><a href="menu/menuManager.jsp"
			target="content">数据管理</a></li>
	</ul>
</body>
</html>
<script type="text/javascript">
	/*动态添加列表的样式*/
	function select(e) {
		var lis = document.getElementsByTagName("li");
		for (var i = 0; i < lis.length; i++) {
			lis[i].className = "";
		}
		e.parentNode.className = "selected";
		
	}
	 
	 //绑定onclick事件
	 window.onload = function() {
	 var as = document.getElementsByTagName("a");
	 for (var i = 0; i < as.length; i++) {
	 as[i].setAttribute("onclick", "select(this)");
	 }
	 } 
</script>
