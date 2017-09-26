<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/admin/admin_comm.css">
<script type="text/javascript"
	src="../js/common/jquery-2.0.3.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
#loading{
	position: absolute; 
	cursor1: wait; 
	left: 25px; 
	top:140px; 
	width: 90%; 
	height: 140px; 
	line-height: 57px; 
	padding-left: 50px; 
	padding-right: 5px; 
	background: #fff url(/Content/loading.gif) no-repeat scroll 5px 10px; 
	border: 2px solid #95B8E7; 
	color: #696969; 
	font-family:\'Microsoft YaHei\';
	display:none;
	
}
</style>
</head>
<body>
	<fieldset>
		<legend>数据导入  添加</legend>
		<form action="#" enctype="multipart/form-data" method="post" id="uploadForm">
			<table>
				<tr>
					<th>文件路径:</th>
					<td><input type="file" name="file" /></td>
				</tr>
				<tr>
					<th>预测期数:</th>
					<td>
						<select id="periodsId" style="width:155px;">
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<input id="uploadButton" type="button" value="上传" class="submit" />
						<input type="button" value="查询" class="reset" id="search" />
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
<fieldset><legend>查询结果</legend>
	<select id="groupId" style="width:155px;">
	</select><span id="desGroupNum"></span>
	<table class="tc" id="result">
		<tr>
			<th>序列号</th>
			<th>开奖号</th>
			<th>备注</th>
		</tr>
	</table>
	<div id="loading">正在加载…………</div>
	</fieldset>
</body>
<script type="text/javascript">
function doSubmit(){
	if(confirm("确认提交！")){
		return true;
	}
	return false;
}
$(function(){
	$(document).ready(function(){
		var dataArrays =[];
		var periods = 40;
		var default_periods = 20;
		var setHtml = function(id,name){
			var htmlClass = "<option value='"+id+"'>"+name+"</option>";
			return htmlClass;
		};
		var selectPeriods = function()
		{
			for(var i=1;i<= periods;i++)
			{
				if(default_periods===i)
				{
					$('#periodsId').append("<option value='"+default_periods+"' selected = 'selected'>"+default_periods+"</option>");
				}
				else
				{
					$('#periodsId').append(setHtml(i,i));
				}
			}
		}
		selectPeriods();
		$("#uploadButton").click(function () {
 			var formData = document.forms["uploadForm"];
			var form = new FormData();
			var file = formData["file"].files[0];
			form.append("file",file);
			form.append("fileName",file.name);
			form.append("fileSize",file.size); 
			$.ajax({
			    url: '../rest/file/upload',
			    type: 'POST',
			    cache: false,
			    data: form,
			    processData: false,
			    contentType: false
			}).done(function(res) {
				if(res.status){
					alert("导入数据成功!");
				}else{
					alert("导入数据失败!");
				}
			}).fail(function(res) {
				console.log(res);
			}); 
		});
		
		var setResultHtml = function(data)
		{
			var header = "<tr><th>序列号</th><th>开奖号</th><th>备注</th></tr>";
			if(!data || data.list.length<=0){
				$("#result").html(header);
				return;
			}
			var htmlClass = header;
			var dataList= data.list;
			for(var i=0;i<dataList.length;i++){
				if(dataList[i].status===1){
					htmlClass+="<tr><td><span style='color:red;'>"+dataList[i].number+"</span></td><td><span style='color:red;'>"+dataList[i].code+"</span></td><td>"+data.desc+"</td></tr>";
				}else{
					htmlClass+="<tr><td>"+dataList[i].number+"</td><td>"+dataList[i].code+"</td><td>"+data.desc+"</td></tr>";
				}
			}
			$("#result").html(htmlClass);
		}
		
		$("#search").click(function () {
 			var dataParam = {};
 			$('#loading').show();
 			dataParam.periodsId = $('#periodsId').val();
 			$.ajax({
			    url: '../rest/file/parse',
			    type: 'GET',
			    cache: false,
			    data: dataParam
			}).done(function(res) {
				if(res.status){
					alert("请导入文件！");
				}else{
					dataArrays = new Array();
					dataArrays = dataArrays.concat(res.asc,res.desc);
					$('#groupId').val("");
					$('#groupId').empty();
					if(dataArrays.length>0){
						for(var i=1;i<= dataArrays.length;i++)
						{
							$('#groupId').append(setHtml(i,i));
						}
					}

					$("#desGroupNum").html(("共查询出<span style='color:red;'>"+dataArrays.length+"</span>组(红色代表历史重复片段，黑色代表前后预测号码)"));
					setResultHtml(dataArrays[0]);
					$('#groupId').change(function(){ 
						setResultHtml(dataArrays[$('#groupId').val()-1]);
					});
				}
				$('#loading').hide();
			}).fail(function(res) {
				console.log(res);
			});
		});
	});
});
</script>
</html>