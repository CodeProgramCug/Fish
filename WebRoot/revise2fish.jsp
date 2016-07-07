<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%@ page language="java" import="java.util.*"%>
<%@page language="java" import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="db_tool.User"%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据录入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
<meta name="author" content="Daliu"/>
<meta name="keywords" content="长江上游珍稀特有鱼类保护信息管理系统、长江上游、珍稀特有鱼类、保护信息"/>
<meta name="describe" content="长江上游珍稀特有鱼类保护信息管理系统"/>
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/base.css"rel="stylesheet"/>
	<link href="css/xiaolong.css"rel="stylesheet"/>
  </head>
  <script>
  </script>
  <body>
  	<%@ include file="top.jsp"%>
	<div class="section">
		<%@ include file="left.jsp"%>
		<div class="article" style="height:500px;width:100%;">
			<h4>监测数据表修改：</h4>
			<br><br>
			<br> 
	
			
			
		
		</div>
		<div class="footer">
			<span class="info">用户名：User  &nbsp当前IP：220.249.99.152 &nbsp登录时间：2016-04-21 09:47</span> 
			<span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016 &nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>
  </body>
  <script>
  window.onload=function(){
  
  	alert("您已成功修改数据，请点击确认");
  	
  	//window.close();
  history.back(-1);
  history.back(-1);
  }
  
  </script>
</html>
