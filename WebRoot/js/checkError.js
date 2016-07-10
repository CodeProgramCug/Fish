
var check=function(input,errorName){
		var string = input;
		
		var error = $("#"+errorName);
		console.log(string.length+"     "+string+"   "+errorName);
		
		if(string.length==0){error.text(""); return;}
		if(string.length>14){  error.text("数据格式错误"); return;}
		else{
			var pat=/^\d{0,4}\.\d{0,9}/g;
			if(!pat.test(string)){
				error.text("数据格式错误"); return;
			}
			error.text("");
			
	
		}
		
	};

$(document).ready(function(){
	
	
	
	var inputPosition = $("#Position2");
	var errorPosition = $("#Position2Error");
	var inputDistance2=$("#Distance2Bank2");
	var errorDistance2 = $("#Distance2Bank2Error")
	inputPosition.focusout(function (){
		var string = inputPosition.val();
		if(string.length == 0 || string == "左岸" || string == "右岸" || string == "中间"){
			errorPosition.text("");
		}
		else{
			errorPosition.text("数据格式错误");
		}
	});
	
	inputDistance2.focusout(function (){
		var string = inputDistance2.val();
		var pat=/^\d{0,10}/g;
		if(!pat.test(string)){
			errorDistance2.text("数据格式错误"); return;
		}
		errorDistance2.text("");
	});
	var  StartLongitude3 = $("#StartLongitude3");
	var StartLatitude3 = $("#StartLatitude3");
	var EndLongitude3 = $("#EndLongitude3");
	var EndLatitude3 = $("#EndLatitude3");
	EndLongitude3.focusout(function(){
	   check(EndLongitude3.val(),"EndLongitude3Error");
	});
	EndLatitude3.focusout(function(){
	   check(EndLatitude3.val(),"EndLatitude3Error");

	});
	StartLongitude3.focusout(function(){
		check(StartLongitude3.val(),"StartLongitude3Error");
	});
	StartLatitude3.focusout(function(){
		check(StartLatitude3.val(),"StartLatitude3Error");
	});
	
	var inputArray = ["StartLongitude1","StartLatitude1","EndLongitude1","EndLatitude1","LongitudeDot","LatitudeDot"];
	for(var i = 0; i<inputArray.length;i++){
		var tagName = "#"+inputArray[i];
	     error = inputArray[i]+"Error";
		var tag = $(tagName);
		console.log(tagName);
		tag.focusout(function(){
			check($(this).val(),$(this).eq(0).attr('id')+"Error");
		});
	}

	$("#Institution1").focusout(function(){
		var str = $(this).val();
		var error = $("#Institution1Error");
		if(str.length == 0){error.text(""); return;}
		if(str.length>60){error.text("输入数据超过60");return;}
	});
	
	$("#Investigator1").focusout(function(){
		var str = $(this).val();
		var error = $("#Investigator1Error");
		if(str.length == 0){error.text(""); return;}
		if(str.length>40){error.text("输入数据超过40");return;}
	});
	
	$("#Site1").focusout(function(){
		var str = $(this).val();
		var error = $("#Site1Error");
		if(str.length == 0){error.text(""); return;}
		if(str.length>200){error.text("输入数据超过200");return;}
	});
	
	$("#River1").focusout(function(){
		var str = $(this).val();
		var error = $("#River1Error");
		if(str.length == 0){error.text(""); return;}
		if(str.length>20){error.text("输入数据超过20");return;}
	});
	
	$("#Weather1").focusout(function(){
		var str = $(this).val();
		var error = $("#Weather1Error");
		if(str.length == 0){error.text(""); return;}
		if(str.length>4){error.text("输入数据超过4");return;}
	});
	
	$("#Temperature1").focusout(function(){
		var pat=/^\d{0,4}\.\d{0,2}/g;
		var error = $("#Temperature1Error");
		if(!pat.test($(this).val())){
			error.text("数据格式错误"); return;
		}
		error.text("");
		
	});
	
	
	
	$("#waterFloor").focusout(function(){
		var str = $(this).val();
		var error = $("#waterFloorError");
		if(str.length == 0){error.text(""); return;}
		if(str=="上"||str=="中"||str=="下"){return;}else error.text("输入数据错误");
	});

	$("#Depath").focusout(function(){
		var pat=/^\d{0,5}\.\d{0,2}/g;
		var error = $("#DepathError");
		if(!pat.test($(this).val())){
			error.text("数据格式错误"); return;
		}
		error.text("");
	});
	
	$("#waterWarm").focusout(function(){
		var pat=/^\d{0,5}\.\d{0,2}/g;
		var error = $("#waterWarmError");
		if(!pat.test($(this).val())){
			error.text("数据格式错误"); return;
		}
		error.text("");
	});
	
	$("#waterHeight").focusout(function(){
		var pat=/^\d{0,5}\.\d{0,2}/g;
		var error = $("#waterHeightError");
		if(!pat.test($(this).val())){
			error.text("数据格式错误"); return;
		}
		error.text("");
	});
	
	
	$("#waterFlow").focusout(function(){
		var pat=/^\d{0,10}\.\d{0,2}/g;
		var error = $("#waterFlowError");
		if(!pat.test($(this).val())){
			error.text("数据格式错误"); return;
		}
		error.text("");
	});  
	
});