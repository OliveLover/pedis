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
            data: JSON.stringify(data),
            beforeSend: function(jqXHR) {
                var authorizationHeader = localStorage.getItem('authorizationToken');
                if (authorizationHeader) {
                    jqXHR.setRequestHeader('Authorization', authorizationHeader)
                }
            }
        }).done(function(data, textStatus, jqXHR) {
            console.log("로그인 완료");
            var authorizationHeader = jqXHR.getResponseHeader('Authorization');
            localStorage.setItem('authorizationToken', authorizationHeader);
            alert('로그인 완료');
            window.location.href = '/';
        }).fail(function (error) {
            alert(error.responseText);
        });
    }

};

main.init();