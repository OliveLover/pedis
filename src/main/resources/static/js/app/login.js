var main = {
    init : function () {
        var _this = this;
        $('#btn-login').on('click', function () {
        _this.login();
        });
    },
    login : function () {
        var data = {
            username: $('#username').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/login',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('로그인 완료');
            window.location.href = '/';
        }).fail(function (error) {
            alert(error.responseText);
        });
    }

};

main.init();