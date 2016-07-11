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
<link href="css/base.css" rel="stylesheet"/>
<link href="css/water.css" rel="stylesheet"/>
<link href="css/jstree.min.css" rel="stylesheet"/>
<link href="css/xiaolong.css" rel="stylesheet"/>
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="js/jstree.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/checkError.js"></script>
<script type="text/javascript" src="js/ForWater.js"></script>
</head>
  
  <body>
    	<%@ include file="top.jsp"%>
	  <div class="section">
		<%@ include file="left.jsp" %>
		<div class="wa" style = "position:relative;">
			
			<div  id="water-outer" style="display:inline-block;width:280px;overFlow-x:scroll;overFlow-y:scroll;">
				<div id="WaterTree" style="">
				</div>
			</div>
			<div id ="content" style="display:inline-block;position:absolute;top:0px;width:82%;">
			<h2 id="Tittle" class = "" style="margin-top:10px;margin-left:10px;"></h2>
			<button id="add" class="addButton"><span class="glyphicon glyphicon-plus">aaa</span>&nbsp添加子节点</button>
			<button id="add2" class="addButton2"><span class="glyphicon glyphicon-plus"></span>&nbsp添加子节点</button>
		    <button id="delete" class="deleteButton"><span class="glyphicon glyphicon-trash"></span>&nbsp删除本节点</button><br>
			        <div id="tool" style = "display:none;">
			             
			             <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>Name:&nbsp</span></div>
					         <input type="text" id="Name" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="NameError"></h4>
				         </div>
				          <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>Photo:&nbsp</span></div>
					         <input type="text" id="Photo" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="PhotoError"></h4>
				         </div>
				          <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>NetsModel:&nbsp</span></div>
					         <input type="text" id="NetsModel" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="NetsModelError"></h4>
				         </div>
				         <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>NetMouthArea:&nbsp</span></div>
					         <input type="text" id="NetMouthArea" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="NetMouthAreaError"></h4>
				         </div>
				         <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>NetMouthDip:&nbsp</span></div>
					         <input type="text" id="NetMouthDip" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="NetMouthDipError"></h4>
				         </div>
				         <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>StartTime:&nbsp</span></div>
					         <input type="text" id="StartTime" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="StartTimeError"></h4>
				         </div>
				         <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>EndTime:&nbsp</span></div>
					         <input type="text" id="EndTime" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="EndTimeError"></h4>
				         </div>
				         <div class="water-left2">
					         <div style="display:inline-block;width:135px;"><span>NetMouthVelocity:&nbsp</span></div>
					         <input type="text" id="NetMouthVelocity" style="display:inline-block;"><font>&nbsp*</font></input>&nbsp<h4 class = "error" id="NetMouthVelocityError"></h4>
				         </div>
				         
			        </div>
			        
			        <div id="get" style="display:none">
			         <%
			           
			          ArrayList<String> list1 = new ArrayList<String>();
			           list1.add("NameGet");
			           list1.add("PhotoGet");
			           list1.add("TotalQuality");
			           list1.add("EggQuality");
			           list1.add("FryQuality");
			           for(int i =0 ;i<list1.size();i++)
			           {
			             out.write("<div class=\"water-left2\"><div style=\"display:inline-block;width:135px;\"><span>"+list1.get(i)+":&nbsp</span></div><input type=\"text\" id=\""
			             +list1.get(i)+"\"style=\"display:inline-block;\"><font>&nbsp*</font></input>&nbsp<h4 class = \"error\" id=\""+list1.get(i)+"Error\"></h4></div>");
			           }
			           
			         %>
			        </div>
			        <div id="Fishes" style="display:none;">
			         <%    
			            ArrayList<String> list = new ArrayList<String>();
			            list.add("PhotoFish");
			            list.add("BodyLength");
			            list.add("Length");
			            list.add("BodyWeight");
			            list.add("Age");
			            for(int i =0 ;i<list1.size();i++)
			           {
			             out.write("<div class=\"water-left2\"><div style=\"display:inline-block;width:135px;\"><span>"+list.get(i)+":&nbsp</span></div><input type=\"text\" id=\""
			             +list.get(i)+"\"style=\"display:inline-block;\"><font>&nbsp*</font></input>&nbsp<h4 class = \"error\" id=\""+list.get(i)+"Error\"></h4></div>");
			           }
			           %>
			        </div>
			        
			        <div id="FishEggs" style="display:none">
			          <% 
			            ArrayList<String> list2 = new ArrayList<String>();
			            list2.add("PhotoEgg");
			            list2.add("Period");
			            list2.add("Diameter");
			            list2.add("EMDiameter");
			            list2.add("PigmentProp");
			            list2.add("EmbryoProp");
			             for(int i =0 ;i<list1.size();i++)
			           {
			             out.write("<div class=\"water-left2\"><div style=\"display:inline-block;width:135px;\"><span>"+list2.get(i)+":&nbsp</span></div><input type=\"text\" id=\""
			             +list2.get(i)+"\"style=\"display:inline-block;\"><font>&nbsp*</font></input>&nbsp<h4 class = \"error\" id=\""+list2.get(i)+"Error\"></h4></div>");
			           }
			           %>
			        </div>
			        
			       
			       <button style="margin-left:44%;margin-top:40px;display:none;" id="update" class="btn btn-primary">确认修改</button>	
			       <button style="margin-left:44%;margin-top:40px;display:none;" id="sumbit" class="btn btn-primary">确认</button>	
			       
			        
			</div>
			
		</div>
	</div>
  </body>
</html>
