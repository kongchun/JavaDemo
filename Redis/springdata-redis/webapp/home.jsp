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
    <div class="container" style="margin-bottom:64px;">
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
        
        <div class="input-group mt-4">
            <input id="rpush" class="form-control" placeholder="Key名称"/>
            <input id="rpush-list" class="form-control" placeholder="要存入List中的值 按空格分隔"/>
            <button class="btn" onclick="rpush()">存入List</button>
        </div>
        <div class="input-group mt-2">
            <input id="lrange" class="form-control" placeholder="Key名称"/>
            <input id="lrange-start" class="form-control" placeholder="起点" type="number"/>
            <input id="lrange-end" class="form-control" placeholder="终点" type="number"/>
            <button class="btn" onclick="lrange()">列出List</button>
        </div>
        <div class="input-group mt-2">
            <input id="lset" class="form-control" placeholder="Key名称"/>
            <input id="lset-index" class="form-control" placeholder="索引" type="number"/>
            <input id="lset-value" class="form-control" placeholder="Key值"/>
            <button class="btn" onclick="lset()">修改List</button>
        </div>
        <div class="input-group mt-2">
            <input id="lrem" class="form-control" placeholder="Key名称"/>
            <input id="lrem-count" class="form-control" placeholder="删除个数"/>
            <input id="lrem-value" class="form-control" placeholder="Key值"/>
            <button class="btn" onclick="lrem()">List删除</button>
        </div>
        
        <div class="input-group mt-4">
            <input id="hmset" class="form-control" placeholder="Key名称"/>
            <input id="hmset-map" class="form-control" placeholder="要存入Hash中的键值 按空格分隔"/>
            <button class="btn" onclick="hmset()">存入Hash</button>
        </div>
        <div class="input-group mt-2">
            <input id="hgetall" class="form-control" placeholder="Key名称"/>
            <button class="btn" onclick="hgetall()">列出Hash</button>
        </div>
        <div class="input-group mt-2">
            <input id="hset" class="form-control" placeholder="Key名称"/>
            <input id="hset-hashkey" class="form-control" placeholder="HashKey"/>
            <input id="hset-value" class="form-control" placeholder="Key值"/>
            <button class="btn" onclick="hset()">修改Hash</button>
        </div>
        <div class="input-group mt-2">
            <input id="hdel" class="form-control" placeholder="Key名称"/>
            <input id="hdel-list" class="form-control" placeholder="要从Hash中的键 按空格分隔"/>
            <button class="btn" onclick="hdel()">Hash删除</button>
        </div>
        
        <div class="input-group mt-4">
            <input id="sadd" class="form-control" placeholder="Key名称"/>
            <input id="sadd-list" class="form-control" placeholder="要存入Set中的值 按空格分隔"/>
            <button class="btn" onclick="sadd()">存入Set</button>
        </div>
        <div class="input-group mt-2">
            <input id="smembers" class="form-control" placeholder="Key名称"/>
            <button class="btn" onclick="smembers()">列出Set</button>
        </div>
        <div class="input-group mt-2">
            <input id="sinter" class="form-control" placeholder="Key列表 按空格分隔"/>
            <button class="btn" onclick="sinter()">Set交集</button>
        </div>
        <div class="input-group mt-2">
            <input id="sunion" class="form-control" placeholder="Key列表 按空格分隔"/>
            <button class="btn" onclick="sunion()">Set并集</button>
        </div>
        <div class="input-group mt-2">
            <input id="srem" class="form-control" placeholder="Key名称"/>
            <input id="srem-list" class="form-control" placeholder="要从Set中的值 按空格分隔"/>
            <button class="btn" onclick="srem()">Set删除</button>
        </div>
        <div class="input-group mt-2">
            <input id="zadd" class="form-control" placeholder="Key名称"/>
            <input id="zadd-set" class="form-control" placeholder="要存入ZSet中的分数和值 按空格分隔"/>
            <button class="btn" onclick="zadd()">存入有序Set</button>
        </div>
        <div class="input-group mt-2">
            <input id="zrange" class="form-control" placeholder="Key名称"/>
            <input id="zrange-start" class="form-control" placeholder="起点" type="number"/>
            <input id="zrange-end" class="form-control" placeholder="终点" type="number"/>
            <button class="btn" onclick="zrange()">列出有序Set</button>
        </div>
        
    </div>
    <nav class="navbar navbar-dark bg-dark fixed-bottom">
        <div class="container">
            <div class="navbar-brand">结果</div>
            <div id="result" class="text-white"></div>
        </div>
    </nav>
    
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="static/js/home.js" type="text/javascript"></script>
</body>
</html>