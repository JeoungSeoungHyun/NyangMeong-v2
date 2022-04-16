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

    //console.log(writeDto);

    let response = await fetch("/s/post", {
        method: "POST",
        body: JSON.stringify(writeDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("공지사항 등록 성공");
        location.href = "/";
    } else {
        alert("공지사항 등록 실패");
    }
}