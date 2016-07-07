<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.io.*"%>
<%
	Map attribute = ActionContext.getContext().getSession();
	ArrayList<String> data = (ArrayList<String>) attribute
			.get("fishData");
	ArrayList<String> colname=(ArrayList<String>) attribute.get("colname");
	int columnCount = (Integer) attribute.get("count");
%>
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
	<div class="nav">
		<div id="logo"></div>
		<div class="nav-menu">
			<span class="nav-menu-list"><span class="glyphicon glyphicon-send"></span>&nbsp导航</span>
			<span class="nav-menu-list"><span class="glyphicon glyphicon-list-alt"></span>&nbsp数据库明细</span>
			<span class="nav-menu-list"><span class="glyphicon glyphicon-duplicate"></span>&nbsp数据备份</span>
			<span class="nav-menu-list"><span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp关于</span>
			<span class="nav-menu-list"><span class="glyphicon glyphicon-log-out"></span>&nbsp退出</span>
		</div>
	</div>
	<div class="section">
		<ul class="">
			<li class=""><span class="glyphicon glyphicon-pencil"></span>&nbsp数据录入</li>
			<li class=""><span class="glyphicon glyphicon-search"></span>&nbsp数据检索</li>
			<li class=""><span class="glyphicon glyphicon-modal-window"></span>&nbsp数据分析</li>
			<li class=""><span class="glyphicon glyphicon-retweet"></span>&nbsp导入导出</li>
			<li class=""><span class="glyphicon glyphicon-user"></span>&nbsp用户管理</li>
			<li class=""><span class="glyphicon glyphicon-cog"></span>&nbsp系统管理</li>
		</ul>
		<div id="article">
			<div id="map">
				<div id="mapview"></div>
				<div id="mapinfo">地图相关信息</div>
			</div>
			<h4>调查基本信息</h4>
			<div id="search"  style="height:360px;width:1150px;overflow:scroll;align:center">
			
				<div id="key"><span class="glyphicon glyphicon-triangle-right"></span></div>
				<div id="key2"><span class="glyphicon glyphicon-triangle-left"></span></div>
				<p class="">
					<table class="table">
						<tr>
					<%
							for(int t=0;t<colname.size();t++){
							
					%>
								<th><%=colname.get(t)%></th>
					<% 		
							}
					 %>
					 </tr>
						<%
							for (int i = 0; i < data.size()/columnCount; i++) {
							%>
								<tr>
								<%
									//System.out.println(String.valueOf(seed.get(12*i)));
									for (int j = 0; j < (columnCount); j++) {
								%>
										<td align="center"><%=data.get((columnCount) * i + j)%></td>
										<%
											}
										}
										%>
							</tr>
					</table>
					
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