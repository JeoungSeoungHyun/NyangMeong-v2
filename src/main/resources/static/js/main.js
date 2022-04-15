        //지도 옵션
        var mapOptions = {
            center: new naver.maps.LatLng(35.159665, 129.060447),
            zoom: 15,
            // 커스텀 컨트롤러 생성


            // 맵 타입 컨트롤러 생성
            mapTypeControl: true,

            // 줌 컨트롤러 생성
            zoomControl: true,
            zoomControlOptions: {
                position: naver.maps.Position.RIGHT_CENTER
            }
        };
        // 커스텀 컨트롤러 생성
        var locationBtnHtml = ` <div class="m_box_search_map">
        <div class="text-center m_bg_secondary m_box_lg_up_round" style="border-top-right-radius: 25px;">
            <div class="m_ph_sm">
                <h3 class="m_txt_title">검색</h3>
            </div>
            <form class="container">
                <input class="form-control m_ph_sm" type="text" placeholder="주소 또는 명칭으로 검색하실 수 있습니다">
            </form>
            <div class="d-flex m_box_search_category">
                <a href="#">
                    <div>
                        <i class="fa-solid fa-flag"></i>
                    </div>
                    <div>관광</div>
                </a>
                <a href="#">
                    <div>
                        <i class="fa-solid fa-bed"></i>
                    </div>
                    <div>숙박</div>
                </a>
                <a href="#">
                    <div>
                        <i class="fa-solid fa-mug-hot"></i>
                    </div>
                    <div>식음료</div>
                </a>
                <a href="#">
                    <div>
                        <i class="fa-solid fa-volleyball"></i>
                    </div>
                    <div>체험</div>
                </a>
                <a href="#">
                    <div>
                        <i class="fa-solid fa-house-chimney-medical"></i>
                    </div>
                    <div>동물병원</div>
                </a>
            </div>
        </div>
        <div class = "m_box_check_category">
            <div class="m_pl_sm hr-sect m_txt_lg m_txt_title">주변 탐색</div>
            <div class="m_pl_md" >
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="check1" name="option1" value="something" checked>
                    <label class="form-check-label m_font_lg">관광지</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="check1" name="option2" value="something" checked>
                    <label class="form-check-label m_font_lg">숙박</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="check1" name="option3" value="something" checked>
                    <label class="form-check-label m_font_lg">식음료</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="check1" name="option4" value="something" checked>
                    <label class="form-check-label m_font_lg">체험</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="check1" name="option5" value="something" checked>
                    <label class="form-check-label m_font_lg">동물병원</label>
                </div>
            </div>
        </div>
    </div>`;

        var customControl = new naver.maps.CustomControl(locationBtnHtml, {
            position: naver.maps.Position.TOP_LEFT
        });

        // 지도 생성
        var map = new naver.maps.Map('map', mapOptions);

        // 지도에 커스텀컨트롤러 붙이기
        naver.maps.Event.once(map, 'init', function() {
            customControl.setMap(map);
        });

        // 마커 생성
        var markerOptions = {
            position: new naver.maps.LatLng(35.159665, 129.060447),
            map: map,
            icon: './img/pin_default.png'
        };
        var marker = new naver.maps.Marker(markerOptions);