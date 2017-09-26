<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>重庆时时彩</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<script type="text/javascript"
	src="js/common/jquery-2.0.3.min.js"></script>
<head>
<style type="text/css">
body {
	background: #008ead;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei",
		"\9ED1\4F53", Arial, sans-serif;
	color: #222;
	font-size: 12px;
}

* {
	padding: 0px;
	margin: 0px;
}

table {
	width: 100%;
	border: 0px;
}

table, table tr, table tr td {
	padding-top: 0px;
	padding-bottom: 0px;
	padding-left: 5px;
	padding-right: 5px;
	margin: 0px;
}

.menu {
	width: 20%;
}

.c {
	width: 1px;
}

.content {
	
}

#top {
	background: url("images/admin/mlogo.gif") no-repeat;
	height: 100px;
	line-height: 168px;
	color: #ffffff;
	font-size: 16px;
	text-align: right;
}

#main {
	width: 100%;
}

.index_page{
	color:#ffffff;
}
</style>
</head>
<body>
	<div id="top">
		今天是<span id="date"></span>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="default.jsp" target="content" class="index_page">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	</div>
	<div id="main">
		<table>
			<tr>
				<td class="menu"><iframe id="menu" name="menu" frameborder="0"
						width="100%" height="558" src="menu.jsp"></iframe></td>
				<td class="c"></td>
				<td class="content"><iframe id="content" name="content"
						frameborder="0" width="100%" height="558" src="default.jsp"></iframe>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<script type="text/javascript">
	/*生成日期*/
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	document.getElementById("date").innerHTML = year + "年" + month + "月" + day
			+ "日";
</script>
