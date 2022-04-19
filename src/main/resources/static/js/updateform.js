// 회원 정보 수정 버튼 이벤트 리스너
$("#btn-update").click(()=>{
    update();
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
        console.log(response);

        let responseParse = await response.json();
        console.log(responseParse);
    
        if (responseParse.code == 1) {
            alert("업데이트 성공");
            location.href = `/s/user/${id}/update-form`;
        } else {
            alert("업데이트 실패");
        }
    }

