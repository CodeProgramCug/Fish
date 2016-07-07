<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%@ page language="java" import="java.util.*"%>
<%@page language="java" import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="db_tool.User"%>
<%@page language="java" import="db_tool.DbBean"%>
<%@page language="java" import="java.sql.ResultSet"%>
<%
	request.setCharacterEncoding("utf-8");
	String Qtype=request.getParameter("Qtype");
	String msID=request.getParameter("msID");
	System.out.println("revise Qtype and msID:"+Qtype+","+msID);
	ArrayList oneMessage=new ArrayList();
	String sql="";
	DbBean db=new DbBean();
	ResultSet rs=null;
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/base.css"rel="stylesheet"/>
	<link href="css/xiaolong.css"rel="stylesheet"/>
  </head>
  <script>
  </script>
  <body>
  	<%@ include file="top.jsp"%>
	<div class="section">
		<%@ include file="left.jsp"%>
		<div class="article" style="height:500px;width:100%;">
			<h4>监测数据表修改：</h4>
			<br><br>
			<br>
	<%if(Qtype.equals("1")){
		sql+="select * from Monit_Phy where ID='"+msID+"'";
		System.out.println("Q=1:"+sql);
		try{
			rs=db.executeQuery(sql);
			rs.next();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
					oneMessage.add(rs.getString(i));
			
		}catch(Exception e){System.out.println(e);}
	 %>	
	 
	 <span style="margin:0 30% 0 0;">您当前正在修改浮游植物监测数据：</span><br><br>
			<div id="Monit_Phy" class="contex3" >
				<form action="reviseFish.action" method="post" onsubmit="return xcheck(this)">
				<div class="context2">
				<div class="float-left" >
					<span>MonitPerson:&nbsp</span><br>
					<span >PhyName:&nbsp</span><br>
					<span>SectRive:&nbsp</span><br>
					<span>MonitSiteName:&nbsp</span><br>
					<span>EastLo:&nbsp</span><br>
					<span>NorthLa:&nbsp</span><br>
				</div>
				<div class="float-left">
					<input type="text" name="MonitPerson" value="<%=oneMessage.get(1)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="PhyName" value="<%=oneMessage.get(2)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="SectRive" value="<%=oneMessage.get(3)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="MonitSiteName" value="<%=oneMessage.get(4)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="EastLo" value="<%=oneMessage.get(5)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="NorthLa" value="<%=oneMessage.get(6)%>"><font>&nbsp*</font></input><br>
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
					<input type="text" name="MonitDate" id="d15" value="<%=oneMessage.get(7)%>" onFocus="WdatePicker({isShowWeek:true})"> <font>&nbsp*</font></input><br>
					<input type="text" name="MonitTime" id="d233" value="<%=oneMessage.get(8)%>" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})"/><font>&nbsp*</font></input><br>
					<input type="text" name="Locat" value="<%=oneMessage.get(9)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="WaterDep" value="<%=oneMessage.get(10)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="Quantity" value="<%=oneMessage.get(11)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="Biomass" value="<%=oneMessage.get(12)%>"><font>&nbsp*</font></input><br>
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
					<input type="text" name="Transparen" value="<%=oneMessage.get(13)%>"> <font>&nbsp*</font></input><br>
					<input type="text" name="OwnerUnit" value="<%=oneMessage.get(14)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="OwnerID" value="<%=oneMessage.get(15)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="OperateDate" id="d15" value="<%=oneMessage.get(16)%>" onFocus="WdatePicker({isShowWeek:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="OperateTime" id="d233" value="<%=oneMessage.get(17)%>" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="DataState" value="<%=oneMessage.get(18)%>"><font>&nbsp*</font></input><br>	
				</div>
			</div>
				<br><br><br>
				<input type="hidden" name="recordType" value="1" />
				<input type="hidden" name="ID" value="<%=oneMessage.get(0)%>" />
				<a href="javascript:void(0)" class="submit1" ><input type="submit" value="确认修改" /></a>
				
				<br>
			</form>
		   </div>
	<%}else if(Qtype.equals("2")){
			sql+="select * from Monit_Zooplan where ID='"+msID+"'";
		System.out.println("Q=2:"+sql);
		try{
			rs=db.executeQuery(sql);
			rs.next();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
					oneMessage.add(rs.getString(i));
			
		}catch(Exception e){System.out.println(e);}
	 %>	
	 <span style="margin:0 30% 0 0;">您当前正在修改浮游动物监测数据：</span><br><br>
			<div id="Monit_Zooplan" class="contex3">
				<form action="reviseFish.action" method="post" onsubmit="return xcheck(this)" >
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
					<input type="text" name="MonitPerson" value="<%=oneMessage.get(1)%>"> <font>&nbsp*</font></input><br>
					<input type="text" name="ZooplanName" value="<%=oneMessage.get(2)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="SectRive" value="<%=oneMessage.get(3)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="MonitSiteName" value="<%=oneMessage.get(4)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="EastLo" value="<%=oneMessage.get(5)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="NorthLa" value="<%=oneMessage.get(6)%>"><font>&nbsp*</font></input><br>
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
					<input type="text" name="MonitDate" id="d15" value="<%=oneMessage.get(7)%>" onFocus="WdatePicker({isShowWeek:true})"> <font>&nbsp*</font></input><br>
					<input type="text" name="MonitTime" id="d233" value="<%=oneMessage.get(8)%>" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})"/><font>&nbsp*</font></input><br>
					<input type="text" name="Locat" value="<%=oneMessage.get(9)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="WaterDep" value="<%=oneMessage.get(10)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="Quantity" value="<%=oneMessage.get(11)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="Biomass" value="<%=oneMessage.get(12)%>"><font>&nbsp*</font></input><br>
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
					<input type="text" name="Transparen" value="<%=oneMessage.get(13)%>"><font>&nbsp*</font> </input><br>
					<input type="text" name="OwnerUnit" value="<%=oneMessage.get(14)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="OwnerID" value="<%=oneMessage.get(15)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="OperateDate" id="d15" value="<%=oneMessage.get(16)%>" onFocus="WdatePicker({isShowWeek:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="OperateTime" id="d233" value="<%=oneMessage.get(17)%>" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="DataState" value="<%=oneMessage.get(18)%>"><font>&nbsp*</font></input><br>	
				</div>
			</div>
				<br><br><br>
				<input type="hidden" name="recordType" value="2" />
				<input type="hidden" name="ID" value="<%=oneMessage.get(0)%>" />
				<a href="javascript:void(0)" class="submit1" ><input type="submit" value="确认修改" /></a>
				
				<br>
			</form>
			</div>
	<%}else if(Qtype.equals("3")){ 
			sql+="select * from Monit_Ben where ID='"+msID+"'";
		System.out.println("Q=3:"+sql);
		try{
			rs=db.executeQuery(sql);
			rs.next();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
					oneMessage.add(rs.getString(i));
			
		}catch(Exception e){System.out.println(e);}
	%>		
			<span style="margin:0 30% 0 0;">您当前正在修改底栖生物监测数据：</span><br><br>
			<div id="Monit_Ben" class="contex3">
				<form action="reviseFish.action" method="post" onsubmit="return xcheck(this)" >
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
					<input type="text" name="MonitPerson" value="<%=oneMessage.get(1)%>"><font>&nbsp*</font> </input><br>
					<input type="text" name="BenName" value="<%=oneMessage.get(2)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="SectRive" value="<%=oneMessage.get(3)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="EastLo" value="<%=oneMessage.get(4)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="NorthLa" value="<%=oneMessage.get(5)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="MonitSiteName" value="<%=oneMessage.get(6)%>"><font>&nbsp*</font></input><br>
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
					<input type="text" name="MonitDate" id="d15" value="<%=oneMessage.get(7)%>" onFocus="WdatePicker({isShowWeek:true})"> <font>&nbsp*</font></input><br>
					<input type="text" name="MonitTime" id="d233" value="<%=oneMessage.get(8)%>" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})"/><font>&nbsp*</font></input><br>
					<input type="text" name="Locat" value="<%=oneMessage.get(9)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="Quantity" value="<%=oneMessage.get(10)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="Biomass" value="<%=oneMessage.get(11)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="Transparen" value="<%=oneMessage.get(12)%>"><font>&nbsp*</font></input><br>
				</div>		
				<div class="float-left">
					<span>OwnerUnit:&nbsp</span><br>
					<span>OwnerID:&nbsp</span><br>
					<span>OperateDate:&nbsp</span><br>
					<span>OperateTime:&nbsp</span><br>
					<span>DataState:&nbsp</span><br>					
				</div>
				<div class="float-left">
					<input type="text" name="OwnerUnit" value="<%=oneMessage.get(13)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="OwnerID" value="<%=oneMessage.get(14)%>"><font>&nbsp*</font></input><br>
					<input type="text" name="OperateDate" id="d15" value="<%=oneMessage.get(15)%>" onFocus="WdatePicker({isShowWeek:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="OperateTime" id="d233" value="<%=oneMessage.get(16)%>" onFocus="WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" ><font>&nbsp*</font></input><br>
					<input type="text" name="DataState" value="<%=oneMessage.get(17)%>"><font>&nbsp*</font></input><br>	
				</div>
			</div>
				<br><br><br>
				<input type="hidden" name="recordType" value="3" />
				<input type="hidden" name="ID" value="<%=oneMessage.get(0)%>" />
				<a href="javascript:void(0)" class="submit1" ><input type="submit" value="确认修改" /></a>
				
				<br>
				</form>
			</div>
		<%} %>	
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
