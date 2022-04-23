package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.image.PublicDataImage;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.service.PlaceService;
import spring.project.nyangmong.util.ChooseImg;
import spring.project.nyangmong.util.ContentSeqDownload;
import spring.project.nyangmong.util.OptionChange;
import spring.project.nyangmong.web.dto.craw.PlaceDto;
import spring.project.nyangmong.web.dto.craw.Result;
import spring.project.nyangmong.web.dto.places.ImageListDto;
import spring.project.nyangmong.web.dto.places.InfoRespDto;
import spring.project.nyangmong.web.dto.places.PlaceListDto;
import spring.project.nyangmong.web.dto.places.PlacesOptionDto;

@RequiredArgsConstructor
@Controller
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceRepository placeRepository;
    private final ImageRepository imageRepository;
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

    @GetMapping("test/place/list")
    public @ResponseBody Page<Places> listTest(@RequestParam(defaultValue = "0") Integer page) {
        PageRequest pq = PageRequest.of(page, 24);
        return placeRepository.searchPartName("식음료", pq);
    }

    @GetMapping("/place/search")
    public String searchPartName(@RequestParam String partName, Model model) {
        long count = placeRepository.countPartName(partName);
        List<Places> places = placeService.분류검색(partName);
        if (!partName.equals("동물병원")) {
            List<InfoRespDto> InfoListRespDto = new ArrayList<>();
            List<String> placesImages = ChooseImg.imgList(places);
            for (int i = 0; i < places.size(); i++) {
                InfoRespDto infoRespDto = new InfoRespDto(places.get(i), placesImages.get(i));
                InfoListRespDto.add(infoRespDto);
            }
            model.addAttribute("InfoListRespDto", InfoListRespDto);
        } else {
            model.addAttribute("InfoListRespDto", places);
        }
        // long count = placeRepository.countPartName(partName);
        // model.addAttribute("count", count);
        // PlaceListDto placeDto = new PlaceListDto();
        // placeDto.setPlaces(places);
        // placeDto.setTitle(places.get(i).getTitle());
        // placeDto.setAddress(places.get(i).getAddress());
        // model.addAttribute("pdto", placeDto);
        // model.addAttribute("places", places);
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
        if (keyword.equals("")) {
            long count = placeRepository.count();
            PageRequest pq = PageRequest.of(page, 16);
            model.addAttribute("nextPage", page + 1);
            model.addAttribute("previewPage", page - 1);
            model.addAttribute("count", count);
            model.addAttribute("places", placeRepository.findAll(pq));
            return "pages/place/search";
        }

        List<Places> places = placeRepository.searchPlaces(keyword);
        PlaceListDto placeDto = new PlaceListDto();
        placeDto.setPlaces(places);
        for (int i = 0; i < places.size(); i++) {
            placeDto.setTitle(places.get(i).getTitle());
            placeDto.setAddress(places.get(i).getAddress());
        }

        model.addAttribute("pdto", placeDto);
        model.addAttribute("places", places);
        return "pages/place/outlineList";

    }

    // 데이터베이스 받아오는 url 들어갈때 시간이 많이 걸립니다.
    // 모두 받아오고 둘러보기로 이동.
    @GetMapping("/list")
    public String download(Model model) {

        for (int k = 1; k < 6; k++) {

            List<Integer> contentSeqList = new ContentSeqDownload().contentSeqDown(k);

            // System.out.println(contentSeqList);

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

        return "redirect:/s/admin/adminMain";

    }
}