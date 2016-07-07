<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page language="java" import="java.util.*"%>
<%@page language="java" import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="db_tool.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta name="author" content="Daliu"/>
<meta name="keywords" content="长江上游珍稀特有鱼类保护信息管理系统、长江上游、珍稀特有鱼类、保护信息"/>
<meta name="describe" content="长江上游珍稀特有鱼类保护信息管理系统"/>
<title>长江上游珍稀特有鱼类保护信息管理系统</title>
<link href="css/base.css"rel="stylesheet"/>
<script type="text/javascript"src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"src="js/base.js"></script>
</head>
<body>
	<%@ include file="top.jsp"%>
	<div class="section">
		<%@ include file="left.jsp"%>
		<div id="article">
			<div id="map">
				<div id="mapview"></div>
				<div id="mapinfo">地图相关信息</div>
			</div>
			
			<div id="search">
				<div id="key"><span class="glyphicon glyphicon-triangle-right"></span></div>
				<div id="key2"><span class="glyphicon glyphicon-triangle-left"></span></div>
				<h4>调查基本信息</h4>
				<br/>
				<p class="">
					<span class="">调查人</span>
					<span class=""><input type="text"id="searchUser"name="searchUser"/></span>
					<span class="">调查时间</span>
					<span class=""><input type="text"id="searchDate"name="searchDate"/></span>
					
					<br/>
					<br/>
					<button id="search-button">查询</button>
					
				</p>
			</div>
		</div>
		<div class="footer">
			<span class="info">用户名：User  &nbsp当前IP：220.249.99.152 &nbsp登录时间：2016-04-21 09:47</span> 
			<span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016 &nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>
	
</body>
</html>