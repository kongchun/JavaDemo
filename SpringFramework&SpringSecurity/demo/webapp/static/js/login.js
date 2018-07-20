$(function () {

    let register_panel = $('#register-panel');
    let login_panel = $('#login-panel');
    let login_switcher = $('#login-switcher');
	let login_form = $('#login-form');
	let register_form = $('#register-form');
	
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		beforeSend: function (xhr) {
			xhr.setRequestHeader(header, token);
		}
	});

    login_switcher.switchbutton({
        checked: true,
        onText: "登录",
        offText: "注册",
        onChange: function(checked){
            if (checked) {
                register_form.form('clear');
                register_panel.hide();
                login_panel.fadeIn();
            } else {
            	login_form.form('clear');
                login_panel.hide();
                register_panel.fadeIn();
            }
        }
    });
    
    $('#login-button').on('click',function(){
    	if(login_form.form('validate')){
    		let data = login_form.serialize();
        	$.ajax({
        		url: "validate",
        		method: "POST",
        		data: data,
        		success: (data)=>{
        			if(data.value===1){
        				location.href="content";
        			}else{
        				$.messager.alert('警告','用户名或密码错误！'+data.content);
        			}
        		},
        		error: (data)=>{
        			$.messager.alert('警告','登录失败请重试！'+data.content);
        		}
        	});
    	}else{
    		$.messager.alert('警告','请正确输入！');
    	}
    });
    
    $('#register-button').on('click',function(){
    	if(register_form.form('validate')){
    		let data = register_form.serialize();
        	$.ajax({
        		url: "validate/register",
        		method: "POST",
        		data: data,
        		success: (data)=>{
        			if(data.value===1){
        				$.messager.show({
        					title:'信息',
        					msg:'注册成功！请登录'
        				});
        				login_switcher.switchbutton('check');
        			}else{
        				$.messager.alert('警告','用户名已被占用！');
        			}
        		},
        		error: (data)=>{
        			$.messager.alert('警告','注册失败请重试！');
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