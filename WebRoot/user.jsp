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
		<div class="article"id="article">
			<table class="table user-table">
						<tr>
							<th>#</th>
							<th>用户名</th>
							<th>密码</th>
							<th>邮箱</th>
							<th>注册时间</th>
							<th>修改</th>
							<th>删除</th>
						</tr>
						<tr>
							<td>1</td>
							<td>Daliu</td>
							<td>222</td>
							<td>891599396@qq.com</td>
							<td>2016-5-4</td>
							<td><a href="#"title="修改"><span class="glyphicon glyphicon-edit"></span></a></td>
							<td><a href="#"title="删除"><span class="glyphicon glyphicon-remove"></span></a></td>
						</tr>
						<tr>
							<td>2</td>
							<td>Daliu</td>
							<td>222</td>
							<td>891599396@qq.com</td>
							<td>2016-5-4</td>
							<td><a href="#"title="修改"><span class="glyphicon glyphicon-edit"></span></a></td>
							<td><a href="#"title="删除"><span class="glyphicon glyphicon-remove"></span></a></td>
						</tr>
						<tr>
							<td>3</td>
							<td>Daliu</td>
							<td>222</td>
							<td>891599396@qq.com</td>
							<td>2016-5-4</td>
							<td><a href="#"title="修改"><span class="glyphicon glyphicon-edit"></span></a></td>
							<td><a href="#"title="删除"><span class="glyphicon glyphicon-remove"></span></a></td>
						</tr>
						<tr>
							<td>4</td>
							<td>Daliu</td>
							<td>222</td>
							<td>891599396@qq.com</td>
							<td>2016-5-4</td>
							<td><a href="#"title="修改"><span class="glyphicon glyphicon-edit"></span></a></td>
							<td><a href="#"title="删除"><span class="glyphicon glyphicon-remove"></span></a></td>
						</tr>
					</table>
		</div>
		<div class="footer">
			<span class="info">用户名：User  &nbsp当前IP：220.249.99.152 &nbsp登录时间：2016-04-21 09:47</span> 
			<span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016 &nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>
	
</body>
</html>