$(function () {

	let users_page = $('#users');
	let settings_page = $('#settings');
	
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	
	$('#users-link').on('click',function(){
		users_page.show();
		settings_page.hide();
	});
	
	$('#settings-link').on('click',function(){
		users_page.hide();
		settings_page.show();
	});
	
	$('#admin-link').on('click',function(){
		location.href="admin";
	});
	
	$('#logout-link').on('click',function(){
		location.href = "logout";
	});
	
	$('#user-list').datalist({
	    url: 'content/getUserList',
	    method: 'get',
	    lines: true,
	    textField: "username",
	    groupField: "role"
	});
	
	$('#settings-button').on('click',function(){
		let settings_form = $('#settings-form');
    	if(settings_form.form('validate')){
    		let data = settings_form.serialize();
        	$.ajax({
        		url: "content/changePassword",
        		method: "POST",
        		data: data,
        		beforeSend: (xhr)=> {
        			xhr.setRequestHeader(header, token);
        		},
        		success: (data)=>{
        			if(data.value===1){
        				$.messager.show({
        					title:'信息',
        					msg:'修改密码成功！'
        				});
        				settings_form.form('clear');
        			}else if(data.value===-1){
        				$.messager.alert('警告','原密码错误！');
        			}else{
        				$.messager.alert('警告','修改失败请重试！');
        			}
        		},
        		error: (data)=>{
        			$.messager.alert('警告','修改失败请重试！');
        		}
        	});
    	}else{
    		$.messager.alert('警告','请正确输入！');
    	}
    });
	
});

$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '输入不一致。'
	}
});