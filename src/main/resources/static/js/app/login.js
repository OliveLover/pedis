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
            url: '/login',
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data, textStatus, response) {
            console.log("로그인 완료");
            console.log(response.getResponseHeader('Authorization'));
            localStorage.setItem('authorizationToken', response.getResponseHeader('Authorization'));
            alert('로그인 완료');
            window.location.href = '/';
        }).fail(function (error) {
            alert(error.responseText);
        });
    }

};

main.init();