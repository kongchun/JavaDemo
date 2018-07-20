<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo</title>
</head>
<body>
<h4>控制Quartz</h4>
<table>
    <tr>
        <td>
            <button onclick="start()">启动定时任务</button>
        </td>
    </tr>
    <tr>
        <td>
            <input id="add-job" placeholder="任务名称"/>
        </td>
        <td>
            <input id="add-trigger" placeholder="触发器名称"/>
        </td>
        <td>
            <input id="add-cron" placeholder="CRON表达式"/>
        </td>
        <td>
            <button onclick="add(1)">新增日志任务</button>
            <button onclick="add(2)">新增Flyway任务</button>
        </td>
    </tr>
    <tr>
        <td>
            <input id="del-job" placeholder="任务名称"/>
        </td>
        <td>
            <button onclick="del(1)">删除日志任务</button>
            <button onclick="del(2)">删除Flyway任务</button>
        </td>
    </tr>
    <tr>
        <td>
            <input id="pau-job" placeholder="任务名称"/>
        </td>
        <td>
            <button onclick="pau(1)">暂停日志任务</button>
            <button onclick="pau(2)">暂停Flyway任务</button>
        </td>
    </tr>
    <tr>
        <td>
            <input id="res-job" placeholder="任务名称"/>
        </td>
        <td>
            <button onclick="res(1)">继续日志任务</button>
            <button onclick="res(2)">继续Flyway任务</button>
        </td>
    </tr>
</table>
<script type="text/javascript">
function start(){
	location.assign('start');
}
function add(type){
	let jobName = document.getElementById('add-job').value;
	let triggerName = document.getElementById('add-trigger').value;
	let cronExpression = document.getElementById('add-cron').value;
	location.assign('add?type='+type+'&jobName='+jobName+'&triggerName='+triggerName+'&cron='+cronExpression);
}
function del(type){
	let jobName = document.getElementById('del-job').value;
	location.assign('del?type='+type+'&jobName='+jobName);
}
function pau(type){
    let jobName = document.getElementById('pau-job').value;
    location.assign('pau?type='+type+'&jobName='+jobName);
}
function res(type){
	let jobName = document.getElementById('res-job').value;
    location.assign('res?type='+type+'&jobName='+jobName);
}
</script>
</body>
</html>