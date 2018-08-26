<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CXF Demo</title>
</head>
<body>
    <div>
        <input id="user-get"/>
        <button id="btn-getuser">查找用户</button>
    </div>
    <div>
        <input id="userid-put"/>
        <input id="username-put"/>
        <button id="btn-putuser">新增或修改用户</button>
    </div>
    <div>
        <input id="user-delete"/>
        <button id="btn-deleteuser">删除用户</button>
    </div>
    <div>
        <button id="btn-getlogs">获取日志</button>
    </div>
    <div id="result"></div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        let result = $("#result");
        $("#btn-getuser").on("click",()=>{
            result.html("");
            $.getJSON("user/getUser?id="+$("#user-get").val().trim(),(data)=>{
                result.html(JSON.stringify(data));
            });
        });
        $("#btn-putuser").on("click",()=>{
            result.html("");
            $.ajax({
                url: "user/putUser",
                method: "PUT",
                data: JSON.stringify({"id": $("#userid-put").val().trim(),"name":$("#username-put").val().trim()}),
                contentType: "application/json; charset=UTF-8",
                success: (data)=>{
                    result.html(data);
                }
            });
        });
        $("#btn-deleteuser").on("click",()=>{
            result.html("");
            $.ajax({
                url: "user/deleteUser?id="+$("#user-delete").val().trim(),
                method: "DELETE", 
                success: (data)=>{
                    result.html(data);
                }
            });
        });
        $("#btn-getlogs").on("click",()=>{
            result.html("");
            $.getJSON("user/getLogs",(data)=>{
                result.html(JSON.stringify(data));
            });
        });
    </script>
</body>
</html>