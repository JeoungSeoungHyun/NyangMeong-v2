// 1. 이미지 클릭 시 파일 등록하는 input 태그 나오게 설정
$("#profile-img-btn").click(() => {
    $("#profile-img-input").click(); // 파일 선택창이 뜸
});

// 2. 파일을 선택하면
$("#profile-img-input").change((event) => {
    profileImgUpdate(event);
});

// 3. fetch해서 프로필 사진 변경하기
async function profileImgUpdate(event) {
    // image/png 이런식의 파일임.
    let f = event.target.files[0];
    
    if (!f.type.match("image.*")) {
        alert("이미지를 선택해주세요!");
        return;
    }

    let userId = $("#userId").val();
    // Multipart File => header 가 필요 없음
    // multipart/form-data 로 파일을 전송하는 것이 가장 편하다.
    // form 태그 자바스크립트 객체 찾기 => fileForm
    // form 태그 key:value 데이터 변환 => formData
    let fileForm = $("#file-form")[0];
    let formData = new FormData(fileForm);

    let response = await fetch(`/s/api/user/${userId}/profile-img`, {
        method: "put",
        body: formData
    });

    if (response.status == 200) {
        // alert("프로필 변경 성공!")
        imgPreview(event, f);
    } else {
        alert("프로필 변경에 실패하였습니다");
    }
}

// 4. 변경된 프로필 사진 미리보기
function imgPreview(event, f) {
    let reader = new FileReader();
    // 콜백 (파일 이미지가 객체화 되면!!)
    reader.onload = (event) => {
        // console.log(event.target.result);
        $("#profile-img-btn").attr("src", event.target.result);
    }
    reader.readAsDataURL(f); // 비동기 실행(I/O)
}