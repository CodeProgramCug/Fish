<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta name="author" content="Daliu" />
<meta name="keywords" content="长江上游珍稀特有鱼类保护信息管理系统、长江上游、珍稀特有鱼类、保护信息" />
<meta name="describe" content="长江上游珍稀特有鱼类保护信息管理系统" />
<title>长江上游珍稀特有鱼类保护信息管理系统</title>
<link href="css/base.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
//http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js
 function yns(){  
    var publish=$("#formxx [name='publish']:checked").val();
 
	if(publish=="yes"){
	    
		document.getElementById('cnt').innerHTML='<font color="red">&nbsp*</font>';
 
	}else{
		document.getElementById('cnt').innerHTML=' ';
	}
	
}
 function checkBlankSpace(str){
	    while(str.lastIndexOf(" ")>=0){
	      	str = str.replace(" ","");
	    }
	    while(str.lastIndexOf("\n")>=0){
	      	str = str.replace("\n","");
	    }
	    
	    if(str.length == 0){
	     	return 1;
	    }
   }

function check(){
	var password=$("#loginform [name='password']").val();
	var name=$("#loginform [name='username']").val();
	
		var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写        
		if (inputCode.length <= 0) { //若输入的验证码长度为0  
			alert("请输入验证码！"); //则弹出请输入验证码 
			return false; 
		} else if (inputCode != code) { //若输入的验证码与产生的验证码不一致时  
			alert("验证码输入错误！@_@"); //则弹出验证码输入错误  
			createCode();//刷新验证码  
			document.getElementById("input").value = "";//清空文本框 
			return false; 
		} 
	
	if(name==null||name.length==0||checkBlankSpace(name)==1){
		alert("用户名不能为空");
  		$("#username").focus();
		return false;
	}
	if(checkRegister()==false){
		return false;
	}
	if(password==null||password.length==0||checkBlankSpace(password)==1){
		alert("密码不能为空! ");
		return false;
	}
	

}
  function checkRegister(){
  	var uname = $("#username").val();
  	var passwd = $("#password").val();
  	//检查输入内容格式
    var flag = true;
    $.ajax({
		type : "post",
		async:false,
		url : "check.action?username="+uname+"&password="+passwd,
		success : function(result) {
			if(result=="nouserErr"){
				alert("该用户不存在！");
				$("#username").focus();
				flag = false
			}
			if(result=="passwdErr"){
				alert("密码错误！");
				$("#password").focus();
				flag = false
			}
		},
		failure:function(){}
     });
     return flag;
  }
</script>

<script type="text/javascript">
	var code; //在全局定义验证码   
	//产生验证码  
	window.onload = function createCode() {
		code = "";
		var codeLength = 4;//验证码的长度  
		var checkCode = document.getElementById("code");
		var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//随机数  
		for ( var i = 0; i < codeLength; i++) {//循环操作  
			var index = Math.floor(Math.random() * 36);//取得随机数的索引（0~35）  
			code += random[index];//根据索引取得随机数加到code上  
		}
		checkCode.value = code;//把code值赋给验证码  
	}
	
	
	function createCode() {
		code = "";
		var codeLength = 4;//验证码的长度  
		var checkCode = document.getElementById("code");
		var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//随机数  
		for ( var i = 0; i < codeLength; i++) {//循环操作  
			var index = Math.floor(Math.random() * 36);//取得随机数的索引（0~35）  
			code += random[index];//根据索引取得随机数加到code上  
		}
		checkCode.value = code;//把code值赋给验证码  
	}
	//校验验证码  
	function validate() {
		var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写        
		if (inputCode.length <= 0) { //若输入的验证码长度为0  
			alert("请输入验证码！"); //则弹出请输入验证码  
		} else if (inputCode != code) { //若输入的验证码与产生的验证码不一致时  
			alert("验证码输入错误！@_@"); //则弹出验证码输入错误  
			createCode();//刷新验证码  
			document.getElementById("input").value = "";//清空文本框  
		} else { //输入正确时  
			alert("^-^"); //弹出^-^  
		}
	}
</script>

</head>
<body class="background">
	<form id="loginform" action="login.action" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<table id="login">
		<tr>
			<th colspan="3">长江上游珍稀特有鱼类信息管理系统</th>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><input type="text" placeholder="username" id="username" name="username" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td>密&nbsp&nbsp&nbsp码：</td>
			<td><input type="password" placeholder="password" id="password" name="password" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td>验证码：</td>
			<td><input type="text" id="input" />
			</td>
			<td><input type="button" id="code" onclick="createCode()" style = "width:45px"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="提交" /> <a href="register.jsp">注册</a></td>
			<td></td>
		</tr>
	</table>
	</form>
</body>
</html>