<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宝塔镇河妖</title>
<script src="https://cdn.bootcss.com/jquery/2.1.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#insert").click(function() {
			$("#div").show();
		})
		$("#hide").click(function() {
			$("#div").hide();
			$("#hide input").each(function() {
				$(this).val("");
			});
		})
		$("#search").keyup(function() {
			var q = $("#search").val();
			$("#showCompanyname").empty();
			$.get("/search/", {
				'text' : q
			}, function(data) {
				if (data.length!=0) {
					var jsonObj = eval('(' + data + ')');
					var $ul = $("<ul></ul>");
					$.each(jsonObj, function(i, item) {
						var $li = $("<li></li>");
						$li.text(item.name);
						$ul.append($li);
					})
					$("#showCompanyname").append($ul).show();
					$("ul li").each(function() {
						$(this).click(function() {
							$("#search").val($(this).text());
							$("#showCompanyname").hide();
							qv=$(this).text()
							$.get("/search/",{'text':qv},function(data1){
								$("#showCompanyname").hide();
								$("#tbody").empty();
								var jsonObj1 = eval('(' + data1 + ')');
								$.each(jsonObj1, function(i, item) {
									$("#tbody").append("<tr>"+
											"<td>"+item.name+"</td>"+
											"<td>"+item.phonenumber+"</td>"+
											"<td>"+item.age+"</td>"+
											"<td>"+item.company+"</td>"+
											"<td>"+item.email+"</td>"+
										"</tr>>")
								})
							})
						});
					});
				}
			})
		})
				
	$("#checkAll").click(function() {
							if ($(this).is(':checked')) {
								$('input[name="check"]').each(function() {
									//此处如果用attr，会出现第三次失效的情况
									$(this).prop("checked", true);
								});
							} else {
								$('input[name="check"]').each(function() {
									$(this).removeAttr("checked", false);
								});
								//$(this).removeAttr("checked");
							}
						});
						$("#search").keydown(function() {
							$("#showCompanyname").empty();
						})
					
		$("#delete").click(function(){
			var str="";
			$('input:checkbox[name=check]:checked').each(function(i){
				if(i==0){
					str+=$(this).val()
				}else{
					str+=","+$(this).val()
				}
			})
			window.location.href="/delete?ids="+str
		})
	});
	
</script>
<style type="text/css">

</style>
</head>
<body>
	<center>
		<h1 style="color: purple;">${username}的电话本</h1>
		<a href="/exit">退出</a>
		<table id="table-3">
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"></th>
					<th width="40px">姓名</th>
					<th>号码</th>
					<th>年龄</th>
					<th>公司</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach var="l" items="${page.phone }">
					<tr>
						<td><input type="checkbox" name="check" value="${l.id }"></td>
						<td width="40px">${l.name }</td>
						<td>${l.phonenumber}</td>
						<td>${l.age }</td>
						<td>${l.company }</td>
						<td>${l.email }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<button
			onclick="window.location.href='/page?num=${page.from}&size=${page.size }'">首页</button>
		<button
			onclick="window.location.href='/page?num=${page.pageCount-1}&size=${page.size }'">上一页</button>
		当前第${page.pageCount}页/共${page.to}页
		<button
			onclick="window.location.href='/page?num=${page.pageCount+1}&size=${page.size }'">下一页</button>
		<button
			onclick="window.location.href='/page?num=${page.to}&size=${page.size }'">末页</button>
		<br> 
		<button id="delete">删除</button>
		搜索<input id="search" type="text">
		<button id="insert">添加</button>
		<div class="bdsug" style="height: auto;" id="showCompanyname"></div>
		<br />

		<div id="div" style="height: 60px; width: 200px; display: none">
			<button id="hide" style="float: right">×</button>
			<br>
			<form style="float: left" action="insert" method="post">
				姓名:<input type="text" name="name"><br> 号码:<input
					type="text" name="phonenumber"><br> 年龄:<input
					type="text" name="age"><br> 公司:<input type="text"
					name="company"><br> Email:<input type="text"
					name="email"><br> <input type="submit" value="提交">
			</form>
		</div>
	</center>
</body>
</html>