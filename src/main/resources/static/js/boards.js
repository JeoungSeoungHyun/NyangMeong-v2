//공지사항 및 댕냥이자랑 게시판

//이벤트 리스너 - 공지사항 등록 
$("#btn-write").click(() => {
    write();
});
async function write() {
    let writeDto = {
        title: $("#title").val(),
        content: $("#summernote").val()
    }
 let id = $("#userId").val();
    console.log(writeDto);
    console.log(id);
    let response = await fetch(`/s/boards/${id}/update`, {
        method: "POST",
        body: JSON.stringify(writeDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("게시글 등록 성공");
        location.href = "/";
    } else {
        alert("게시글 등록 실패");
    }
}

//게시글 삭제 (댕냥이자랑)

$("#btn-delete").click(() => {
    deletePost();
});

async function deletePost() {
    let postId = $("#postId").val();
    let response = await fetch(`/s/api/post/${postId}`, {
        method: "DELETE" // delete는 body가 없다.
    });
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("삭제성공");
        location.href = "/";
    } else {
        alert("삭제실패");
    }
}