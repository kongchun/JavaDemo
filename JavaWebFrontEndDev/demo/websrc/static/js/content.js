let $ = require('jquery');
$(function() {
    let usersPage = $('#users');
    let settingsPage = $('#settings');

    let token = $('meta[name=\'_csrf\']').attr('content');
    let header = $('meta[name=\'_csrf_header\']').attr('content');

    $('#users-link').on('click', function() {
        usersPage.show();
        settingsPage.hide();
    });
    $('#settings-link').on('click', function() {
        usersPage.hide();
        settingsPage.show();
    });

    $('#admin-link').on('click', function() {
        location.href = 'admin';
    });

    $('#logout-link').on('click', function() {
        location.href = 'logout';
    });

    $('#user-list').datalist({
        url: 'content/getUserList',
        method: 'get',
        lines: true,
        textField: 'username',
        groupField: 'role',
    });

    $('#settings-button').on('click', function() {
        let settingsForm = $('#settings-form');
        if (settingsForm.form('validate')) {
            let data = settingsForm.serialize();
            $.ajax({
                url: 'content/changePassword',
                method: 'POST',
                data: data,
                beforeSend: (xhr) => {
                    xhr.setRequestHeader(header, token);
                },
                success: (data) => {
                    if (data.value === 1) {
                        $.messager.show({
                            title: '信息',
                            msg: '修改密码成功！',
                        });
                        settingsForm.form('clear');
                    } else if (data.value === -1) {
                        $.messager.alert('警告', '原密码错误！');
                    } else {
                        $.messager.alert('警告', '修改失败请重试！');
                    }
                },
                error: () => {
                    $.messager.alert('警告', '修改失败请重试！');
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
