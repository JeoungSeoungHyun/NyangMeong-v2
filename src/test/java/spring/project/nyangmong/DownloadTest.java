package spring.project.nyangmong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.web.dto.craw.PlaceDto;
import spring.project.nyangmong.web.dto.craw.Result;

public class DownloadTest {
    private PlaceRepository placeRepository;
    private ImageRepository imageRepository;

    // @Test
    public void 크롤링테스트() {
        // 1. DB 연결
        RestTemplate rt = new RestTemplate();
        // String url =
        // "http://www.pettravel.kr/api/listArea.do?page=10&pageBlock=5&areaCode=AC01";
        String url = "http://www.pettravel.kr/api/detailSeqPart.do?partCode=PC02&contentNum=1";
        Result[] responseDtos = rt.getForObject(url, Result[].class);
        Result responseDto = responseDtos[0];
        System.out.println(responseDto);
    }

    @Autowired
    @Test
    public void test2() {
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

        // List<Image> images = new ArrayList<>();
        // for (int i = 0; i < placeDto.getImageList().size(); i++) {
        // Image image = Image.builder()
        // .imgurl(placeDto.getImageList().get(i).getImage())
        // .places(placeEntity) // <- placeEntity
        // .build();
        // images.add(image);
        // }

        // imageRepository.saveAll(images);
    }
}