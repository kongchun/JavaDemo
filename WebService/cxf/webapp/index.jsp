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
        <button id="btn-putuser">新增用户</button>
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
                data: "id="+$("#userid-put").val().trim()+"&name="+$("#username-put").val().trim(),
                success: (data)=>{
                    result.html(data);
                }
            });
        });
    </script>
</body>
</html>