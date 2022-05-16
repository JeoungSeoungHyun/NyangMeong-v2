package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.fav.Fav;
import spring.project.nyangmong.domain.fav.FavRepository;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.image.PublicDataImage;
import spring.project.nyangmong.domain.placelikes.PlaceLikes;
import spring.project.nyangmong.domain.placelikes.PlaceLikesRepository;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.PlaceService;
import spring.project.nyangmong.util.ChooseImg;
import spring.project.nyangmong.util.ContentSeqDownload;
import spring.project.nyangmong.util.OptionChange;
import spring.project.nyangmong.web.dto.craw.PlaceDto;
import spring.project.nyangmong.web.dto.craw.Result;
import spring.project.nyangmong.web.dto.places.ImageListDto;
import spring.project.nyangmong.web.dto.places.InfoRespDto;
import spring.project.nyangmong.web.dto.places.PlacesOptionDto;

@RequiredArgsConstructor
@Controller
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceRepository placeRepository;
    private final ImageRepository imageRepository;
    private final FavRepository favRepository;
    private final PlaceLikesRepository placeLikesRepository;
    private final HttpSession session;
    private final OptionChange change;

    // 상세보기

    // @GetMapping("/place/{contentSeq}")
    // public String detailPlaces(@PathVariable Integer contentSeq, Model model) {
    // Places places = placeService.상세보기(contentSeq);
    // List<PublicDataImage> imageList =
    // imageRepository.ImagecontentSeq(contentSeq);
    // ImageListDto dto = new ImageListDto();
    // dto.setPublicDataImage(imageList);

    // // List<PlaceDto> placesDto = new ArrayList<>();

    // model.addAttribute("imageList", dto);
    // model.addAttribute("places", places);
    // return "pages/place/placeDetail";
    // }

    @GetMapping("/place/{contentSeq}")
    public String detailPlaces(@PathVariable Integer contentSeq, Model model) {
        User principal = (User) session.getAttribute("principal");
        Places places = placeService.상세보기(contentSeq);
        List<PublicDataImage> imageList = imageRepository.ImagecontentSeq(contentSeq);
        ImageListDto dto = new ImageListDto();
        dto.setPublicDataImage(imageList);

        PlacesOptionDto option = new PlacesOptionDto();
        option.setBathFlagShow(placeService.옵션표시(places.getBathFlag()));
        option.setParkingFlagShow(placeService.옵션표시(places.getParkingFlag()));
        option.setEntranceFlagShow(placeService.옵션표시(places.getEntranceFlag()));
        option.setEmergencyFlagShow(placeService.옵션표시(places.getEmergencyFlag()));
        option.setProvisionFlagShow(placeService.옵션표시(places.getProvisionFlag()));
        option.setInOutFlagShow(placeService.옵션표시(places.getInOutFlag()));
        option.setPetFlagShow(placeService.옵션표시(places.getPetFlag()));
        // List<PlaceDto> placesDto = new ArrayList<>();
        Integer favId = null;
        Integer placeLikesId = null;
        if (principal != null) {
            Optional<Fav> favOp = favRepository.mFindUserIdAndPlacesId(principal.getId(), places.getContentSeq());
            if (favOp.isPresent()) {
                favId = favOp.get().getId();
            }
            Optional<PlaceLikes> placeLikesOp = placeLikesRepository.mFindUserIdAndPlacesId(principal.getId(),
                    places.getContentSeq());
            if (placeLikesOp.isPresent()) {
                placeLikesId = placeLikesOp.get().getId();
            }
        }
        System.out.println("즐찾 : " + favId);
        System.out.println("좋아요 : " + placeLikesId);
        model.addAttribute("favId", favId);
        model.addAttribute("placeLikesId", placeLikesId);
        model.addAttribute("option", option);
        model.addAttribute("imageList", dto);
        model.addAttribute("places", places);
        return "pages/place/placeDetail";
    }

    @GetMapping("/outline")
    public String outline(Model model) {
        // 총 카운트
        long count = placeRepository.count();
        model.addAttribute("count", count);

        // 숙박 TOP 4 정보
        long countHotel = placeRepository.countPartName("숙박");
        List<Places> placesHotel = placeRepository.placeTop4("숙박");
        List<String> placesHotelImages = ChooseImg.imgList(placesHotel);
        List<InfoRespDto> HotelInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesHotel.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesHotel.get(i), placesHotelImages.get(i));
            HotelInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("HotelInfoListRespDto", HotelInfoListRespDto);
        model.addAttribute("countHotel", countHotel);
        model.addAttribute("placesHotel", placesHotel);

        // 식음료 TOP 4 정보
        long countCafe = placeRepository.countPartName("식음료");
        List<Places> placesCafe = placeRepository.placeTop4("식음료");
        List<String> placesCafeImages = ChooseImg.imgList(placesCafe);
        List<InfoRespDto> CafeInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesCafe.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesCafe.get(i), placesCafeImages.get(i));
            CafeInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("CafeInfoListRespDto", CafeInfoListRespDto);
        model.addAttribute("countCafe", countCafe);
        model.addAttribute("placesCafe", placesCafe);

        // 관광지 TOP 4 정보
        long countSpot = placeRepository.countPartName("관광지");
        List<Places> placesSpot = placeRepository.placeTop4("관광지");
        List<String> placesSpotImages = ChooseImg.imgList(placesSpot);
        List<InfoRespDto> SpotInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesSpot.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesSpot.get(i), placesSpotImages.get(i));
            SpotInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("SpotInfoListRespDto", SpotInfoListRespDto);
        model.addAttribute("countSpot", countSpot);
        model.addAttribute("placesSpot", placesSpot);
        // 체험 TOP 4 정보
        long countAct = placeRepository.countPartName("체험");
        List<Places> placesActivity = placeRepository.placeTop4("체험");
        List<String> placesAcitivityImages = ChooseImg.imgList(placesActivity);
        List<InfoRespDto> ActivityInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesActivity.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesActivity.get(i), placesAcitivityImages.get(i));
            ActivityInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("ActivityInfoListRespDto", ActivityInfoListRespDto);
        model.addAttribute("countAct", countAct);
        model.addAttribute("placesActivity", placesActivity);

        // 동물병원 TOP 4 정보
        long countHos = placeRepository.countPartName("동물병원");
        List<Places> placesHospital = placeRepository.placeTop4("동물병원");
        model.addAttribute("countHos", countHos);
        model.addAttribute("placesHospital", placesHospital);
        return "/pages/place/outlineList";
    }

    @GetMapping("/test/place/list")
    public @ResponseBody Page<Places> listTest(@RequestParam(defaultValue = "0") Integer page) {
        PageRequest pq = PageRequest.of(page, 24);
        return placeRepository.searchPlaces("원주", pq);
    }

    @GetMapping("/place/search")
    public String searchPartName(@RequestParam String partName,
            @RequestParam(defaultValue = "0") Integer page,
            Model model) {
        // 페이징 위한 페이지 리퀘스트
        PageRequest pq = PageRequest.of(page, 24);
        // 카테고리별 개수
        long count = placeRepository.countPartName(partName);
        // 카테고리별 페이징
        Page<Places> placesPaging = placeService.분류검색(partName, pq);
        // 카테고리별 플레이스
        List<Places> places = placesPaging.getContent();
        // 플레이스와 대표이미지 하나를 리스트로 저장하여 전달하는 Dto
        List<InfoRespDto> InfoListRespDto = new ArrayList<>();
        // 동물병원은 대표이미지가 없으니 제외
        if (!partName.equals("동물병원")) {
            // 플레이스의 이미지들 중 첫번째 이미지만 가져와 리스트로 만든다.
            List<String> placesImages = ChooseImg.imgList(places);
            // Dto에 플레이스와 해당하는 대표이미지를 연관시켜 Dto에 담는다.
            for (int i = 0; i < places.size(); i++) {
                InfoRespDto infoRespDto = new InfoRespDto(places.get(i), placesImages.get(i));
                InfoListRespDto.add(infoRespDto);
            }
            model.addAttribute("InfoListRespDto", InfoListRespDto);
            model.addAttribute("paging", placesPaging);
        } else {
            model.addAttribute("InfoListRespDto", places);
            model.addAttribute("paging", placesPaging);
        }
        // 이전 페이지 이동 버튼 위한 model
        model.addAttribute("prevPage", page - 1);
        // 다음 페이지 이동 버튼 위한 model
        model.addAttribute("nextPage", page + 1);
        // 버튼 이동시 필요한 partName 전달 위한 model
        model.addAttribute("partName", partName);
        // 카테고리별 개수 전달 위한 model
        model.addAttribute("count", count);
        if (partName.equals("관광지")) {
            return "pages/place/spotList";
        } else if (partName.equals("동물병원")) {
            return "pages/place/hospitalList";
        } else if (partName.equals("식음료")) {
            return "pages/place/cafeList";
        } else if (partName.equals("체험")) {
            return "pages/place/activityList";
        } else if (partName.equals("숙박")) {
            return "pages/place/hotelList";
        } else {
            throw new RuntimeException("해당 관광정보를 찾을 수 없습니다");
        }
    }

    @GetMapping("/outline/search")
    public String searchOutLine(@RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") Integer page, Model model) {
        PageRequest pq = PageRequest.of(page, 16);
        if (keyword.equals("")) {
            long count = placeRepository.count();
            model.addAttribute("nextPage", page + 1);
            model.addAttribute("previewPage", page - 1);
            model.addAttribute("count", count);
            model.addAttribute("places", placeRepository.findAll(pq));
            return "pages/place/search";
        }
        Page<Places> placesPaging = placeRepository.searchPlaces(keyword, pq);
        long count = placesPaging.getTotalElements();
        model.addAttribute("places", placesPaging);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("previewPage", page - 1);
        model.addAttribute("count", count);
        return "pages/place/search";

    }

    // 데이터베이스 받아오는 url 들어갈때 시간이 많이 걸립니다.
    // 모두 받아오고 둘러보기로 이동.
    @GetMapping("/list")
    public String download(Model model) {

        for (int k = 1; k < 6; k++) {

            List<Integer> contentSeqList = new ContentSeqDownload().contentSeqDown(k);

            System.out.println(contentSeqList);

            RestTemplate rt = new RestTemplate();
            for (int j = 0; j < contentSeqList.size(); j++) {

                String url = "http://www.pettravel.kr/api/detailSeqPart.do?partCode=PC0" + k + "&contentNum="
                        + contentSeqList.get(j);

                // System.out.println("url : " + url);

                Result[] responseDtos = rt.getForObject(url, Result[].class);

                Result responseDto = responseDtos[0];

                // 다운 받은 것
                PlaceDto placeDto = responseDto.getResultList(); // 한건
                // System.out.println("Dto : " + placeDto);
                // Place 엔티티에 옮김
                Places place = Places.builder()
                        .contentSeq(placeDto.getContentSeq())
                        .areaName(placeDto.getAreaName())
                        .partName(placeDto.getPartName())
                        .title(placeDto.getTitle())
                        .keyword(placeDto.getKeyword())
                        .address(placeDto.getAddress())
                        .tel(placeDto.getTel())
                        .latitude(placeDto.getLatitude())
                        .longitude(placeDto.getLongitude())
                        .usedTime(placeDto.getUsedTime())
                        .homePage(placeDto.getHomePage())
                        .content(placeDto.getContent())
                        .provisionSupply(placeDto.getProvisionSupply())
                        .petFacility(placeDto.getPetFacility())
                        .restaurant(placeDto.getRestaurant())
                        .parkingLog(placeDto.getParkingLog())
                        .mainFacility(placeDto.getMainFacility())
                        .usedCost(placeDto.getUsedCost())
                        .policyCautions(placeDto.getPolicyCautions())
                        .emergencyResponse(placeDto.getEmergencyResponse())
                        .memo(placeDto.getMemo())
                        .bathFlag(new OptionChange().change(placeDto.getBathFlag()))
                        .provisionFlag(new OptionChange().change(placeDto.getProvisionFlag()))
                        .petFlag(new OptionChange().change(placeDto.getPetFlag()))
                        .petWeight(placeDto.getPetWeight())
                        .petBreed(placeDto.getPetBreed())
                        .emergencyFlag(new OptionChange().change(placeDto.getEmergencyFlag()))
                        .entranceFlag(new OptionChange().change(placeDto.getEntranceFlag()))
                        .parkingFlag(new OptionChange().change(placeDto.getParkingFlag()))
                        .inOutFlag(new OptionChange().change(placeDto.getInOutFlag()))
                        // 추가
                        .build();

                // System.out.println(place);

                Places placeEntity = placeRepository.save(place); // id 찾으려구

                // PC05 => 병원 이미지 하나도 없어서 이미지 저장 안해야됨 => 놔두면 nullpointerexception 발생
                if (k != 5) {

                    List<PublicDataImage> images = new ArrayList<>();
                    for (int i = 0; i < placeDto.getImageList().size(); i++) {
                        PublicDataImage image = PublicDataImage.builder()
                                .imgurl(placeDto.getImageList().get(i).getImage())
                                .places(placeEntity) // <- placeEntity
                                .build();
                        images.add(image);
                    }

                    imageRepository.saveAll(images);
                }

            }
        }
        return "redirect:/";
    }

    // 좋아요
    @PostMapping("/s/api/places/{placesId}/like")
    public ResponseEntity<?> boardsLike(@PathVariable Integer placesId) {
        User principal = (User) session.getAttribute("principal");
        PlaceLikes placeLikesEntity = placeService.좋아요(placesId, principal);
        Integer placeLikesId = placeLikesEntity.getId();
        return new ResponseEntity<>(placeLikesId, HttpStatus.CREATED);
    }

    // 좋아요 취소
    @DeleteMapping("/s/api/places/{placesId}/like/{placeLikesId}")
    public ResponseEntity<?> boardsUnLike(@PathVariable Integer placeLikesId) {
        User principal = (User) session.getAttribute("principal");
        boolean result = placeService.좋아요취소(placeLikesId, principal);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 즐겨찾기
    @PostMapping("/s/api/places/{placesId}/fav")
    public ResponseEntity<?> boardsFav(@PathVariable Integer placesId) {
        User principal = (User) session.getAttribute("principal");
        Fav favEntity = placeService.즐겨찾기(placesId, principal);
        Integer favId = favEntity.getId();
        return new ResponseEntity<>(favId, HttpStatus.CREATED);
    }

    // 즐겨찾기 취소
    @DeleteMapping("/s/api/places/{placesId}/fav/{favId}")
    public ResponseEntity<?> boardsUnFav(@PathVariable Integer favId) {
        User principal = (User) session.getAttribute("principal");
        boolean result = placeService.즐겨찾기취소(favId, principal);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}