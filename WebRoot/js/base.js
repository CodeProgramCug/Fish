$(document).ready(function(){
	var height = $(document).height();
	var width = $(document).width();
	var realheight = height-100;
	$("#article").height(realheight).width(width-150);
	$("#mapinfo").height(height-500);
	//点击按钮1后
	$("#key").click(function(){
	var left = width-660+"px";
	//alert(left);
		$("#search").width("500px").css({"position":"absolute","left":left,"top":"100px"});
		$("#map").width(width-660).height(height-100);
		$("#key").css({"display":"none"});
		$("#key2").css({"display":"block"});
	});
	//点击按钮2后
	$("#key2").click(function(){
		$("#map").width(0).height(0);
		$("#search").width("100%").css({"position":"absolute","left":"0","top":"100px"});
		$("#key").css({"display":"block"});
		$("#key2").css({"display":"none"});
	});
	//动态计算树高度
	$("#tree-outer").height(height-124);
	

	var tree = $('#tree');

	  var MonitArr=[];
		$.ajax({
			type:"get",
			async:true,
			url:"QueryMonitSite.action",
			success:function(result){
				if(result=="error"){
					alert("后台无数据，您可添加数据");
					return;
				}
				var MonitArr= result.split(',');
			    var initTree = new Array(MonitArr.length);
			    for(var i = 0 ;i<MonitArr.length;i++){
			    	var d = {};
			    	console.log(MonitArr[i]);
			    	d["text"] = MonitArr[i];
			    	d["id"] = i.toString();
			    	d["state"] = "open";
			    	initTree[i] = d;
			    }
				/*tree.html文件中的文件夹树型列表js*/
				tree.jstree({
				    'core' : {
					"check_callback" : true,
				      'data' :
					  [{"id":"jh",
						"text":"华中农业大学",
						"icon":"./css/tree_icon.png",
						"state":"open",
						"children": initTree
						}]
						
				    },
					'strings':{'Loading ...' : 'Please wait ...'}
				  });
				return true;
			}
		});
	

//


// 

//alert(t);
  //var beforeChange=0;
 // var afterChange=1;
  var selected = '';
  var beforechange= '';
  //用户单击书节点时候触发
  tree.on("changed.jstree", function (e, data) {
	//被选中的节点编号
	selected =  data.selected;
	var selnode=data.instance.get_node(selected);
	var  text = tree.jstree().get_text(selected);
	if(text==false)
	{
		text = "无";
	}
	if(text=="华中农业大学"){
		$("#add-child-node1").hide();
		$("#add-feature").hide();
		$("#add-child-node").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加监测点");
	}else if(text.substring(0,3)=="MON"){
		$("#add-child-node1").hide();
		$("#add-feature").hide();
		$("#add-child-node").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加断面");
	}else if(text.substring(0,3)=="SEC"){
		$("#add-feature").hide();
		$("#add-child-node").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加测线");
		$("#add-child-node1").show();
	}else{
		$("#add-child-node1").hide();
		$("#add-feature").hide();
		$("#add-child-node").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加子节点");
	}
	$("#node-text").html("已选中：<small><i>"+text+"</i></small>");
	

	  if(beforechange!=selected.toString()){
		  if(selected=="jh"){
			  $("#input-place").css({"display":"none"});
			  $("#update-data").css({"display":"none"});  //确认修改按钮
			  $("#add-monitsite input").each(function(){
				  $(this).val("");
			  });
			  
			  return;
		  }else {
			  var MonitArr=[];
			  $("#input-place").css({"display":"block"});
			  $("#update-data").css({"display":"block"});
		  $.ajax({
			  type:"get",
			  url:"ShowMessage.action?monitID="+text,
			  async:true,
			  success:function(result){
				  MonitArr=result.split(',');
				  if(text.substring(0, 3)=="MON"){
					  $("#add-surface input").each(function(){
						  $(this).val("");	  
					  });
					  $("#add-monitsite input").each(function(i,n){
						  $(this).val(MonitArr[i+1]);	  
					  });
					  $("#add-monitsite").css({"display":"block"});
					  $("#add-surface").css({"display":"none"});
					  $("#add-line").css({"display":"none"});
					  $("#add-water").css({"display":"none"});
					  $("#add-child-sub").css({"display":"none"});
					
				  }else if(text.substring(0, 3)=="SEC"){
					  $("#add-line input").each(function(){
						  $(this).val("");	  
					  });
					  $("#add-surface input").each(function(i,n){
						  $(this).val(MonitArr[i+1]);	  
					  });
					  $("#add-monitsite").css({"display":"none"});
					  $("#add-surface").css({"display":"block"});
					  $("#add-line").css({"display":"none"});
					  $("#add-water").css({"display":"none"});
					  $("#add-child-sub").css({"display":"none"});
				  }else if(text.substring(0, 3)=="LIN"){
					  $("#add-line input").each(function(i,n){
						  $(this).val(MonitArr[i+1]);
						  
					  });
					  $("#add-monitsite").css({"display":"none"});
					  $("#add-surface").css({"display":"none"});
					  $("#add-line").css({"display":"block"});
					  $("#add-water").css({"display":"none"});
					  $("#add-child-sub").css({"display":"none"});
				  }else{
					//  alert("未知错误");
					  $("#add-water input").each(function(i,n){
						  $(this).val(MonitArr[i+1]);
						  
					  });
					  $("#add-monitsite").css({"display":"none"});
					  $("#add-surface").css({"display":"none"});
					  $("#add-line").css({"display":"none"});
					  $("#add-water").css({"display":"block"});
					  $("#add-child-sub").css({"display":"none"});
				  }
			  }
		  });
			beforechange=selected;
		}
		 //修改监测信息 
		  $("#update-data").unbind("click").bind("click",function(){
			  if(text.substring(0,3)=="MON"){
				  $.ajax({
				   	  type:"post",
					  async:true,
					  url:"UpdateMonit.action",
					  data:{
						  "addType":"1",
						  "fatherText":text,
						  "Institution":$("#Institution1").val(),
						  "Investigator":$("#Investigator1").val(),
						  "InvestigationDate":$("#InvestigationDate1").val(),
						  "Site":$("#Site1").val(),
						  "River":$("#River1").val(),
						  "Photo":$("#Photo1").val(),
						  "StartTime":$("#StartTime1").val(),
						  "EndTime":$("#EndTime1").val(),
						  "StartLongitude":$("#StartLongitude1").val(),
						  "StartLatitude":$("#StartLatitude1").val(),
						  "EndLongitude":$("#EndLongitude1").val(),
						  "EndLatitude":$("#EndLatitude1").val(),
						  "Weather":$("#Weather1").val(),
						  "Temperature":$("#Temperature1").val()
					  },
					  success:function(result){
						  if(result=="error"){
							  alert("修改失败！");
						  }else{
							  $("#input-place").css({"display":"none"});
							  alert("修改成功！");
							  $("#add-monitsite input").each(function(){
								  $(this).val("");
							  });
						  }
					  }
			   });
			  }else if(text.substring(0,3)=="SEC"){
				  $.ajax({
				   	  type:"post",
					  async:true,
					  url:"UpdateMonit.action",
					  data:{
						  "addType":"2",
						  "fatherText":text,
						  "Position":$("#Position2").val(),
						  "Distance2Bank":$("#Distance2Bank2").val()
					  },
					  success:function(result){
						  if(result=="error"){
							  alert("修改失败！");
						  }else{
							  $("#input-place").css({"display":"none"});
							  alert("修改成功！");
							  $("#add-surface input").each(function(){
								  $(this).val("");  
							  });
						  }
					  }
			   });
			  }else if(text.substring(0,3)=="LIN"){
				  $.ajax({
				   	  type:"post",
					  async:true,
					  url:"UpdateMonit.action",
					  data:{
						  "addType":"3",
						  "fatherText":text,
						  "StartLongitude":$("#StartLongitude3").val(),
						  "StartLatitude":$("#StartLatitude3").val(),
						  "EndLongitude":$("#EndLongitude3").val(),
						  "EndLatitude":$("#EndLatitude3").val()
					  },
					  success:function(result){
						  if(result=="error"){
							  alert("修改失败！");
						  }else{
							  $("#input-place").css({"display":"none"});
							  alert("修改成功！");
							  $("#add-line input").each(function(){
								  $(this).val("");
							  });
						  } 
					  }
			   });
			  }else{
				  
				  $.ajax({
				   	  type:"post",
					  async:true,
					  url:"UpdateMonit.action",
					  data:{
						  "addType":"4",
						  "fatherText":text,
						  "Number":$("#Number").val(),
						  "waterFloor":$("#waterFloor").val(),
						  "Depath":$("#Depath").val(),
						  "waterWarm":$("#waterWarm").val(),
						  "waterHeight":$("#waterHeight").val(),
						  "waterFlow":$("#waterFlow").val()
					  },
					  success:function(result){
						  if(result=="error"){
							  alert("修改失败！");
						  }else{
							  $("#input-place").css({"display":"none"});
							  alert("修改成功！");
							  $("#add-line input").each(function(){
								  $(this).val("");
							  });
						  } 
					  }
			   });
				  
			  }
		  });
	  }
	
	
	
	$("#rename-place").css({"display":"none"});
	$("#tree-menu").css({"display":"block"});
	
	
	
    var test = tree.jstree().get_json(); 
	var  string = JSON.stringify(test);
	//alert("test:"+test+"--string:"+string)
	//此处发送AJAX数据到数据库
	/*$.post("",{data:string},function(){
	
	});*/
  //console.log(string);
	
	//changeNode();
	var MonitArr=[];
	//双击树节点，来向服务器请求子节点信息
	tree.unbind("dblclick.jstree").bind("dblclick.jstree", function (obj) {
		
		if(!data.instance.is_leaf(selnode)) return;
			
		  if(selected=='jh'){
			  $.ajax({
				  type:"get",
				  async:true,
				  url:"AllMonit.action?monitID=jjhh",
				  success:function(result){
					  MonitArr=result.split(',');
					  for(var ix = 0;ix<MonitArr.length;ix ++){
							tree.jstree().create_node(selected,MonitArr[ix],"last");	
						}
					  tree.jstree().open_node(selected);
				  }
			  });
		  }else
		  $.ajax({
			  
			  ///这个需要后台的合作
			  type:"get",
			  async:true,
			  url:"AllMonit.action?monitID="+text,
			  success:function(result){
				  if(result=="01"){
					  alert("往后索引有待开发！");
					  return;
				  }else if(result=="00"){
					  alert("发生错误，请稍后再试！")
					  return;
				  }else if(result==""||result==null){
					  alert("往后已无索引！");
				  }else{
				 MonitArr=result.split(',');
				  for(var ix = 0;ix<MonitArr.length;ix ++){
						tree.jstree().create_node(selected,MonitArr[ix],"last");	
					}
				  tree.jstree().open_node(selected);
				  }
			  }
		  });

	  });
  });
 
  
  //添加子节点
  $("#add-child-node").unbind("click").bind("click",function(){
	  $("#add-feature").css({"display":"none"});
	  $("#rename-place").css({"display":"none"});
	  $("#input-place").css({"display":"block"});
	  $("#update-data").css({"display":"none"});
	  var  text = tree.jstree().get_text(selected);
	  if(selected=="jh"){
		  $("#add-monitsite").css({"display":"block"});
		  $("#add-surface").css({"display":"none"});
		  $("#add-line").css({"display":"none"});
		  $("#add-water").css({"display":"none"});
		  $("#add-child-sub").css({"display":"block"});
	  }else if(text.substring(0,3)=="MON"){
		  $("#add-monitsite").css({"display":"none"});
		  $("#add-surface").css({"display":"block"});
		  $("#add-line").css({"display":"none"});
		  $("#add-water").css({"display":"none"});
		  $("#add-child-sub").css({"display":"block"});
	  }else if(text.substring(0,3)=="SEC"){
		  $("#add-monitsite").css({"display":"none"});
		  $("#add-surface").css({"display":"none"});
		  $("#add-line").css({"display":"block"});
		  $("#add-water").css({"display":"none"});
		  $("#add-child-sub").css({"display":"block"});
	  }else if(text.substring(0,3)=="LIN"){
		  $("#add-monitsite").css({"display":"none"});
		  $("#add-surface").css({"display":"none"});
		  $("#add-line").css({"display":"none"});
		  $("#add-water").css({"display":"block"});
		  $("#add-child-sub").css({"display":"block"}); //增加节点的时候的确定按钮
	  }
	  //增加监测点
	   $("#add-child-sub").unbind("click").bind("click",function(){
		  if(selected=="jh"){
			  $.ajax({
			   	  type:"post",
				  async:true,
				  url:"op_monsite.action",
				  data:{
					  "flag":"insert",
					  //"fatherText":text,
					  "Institution":$("#Institution1").val(),
					  "Investigator":$("#Investigator1").val(),
					  "InvestigationDate":$("#InvestigationDate1").val(),
					  "Site":$("#Site1").val(),
					  "River":$("#River1").val(),
					  //"Photo":$("#Photo1").val(),
					  "StartTime":$("#StartTime1").val(),
					  "EndTime":$("#EndTime1").val(),
					  "StartLongitude":$("#StartLongitude1").val(),
					  "StartLatitude":$("#StartLatitude1").val(),
					  "EndLongitude":$("#EndLongitude1").val(),
					  "EndLatitude":$("#EndLatitude1").val(),
					  "Weather":$("#Weather1").val(),
					  "Temperature":$("#Temperature1").val()
				  },
				  success:function(result){
					  if(result=="error"){
						  alert("添加失败！");
					  }else{
						  tree.jstree().create_node(selected,result,"last");
						  tree.jstree().open_node(selected);
						  $("#input-place").css({"display":"none"});
						  alert("添加成功！");
						  $("#add-monitsite input").each(function(){
							  $(this).val("");
							  
						  });
					  }
				  }
		   });
		  }else if(text.substring(0,3)=="MON"){
			  $.ajax({
			   	  type:"post",
				  async:true,
				  url:"AddChild.action",
				  data:{
					  "addType":"2",
					  "fatherText":text,
					  "Position":$("#Position2").val(),
					  "Distance2Bank":$("#Distance2Bank2").val()
				  },
				  success:function(result){
					  if(result=="error"){
						  alert("添加失败！");
					  }else{
						  tree.jstree().create_node(selected,result,"last");
						  tree.jstree().open_node(selected);
						  $("#input-place").css({"display":"none"});
						  alert("添加成功！");
						  $("#add-surface input").each(function(){
							  $(this).val("");
							  
						  });
					  }
				  }
		   });
		  }else if(text.substring(0,3)=="SEC"){
			  $.ajax({
			   	  type:"post",
				  async:true,
				  url:"AddChild.action",
				  data:{
					  "addType":"3",
					  "fatherText":text,
					  "StartLongitude":$("#StartLongitude3").val(),
					  "StartLatitude":$("#StartLatitude3").val(),
					  "EndLongitude":$("#EndLongitude3").val(),
					  "EndLatitude":$("#EndLatitude3").val()
				  },
				  success:function(result){
					  if(result=="error"){
						  alert("添加失败！");
					  }else{
						  tree.jstree().create_node(selected,result,"last");
						  tree.jstree().open_node(selected);
						  $("#input-place").css({"display":"none"});
						  alert("添加成功！");
						  $("#add-line input").each(function(){
							  $(this).val("");
							  
						  });
					  } 
				  }
		   });
		  }else if(text.substring(0,3)=="LIN"){
			  $.ajax({
			   	  type:"post",
				  async:true,
				  url:"AddChild.action",
				  data:{
					  "addType":"4",
					  "fatherText":text,
					  "Number":$("#Number").val(),
					  "waterFloor":$("#waterFloor").val(),
					  "Depath":$("#Depath").val(),
					  "waterWarm":$("#waterWarm").val(),
					  "waterHeight":$("#waterHeight").val(),
					  "waterFlow":$("#waterFlow").val()
				  },
				  success:function(result){
					  if(result=="error"){
						  alert("添加失败！");
					  }else{
						  tree.jstree().create_node(selected,result,"last");
						  tree.jstree().open_node(selected);
						  $("#input-place").css({"display":"none"});
						  alert("添加成功！");
						  $("#add-line input").each(function(){
							  $(this).val("");
							  
						  });
					  } 
				  }
		   });
			  
		  }
		   
	   });
	
  });
//添加浮游、底栖生物
  $("#add-child-node1").unbind("click").bind("click",function(){
	  $("#rename-place").css({"display":"none"});
	  $("#input-place").css({"display":"block"});
	  $("#update-data").css({"display":"none"});
	  $("#add-line").hide();
	  var  text = tree.jstree().get_text(selected);
	  if(selected=="jh"){
		  $("#add-monitsite").css({"display":"block"});
		  $("#add-surface").css({"display":"none"});
		  $("#add-feature").css({"display":"none"});
		  $("#add-water").css({"display":"none"});
		  $("#add-child-sub").css({"display":"block"});
	  }else if(text.substring(0,3)=="MON"){
		  $("#add-monitsite").css({"display":"none"});
		  $("#add-surface").css({"display":"block"});
		  $("#add-feature").css({"display":"none"});
		  $("#add-water").css({"display":"none"});
		  $("#add-child-sub").css({"display":"block"});
	  }else if(text.substring(0,3)=="SEC"){
		  $("#add-monitsite").css({"display":"none"});
		  $("#add-surface").css({"display":"none"});
		  $("#add-feature").css({"display":"block"});
		  $("#add-water").css({"display":"none"});
		  $("#add-child-sub").css({"display":"block"});
	  }else if(text.substring(0,3)=="LIN"){
		  $("#add-monitsite").css({"display":"none"});
		  $("#add-surface").css({"display":"none"});
		  $("#add-feature").css({"display":"none"});
		  $("#add-water").css({"display":"block"});
		  $("#add-child-sub").css({"display":"block"});
	  }
	   $("#add-child-sub").unbind("click").bind("click",function(){
		   $.ajax({
			   	  type:"post",
				  async:true,
				  url:"AddChild.action",
				  data:{
					  "addType":"33",
					  "fea":$("#fea").val(),
					  "fea1":$("#fea1").val(),
					  "fea2":$("#fea2").val(),
					  "fea3":$("#fea3").val(),
					  "fea4":$("#fea4").val(),
				  },
				  success:function(result){
					  if(result=="error"){
						  alert("添加失败！");
					  }else{
						  tree.jstree().create_node(selected,result,"last");
						  tree.jstree().open_node(selected);
						  $("#input-place").css({"display":"none"});
						  alert("添加成功！");
						  $("#add-feature input").each(function(){
							  $(this).val("");
							  
						  });
					  } 
				  }
		   });
		   
	   });
	
  });
  //对节点重命名
  $("#rename-node").unbind("click").bind("click",function(){
	  $("#input-place").css({"display":"none"});
	  $("#rename-place").css({"display":"block"});
	  $("#rename-sub").unbind("click").bind("click",function(){
		  var rename = $("#rename").val();
		  /*
		   $.ajax({
		   		  type:"get",
				  async:true,
				  url:"AllMonit.action?monitID="+text,
				  success:function(result){
					  
				  }
		   });
		   */
		  tree.jstree().rename_node(selected,rename);
	  });
  });
  //删除节点
  $("#delete-node").unbind("click").bind("click",function(){ 
	  
	  if(selected=="jh"){
		  alert("该根节点不能删除！");
		  return;
	  }
	  if(confirm("您确定删除该条数据吗？")){
	  $("#input-place").css({"display":"none"});
	  $("#rename-place").css({"display":"none"});
	  var  text = tree.jstree().get_text(selected);
	   $.ajax({
		   	  type:"get",
			  async:true,
			  url:"DeleteMonit.action?monitID="+text,
			  success:function(result){
				  if(result=="isnull"){
					  alert("删除失败，请检查数据库是否有该数据！");
					  return false;
				  }else if(result=="error"){
					  alert("删除失败，请稍后再试！");
					  return false;
				  }else if(result == "success"){
					  tree.jstree().delete_node(selected);
					  alert("删除成功！");
					  $("#input-place").css({"display":"none"});
						$("#rename-place").css({"display":"none"});
						$("#tree-menu").css({"display":"none"});
				  }
			  }
	   }); 
  }
  });
});