$(document).ready(function(){
    //최상단 체크박스 클릭
    $("#checkall").click(function(){
        //클릭되었으면
        if($("#checkall").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[id=chk]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[id=chk]").prop("checked",false);
        }
    })
})


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