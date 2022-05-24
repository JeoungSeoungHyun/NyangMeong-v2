//게시글 폼 크기
    $("#summernote").summernote({
        height: 500
    });
    
//게시글 수정버튼 클릭 이벤트
$("#btn-updateJarang").click((event) => {
    update();
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

// 썸네일 파일 이미지 확인
$("#img-input").change((event)=>{
    let f = event.target.files[0];

    if(!f.type.match("image.*")){
        alert("이미지를 선택해주세요!")
        return ;
    }
});

//페이징 disable처리
 let checkAuthBtn = () => {
        let first = $("#first").val();
        let last = $("#last").val();

        console.log(first);
        console.log(last);

        if (first == "true") {
            $("#prev").addClass("disabled");
            $("#next").removeClass("disabled");
        } else if (last == "true") {
            $("#prev").removeClass("disabled");
            $("#next").addClass("disabled");
        } else {
            $("#prev").removeClass("disabled");
            $("#next").removeClass("disabled");
        }
    };
    checkAuthBtn();
   
