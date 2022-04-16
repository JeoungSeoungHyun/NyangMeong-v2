package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.image.PublicDataImage;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.util.ContentSeqDownload;
import spring.project.nyangmong.web.dto.craw.PlaceDto;
import spring.project.nyangmong.web.dto.craw.Result;

@RequiredArgsConstructor
@Controller
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final ImageRepository imageRepository;

    @GetMapping("/")
    public String main() {
        return "pages";
    }

    @GetMapping("/place/{id}")
    public String detailPlaces(@PathVariable Integer contentSeq, Model model) {

        return "";
    }

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
                        .bathFlag(placeDto.getBathFlag())
                        .provisionFlag(placeDto.getProvisionFlag())
                        .petFlag(placeDto.getPetFlag())
                        .petWeight(placeDto.getPetWeight())
                        .petBreed(placeDto.getPetBreed())
                        .emergencyFlag(placeDto.getEmergencyFlag())
                        .entranceFlag(placeDto.getEntranceFlag())
                        .parkingFlag(placeDto.getParkingFlag())
                        .inOutFlag(placeDto.getInOutFlag())
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
        return "pages/list";
    }

    @GetMapping("/detail")
    public String detail() {
        return "pages/detail/placeDetail";
    }

}