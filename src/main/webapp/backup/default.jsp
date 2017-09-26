<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String path = request.getScheme()+"://"+request.getServerName();
%>
<html>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <script type="text/javascript" src="js/common/jquery-2.0.3.min.js"></script>
<head>
<style type="text/css">
body{
	background: #ebebeb;
}
embed{
	position: relative;
	left:120px;
	top:8px;
}
</style>
</head>
<body>
<embed src="images/admin/welcome.swf" width="770" height="530">
</body>
</html>
