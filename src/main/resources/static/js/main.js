        // 이미지 슬라이드 시작

        $(document).ready(function() {
            var infoToast = document.getElementById('infoToast');
            infoToast.addEventListener('hidden.bs.toast', function() {
                //roll-in-blurred-right
                $("#gitBtn").addClass("jello-horizontal");
            });
            var toast = new bootstrap.Toast(infoToast);
            toast.show();
        });

        $('#vertical-carousel').bind('mousewheel DOMMouseScroll', function(e) {
            if (e.originalEvent.wheelDelta > 0 || e.originalEvent.detail < 0) {
                $(this).carousel('prev');
            } else {
                $(this).carousel('next');
            }
            e.preventDefault();
        });

        $("#vertical-carousel").on("touchstart", function(event) {
            var yTouchPointStart = event.originalEvent.touches[0].pageY;
            $(this).on("touchmove", function(event) {
                var yTouchPointEnd = event.originalEvent.touches[0].pageY;
                if (Math.floor(yTouchPointStart - yTouchPointEnd) > 1) {
                    $(".carousel").carousel('next');
                } else if (Math.floor(yTouchPointStart - yTouchPointEnd) < -1) {
                    $(".carousel").carousel('prev');
                }
            });
            $(".carousel").on("touchend", function() {
                $(this).off("touchmove");
            });
            event.preventDefault();
        });

    // 이미지 슬라이드 끝


    //지도 옵션
     var mapOptions = {
            center: new naver.maps.LatLng(38.1306367046835, 127.982807156954),
            zoom: 9,

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
        <form id="form" onsubmit="return false">
            <div class=" text-center m_bg_secondary m_box_lg_up_round" style="border-top-right-radius: 25px;">
                <div class="m_ph_sm">
                    <h3  class="m_txt_title">검색</h3>
                </div>
                <input id="keyword" name="keyword" class="form-control m_ph_sm container" type="text" placeholder="주소 또는 명칭으로 검색하실 수 있습니다" />
          
                <div class="d-flex m_box_search_category">
                    <a href="javascript:linkListGo('관광지')">
                        <div>
                            <i class="fa-solid fa-flag"></i>
                        </div>
                        <div>관광</div>
                    </a>
                    <a href="javascript:linkListGo('숙박')">
                        <div>
                            <i class="fa-solid fa-bed"></i>
                        </div>
                        <div>숙박</div>
                    </a>
                    <a href="javascript:linkListGo('식음료')">
                        <div>
                            <i class="fa-solid fa-mug-hot"></i>
                        </div>
                        <div>식음료</div>
                    </a>
                    <a href="javascript:linkListGo('체험')">
                        <div>
                            <i class="fa-solid fa-volleyball"></i>
                        </div>
                        <div>체험</div>
                    </a>
                    <a href="javascript:linkListGo('동물병원')">
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
                    <input class="form-check-input" type="checkbox" id="spotOption" name="option" value="spot" onclick=totalSearch('spot')  checked>
                    <label class="form-check-label m_font_lg">관광지</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="hotelOption" name="option" value="hotel" onclick=totalSearch('hotel') checked>
                    <label class="form-check-label m_font_lg">숙박</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="cafeOption" name="option" value="cafe" onclick=totalSearch('cafe')  checked>
                    <label class="form-check-label m_font_lg">식음료</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="activityOption" name="option" value="activity" onclick=totalSearch('activity')  checked>
                    <label class="form-check-label m_font_lg">체험</label>
                </div>
                <div class="form-check m_pb_sm">
                    <input class="form-check-input" type="checkbox" id="hospitalOption" name="option" value="hospital" onclick=totalSearch('hospital')  checked>
                    <label class="form-check-label m_font_lg">동물병원</label>
                </div>
            </div>
            </div>
        </form>
    </div>`;

        var customControl = new naver.maps.CustomControl(locationBtnHtml, {
            position: naver.maps.Position.TOP_LEFT
        });

        // 지도 커스텀 컨트롤러 검색 및 마커 표시 위한 오브젝트 생성
        let searchOption = {
            keyword: "",
            cafe: true,
            hotel: true,
            activity: true,
            hospital: true,
            spot: true
        }

        // 지도 생성
        var map = new naver.maps.Map('map', mapOptions);

        // 지도에 커스텀컨트롤러 붙이기
        naver.maps.Event.once(map, 'init', function() {
            customControl.setMap(map);

            naver.maps.Event.addDOMListener(customControl.getElement(), 'click', function() {
                for(marker of markers){
                    marker.setMap(null);
                }
                // search(customControl.getElement);
                searchOption.keyword = $("#keyword").val();
                // 체크박스 체크 확인 체크=true / 체크해제=false
                if(!$("#hotelOption").is(':checked')){
                    searchOption.hotel = false;
                } else{
                    searchOption.hotel = true;
                }
                if(!$("#cafeOption").is(':checked')){
                    searchOption.cafe = false;
                } else {
                    searchOption.cafe = true;
                }
                if(!$("#activityOption").is(':checked')){
                    searchOption.activity = false;
                } else {
                    searchOption.activity = true;
                }
                if(!$("#spotOption").is(':checked')){
                    searchOption.spot = false;
                } else {
                    searchOption.spot = true;
                }
                if(!$("#hospitalOption").is(':checked')){
                    searchOption.hospital = false;
                } else{
                    searchOption.hospital = true;
                }
                totalSearch();
            });
            naver.maps.Event.addDOMListener(customControl.getElement(), 'keyup', ()=>{
                $("#keyword").keyup((event)=>{
                    if(event.keyCode == 13){
                        for(marker of markers){
                            marker.setMap(null);
                        }
                        searchOption.keyword = $("#keyword").val();
                        // 체크박스 체크 확인 체크=true / 체크해제=false
                        if(!$("#hotelOption").is(':checked')){
                            searchOption.hotel = false;
                        } else{
                            searchOption.hotel = true;
                        }
                        if(!$("#cafeOption").is(':checked')){
                            searchOption.cafe = false;
                        } else {
                            searchOption.cafe = true;
                        }
                        if(!$("#activityOption").is(':checked')){
                            searchOption.activity = false;
                        } else {
                            searchOption.activity = true;
                        }
                        if(!$("#spotOption").is(':checked')){
                            searchOption.spot = false;
                        } else {
                            searchOption.spot = true;
                        }
                        if(!$("#hospitalOption").is(':checked')){
                            searchOption.hospital = false;
                        } else{
                            searchOption.hospital = true;
                        }
                        totalSearch();
                    }
                });
                
            });

        });

       // 마커 저장 변수
       let markers=[];
        
        // 좌표 요청 함수
        let loadPoints = async() =>{ 

            // 이전의 마커들을 저장한 변수에 for문을 돌려서 이전의 마커들 삭제  
            for(marker of markers){
                marker.setMap(null);
            }
            
            let response = await fetch(`/api/place/points`); // 패치 요청으로 좌표를 배열로 받아온다
            
            let responseParse = await response.json();
            
            let points = responseParse; // 응답 받은 데이터를 json으로 변환 하여 변수에 저장
            
            marker.setMap(null);
            
            makeMarker(points); // 좌표를 만드는 함수로 전달
            
        };      
        

         // 좌표를 만드는 함수
        let makeMarker = (points) => { 

            // 좌표값으로 마커 생성
            for (point of points) {
                var markerOptions = {
                    position: new naver.maps.LatLng(point[0], point[1]),
                    map: map,
                    icon: './img/pin_default.png'
                };
                var marker = new naver.maps.Marker(markerOptions);
                markers.push(marker);
                // 마커 리스너 생성 (마커와 지도 클릭이 별개로 구분되어 필요)
                naver.maps.Event.addListener(marker, 'click', function(event) {
                    x = event.coord.x;
                    y = event.coord.y;
                    map.setCenter(new naver.maps.LatLng(y, x));
                    // console.log(event.coord);
                    map.setZoom(map.zoom + 3);
                });
            };

        };

        loadPoints();

        //좋아요 탭

        $("#mytabs>ul>li>a").each(function(i) {
            $(this).attr("href", "#mytab" + i)
        })
        $("#mytabs>div>div").each(function(i) {
            $(this).attr("id", "mytab" + i)
        })

    $(document).ready(function () {

        $('ul.tabs li').click(function () {
            var tab_id = $(this).attr('data-tab');

            $('ul.tabs li').removeClass('current');
            $('.tab-content').removeClass('current');

            $(this).addClass('current');
            $("#" + tab_id).addClass('current');
        })
        }) 

        // 지도검색 fetch 요청 함수
        let totalSearch = async()=>{

            let response = await fetch('/api/place/search',{
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=utf-8"
                },
                body: JSON.stringify(searchOption)
            });
 
            let responseParse = await response.json();

            // console.log(responseParse);
            
            let points = responseParse; // 응답 받은 데이터를 json으로 변환 하여 변수에 저장

            //  console.log(points);
     
             makeMarker(points); // 좌표를 만드는 함수로 전달
        };

      