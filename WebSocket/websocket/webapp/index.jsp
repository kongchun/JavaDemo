<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket</title>
</head>
<body>
	<input id="user" placeholder="用户名" />
	<button id="btn-login">登录</button>
	<button id="btn-logout">下线</button>

	<input id="text" type="text" placeholder="消息内容" />

	<input id="to" type="text" placeholder="发送给" />
	<button id="btn-send">发送</button>

	<div id="message" style="background: lightgrey"></div>
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	$(()=>{
		$('#btn-login').click(login);
		$('#btn-logout').click(closeWebSocket);
		//当窗口关闭时 主动关闭websocket连接
		$(window).on('beforeunload',closeWebSocket);
		$('#btn-send').click(send);
	});
	
	let ws;
	function login(){
		let user = $('#user').val().trim();
		ws = new WebSocket('ws://127.0.0.1:8080/websocket/websocket/'+user);
        ws.onerror = (error)=>{
            setMessageInnerHTML('error');
            console.log(error);
        };
        //连接成功建立的回调方法
        ws.onopen = ()=>{
            setMessageInnerHTML('登录成功');
        };
        //接收到消息的回调方法
        ws.onmessage = (event)=>{
        	let data = JSON.parse(event.data);
        	setMessageInnerHTML(data.from+'说：'+data.message);
        };
        //连接关闭的回调方法
        ws.onclose = ()=>{
            setMessageInnerHTML('已下线');
        };
	}
    //将消息显示在网页上
    function setMessageInnerHTML(text){
        $('#message').append(text + '<br/>');
    }
    //关闭连接
    function closeWebSocket(){
        ws.close();
    }
    //发送消息
    function send(){
        let message = $('#text').val().trim();
        let to = $('#to').val().trim();
        if(''!==message){
            ws.send(JSON.stringify({
            	from: $('#user').val().trim(),
            	to: to,
                message: message
            }));
            setMessageInnerHTML('我说：'+message);
        }
    }
	</script>
</body>
</html>