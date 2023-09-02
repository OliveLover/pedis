var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
        _this.save();
        });
    },
    save : function () {
        var data = {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/join',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원 가입이 완료되었습니다.');
            console.log('호호호');
            window.location.href = '/';
        }).fail(function (error) {
            alert(error.responseText);
            console.log('하하하');
        });
    }

};

main.init();