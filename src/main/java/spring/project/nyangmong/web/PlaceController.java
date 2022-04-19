package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.image.PublicDataImage;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.service.PlaceService;
import spring.project.nyangmong.util.ContentSeqDownload;
import spring.project.nyangmong.util.OptionChange;
import spring.project.nyangmong.web.dto.craw.ImageDto;
import spring.project.nyangmong.web.dto.craw.PlaceDto;
import spring.project.nyangmong.web.dto.craw.Result;
import spring.project.nyangmong.web.dto.places.ImageListDto;
import spring.project.nyangmong.web.dto.places.PlaceListDto;
import spring.project.nyangmong.web.dto.places.PlaceMapDto;
import spring.project.nyangmong.web.dto.places.PlacesOptionDto;

@RequiredArgsConstructor
@Controller
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceRepository placeRepository;
    private final ImageRepository imageRepository;
    private final HttpSession session;
    private final OptionChange change;

    // 맵 연습중
    @GetMapping({ "/", "main", "mainPage" })
    public String main() {
        return "pages/mainPage";
    }

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

    @GetMapping("/place/{contentSeq}") // 이거 JSON 불러오는데 맞나요?
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
        long count = placeRepository.count();
        model.addAttribute("count", count);
        List<Places> placesSpot = placeRepository.placeTop4("관광지");
        long countSpot = placeRepository.countPartName("관광지");
        model.addAttribute("countSpot", countSpot);
        model.addAttribute("placesSpot", placesSpot);

        List<Places> placesHospital = placeRepository.placeTop4("동물병원");
        long countHos = placeRepository.countPartName("동물병원");
        model.addAttribute("countHos", countHos);
        model.addAttribute("placesHospital", placesHospital);

        List<Places> placesCafe = placeRepository.placeTop4("식음료");
        long countCafe = placeRepository.countPartName("식음료");
        model.addAttribute("countCafe", countCafe);
        model.addAttribute("placesCafe", placesCafe);

        List<Places> placesActivity = placeRepository.placeTop4("체험");
        long countAct = placeRepository.countPartName("체험");
        model.addAttribute("countAct", countAct);
        model.addAttribute("placesActivity", placesActivity);

        List<Places> placesHotel = placeRepository.placeTop4("숙박");
        long countHotel = placeRepository.countPartName("숙박");
        model.addAttribute("countHotel", countHotel);
        model.addAttribute("placesHotel", placesHotel);
        return "pages/place/outlineList";
    }

    @GetMapping("/place/search")
    public String searchPartName(@RequestParam String partName, Model model) {
        List<Places> places = placeService.분류검색(partName);
        long count = placeRepository.countPartName(partName);
        // long count = placeRepository.countPartName(partName);
        // model.addAttribute("count", count);
        PlaceListDto placeDto = new PlaceListDto();
        placeDto.setPlaces(places);
        for (int i = 0; i < places.size(); i++) {
            placeDto.setTitle(places.get(i).getTitle());
            placeDto.setAddress(places.get(i).getAddress());
        }
        model.addAttribute("count", count);
        model.addAttribute("pdto", placeDto);
        model.addAttribute("places", places);
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
    public String searchOutLine(@RequestParam(defaultValue = "") String keyword, Model model) {
        if (keyword.equals("")) {
            List<Places> places = placeRepository.findAll();
            model.addAttribute("places", places);
            return "pages/list/outlineList";
        }
        List<Places> places = placeRepository.searchPlaces(keyword, keyword);
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
        return "pages/place/outlineList";
    }

}