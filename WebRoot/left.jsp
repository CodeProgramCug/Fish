<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

		<ul class="ul">
			<li class="li"><span class="glyphicon glyphicon-pencil"></span><a href="dataRecord.jsp">&nbsp数据录入</a></li>
			<li class="li"><span class="glyphicon glyphicon-search"></span><a href="tree.jsp">&nbsp数据检索</a></li>
			<li class="li"><span class="glyphicon glyphicon-modal-window"></span><a>&nbsp数据分析</a></li>
			<li class="li"><span class="glyphicon glyphicon-retweet"></span><a href="import.jsp">&nbsp导入导出</a></li>
			
			<%if(  ((Set<String>)session.getAttribute("roleRight")).contains("User_manege")   ){ %>
			<li class="li"><span class="glyphicon glyphicon-user"></span><a href="userManager.jsp">&nbsp用户管理</a></li>
			<%}%>
			<%if(  ((Set<String>)session.getAttribute("roleRight")).contains("Sys_manege" )){ %>
			<li class="li"><span class="glyphicon glyphicon-cog"></span><a>&nbsp系统管理</a></li>
			<%}%>
			<li class="li"><a>个人用户信息</a></li>
			<li class="li"><a>数据请求</a></li>
		</ul>