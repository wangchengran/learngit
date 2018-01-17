<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://cdn.bootcss.com/jquery/2.1.2/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var value1=$("#username").val();
	var value2=$("#password").val();
	var value3=$("#realname").val();
	var temp=0;
	if(value1==null){
		$("#div1").value("x");
	}else{
		$("#div1").value("√");
		temp=temp+1;
	}
	if(value2==null){
		$("#div2").value("x");
	}else{
		$("#div2").value("√");
		temp=temp+1;
	}
	if(value3==null){
		$("#div3").value("x");
	}else{
		$("#div3").value("√");
		temp=temp+1;
	}
	
	if(temp==3){
		$("form").submit();
	}
})



</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"  ></script>
</head>
<body>
<center>
	<form id="form" action="/register" method="post">
	请输入以下信息:<br>
	账号:<input id="username" type="text" name="username"><div id="div1"></div><br>
	姓名:<input id="realname" type="text" name="realname"><div id="div2"></div><br>
	登陆密码:<input id="password" type="password" name="password"><div id="div3"></</div><br>
	<input type="submit" value="提交">
	</form>
</center>
</body>
</html>