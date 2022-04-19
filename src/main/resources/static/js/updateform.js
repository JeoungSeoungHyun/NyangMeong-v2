$("#btn_update").click(()=>{
    update();
});

    async function update() {
        let id = $("#id").val();
        let updateDto = {
            password: $("#password").val(),
            email: $("#email").val(),
            addr: $("#addr").val()
        }
    
        let response = await fetch(`/s/api/user/${id}`, {
            method: "PUT",
            body: JSON.stringify(updateDto),
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            }
        });
        let responseParse = await response.json();
    
        if (responseParse.code == 1) {
            alert("업데이트 성공");
            location.href = `/s/user/${id}`;
        } else {
            alert("업데이트 실패");
        }
    }

