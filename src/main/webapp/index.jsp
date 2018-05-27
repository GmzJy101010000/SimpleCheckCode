<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码</title>
<script type="text/javascript" src="/checkCode/libs/jquery.js"></script>
<script type="text/javascript">
function reloadImage(){
	var ts=new Date().getTime();
	document.getElementById("image").src="<%=request.getContextPath()%>/servlet/ImageServlet?ts="+ts;
}
function checkContent(){
	
var a=document.getElementById("codeInput").value;
if(a.length<4){	
}
else if(a.length==4){
	   
	b=a.toUpperCase(); 
	$.ajax({
		  url: "/checkCode/checkCodeServlet",
		  
		  type:"POST",
		  success: function( result ) {
			 if(b==result){
				 document.getElementById("false").style.display="none";
					document.getElementById("true").style.display="block";
			 }
			 else{
				 document.getElementById("true").style.display="none";
				 document.getElementById("false").style.display="block";
					reloadImage();
			 }
		 
		  },
		  error:function( result ) {
		   alert("error");
		  }
		});
	
}
else{
	 document.getElementById("true").style.display="none";
	document.getElementById("false").style.display="block";
	reloadImage();
}
}
</script>
</head>
<body>
<form action="/servlet/checkCodeServlet" method="get">
<input id="codeInput" type="text" name="checkcode" onkeyup="checkContent()"></input>
<img id="image" alt="验证码" src="<%=request.getContextPath()%>/servlet/ImageServlet">
<a href="javascript:reloadImage();">换一张图片</a>
<p id="false" style="display:none">验证码输入错误</p>
<p id="true" style="display:none">验证码输入正确</p>
<br><br><input type="submit" value="提交"></input>

</form>


</body>
</html>