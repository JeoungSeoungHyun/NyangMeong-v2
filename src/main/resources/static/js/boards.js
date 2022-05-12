//공지사항 및 댕냥이자랑 게시판

//이벤트 리스너 - 공지사항 등록 

//자랑 수정
$("#btn-updateJarang").click((event) => {
    update();
});

//자랑 수정
async function update() {
    let id = $("#userId").val();

    console.log(id);

    let fileForm = $("#fileForm")[0];

    // console.log(fileForm);

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

    // 썸네일 파일 이미지 확인
    $("#img-input").change((event)=>{
        let f = event.target.files[0];

        if(!f.type.match("image.*")){
            alert("이미지를 선택해주세요!")
            return ;
        }
    });
}