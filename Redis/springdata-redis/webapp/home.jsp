<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Redis Demo</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
	    <div class="input-group mt-2">
	        <input id="exists" class="form-control" placeholder="Key名称"/>
	        <button class="btn" onclick="exists()">判断存在</button>
	    </div>
	    <div class="input-group mt-2">
	        <input id="del" class="form-control" placeholder="Key名称"/>
	        <button class="btn" onclick="del()">删除</button>
	    </div>
	    <div class="input-group mt-2">
	        <input id="expire" class="form-control" placeholder="Key名称"/>
	        <input id="expire-timeout" class="form-control" placeholder="过期时间（秒）" type="number"/>
	        <button class="btn" onclick="expire()">设置过期时间</button>
	    </div>
	    <div class="input-group mt-2">
	        <input id="ttl" class="form-control" placeholder="Key名称"/>
	        <button class="btn" onclick="ttl()">获取过期时间</button>
	    </div>
	    
	    <div class="input-group mt-4">
            <input id="set" class="form-control" placeholder="Key名称"/>
            <input id="set-value" class="form-control" placeholder="Key值"/>
            <button class="btn" onclick="set()">存入字符串</button>
        </div>
        <div class="input-group mt-2">
            <input id="get" class="form-control" placeholder="Key名称"/>
            <button class="btn" onclick="get()">获取字符串Key</button>
        </div>
        <div class="input-group mt-2">
            <input id="incr" class="form-control" placeholder="Key名称"/>
            <input id="incr-delta" class="form-control" placeholder="变化值" type="number"/>
            <button class="btn" onclick="incr()">增加</button>
        </div>
        <div class="input-group mt-2">
            <input id="decr" class="form-control" placeholder="Key名称"/>
            <input id="decr-delta" class="form-control" placeholder="变化值" type="number"/>
            <button class="btn" onclick="decr()">减少</button>
        </div>
	    
	    <div class="card mt-3">
	        <div id="result" class="card-body"></div>
	    </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        let result = $('#result');
        function exists(){
        	$.get("exists?key="+$('#exists').val().trim(),function (data){
        		let text;
        		if(data){
        			text = '存在';
        		}else{
        			text = '不存在';
        		}
        		result.html(text);
        	});
        }
        function del(){
            $.get("del?key="+$('#del').val().trim(),function (data){
                let text;
                if(data){
                    text = '成功';
                }else{
                    text = '失败';
                }
                result.html(text);
            });
        }
        function expire(){
            $.get("expire?key="+$('#expire').val().trim()+"&timeout="+$('#expire-timeout').val(),function (data){
                let text;
                if(data){
                    text = '成功';
                }else{
                    text = '失败';
                }
                result.html(text);
            });
        }
        function ttl(){
            $.get("ttl?key="+$('#ttl').val().trim(),function (data){
                result.html(data+'秒');
            });
        }
        function set(){
            $.get("set?key="+$('#set').val().trim()+"&value="+$('#set-value').val().trim(),function (data){
                let text;
                if(data){
                    text = '成功';
                }else{
                    text = '失败';
                }
                result.html(text);
            });
        }
        function get(){
            $.get("get?key="+$('#get').val().trim(),function (data){
                result.html(data);
            });
        }
        function incr(){
            $.get("incr?key="+$('#incr').val().trim()+"&delta="+$('#incr-delta').val().trim(),function (data){
                result.html(data);
            });
        }
        function decr(){
            $.get("decr?key="+$('#decr').val().trim()+"&delta="+$('#decr-delta').val().trim(),function (data){
                result.html(data);
            });
        }
    </script>
</body>
</html>