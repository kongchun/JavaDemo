$(function() {
    let registerPanel = $('#register-panel');
    let loginPanel = $('#login-panel');
    let loginSwitcher = $('#login-switcher');
    let loginForm = $('#login-form');
    let registerForm = $('#register-form');

    let token = $('meta[name=\'_csrf\']').attr('content');
    let header = $('meta[name=\'_csrf_header\']').attr('content');
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
    });

    loginSwitcher.switchbutton({
        checked: true,
        onText: '登录',
        offText: '注册',
        onChange: function(checked) {
            if (checked) {
                registerForm.form('clear');
                registerPanel.hide();
                loginPanel.fadeIn();
            } else {
                loginForm.form('clear');
                loginPanel.hide();
                registerPanel.fadeIn();
            }
        },
    });

    $('#login-button').on('click', function() {
        if (loginForm.form('validate')) {
            let data = loginForm.serialize();
            $.ajax({
                url: 'validate',
                method: 'POST',
                data: data,
                success: (data) => {
                    if (data.value === 1) {
                        location.href = 'content';
                    } else {
                        $.messager.alert('警告', '用户名或密码错误！' + data.content);
                    }
                },
                error: (data) => {
                    $.messager.alert('警告', '登录失败请重试！' + data.content);
                },
            });
        } else {
            $.messager.alert('警告', '请正确输入！');
        }
    });

    $('#register-button').on('click', function() {
        if (registerForm.form('validate')) {
            let data = registerForm.serialize();
            $.ajax({
                url: 'validate/register',
                method: 'POST',
                data: data,
                success: (data) => {
                    if (data.value === 1) {
                        $.messager.show({
                            title: '信息',
                            msg: '注册成功！请登录',
                        });
                        loginSwitcher.switchbutton('check');
                    } else {
                        $.messager.alert('警告', '用户名已被占用！');
                    }
                },
                error: () => {
                    $.messager.alert('警告', '注册失败请重试！');
                },
            });
        } else {
            $.messager.alert('警告', '请正确输入！');
        }
    });
});

$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value, param) {
            return value == $(param[0]).val();
        },
        message: '输入不一致。',
    },
});
