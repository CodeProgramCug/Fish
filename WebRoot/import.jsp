<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@page language="java" import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="db_tool.User"%>
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
<link href="css/jianchao.css"rel="stylesheet"/>
</head>
<body>
	<%@ include file="top.jsp"%>
	<div class="section">
		<%@ include file="left.jsp"%>
	<div class="article">
	<table>
	<form action="import.action" method="post" id="import" enctype="multipart/form-data">
	<tr>
		<td>请选择进行导入操作的表：</td>
		<td>
			<select name="ctable">
			<option value="kong" selected="selected"></option>
  			<option name="TableName" id="TableName" value="Base_Ben" >Base_Ben</option>
  			<option name="TableName" id="TableName" value="Base_Fish" >Base_Fish</option>
  			<option name="TableName" id="TableName" value="Base_IndexMethod" >Base_IndexMethod</option>
  			<option name="TableName" id="TableName" value="Base_MonitPersonel" >Base_MonitPersonel</option>
  			<option name="TableName" id="TableName" value="Base_MonitSite" >Base_MonitSite</option>
  			<option name="TableName" id="TableName" value="Base_MonitUnit" >Base_MonitUnit</option>
  			<option name="TableName" id="TableName" value="Base_Phy" >Base_Phy</option>
  			<option name="TableName" id="TableName" value="Base_Zooplan" >Base_Zooplan</option>
  			<option name="TableName" id="TableName" value="Catch_Biolog" >Catch_Biolog</option>
  			<option name="TableName" id="TableName" value="Catch_FishingGear" >Catch_FishingGear</option>
  			<option name="TableName" id="TableName" value="Catch_FishObj" >Catch_FishObj</option>
  			<option name="TableName" id="TableName" value="Contami_Analysis" >Contami_Analysis</option>
  			<option name="TableName" id="TableName" value="Contami_Fish" >Contami_Fish</option>
  			<option name="TableName" id="TableName" value="Early_Biolog" >Early_Biolog</option>
  			<option name="TableName" id="TableName" value="Early_SurveyState" >Early_SurveyState</option>
  			<option name="TableName" id="TableName" value="Early_Watch" >Early_Watch</option>
  			<option name="TableName" id="TableName" value="Early_WaterQualit" >Early_WaterQualit</option>
  			<option name="TableName" id="TableName" value="Gen_ColonyResear" >Gen_ColonyResear</option>
  			<option name="TableName" id="TableName" value="Gen_MolecuMaker" >Gen_MolecuMaker</option>
  			<option name="TableName" id="TableName" value="Gen_Prim" >Gen_Prim</option>
  			<option name="TableName" id="TableName" value="Gen_SampleInfo" >Gen_SampleInfo</option>
  			<option name="TableName" id="TableName" value="Monit_Ben" >Monit_Ben</option>
  			<option name="TableName" id="TableName" value="Monit_Phy" >Monit_Phy</option>
  			<option name="TableName" id="TableName" value="Monit_Zooplan" >Monit_Zooplan</option>
  			<option name="TableName" id="TableName" value="Right" >Right</option>
  			<option name="TableName" id="TableName" value="Role" >Role</option>
  			<option name="TableName" id="TableName" value="RoleRight" >RoleRight</option>
  			<option name="TableName" id="TableName" value="Sy_DataLog" >Sy_DataLog</option>
  			<option name="TableName" id="TableName" value="Sy_StateCode" >Sy_StateCode</option>
  			<option name="TableName" id="TableName" value="Sy_SystemCode" >Sy_SystemCode</option>
  			<option name="TableName" id="TableName" value="Sy_SystemLog" >Sy_SystemLog</option>
  			<option name="TableName" id="TableName" value="Sy_TableInfo" >Sy_TableInfo</option>
  			<option name="TableName" id="TableName" value="Sy_UserGroup" >Sy_UserGroup</option>
  			<option name="TableName" id="TableName" value="Sy_UserInfo" >Sy_UserInfo</option>
  			<option name="TableName" id="TableName" value="User" >User</option>
  			<option name="TableName" id="TableName" value="UserRight" >UserRight</option>
  			<option name="TableName" id="TableName" value="Water_HyData" >Water_HyData</option>
  			<option name="TableName" id="TableName" value="Water_HyStat" >Water_HyStat</option>
  			<option name="TableName" id="TableName" value="Water_QualityMonit" >Water_QualityMonit</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>请选择操作：</td>
		<td>
			<input type="text"name="filepath"id="filepath" readonly="readonly";>
		</td>
		<td>
			<a class="file">导入文件<input type="file" id="upload" value="导入数据" name="upload" onChange="showInfo(this)";></a>
			<!-- <a class="file">导出数据<input type="file" value="导出数据" name="export" ></a> -->
		</td>
		<td><h5>(格式为txt或csv)</h5></td>
	</tr>
	<tr>
		<td></td>
		<td><a class="file">数据导入<input type="submit"  value="submit"></a></td>
	</tr>
	<script type="text/javascript">
	function showInfo(obj)
	{
    	var filename = obj.value.split('/');
    	var filend=filename[filename.length-1].split('\.')[1];
   		/* document.getElementById("filename").innerText = filename;
    	document.getElementById("fileext").innerText = fileext; */
    	/* alert(filename); */
    	if((filend!="csv")&&(filend!="txt"))
    	{
    		alert("文件格式不符合要求,请重新选择文件!");
    		clearFileInput(upload);
    	}
    	else
    	{
    		document.getElementById("filepath").value = filename;
    	}
	}	
	function clearFileInput(file){
		var form=document.createElement('form');
		document.body.appendChild(form);
		//记住file在旧表单中的的位置
		var pos=file.nextSibling;
		form.appendChild(file);
		form.reset();
		pos.parentNode.insertBefore(file,pos);
		document.body.removeChild(form);
		}
	</script>
	</form>
	<form action="export.action" method="post" id="export" enctype="multipart/form-data">
	<tr>
		<td>请选择进行导出操作的表：</td>
		<td>
			<select name="ctable" id="ctable" onChange="changename()">
			<option value="kong" selected="selected"></option>
  			<option name="TableName" id="TableName" value="Base_Ben" >Base_Ben</option>
  			<option name="TableName" id="TableName" value="Base_Fish" >Base_Fish</option>
  			<option name="TableName" id="TableName" value="Base_IndexMethod" >Base_IndexMethod</option>
  			<option name="TableName" id="TableName" value="Base_MonitPersonel" >Base_MonitPersonel</option>
  			<option name="TableName" id="TableName" value="Base_MonitSite" >Base_MonitSite</option>
  			<option name="TableName" id="TableName" value="Base_MonitUnit" >Base_MonitUnit</option>
  			<option name="TableName" id="TableName" value="Base_Phy" >Base_Phy</option>
  			<option name="TableName" id="TableName" value="Base_Zooplan" >Base_Zooplan</option>
  			<option name="TableName" id="TableName" value="Catch_Biolog" >Catch_Biolog</option>
  			<option name="TableName" id="TableName" value="Catch_FishingGear" >Catch_FishingGear</option>
  			<option name="TableName" id="TableName" value="Catch_FishObj" >Catch_FishObj</option>
  			<option name="TableName" id="TableName" value="Contami_Analysis" >Contami_Analysis</option>
  			<option name="TableName" id="TableName" value="Contami_Fish" >Contami_Fish</option>
  			<option name="TableName" id="TableName" value="Early_Biolog" >Early_Biolog</option>
  			<option name="TableName" id="TableName" value="Early_SurveyState" >Early_SurveyState</option>
  			<option name="TableName" id="TableName" value="Early_Watch" >Early_Watch</option>
  			<option name="TableName" id="TableName" value="Early_WaterQualit" >Early_WaterQualit</option>
  			<option name="TableName" id="TableName" value="Gen_ColonyResear" >Gen_ColonyResear</option>
  			<option name="TableName" id="TableName" value="Gen_MolecuMaker" >Gen_MolecuMaker</option>
  			<option name="TableName" id="TableName" value="Gen_Prim" >Gen_Prim</option>
  			<option name="TableName" id="TableName" value="Gen_SampleInfo" >Gen_SampleInfo</option>
  			<option name="TableName" id="TableName" value="Monit_Ben" >Monit_Ben</option>
  			<option name="TableName" id="TableName" value="Monit_Phy" >Monit_Phy</option>
  			<option name="TableName" id="TableName" value="Monit_Zooplan" >Monit_Zooplan</option>
  			<option name="TableName" id="TableName" value="Right" >Right</option>
  			<option name="TableName" id="TableName" value="Role" >Role</option>
  			<option name="TableName" id="TableName" value="RoleRight" >RoleRight</option>
  			<option name="TableName" id="TableName" value="Sy_DataLog" >Sy_DataLog</option>
  			<option name="TableName" id="TableName" value="Sy_StateCode" >Sy_StateCode</option>
  			<option name="TableName" id="TableName" value="Sy_SystemCode" >Sy_SystemCode</option>
  			<option name="TableName" id="TableName" value="Sy_SystemLog" >Sy_SystemLog</option>
  			<option name="TableName" id="TableName" value="Sy_TableInfo" >Sy_TableInfo</option>
  			<option name="TableName" id="TableName" value="Sy_UserGroup" >Sy_UserGroup</option>
  			<option name="TableName" id="TableName" value="Sy_UserInfo" >Sy_UserInfo</option>
  			<option name="TableName" id="TableName" value="User" >User</option>
  			<option name="TableName" id="TableName" value="UserRight" >UserRight</option>
  			<option name="TableName" id="TableName" value="Water_HyData" >Water_HyData</option>
  			<option name="TableName" id="TableName" value="Water_HyStat" >Water_HyStat</option>
  			<option name="TableName" id="TableName" value="Water_QualityMonit" >Water_QualityMonit</option>
			</select>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><a class="file" id="download" href="" >数据导出<input type="text"></a></td>
		<td><h5>数据导出格式为csv</h5></td>
	</tr>
	<script type="text/javascript">
		function changename()
		{
			/* alert("aaaaa"); */
			var  myselect=document.getElementById("ctable");
			var index=myselect.selectedIndex ;
			var tname=myselect.options[index].value;
			/* alert(tname); */
			var ntname="export.action?filename="+tname+".csv";
			/* alert(ntname); */
			document.getElementById("download").href = ntname;
		
		}
	
	</script>
	</table>
	</div>
		<div class="footer">
			<span class="info">用户名：User  &nbsp当前IP：220.249.99.152 &nbsp登录时间：2016-04-21 09:47</span> 
			<span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016 &nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>
</body>
</html>