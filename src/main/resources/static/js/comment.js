async function deleteComment(commentId) {
    let response = await fetch(`/s/api/comment/${commentId}`, {
        method: "DELETE"
    });
    let responseParse = await response.json();
    if (responseParse.code == 1) {
        alert("삭제 성공");
        location.reload(); // ajax + ssr
    } else {
        alert("삭제 실패");
    }
}