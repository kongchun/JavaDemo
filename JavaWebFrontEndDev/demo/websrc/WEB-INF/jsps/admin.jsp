<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resource.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Administration</title>
    <%@ include file="/inc/common/page_base_js.jsp"%>
</head>

<body>
    <h2>Administrator:${name }</h2>
    <button onclick="javascript:window.history.back()">返回</button>

    <script type="text/javascript" src="${RESOURCE_STATIC_URL}/js/test-babel.js"></script>
</body>

</html>