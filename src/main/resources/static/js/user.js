let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
        /*$("#btn-login").on("click", () => {
            this.login();
        });*/
    },

    save: function() {
        let data = {
            uid: $("#uid").val(),
            nickname: $("#nickname").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({ // 회원가입 수행 요청
            type: "POST",
            //url: "/auth/joinProc",
            url: "/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=UTF-8",
            dataType: "json"
        }).done(function (resp) { // 응답이 정상이면 done 실행
            if(resp.status === 500) {
                alert("회원가입에 실패했습니다.");
            } else {
                // alert(resp);
                // console.log(resp);
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error) { // 응답이 실패면 fail 실행
            alert(JSON.stringify(error));
        });
    },

    /*login: function() {
        // 아래의 값들을 id값으로 찾아서 변수들에 바인딩 하고, data(자바스크립트 오브젝트)에 넣음
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data), // http body 데이터(MIME타입이 필요), 자바스크립트 오브젝트를 던지면 자바가 이해하지 못하므로 JSON으로 변경
            contentType: "application/json; charset=UTF-8", // // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 버퍼로 오기 때문에 바이트가 String으로 변경된다.
            // 생긴게 json이라면 (응답의 결과가 아래 두 개 함수의 파라미터로 전달) -> javascript 오브젝트로 변경
        }).done(function (resp) { // 응답이 정상이면 done 실행
            alert("로그인이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) { // 응답이 실패면 fail 실행
            alert(JSON.stringify(error));
        });
    }*/
}

index.init();