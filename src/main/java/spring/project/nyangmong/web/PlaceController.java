package spring.project.nyangmong.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.web.dto.craw.PlaceDto;
import spring.project.nyangmong.web.dto.craw.Result;

@Controller
public class PlaceController {

    private PlaceRepository placeRepository;

    @GetMapping("/")
    public String main() {
        return "pages/download";
    }

    @GetMapping("/list")
    public String download(Model model) {

        // 1. DB 연결
        RestTemplate rt = new RestTemplate();
        String url = "http://www.pettravel.kr/api/detailSeqPart.do?partCode=PC02&contentNum=1";
        Result[] response = rt.getForObject(url, Result[].class);
        List<Result> rList = Arrays.asList(response);

        PlaceDto places = rList.get(0).getResultList();
        // List<PlacesDto> pList = Arrays.asList(places);

        // placeRespository.saveAll(pList);
        // DB에 saveAll() 호출
        // placeRespository.saveAll(placesList);

        // 1. Repository의 findAll() 호출
        // 2. model에 담기
        model.addAttribute("places", placeRepository.findAll());
        // model.addAttribute("hospitals", hosList);
        return "pages/list";
    }
}