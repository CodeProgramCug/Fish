<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page language="java" import="db_tool.User"%>
	<div class="nav">
		<div id="logo"></div>
		<div class="nav-menu">
			<a class="nav-menu-list">&nbsp<%=((User)session.getAttribute("user")).getUsername() %></a>
			<a class="nav-menu-list"><span class="glyphicon glyphicon-send"></span>&nbsp导航</a>
			<%if(  ((Set<String>)session.getAttribute("roleRight")).contains("Db_copy")   ){ %>
			<a class="nav-menu-list"><span class="glyphicon glyphicon-list-alt"></span>&nbsp数据库明细</a>
			<a class="nav-menu-list"><span class="glyphicon glyphicon-duplicate"></span>&nbsp数据备份</a>
			<%}%>
			<a class="nav-menu-list"><span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp关于</a>
			<a class="nav-menu-list" href="login.jsp"><span class="glyphicon glyphicon-log-out"></span>&nbsp退出</a>
		</div>
	</div>