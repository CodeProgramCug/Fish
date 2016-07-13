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


<link href="css/base.css"rel="stylesheet"/>
<link href="css/jstree.min.css"rel="stylesheet"/>
<link href="css/xiaolong.css"rel="stylesheet"/>
<script type="text/javascript"src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"src="js/use.js"></script>
<script type="text/javascript"src="js/jstree.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

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
		<div class="article">
			<div id="tree-outer">
				<div id="tree"></div>
			</div>
		
			<div id="tree-table" >
				<div id="node-text"></div>
		<div id="input-place" >
			<div id="add-monitsite" class="add-data">
				<div class="context5" >
				<div class="float-left2">
					<span>用户名	:&nbsp</span><br>
					<span>密码:&nbsp</span><br>
					<span>联系方式:&nbsp</span><br>
					<span>Email:&nbsp</span><br>
				
				</div>
				<div class="float-left2" >
					<input type="text" id="username"><font>&nbsp*</font></input><br>
					<input type="text" id="password"><font>&nbsp*</font></input><br>
					<input type="text" id="tel"></input><br>
					<input type="text" id="email"><font>&nbsp*</font></input><br>
				</div>	
				<div class="float-left2">
					<span>身份:&nbsp</span><br>
					<span>用户状态:&nbsp</span><br>
					<span>上级:&nbsp</span><br>
					<span>角色:&nbsp</span><br>
				</div>
				<div class="float-left2">
					<input type="text" id="identify" ><font>&nbsp*</font></input><br>
					<input type="text" id="active"><font>&nbsp*</font></input><br>
					<input type="text" id="superior" /><font>&nbsp*</font></input><br>
					<input type="text" id="roleName"><font>&nbsp*</font></input><br>
					
					
				</div>			
				
			</div>
		</div>
				
		
	
					

					
			<button style="margin-left:40%" id="update-data"class="btn btn-primary">更新数据</button>
			<button style="margin-left:40%" id="delete-data"class="btn btn-primary">删除当前用户</button>
			<button style="margin-left:40%" id="new-data"class="btn btn-primary">增加用户</button>
					
				</div>
			
				
				

		<!-- 添加浮游、底栖生物-->	
		<div id="add-feature" class="add-data">
				
			</div>
		</div>

				
				
		
				
				<!--<h4>调查基本信息</h4>
				<br/>
				<p class="">
					<table class="table">
						<tr>
							<th>1</th>
							<th>2</th>
							<th>3</th>
						</tr>
						<tr>
							<td>2</td>
							<td>3</td>
							<td>4</td>
						</tr>
						<tr>
							<td>2</td>
							<td>3</td>
							<td>4</td>
						</tr>
						<tr>
							<td>2</td>
							<td>3</td>
							<td>4</td>
						</tr>
						<tr>
							<td>2</td>
							<td>3</td>
							<td>4</td>
						</tr>
					</table>
					
				</p>-->
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