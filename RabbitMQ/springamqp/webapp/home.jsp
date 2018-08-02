<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RabbitMQ Demo</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
	    <div class="form-group mt-3">
		    <input id="index-input" type="number" class="form-control" placeholder="输入参数"/>
	    </div>
		<button onclick="send()" class="btn btn-primary">发送</button>
		<div class="card mt-3">
	       <div id="result" class="card-body"></div>
	   </div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function send(){
	  let input = document.getElementById("index-input").value;
	  let result = document.getElementById("result");
	  if(input<0||input===''){
		  alert('输入必须大于0');
		  return;
	  }
	  let xhr=new XMLHttpRequest();
	  xhr.open("GET","send?index="+input,true);
	  xhr.send();
	  result.innerHTML+='已发送请求fib('+input+')<br/>';
	  xhr.onreadystatechange=function(){
	    if (xhr.readyState==4 && xhr.status==200){
	      document.getElementById("result").innerHTML+='得到结果：'+xhr.responseText+'<br/>';
	    }
	  }
	}
	</script>
</body>
</html>