
//게시글 수정버튼 클릭 이벤트
$("#btn-updateJarang").click((event) => {
    update();
});

//게시글 삭제버튼 클릭 이벤트
$("#btn-delete").click(() => {
    deletePost();
});

//게시글 수정
async function update() {

    let id = $("#userId").val();

    console.log(id);

    let fileForm = $("#fileForm")[0];

    let formData = new FormData(fileForm);

    let response = await fetch(`/s/api/boards/${id}` , {
        method: "PUT",
        body: formData,
    });

    if (response.status == 200) {
        alert("수정성공");
        location.href = "/boards/" + id;
    } else {
        alert("수정실패");
    }
}

//게시글 삭제 
 async function deletePost() {
        let boardsId = $("#boardsId").val();
        let response = await fetch(`/s/api/boards/${boardsId}`, {
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

// 썸네일 파일 이미지 확인
$("#img-input").change((event)=>{
    let f = event.target.files[0];

    if(!f.type.match("image.*")){
        alert("이미지를 선택해주세요!")
        return ;
    }
});

   
