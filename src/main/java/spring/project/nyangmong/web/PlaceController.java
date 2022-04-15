package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import spring.project.nyangmong.domain.image.Image;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.web.dto.craw.PlaceDto;
import spring.project.nyangmong.web.dto.craw.Result;

@Controller
public class PlaceController {

    private PlaceRepository placeRepository;
    private ImageRepository imageRepository;

    @GetMapping("/")
    public String main() {
        return "pages/download";
    }

    @GetMapping("/list")
    public String download(Model model) {
        RestTemplate rt = new RestTemplate();
        String url = "http://www.pettravel.kr/api/detailSeqPart.do?partCode=PC02&contentNum=1";
        Result[] responseDtos = rt.getForObject(url, Result[].class);
        Result responseDto = responseDtos[0];

        // 다운 받은 것
        PlaceDto placeDto = responseDto.getResultList(); // 한건

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
                .bathFlag(placeDto.getBathFlag())
                .provisionFlag(placeDto.getProvisionFlag())
                .petFlag(placeDto.getPetFlag())
                .petWeight(placeDto.getPetWeight())
                .petBreed(placeDto.getPetBreed())
                .emergencyFlag(placeDto.getEmergencyFlag())
                .entranceFlag(placeDto.getEntranceFlag())
                .parkingFlag(placeDto.getParkingFlag())
                .inOutFlag(placeDto.getInOutFlag())
                .build();

        System.out.println(place);

        // List<Places> a = placeRepository.findAll();
        // System.out.println(a);

        Places placeEntity = placeRepository.save(place); // id 찾으려구
        List<Image> images = new ArrayList<>();

        for (int i = 0; i < placeDto.getImageList().size(); i++) {
            Image image = Image.builder()
                    .imgurl(placeDto.getImageList().get(i).getImage())
                    .places(placeEntity) // <- placeEntity
                    .build();
            images.add(image);
        }

        imageRepository.saveAll(images);
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