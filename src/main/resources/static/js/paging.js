//페이징 기법

    let page = 0;
    $("#btn-next").click(() => {
        page++;
        $("#post-box").empty();
        loading();
    });
    $("#btn-prev").click(() => {
        page--;
        $("#post-box").empty();
        loading();
    });
    // 페이징 완료!!
    function pagingDisable(responseParse) {
        if (responseParse.data.first == true) {
            $("#li-prev").addClass("disabled");
            $("#li-next").removeClass("disabled");
        } else if (responseParse.data.last == true) {
            $("#li-next").addClass("disabled");
            $("#li-prev").removeClass("disabled");
        }
    }
    async function loading() {
        let response = await fetch(`/api/post?page=${page}`);
        let responseParse = await response.json();
        if (responseParse.code == 1) {
            pagingDisable(responseParse); // 페이징 버튼 활성화 처리
            //console.log(responseParse);
            responseParse.data.content.forEach((post) => {
                //console.log(post);
                $("#post-box").append(postItemRender(post));
            });
        } else {
            alert("잘못된 요청입니다");
        }
    }
    function postItemRender(post) {
        return `<div class="card mb-3">
            <div class="card-body">
                <h4 class="card-title">${post.title}</h4>
                <a href="/post/${post.id}" class="btn btn-secondary">상세보기</a>
            </div>
        </div>`;
    }
    loading();
