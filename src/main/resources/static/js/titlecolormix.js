    var marker = new naver.maps.Marker(markerOptions);
    // 카테고리 색 변화
    let changeSecondary = (category)=>{
        if($(`#box-${category}`).hasClass("m_bg_primary")==true){
            $(`#box-${category}`).removeClass("m_bg_primary");
            $(`#box-${category}`).addClass("m_bg_secondary");
        };
    }
    let changePrimary = (category)=>{
        $(`#box-${category}`).removeClass("m_bg_secondary");
        $(`#box-${category}`).addClass("m_bg_primary");
    }
    let changeColorBox = () =>{
        let list = ["숙박","관광지","체험","동물병원","식음료"];
        for(category of list){
            changeSecondary(category);
        }
        let title = $("#title").html();
        changePrimary(`${title}`);
    };
    changeColorBox();