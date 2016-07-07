<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<link href="css/jstree.min.css"rel="stylesheet"/>
<link href="css/xiaolong.css"rel="stylesheet"/>
<script type="text/javascript"src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"src="js/base.js"></script>
<script type="text/javascript"src="js/jstree.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
</head>
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
				<div id="tree-menu">
				<button id="add-child-node"class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp添加子节点</button>
				<button id="delete-node"class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span>&nbsp删除本节点</button>
				<button id="rename-node"class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>&nbsp重命名</button>
				</div>
		<div id="input-place" >
			<div id="add-monitsite" class="add-data">
				<div class="context5" >
				<div class="float-left2">
					<span>Institution:&nbsp</span><br>
					<span >Investigator:&nbsp</span><br>
					<span>InvestigationDate:&nbsp</span><br>
					<span>Site:&nbsp</span><br>
					<span>River:&nbsp</span><br>
					<span>Photo:&nbsp</span><br>
					<span>StartTime:&nbsp</span><br>
				</div>
				<div class="float-left2" >
					<input type="text" id="Institution1"><font>&nbsp*</font></input><br>
					<input type="text" id="Investigator1"><font>&nbsp*</font></input><br>
					<input type="text" id="InvestigationDate1" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"><font>&nbsp*</font></input><br>
					<input type="text" id="Site1"><font>&nbsp*</font></input><br>
					<input type="text" id="River1"><font>&nbsp*</font></input><br>
					<input type="text" id="Photo1"><font>&nbsp*</font></input><br>
					<input type="text" id="StartTime1" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"><font>&nbsp*</font></input><br>
				</div>	
				<div class="float-left2">
					<span>EndTime:&nbsp</span><br>
					<span>StartLongitude:&nbsp</span><br>
					<span>StartLatitude:&nbsp</span><br>
					<span>EndLongitude:&nbsp</span><br>
					<span>EndLatitude:&nbsp</span><br>
					<span>Weather:&nbsp</span><br>
					<span>Temperature:&nbsp</span><br>
				</div>
				<div class="float-left2">
					<input type="text" id="EndTime1" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"> <font>&nbsp*</font></input><br>
					<input type="text" id="StartLongitude1" /><font>&nbsp*</font></input><br>
					<input type="text" id="StartLatitude1"><font>&nbsp*</font></input><br>
					<input type="text" id="EndLongitude1"><font>&nbsp*</font></input><br>
					<input type="text" id="EndLatitude1"><font>&nbsp*</font></input><br>
					<input type="text" id="Weather1"><font>&nbsp*</font></input><br>
					<input type="text" id="Temperature1"><font>&nbsp*</font></input><br>
				</div>		
				
			</div>
		</div>
					
		<div id="add-surface" class="add-data">
				<div class="context5" >
				<div class="float-left2">
					<span>Position:&nbsp</span><br>
				</div>
				<div class="float-left2" >
					<input type="text" id="Position2"><font>&nbsp*</font></input><br>
				</div>	
				<div class="float-left2">
					<span>Distance2Bank:&nbsp</span><br>
				</div>
				<div class="float-left2">
					<input type="text" id="Distance2Bank2" > <font>&nbsp*</font></input><br>
				</div>		
				
			</div>
		</div>
					
		<div id="add-line" class="add-data">
				<div class="context5" >
				<div class="float-left2">
					<span>StartLongitude:&nbsp</span><br>
					<span>StartLatitude:&nbsp</span><br>
				</div>
				<div class="float-left2" >
					<input type="text" id="StartLongitude3"><font>&nbsp*</font></input><br>
					<input type="text" id="StartLatitude3"><font>&nbsp*</font></input><br>
				</div>	
				<div class="float-left2">
					<span>EndLongitude:&nbsp</span><br>
					<span>EndLatitude:&nbsp</span><br>
				</div>
				<div class="float-left2">
					<input type="text" id="EndLongitude3"><font>&nbsp*</font></input><br>
					<input type="text" id="EndLatitude3"><font>&nbsp*</font></input><br>
				</div>		
				
			</div>
		</div>
					
			<button style="margin-left:40%" id="add-child-sub"class="btn btn-primary">确定</button>
			<button style="margin-left:40%" id="update-data"class="btn btn-primary">确认修改</button>	
					
				</div>
				<div id="rename-place">
					<input type="text"name=""id="rename"placeholder="请输入新名称"class="form-control"/>
					<button id="rename-sub"class="btn btn-primary">确定</button>
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
			<span class="info">用户名：User  &nbsp当前IP：220.249.99.152 &nbsp登录时间：2016-04-21 09:47</span> 
			<span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016 &nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>
	
</body>
</html>
