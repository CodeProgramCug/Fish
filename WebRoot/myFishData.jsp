<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.io.*"%>
<%
	Map attribute = ActionContext.getContext().getSession();
	ArrayList<String> data = (ArrayList<String>) attribute
			.get("fishData");
	ArrayList<String> colname=(ArrayList<String>) attribute.get("colname");
	int columnCount = (Integer) attribute.get("count");
	String Qtype=(String)attribute.get("Qtype");
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
<script type="text/javascript"src="js/jquery.min.js"></script>
<script type="text/javascript"src="js/base.js"></script>
</head>
<body>
	<%@ include file="top.jsp"%>
	<div class="section">
		<%@ include file="left.jsp"%>
		<div id="article">
			<div id="map">
				<div id="mapview"></div>
				<div id="mapinfo">地图相关信息</div>
			</div>
			<h4>调查基本信息</h4>
			
			<%if(data.size()!=0){ %>
						<h4>您已在表
						<%if(Qtype.equals("1")){ %>
							Monit_Phy
						<%}else if(Qtype.equals("2")){ %>
							Monit_Zooplan
						<%}else if(Qtype.equals("3")){ %>
							Monit_Ben
						<%} %>
							中录入的数据：</h4>
			<div id="search"  style="height:340px;width:1150px;overflow:scroll;align:center">
			
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
					 <th>修改</th>
					 <th>删除</th>
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
											%>
											
											<td><a href="revise.jsp?msID=<%=data.get((columnCount) * i)%>&Qtype=<%=Qtype%>"  title="修改"><span class="glyphicon glyphicon-edit"></span></a></td>
											<td><a onclick="return deleteF(this)" title="删除"><input type="hidden" value="<%=data.get((columnCount) * i)%>" /><span class="glyphicon glyphicon-remove"></span></a></td>
											
											
											<%
										}
										%>
							</tr>
					</table>
				
				</p>
			</div>
			<%}else{ %>	
					<h4>您还没在表
					<%if(Qtype.equals("1")){ %>
					Monit_Phy
					<%}else if(Qtype.equals("2")){ %>
					Monit_Zooplan
					<%}else if(Qtype.equals("3")){ %>
					Monit_Ben
					<%} %>
					中录入数据，请先录入数据。</h4>
					
					<%} %>
		</div>
		<div class="footer">
			<span class="info">用户名：User  &nbsp当前IP：220.249.99.152 &nbsp登录时间：2016-04-21 09:47</span> 
			<span class="copyright">CopyRight©中国水产科学研究院长江水产研究所2015-2016 &nbsp&nbsp技术支持：华中农业大学</span>
		</div>
	</div>
	<script>
	function deleteF(aElement){
	var fishID=aElement.childNodes[0].value;
	
	if(window.confirm("您确定要删除吗？")){
	}else return false;
	//alert("recordType"+fishID);
	$.ajax({
		type : "post",
		async:false,
		url:"deleteData.action?ID="+fishID+"&recordType="+<%=Qtype%>,
		success:function(result){
		//alert("result:"+result);
		if(result == "success"){
				alert("您已删除该数据！");
				location.reload();
				return true;
				}else{
				alert("删除数据失败！");
				}
		},
		error:function(){
		}
	  });
	 }
	</script>
</body>
</html>