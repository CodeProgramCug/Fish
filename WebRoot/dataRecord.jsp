<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%@ page language="java" import="java.util.*"%>
<%@page language="java" import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="db_tool.User"%>
<%
	//request.setCharacterEncoding("utf-8");
	//String  mm=(String)session.getAttribute("userName");
	//mm=URLEncoder.encode(mm, "utf-8");
	//String nn=URLDecoder.decode(mm, "utf-8");
	//System.out.println("dataR jsp user2:"+mm);
	//System.out.println("dataR jsp user3:"+nn);
	//System.out.println("dataR jsp trans user2:"+transName);
	
 %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据录入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
<meta name="author" content="Daliu"/>
<meta name="keywords" content="长江上游珍稀特有鱼类保护信息管理系统、长江上游、珍稀特有鱼类、保护信息"/>
<meta name="describe" content="长江上游珍稀特有鱼类保护信息管理系统"/>
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>

<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/base.css"rel="stylesheet"/>
	<link href="css/xiaolong.css"rel="stylesheet"/>
  </head>
  <body>
  	<%@ include file="top.jsp"%>
	<div class="section">
		<%@ include file="left.jsp"%>
		<div class="article" >
			<h4>监测数据表录入：</h4>
			<span>请选择录入类型  ：&nbsp </span><select id="selecttable" onchange="change()">
				<option value="1" selected >浮游植物监测数据表</option>
				<option value="2">浮游动物监测数据表</option>
				<option value="3">底栖生物监测数据表</option>
				<option value="4">水质监测数据表</option>
			</select>
			<br><br><br>
		
			<div id="Monit_Phy" class="contex3">
				<form action="fishDataRecord.action" method="post" onsubmit="return xcheck(this)">
				<div class="context2" >
				<div class="float-left">
					<span>MonitPerson:&nbsp</span><br>
					<span >PhyName:&nbsp</span><br>
					<span>SectRive:&nbsp</span><br>
					<span>MonitSiteName:&nbsp</span><br>
					<span>EastLo:&nbsp</span><br>
					<span>NorthLa:&nbsp</span><br>
				</div>
				<div class="float-left" >
					<font style="color:blue"><%=((Account)session.getAttribute("account")).getUsername() %></font><br>
					<input type="text" name="PhyName"><font>&nbsp*</font></input><br>
					<input type="text" name="SectRive"><font>&nbsp*</font></input><br>
					<input type="text" name="MonitSiteName"><font>&nbsp*</font></input><br>
					<input type="text" name="EastLo"><font>&nbsp*</font></input><br>
					<input type="text" name="NorthLa"><font>&nbsp*</font></input><br>
				</div>	
				<div class="float-left">
					<span>MonitDate:&nbsp</span><br>
					<span>MonitTime:&nbsp</span><br>
					<span>Locat:&nbsp</span><br>
					<span>WaterDep:&nbsp</span><br>
					<span>Quantity:&nbsp</span><br>
					<span>Biomass:&nbsp</span><br>
				</div>
				<div class="float-left">
					<input type="text" name="MonitDate" id="d15" onFocus="WdatePicker({isShowWeek:true})"> <font>&nbsp*</font></input><br>
					<input type="text" name="MonitTime" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})"/><font>&nbsp*</font></input><br>
					<input type="text" name="Locat"><font>&nbsp*</font></input><br>
					<input type="text" name="WaterDep"><font>&nbsp*</font></input><br>
					<input type="text" name="Quantity"><font>&nbsp*</font></input><br>
					<input type="text" name="Biomass"><font>&nbsp*</font></input><br>
				</div>		
				<div class="float-left">
					<span>Transparen:&nbsp</span><br>
					<span>OwnerUnit:&nbsp</span><br>
					<span>OwnerID:&nbsp</span><br>
					<span>OperateDate:&nbsp</span><br>
					<span>OperateTime:&nbsp</span><br>
					<span>DataState:&nbsp</span><br>					
				</div>
				<div class="float-left">
					<input type="text" name="Transparen"> <font>&nbsp*</font></input><br>
					<input type="text" name="OwnerUnit"><font>&nbsp*</font></input><br>
					<input type="text" name="OwnerID"><font>&nbsp*</font></input><br>
					<input type="text" name="OperateDate" id="d15" onFocus="WdatePicker({isShowWeek:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="OperateTime" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="DataState"><font>&nbsp*</font></input><br>	
				</div>
			</div>
				<br><br><br>
				<input type="hidden" name="recordType" value="1" />
				<input type="hidden" name="recordORrevise" value="1" />

				<a href="javascript:void(0)" class="submit1" ><input type="submit" value="提交" /></a>
				<a href="javascript:void(0)" class="submit2" target="_blank"><input type="button" value="查看已录入的数据" onclick="location.href='queryFish.action?Qtype=1'"></a>
				<br>
			</form>
		   </div>
		
			<div id="Monit_Zooplan" class="contex3">
				<form action="fishDataRecord.action" method="post" onsubmit="return xcheck(this)" >
				  <div class="context2" >
				<div class="float-left" >
					<span>MonitPerson:&nbsp</span><br>
					<span>ZooplanName:&nbsp</span><br>
					<span>SectRive:&nbsp</span><br>
					<span>MonitSiteName:&nbsp</span><br>
					<span>EastLo:&nbsp</span><br>
					<span>NorthLa:&nbsp</span><br>
				</div>
				<div class="float-left">
					<font style="color:blue"><%=((Account)session.getAttribute("account")).getUsername() %></font><br>
					<input type="text" name="ZooplanName"><font>&nbsp*</font></input><br>
					<input type="text" name="SectRive"><font>&nbsp*</font></input><br>
					<input type="text" name="MonitSiteName"><font>&nbsp*</font></input><br>
					<input type="text" name="EastLo"><font>&nbsp*</font></input><br>
					<input type="text" name="NorthLa"><font>&nbsp*</font></input><br>
				</div>	
				<div class="float-left">
					<span>MonitDate:&nbsp</span><br>
					<span>MonitTime:&nbsp</span><br>
					<span>Locat:&nbsp</span><br>
					<span>WaterDep:&nbsp</span><br>
					<span>Quantity:&nbsp</span><br>
					<span>Biomass:&nbsp</span><br>
				</div>
				<div class="float-left">
					<input type="text" name="MonitDate"  id="d15" onFocus="WdatePicker({isShowWeek:true})"> <font>&nbsp*</font></input><br>
					<input type="text" name="MonitTime" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})"/><font>&nbsp*</font></input><br>
					<input type="text" name="Locat"><font>&nbsp*</font></input><br>
					<input type="text" name="WaterDep"><font>&nbsp*</font></input><br>
					<input type="text" name="Quantity"><font>&nbsp*</font></input><br>
					<input type="text" name="Biomass"><font>&nbsp*</font></input><br>
				</div>		
				<div class="float-left">
					<span>Transparen:&nbsp</span><br>
					<span>OwnerUnit:&nbsp</span><br>
					<span>OwnerID:&nbsp</span><br>
					<span>OperateDate:&nbsp</span><br>
					<span>OperateTime:&nbsp</span><br>
					<span>DataState:&nbsp</span><br>					
				</div>
				<div class="float-left">
					<input type="text" name="Transparen"><font>&nbsp*</font> </input><br>
					<input type="text" name="OwnerUnit"><font>&nbsp*</font></input><br>
					<input type="text" name="OwnerID"><font>&nbsp*</font></input><br>
					<input type="text" name="OperateDate" id="d15" onFocus="WdatePicker({isShowWeek:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="OperateTime" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="DataState"><font>&nbsp*</font></input><br>	
				</div>
			</div>
				<br><br><br>
				<input type="hidden" name="recordType" value="2" />
				<input type="hidden" name="recordORrevise" value="1" />
				<a href="javascript:void(0)" class="submit1" ><input type="submit" value="提交" /></a>
				<a href="javascript:void(0)" class="submit2" target="_blank"><input type="button" value="查看已录入的数据" onclick="location.href='queryFish.action?Qtype=2'"></a>
				<br>
			</form>
			</div>
			
			<div id="Monit_Ben" class="contex3">
				<form action="fishDataRecord.action" method="post" onsubmit="return xcheck(this)" >
				<div class="context2">
				<div class="float-left" >
					<span>MonitPerson:&nbsp</span><br>
					<span >BenName:&nbsp</span><br>
					<span>SectRive:&nbsp</span><br>
					<span>EastLo:&nbsp</span><br>
					<span>NorthLa:&nbsp</span><br>
					<span>MonitSiteName:&nbsp</span><br>
				</div>
				<div class="float-left">
					<font style="color:blue"><%=((Account)session.getAttribute("account")).getUsername() %></font><br>
					<input type="text" name="BenName"><font>&nbsp*</font></input><br>
					<input type="text" name="SectRive"><font>&nbsp*</font></input><br>
					<input type="text" name="EastLo"><font>&nbsp*</font></input><br>
					<input type="text" name="NorthLa"><font>&nbsp*</font></input><br>
					<input type="text" name="MonitSiteName"><font>&nbsp*</font></input><br>
				</div>	
				<div class="float-left">
					<span>MonitDate:&nbsp</span><br>
					<span>MonitTime:&nbsp</span><br>
					<span>Locat:&nbsp</span><br>
					<span>Quantity:&nbsp</span><br>
					<span>Biomass:&nbsp</span><br>
					<span>Transparen:&nbsp</span><br>
				</div>
				<div class="float-left">
					<input type="text" name="MonitDate" id="d15" onFocus="WdatePicker({isShowWeek:true})"> <font>&nbsp*</font></input><br>
					<input type="text" name="MonitTime" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})"/><font>&nbsp*</font></input><br>
					<input type="text" name="Locat"><font>&nbsp*</font></input><br>
					<input type="text" name="Quantity"><font>&nbsp*</font></input><br>
					<input type="text" name="Biomass"><font>&nbsp*</font></input><br>
					<input type="text" name="Transparen"><font>&nbsp*</font></input><br>
				</div>		
				<div class="float-left">
					<span>OwnerUnit:&nbsp</span><br>
					<span>OwnerID:&nbsp</span><br>
					<span>OperateDate:&nbsp</span><br>
					<span>OperateTime:&nbsp</span><br>
					<span>DataState:&nbsp</span><br>					
				</div>
				<div class="float-left">
					<input type="text" name="OwnerUnit"><font>&nbsp*</font></input><br>
					<input type="text" name="OwnerID"><font>&nbsp*</font></input><br>
					<input type="text" name="OperateDate" id="d15" onFocus="WdatePicker({isShowWeek:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="OperateTime" id="d233" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="DataState"><font>&nbsp*</font></input><br>	
					<input type="text" disabled="disabled" value="-" style="border-style:none;background-color:white"></input><br>	
				</div>
			</div>
				<br><br><br>
				<input type="hidden" name="recordType" value="3" />
				<input type="hidden" name="recordORrevise" value="1" />
				<a href="javascript:void(0)" class="submit1" ><input type="submit" value="提交" /></a>
				<a href="javascript:void(0)" class="submit2" target="_blank"><input type="button" value="查看已录入的数据" onclick="location.href='queryFish.action?Qtype=3'"></a>
				<br>
				</form>
			</div>
			
			<div id="Water_QualityMonit"  style="height:100%;width:100%;overflow:scroll;display:none">
				<form action="" method="post">
				</form>
			
			</div>
		</div>
		<div class="footer">
			<span class="info">用户名：User  &nbsp当前IP：220.249.99.152 &nbsp登录时间：2016-04-21 09:47</span> 
			<span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016 &nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>
   <script type="text/javascript" src="js/xiaolong.js" ></script>
  </body>
</html>
