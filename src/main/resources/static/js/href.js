    $("#cafe_a").click(() => {
        listGo("식음료");
    });
    $("#hotel_a").click(() => {
        listGo("숙박");
    });
    $("#spot_a").click(() => {
        listGo("관광지");
    });
    $("#activity_a").click(() => {
        listGo("체험");
    });
async function listGo(partName) {
    let response = `/place/search?partName=${partName}`;
    location.href=response;
}

async function pageGo(Name) {
    let response = `/${Name}`;
    location.href=response;
}

function linkListGo(partName) {
    let response = `/place/search?partName=${partName}`;
    location.href=response;
}

async function userDetailGo(Name) {
    let response = `/user/${Name}`;
    location.href=response;
}