<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page language="java" import="java.util.*"%>
<%@page language="java" import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="db_tool.User"%>
<%@page language="java" import="db_tool.GetSqlSession"%>
<%@page language="java" import="org.apache.ibatis.session.SqlSession"%>

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
<link href="css/table.css" rel="stylesheet" />

<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>


<style>
.dropbtn {
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 100px;
}

.dropdown-content a {
    color: black;
    text-decoration: none;
    display: block;
}
.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}
</style>




</head>
<%
	SqlSession sqlSession = GetSqlSession.getSqlSession();
	List<User> listUserinfo1 = sqlSession.selectList("fishSqlMapper.getAllStaff",((User) session.getAttribute("user")).getUsername());
%>

<body>
	<%@ include file="top.jsp"%>
	<div class="section">
		<%@ include file="left.jsp"%>
		<div class="article" id="article">
			<div>下属：</div>

			<div>
				<table class="bordered"
					style="margin-left: auto;margin-right: auto;">
					<thead>
						<tr>
							<th>username</th>
							<th>Telephone</th>
							<th>Email</th>
							<th>Description</th>
							<th>superiorName</th>
							<th>roleName</th>
							<th>active</th>
							<th>修改</th>
							<th>删除</th>
							<th>查看</th>
						</tr>
					</thead>
					<%
					for (int i = 0; i < listUserinfo1.size(); i++) {
						User useritem = listUserinfo1.get(i);
				%>
					<tr>
						<td><%=useritem.getUsername()%></td>
						<td><%=useritem.getTelephone()%></td>
						<td><%=useritem.getEmail()%></td>
						<td><%=useritem.getDescription()%></td>
						<td><%=useritem.getSuperiorname()%></td>
						<td><%=useritem.getRolename()%></td>
						<td><%=useritem.getActive()%></td>
						<td><a href="#"><i class="icon-pencil"></i> Edit</a>
						</td>
						<td><a href="#"><i class="icon-pencil"></i> Delete</a>
						</td>
						<td>
						
						<div class="dropdown">
  <a class="dropbtn">下拉菜单</a>
  <div class="dropdown-content">
    <a href="http://www.runoob.com">菜鸟教程 1</a>
    <a href="http://www.runoob.com">菜鸟教程 2</a>
    <a href="http://www.runoob.com">菜鸟教程 3</a>
  </div>
</div>
						
						
						</td>
					</tr>
					<%
					}
				%>
				</table>
			</div>
		</div>
		<div class="footer">
			<span class="info">用户名：User &nbsp当前IP：220.249.99.152
				&nbsp登录时间：2016-04-21 09:47</span> <span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016
				&nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>

</body>
</html>