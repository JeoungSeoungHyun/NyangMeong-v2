// 회원 정보 수정 버튼 이벤트 리스너
$("#btn-update").click(()=>{
    update();
});

// 반려동물 수정 버튼 이벤트 리스너
$("#btn-pet-update").click(()=>{
    petUpdate();
});

// 회원 정보 수정 함수
async function update() {
    let id = $("#id").val();
    let updateDto = {
        userName: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val()
    }

    let response = await fetch(`/s/api/user/${id}/update`, {
        method: "PUT",
        body: JSON.stringify(updateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }

    });

    // console.log(response);

    let responseParse = await response.json();
    // console.log(responseParse);

    if (responseParse.code == 1) {
        alert("업데이트 성공");
        location.href = `/s/user/${id}/update-form`;
    } else {
        alert("업데이트 실패");
    }
}

// 회원 탈퇴 함수
async function deleteUser(userId) {
    let response = await fetch(`/s/api/user/${userId}/delete`, {
        method: "DELETE",
    });
    // console.log(response);
    let responseParse = await response.json();
    // console.log(responseParse);
    if (responseParse.code == 1) {
        alert("회원탈퇴 성공");
        location.href = "/";
    } else {
        alert("회원탈퇴 실패 : " + responseParse.msg);
    }
}

// 반려동물 정보 수정 함수
async function petUpdate() {
    let id = $("#id").val();
    let petId = $("#pet-id").val();
    let updateDto = {
        petName: $("#pet-name").val(),
        petGender: $("#pet-gender").val(),
        petAge: $("#pet-age").val(),
        petSpices: $("#pet-spices").val()
    }

    let response = await fetch(`/s/api/pet/${petId}/update`, {
        method: "PUT",
        body: JSON.stringify(updateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });

    if (response.status == 200) {
        alert("업데이트 성공");
        location.href = `/s/user/${id}/update-form`;
    } else {
        alert("업데이트 실패");
    }
}