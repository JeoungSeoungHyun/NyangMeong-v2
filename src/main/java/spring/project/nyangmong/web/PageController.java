package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.service.PlaceService;
import spring.project.nyangmong.util.ChooseImg;
import spring.project.nyangmong.web.dto.places.InfoRespDto;

@RequiredArgsConstructor
@Controller
public class PageController {
    private final PlaceService placeService;
    private final PlaceRepository placeRepository;

    // 메인 페이지 맵
    @GetMapping({ "/", "main", "mainPage" })
    public String main(Model model) {

        List<Places> placesHotel = placeRepository.placeTop3("숙박");
        List<String> placesHotelImages = ChooseImg.imgList(placesHotel);
        List<InfoRespDto> HotelInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesHotel.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesHotel.get(i), placesHotelImages.get(i));
            HotelInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("HotelInfoListRespDto", HotelInfoListRespDto);
        model.addAttribute("placesHotel", placesHotel);

        List<Places> placesCafe = placeRepository.placeTop3("식음료");
        List<String> placesCafeImages = ChooseImg.imgList(placesCafe);
        List<InfoRespDto> CafeInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesCafe.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesCafe.get(i), placesCafeImages.get(i));
            CafeInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("CafeInfoListRespDto", CafeInfoListRespDto);
        model.addAttribute("placesCafe", placesCafe);

        List<Places> placesSpot = placeRepository.placeTop3("관광지");
        List<String> placesSpotImages = ChooseImg.imgList(placesSpot);
        List<InfoRespDto> SpotInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesSpot.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesSpot.get(i), placesSpotImages.get(i));
            SpotInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("SpotInfoListRespDto", SpotInfoListRespDto);
        model.addAttribute("placesSpot", placesSpot);

        List<Places> placesActivity = placeRepository.placeTop3("체험");
        List<String> placesAcitivityImages = ChooseImg.imgList(placesActivity);
        List<InfoRespDto> ActivityInfoListRespDto = new ArrayList<>();
        for (int i = 0; i < placesActivity.size(); i++) {
            InfoRespDto infoRespDto = new InfoRespDto(placesActivity.get(i), placesAcitivityImages.get(i));
            ActivityInfoListRespDto.add(infoRespDto);
        }
        model.addAttribute("ActivityInfoListRespDto", ActivityInfoListRespDto);
        model.addAttribute("placesActivity", placesActivity);

        List<Places> placesHospital = placeRepository.placeTop3("동물병원");
        model.addAttribute("placesHospital", placesHospital);

        return "pages/mainPage";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "pages/welcomePage";
    }

    @GetMapping("/jarangDetail")
    public String jarangDetail() {
        return "pages/post/jarangDetail";
    }

    @GetMapping("/jarangList")
    public String jarangList() {
        return "pages/post/jarangList";
    }

    @GetMapping("/jarangWriteForm")
    public String jarangWriteForm() {
        return "pages/post/jarangWriteForm";
    }

    @GetMapping("/noticeWriteForm")
    public String noticeWriteForm() {
        return "pages/post/noticeWriteForm";
    }

    @GetMapping("/favoriteList")
    public String favoriteList() {
        return "pages/user/favoriteList";
    }

    @GetMapping("/findIdForm")
    public String findIdForm() {
        return "pages/user/findIdForm";
    }

    @GetMapping("/findPwForm")
    public String findPwForm() {
        return "pages/user/findPwForm";
    }

    // @GetMapping("/joinForm")
    // public String joinForm() {
    // return "pages/user/joinForm";
    // }

    @GetMapping("/likeList")
    public String likeList() {
        return "pages/user/likeList";
    }

    // @GetMapping("/loginForm")
    // public String loginForm() {
    // return "pages/user/loginForm";
    // }

    @GetMapping("/userChange")
    public String userChange() {
        return "pages/user/userChange";
    }

    @GetMapping("/userDetail")
    public String userDetail() {
        return "pages/user/userDetail";
    }

    @GetMapping("/commentList")
    public String commentList() {
        return "pages/user/commentList";
    }

    @GetMapping("/noticeDetail")
    public String noticeDetail() {
        return "pages/post/noticeDetail";
    }

    @GetMapping("/noticeList")
    public String noticeList() {
        return "pages/post/noticeList";
    }

    @GetMapping("/noticeUpdateForm")
    public String noticeUpdateForm() {
        return "pages/post/noticeUpdateForm";
    }

    @GetMapping("/bbq")
    public @ResponseBody String bhc() {
        return "";
    }
}
