
$(document).ready(function(){
	var cw=document.documentElement.clientWidth;
	var ch=document.documentElement.clientHeight;
	var article=$(".article")[0];
	article.style.width=(cw-162)+"px";
	article.style.height=(ch-100)+"px";
	window.onresize = function () {
	var cw=document.documentElement.clientWidth;
	var ch=document.documentElement.clientHeight;
	var article=$(".article")[0];
	article.style.width=(cw-162)+"px";
	article.style.height=(ch-102)+"px";
	}
});

function change(){
   			var select=document.getElementById("selecttable").value;
   			var phy=document.getElementById("Monit_Phy");
   			var zooplan=document.getElementById("Monit_Zooplan");
   			var ben=document.getElementById("Monit_Ben");
   			var water=document.getElementById("Water_QualityMonit");
   			//alert("zc");
   			if(select==1){
   				phy.style.display="block";
   				zooplan.style.display="none";
   				ben.style.display="none";
   				water.style.display="none";
   			}
   			if(select==2){
   			//alert("saasd");
   				phy.style.display="none";
   				zooplan.style.display="block";
   				ben.style.display="none";
   				water.style.display="none";	
   			}
   			if(select==3){
   				phy.style.display="none";
   				zooplan.style.display="none";
   				ben.style.display="block";
   				water.style.display="none";
   			}
   			if(select==4){
   				phy.style.display="none";
   				zooplan.style.display="none";
   				ben.style.display="none";
   				water.style.display="block";
   			}
   		}
   		
   function xcheck(fnode){
	child=fnode.getElementsByTagName('input');
	var time=/time/i;
	var data=/data/i
	for(var i=0;i<child.length;i++){
	if(child[i].value=='undefind'||child[i].value==""||child[i].value==null){
				alert("请完整填写表单。");
				return false;
			}
		//if(time.test(child[i].name)||data.test(child[i].name)){
			//child[i].value
			//yyyy-xx-dd hh:mm:ss
			//var dReg=/[0-9]{4}-[0-9]{2}-[0-9]{2}\s[0-9]{2}:[0-9]{2}:[0-9]{2}/g;
			//var dReg2=/[0-9]{4}-[0-9]{2}-[0-9]{2}\s[0-9]:[0-9]{2}:[0-9]{2}/g;
			//if(!dReg.test(child[i].value)&&!dReg2.test(child[i].value)){
			//	alert("请按照时间格式填写。格式：yyyy-xx-dd hh:mm:ss");
			//	return false;
			//}
		//}
	}
	return true;
   }
   
   