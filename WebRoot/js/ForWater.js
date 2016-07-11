$(document).ready(function(){
	var height = $(document).height();
	$("#water-outer").height(height-124);
	var waterTree = $("#WaterTree");

		$.ajax({									//获取采样水层点
			type:"post",
			async:true,
			url:"op_watlay.action",
			data:{
				"flag":"get_all",
			},
			success:function(result){
				if(result=="no"){
					alert("后台无数据，您可添加数据");
					return;
				}else if(result=="error"){
					alert("加载失败，请稍后重试");
					return;
				}
				var MonitArr= result.split(',');
			    var initTree = new Array(MonitArr.length);
			    for(var i = 0 ;i<MonitArr.length;i++){
			    	var d = {};
			    	console.log(MonitArr[i]);
			    	d["text"] ="采样水层:"+MonitArr[i];
			    	d["id"] = i.toString();
			    	d["state"] = "open";
			    	initTree[i] = d;
			    }
				waterTree.jstree({
				    'core' : {
					"check_callback" : true,
				      'data' :
					  [{"id":"jh",
						"text":"采样水层列表",
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

	 function show(text){
		 $("#tool").css({"display":"none"});
         $("#get").css({"display":"none"});
         $("#Fishes").css({"display":"none"});
         $("#FishEggs").css({"display":"none"});
		 $("#delete").css({"display":"inline-block"});
		 $("#add").css({"display":"none"});
		 $("#add2").css({"display":"none"});
         $("#update").css({"display":"block"});
		 $("#Tittle").css({"display":"block"});
		 $("#sumbit").css({"display":"none"});
 		 if(text.substring(0,1) == "网") {
			$("#tool").css({"display":"block"});
			$("#Tittle").text("网具:");
			$("#tool input").each(function(){
				  $(this).val("");	  
			  });
		
 		}else if(text.substring(0,1) == "渔"){
			$("#Tittle").text("渔获物:");
 			$("#add").css({"display":"inline-block"});
 			$("#add2").css({"display":"inline-block"});
 			$("#add").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加卵样本");
 			$("#add2").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加鱼样本");
            $("#get").css({"display":"block"});
            $("#get input").each(function(){
				  $(this).val("");	  
			 });
 		}else if(text.substring(0,1) == "鱼"){

            $("#Fishes").css({"display":"block"});
			$("#Tittle").text("鱼样本:");
			$("#Fishes input").each(function(){
				  $(this).val("");	  
			  });
			
		}else if(text.substring(0,1)=="卵"){

            $("#FishEggs").css({"display":"block"});
			$("#Tittle").text("卵样本:");
			$("#FishEggs input").each(function(){
				  $(this).val("");	  
			  });
		}else{
			    $("#update").css({"display":"none"});
				$("#add").css({"display":"inline-block"});
	 			$("#add2").css({"display":"inline-block"});
	 			$("#add").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加网具");
	 			$("#add2").html("<span class='glyphicon glyphicon-plus'></span>&nbsp"+"添加渔获物");
		}
		 
	 }
	
	 function addParam(text){
		 var par = {};
		
		 if(text.substring(0,1) == "网") {
				par["Name"] = $("#Name").val();
				par["Photo"] = $("#Photo").val();
				par["NetsModel"] = $("#NetsModel").val();
				par["NetMouthArea"] = $("#NetMouthArea").val();
				par["NetMouthDip"] = $("#NetMouthDip").val();
				par["StartTime"] = $("#StartTime").val();
				par["EndTime"] = $("#EndTime").val();
				par["NetMouthVelocity"] = $("#NetMouthVelocity").val();
			
				return par;
	 	   }else if(text.substring(0,1) == "渔"){
	 		   par["Name"] = $("#NameGet").val();
	 		   par["Photo"] = $("#PhotoGet").val();
	 		   par["TotalQuality"]= $("#TotalQuality").val();
	 		   par["EggQuality"] = $("#EggQuality").val();
	 		   par["FryQuality"] = $("#FryQuality").val();
	 		   return par;
	 		}else if(text.substring(0,1) == "鱼"){
	 			par["Photo"] = $("#PhotoFish").val();
	 			par["BodyLength"] = $("#BodyLength").val();
	 			par["Length"] = $("#Length").val();
	 			par["BodyWeight"] = $("#BodyWeight").val();
	 			par["Age"] = $("#Age").val();
                return par;               
			}else if(text.substring(0,1)=="卵"){
                par["Photo"] = $("#PhotoEgg").val();
                par["Period"] = $("#Period").val();
                par["Diameter"] = $("#Diameter").val();
                par["EMDiameter"] = $("#EMDiameter").val();
                par["PigmentProp"] = $("#PigmentProp").val();
                par["EmbryoProp"] = $("#EmbryoProp").val();
                return par;
			}
	 }
	 
	 var select = "";
	 var lastSelect = ""; 
	waterTree.on("changed.jstree", function (e, data) {
		select = data.selected;
		var selnode=data.instance.get_node(select);
		//if(select == false){ alert("false"); return;}
		
		var  text = waterTree.jstree().get_text(select);
		show(text);
		var id = text.split(":")[1];
		$.ajax({
			  type:"get",
			  url:"ShowMessage.action?monitID="+id,
			  async:true,
			  success:function(result){
			       var first = id.substring(0,3);
			       var Mon = result.split(",");
			       switch(first){
			       case "FSS": 
			    	$("#Fishes input").each(function(){
							  $(this).val("");	  
						 });
						 $("#Fishes input").each(function(i,n){
							  $(this).val(Mon[i+1]);	  
						});
			  
			       break;
			       
			       case "FSE": 
			    	$("#FishEggs input").each(function(){
							  $(this).val("");	  
						 });
						 $("#FishEggs input").each(function(i,n){
							  $(this).val(Mon[i+1]);	  
						});
			       break;
			       
		          case "CTH": 
	    	      $("#get input").each(function(){
					  $(this).val("");	  
				    });
				   $("#get input").each(function(i,n){
					  $(this).val(Mon[i+1]);	  
				  });
				   break;
				   
		          case "NET": 
		    	      $("#tool input").each(function(){
						  $(this).val("");	  
					    });
					   $("#tool input").each(function(i,n){
						  $(this).val(Mon[i+1]);	  
					  });
					   break;				   
				   
	       }
			       
			  }
		});
		
		waterTree.unbind("dblclick.jstree").bind("dblclick.jstree", function (obj) {
			var te = text.split(":")[1];
			if(select == lastSelect) { return;}
			if(select == "jh"){
				//这里调用接口获取采样水层列表的子节点：
				  $.ajax({
					  type:"get",
					  async:true,
					  url:"AllMonit.action?monitID="+te,
					  success:function(result){
						  MonitArr=result.split(',');
						  for(var ix = 0;ix<MonitArr.length;ix ++){
							  waterTree.jstree().create_node(selected,"采样水层:"+MonitArr[ix],"last");	

							}
						  waterTree.jstree().open_node(selected);
					  }
				  });
				
			}
			else{
		
				
			var text1 = text.split(":")[1];
			  $.ajax({
				 type:"get",
				  async:true,
				  url:"AllMonit.action?monitID="+text1,
				  success:function(result){
					 if(result=="00"){
						  alert("发生错误，请稍后再试！")
						  return;
					  }else if(result==""||result==null){
						  alert("往后已无索引！");
					  }else{
					  MonitArr=result.split(',');
					  var str;
					  for(var i = 0;i<MonitArr.length;i ++){
						    var string = MonitArr[i].substring(0,3);
						    console.log(string);
						    console.log(MonitArr[i].substring(0,3)+"     "+MonitArr[i].substring(0,3));
						    if(string=="FSS") str = "鱼样本:";
						    else if(string == "FSE") str = "卵样本:";
						    else if(string=="CTH") str="渔获物:";
						    else if(string == "NET") str ="网具:";
						    else if(string == "WLE") str = "采样水层:";
						    waterTree.jstree().create_node(select,str+MonitArr[i],"last");	
						}
					  waterTree.jstree().open_node(select);
					  }
				  }
			  });
				
			}

		});
	 });
	 
	
	var toolOrCatch;//判断是工具还是与捕获物；
	var fishOrEgg;
    $("#add").unbind("click").bind("click",function(){
    	var  text = waterTree.jstree().get_text(select);
    	show("kkk");
    	$("#delete").css({"display":"none"});
    	$("#update").css({"display":"none"});
    	$("#sumbit").css({"display":"inline-block"});
    	if(text.substring(0,1) == "采"){
    		$("#tool").css({"display":"block"});
    		toolOrCatch = "NET";
    		$.ajax({
			   	  type:"post",
				  async:true,
				  url:"op_catools.action",
				  data:{
					  "flag":"query",
					  "ID_WaterLayer":text.split(":")[1]
				  },
				  success:function(result){
					  if(result=="enough"){
					      $("#sumbit").css({"display":"none"});
						   alert("只能有一个网具");
						   show(text);
						   return;
					  }
				  }
		   });
    		$("#Tittle").text("添加工具");
    		
    	}
    	else{
    		$("#FishEggs").css({"display":"block"});
    		fishOrEgg="FSE";
    		$("Tittle").text("添加卵样本");
    	}
    	
    	
    });
    
    $("#add2").unbind("click").bind("click",function(){
    	var  text = waterTree.jstree().get_text(select);
    	show("kkk");
    	$("#delete").css({"display":"none"});
    	$("#update").css({"display":"none"});
    	$("#sumbit").css({"display":"inline-block"});
    	if(text.substring(0,1) == "采"){
    		$("#get").css({"display":"block"});
    		$("#Tittle").text("添加捕获物");
    		toolOrCatch="CTH";
    	}
    	else{
    		$("#Fishes").css({"display":"block"});
    		fishOrEgg="FSS";
    		$("Tittle").text("添加鱼样本");
    	}
    });
    
    $("#sumbit").unbind("click").bind("click",function(){
  	  var  text = waterTree.jstree().get_text(select);
  	   var ID = text.split(":")[1];
  	   var first = ID.substring(0,3);
  	   var url;
  	   var str;
  	   var par;
  	   console.log(first);
  	
  	   if(first == "WLE"){
  		   if(toolOrCatch=="NET") { 
  			   
  			   str="网具:";
  			   par = addParam(str);//返回参数
               url="op_catools.action";  
               par["flag"]="insert"; 
               par["ID_WaterLayer"]=ID;
               }
  		   else{
  			 str="渔获物:";  			  
  			 par = addParam(str);
  			 url="op_catch.action";   
  			 par["flag"]="insert"; 
  			 par["ID_WaterLayer"]=ID; 
  		   }
  	   }else{
  		   if(fishOrEgg=="FSE"){
  			 str="卵样本:";
  			 par = addParam(str);
  			 url="op_egg.action";   	
  			 par["flag"]="insert";
  			 par["ID_Catches"]=ID;
  		   }else{
  			 str ="鱼样本:"; 
  			 par = addParam(str);
  			 url="op_fish.action";   	
  			 par["flag"]="insert"; 
  			 par["ID_Catches"]=ID;
  		   }
  	   }
  	    $.ajax({
			   	  type:"post",
				  async:true,
				  url:url,
				  data:par,
				  success:function(result){
					  if(result=="error"){
						  alert("添加失败！");
					  }else{
						  //更新结点
						  alert("添加成功");
						  waterTree.jstree().create_node(select,str+result,"last");
					  } 
				  }
		   });
    });
    
    $("#delete").unbind("click").bind("click",function(){
    	show("kkk");
    	var url = "DeleteMonit.action";
    	
    	$("#delete").css({"display":"none"});
    	$("#update").css({"display":"none"});
    	////接口
    	waterTree.jstree().delete_node(select);
    	var  text = waterTree.jstree().get_text(select);
    	var par = {};
    	var ID = text.split(":")[1];
    	
    	par["monitID"]=ID;
    	
 	    $.ajax({
			   	  type:"post",
				  async:true,
				  url:url,
				  data:par,
				  success:function(result){
					  if(result=="error"){
						  alert("删除失败！");
					  }else{
						  alert("删除成功！");
					  } 
				  }
		   });
    	
    	
    });
    
    $("#update").unbind("click").bind("click",function(){
    	
    	   var  text = waterTree.jstree().get_text(select);
    	   var par = addParam(text);//返回参数
    	   var ID = text.split(":")[1];
      	   var first = ID.substring(0,3);
      	   var url;

      	   switch(first){
	   			case "NET":  url="op_catools.action";   par["flag"]="update"; par["SampleID"]=ID; break;
	   			case "CTH":  url="op_catch.action";   	par["flag"]="update"; par["SampleID"]=ID; break;
	   			case "FSS":  url="op_fish.action";   	par["flag"]="update"; par["SampleID"]=ID; break;
	   			case "FSE":  url="op_egg.action";   	par["flag"]="update"; par["SampleID"]=ID; break;
      	   }  	   
    	    $.ajax({
			   	  type:"post",
				  async:true,
				  url: url ,
				  data:par,
				  success:function(result){
					  if(result=="error"){
						  alert("更新失败！");
					  }else{
						  alert("更新成功！");
					  } 
				  }
		   });
    });
    
});
    