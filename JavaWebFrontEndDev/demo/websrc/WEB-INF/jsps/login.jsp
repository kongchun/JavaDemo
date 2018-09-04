<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/resource.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <sec:csrfMetaTags />
    <title>登录</title>
    <%@ include file="/inc/common/page_base_css.jsp"%>
    <%@ include file="/inc/plugins/plugin_easyui.jsp"%>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_STATIC_URL}/css/login.css">
</head>

<body>
    <div class="panel-container">
        <div id="login-panel" class="panel">
            <div class="panel-header">
                <div class="panel-title">登录</div>
            </div>
            <div class="easyui-panel panel-body">
                <form id="login-form" method="post">
                    <div class="row">
                        <label for="login-name" class="textbox-label">用户名:</label>
                        <input id="login-name" class="easyui-textbox" name="username" required />
                    </div>
                    <div class="row">
                        <label for="login-password" class="textbox-label">密码:</label>
                        <input id="login-password" class="easyui-passwordbox" name="password" required />
                    </div>
                    <div class="submit-row">
                        <button id="login-button" class="easyui-linkbutton" type="button">登录</button>
                    </div>
                </form>
            </div>
        </div>
        <div id="register-panel" class="panel">
            <div class="panel-header">
                <div class="panel-title">注册</div>
            </div>
            <div class="easyui-panel panel-body">
                <form id="register-form" method="post">
                    <div class="row">
                        <label for="register-name" class="textbox-label">用户名:</label>
                        <input id="register-name" class="easyui-textbox" name="username" required />
                    </div>
                    <div class="row">
                        <label for="register-password" class="textbox-label">密码:</label>
                        <input id="register-password" class="easyui-passwordbox" name="password" required />
                    </div>
                    <div class="row">
                        <label for="register-confirm" class="textbox-label">确认密码:</label>
                        <input id="register-confirm" class="easyui-passwordbox" name="register-confirm" required validType="equals['#register-password']" />
                    </div>
                    <div class="submit-row">
                        <button id="register-button" class="easyui-linkbutton" type="button">注册</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="switcher">
            <span>切换登录/注册</span>
            <input id="login-switcher" title="login-switcher" />
        </div>
    </div>

    <script type="text/javascript" src="${RESOURCE_STATIC_URL}/js/login.js"></script>
</body>

</html>