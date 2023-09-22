let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-delete").on("click", () => {
            this.deleteById();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });
    },

    /*글쓰기*/
    save: function() {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=UTF-8",
            dataType: "json"
        }).done(function (resp) {
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    /*글삭제*/
    deleteById: function() {
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function (resp) {
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    /*글수정*/
    update: function() {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=UTF-8",
            dataType: "json"
        }).done(function (resp) {
            alert("글수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    /*댓글등록*/
    replySave: function() {
        let data = {
            content: $("#reply-content").val()
        };
        let boardId = $("#boardId").val();

        console.log(boardId);

        $.ajax({
            type: "POST",
            url: `/api/board/${boardId}/reply`, // 자바스크립트의 변수값이 String에 들어옴
            data: JSON.stringify(data),
            contentType: "application/json; charset=UTF-8",
            dataType: "json"
        }).done(function (resp) {
            alert("댓글 작성이 완료되었습니다.");
            location.href = `/board/${boardId}`; // 백틱(`)을 사용하는 이유는 URL 뒷부분의 게시물 번호를 동적으로 받기 위해서!
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    /*댓글삭제*/
    replyDelete: function(boardId, replyId) {
        alert(replyId);
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function (resp) {
            alert("댓글 삭제 성공");
            location.href = `/board/${boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();