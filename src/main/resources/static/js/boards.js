//공지사항 및 댕냥이자랑 게시판

//이벤트 리스너 - 공지사항 등록 
$("#btn-writeJarang").click(() => {
    writeJarang();
});

$("#btn-writeNotice").click(() => {
    writeNotice();
});

//자랑 수정
    $("#btn-updateJarang").click((event) => {
        update();
    });

// 자랑글쓰기 
async function writeJarang() {
    let writeDto = {
        title: $("#title").val(),
        content: $("#summernote").val() 
    }
 let id = $("#userId").val();
    console.log(writeDto);
    console.log(id);
    let response = await fetch(`/s/boards`, {
        method: "POST",
        body: JSON.stringify(writeDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("게시글 등록 성공");
        location.href = "/boards";
    } else {
        alert("게시글 등록 실패"); 
    }
}
// 공지사항 쓰기 
async function writeNotice() {
    let writeDto = {
        title: $("#title").val(),
        content: $("#summernote").val()
    }
 let id = $("#userId").val();
    console.log(writeDto);
    console.log(id);
    let response = await fetch(`/s/notice`, {
        method: "POST",
        body: JSON.stringify(writeDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("게시글 등록 성공");
        location.href = "/notice";
    } else {
        alert("게시글 등록 실패");
    }
}



//자랑 수정
 async function update() {
        let id = $("#id").val();
        let post = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        console.log(post);
        let postJson = JSON.stringify(post);
        console.log(postJson);
        let response = await fetch("/s/api/boards/{id}" + id, {
            method: "PUT",
            body: postJson,
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            }
        });
        let responseObject = await response.json();
        if (responseObject.code == 1) {
            alert("수정성공");
            location.href = "/post/" + id;
        } else {
            alert("수정실패");
        }
    }