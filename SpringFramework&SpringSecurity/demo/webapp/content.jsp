<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<sec:csrfMetaTags />
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="static/css/reset.min.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/material-teal/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="static/css/content.css">
</head>
<body class="easyui-layout">
	<div class="nav" data-options="region:'north'">
	    <%-- ${SPRING_SECURITY_CONTEXT.authentication.principal.username} --%>
		<span>${name}的主页</span>
	</div>
	<div class="west-area" data-options="region:'west',split:true"
		title="菜单">
		<div id="menu" class="easyui-menu" data-options="inline:true">
			<div id="users-link" data-options="iconCls:'icon-search'">用户</div>
			<div id="settings-link" data-options="iconCls:'icon-lock'">修改密码</div>
			<div id="admin-link">管理员可用</div>
			<div class="menu-sep"></div>
			<div id="logout-link" data-options="iconCls:'icon-clear'">退出登录</div>
		</div>
	</div>
	<div class="center-area" data-options="region:'center',title:'内容'">
		<div id="users">
			<ul id="user-list" title="用户列表" class="easyui-datalist"></ul>
		</div>
		<div id="settings">
			<div id="settings-panel" class="panel">
				<div class="panel-header">
					<div class="panel-title">修改密码</div>
				</div>
				<div class="easyui-panel panel-body">
					<form id="settings-form" method="post">
						<div class="row">
							<label for="settings-origin" class="textbox-label">原始密码:</label>
							<input id="settings-origin" class="easyui-passwordbox"
								name="origin" required />
						</div>
						<div class="row">
							<label for="settings-password" class="textbox-label">新的密码:</label>
							<input id="settings-password" class="easyui-passwordbox"
								name="password" required />
						</div>
						<div class="row">
							<label for="settings-confirm" class="textbox-label">确认密码:</label>
							<input id="settings-confirm" class="easyui-passwordbox"
								name="settings-confirm" required
								validType="equals['#settings-password']" />
						</div>
						<div class="submit-row">
							<button id="settings-button" class="easyui-linkbutton"
								type="button">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript"
		src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/js/content.js"></script>
</body>
</html>