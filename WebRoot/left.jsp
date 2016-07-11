<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"src="js/jstree.min.js"></script>
<script>
$(document).ready(function(){
	// 一级菜单
	$(".co").on("click",function(){
		if ($(this).siblings("ul").css("display") == "none"){
			$(".co").siblings("ul").slideUp("500");
			$(".words").removeClass("icon-word1");
			$(this).siblings("ul").slideDown("slow");
			$(this).children("span:eq(0)").removeClass("icon-word");
	  		$(this).children("span:eq(1)").addClass("icon-word1");	
		}
		else {
			$(this).siblings("ul").slideUp("500");
			$(this).children("span:eq(1)").removeClass("icon-word1");
		}
	});
});
</script>
		<div class="sidebar">
			<ul id="nav">
				<li class="navmenu">
				<div class="co">
					<span class="icon2 icon-cailiao"></span><span class="words icon-word">数据管理</span>
				</div>
					<ul>
						<li><a href="dataRecord.jsp"><span class="icon-item"></span>数据录入</a></li>
						<li><a href=""><span class="icon-item"></span>数据分析</a></li>
					</ul>
				</li>
				<hr>

				<li class="navmenu">
				<div class="co">
					<span class="icon2 icon-jiansuo"></span><span class="words icon-word">数据检索</span>
				</div>
					<ul>
						<li><a href="tree.jsp"><span class="icon-item"></span>监测点</a></li>
						<li><a href="Water.jsp"><span class="icon-item"></span>采样水层</a></li>
						<li><a href="tree.jsp"><span class="icon-item"></span>C</a></li>
					</ul>
				</li>
				<hr>
				
				<li class="navmenu">
				<div class="co">
					<span class="icon2 icon-daoru"></span><span class="words icon-word">数据导入</span>
				</div>
					<ul>
						<li><a href="import.jsp"><span class="icon-item"></span>导入导出</a></li>
					</ul>
				</li>
				<hr>
				<li class="navmenu">
				<div class="co">
					<span class="icon2 icon-yonghu"></span><span class="words icon-word">用户中心</span>
				</div>
					<ul>
						<li><a href=""><span class="icon-item"></span>用户信息</a></li>
						<%if(  ((Set<String>)session.getAttribute("roleRight")).contains("User_manege")   ){ %>
						<li><a href="userManager.jsp"><span class="icon-item"></span>用户管理</a></li>
						<%}%>
					</ul>
				</li>
				<hr>
				<li class="navmenu">
				<div class="co">
					<span class="icon2 icon-admin"></span><span class="words icon-word">系统配置</span>
				</div>
					<ul>
					<li><a href=""><span class="icon-item"></span>系统管理</a></li>
					<%if(  ((Set<String>)session.getAttribute("roleRight")).contains("Sys_manege" )){ %> 
						<li><a href=""><span class="icon-item"></span>系统管理</a></li>
					<%}%>
					
					</ul>
				</li>
				<hr>
			</ul>
		</div>
		
		