<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
	pageEncoding="UTF-8"%>
<%@page language="java" import="tree_util.*"%>
<%@page import="tree_util.Account"%>

<%
	List<String> user_power = (List<String>) session.getAttribute("userPower");		//封装用户权限
 %>
	<div class="nav">
		<div id="logo"></div>
		<div class="nav-menu">
			<a class="nav-menu-list">&nbsp<%=((Account)session.getAttribute("account")).getUsername() %></a>
			<a class="nav-menu-list"><span class="glyphicon glyphicon-send"></span>&nbsp导航</a>
			<% 
			  
		         //角色 
		         if(((Account) session.getAttribute("account")).getRoleName().equals("student")){
		            out.println("<a class=\"nav-menu-list\"><span class=\"glyphicon glyphicon-list-alt\"></span>&nbsp数据库明细</a>");
		            out.println("<a class=\"nav-menu-list\"><span class=\"glyphicon glyphicon-duplicate\"></span>&nbsp数据备份</a>");
		         }
			 %>
			<a class="nav-menu-list"><span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp关于</a>
			<a class="nav-menu-list" href="login.jsp"><span class="glyphicon glyphicon-log-out"></span>&nbsp退出</a>
		</div>
	</div>