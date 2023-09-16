// 클라이언트 측 JavaScript 코드

// 페이지가 로드될 때 실행
$(document).ready(function() {
    checkLoginStatus();
});

// 로그인 상태 확인 함수
function checkLoginStatus() {
    var authorizationHeader = localStorage.getItem('authorizationToken');
    if (authorizationHeader) {
        // 토큰이 있으면 로그인 상태로 간주
        changeLoginButtonToLogout();
    } else {
        // 토큰이 없으면 비로그인 상태로 간주
    }
}

// "로그인" 버튼을 "로그아웃" 버튼으로 변경하는 함수
function changeLoginButtonToLogout() {
    var $loginButton = $('#login-button');
    if ($loginButton) {
        $loginButton.text('로그아웃');
        $loginButton.attr('href', '/logout'); // 로그아웃 링크로 변경
    }
}
