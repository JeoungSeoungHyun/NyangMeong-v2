package spring.project.nyangmong;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import spring.project.nyangmong.domain.image.Image;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.web.dto.craw.ImageDto;
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
        // List<Result> rList = Arrays.asList(response);

        // Places places = new Places(

        // null,
        // rList.get(0).getResultList().getContentSeq(),
        // rList.get(0).getResultList().getAreaName(),
        // rList.get(0).getResultList().getPartName(),
        // rList.get(0).getResultList().getTitle(),
        // rList.get(0).getResultList().getKeyword(),
        // rList.get(0).getResultList().getAddress(),
        // rList.get(0).getResultList().getTel(),
        // rList.get(0).getResultList().getLatitude(),
        // rList.get(0).getResultList().getLongitude(),
        // rList.get(0).getResultList().getUsedTime(),
        // rList.get(0).getResultList().getHomePage(),
        // rList.get(0).getResultList().getContent(),
        // rList.get(0).getResultList().getProvisionSupply(),
        // rList.get(0).getResultList().getPetFacility(),
        // rList.get(0).getResultList().getRestaurant(),
        // rList.get(0).getResultList().getParkingLog(),
        // rList.get(0).getResultList().getMainFacility(),
        // rList.get(0).getResultList().getUsedCost(),
        // rList.get(0).getResultList().getPolicyCautions(),
        // rList.get(0).getResultList().getEmergencyResponse(),
        // rList.get(0).getResultList().getMemo(),
        // rList.get(0).getResultList().getBathFlag(),
        // rList.get(0).getResultList().getProvisionFlag(),
        // rList.get(0).getResultList().getPetFlag(),
        // rList.get(0).getResultList().getPetWeight(),
        // rList.get(0).getResultList().getPetBreed(),
        // rList.get(0).getResultList().getEmergencyFlag(),
        // rList.get(0).getResultList().getEntranceFlag(),
        // rList.get(0).getResultList().getParkingFlag(),
        // rList.get(0).getResultList().getInOutFlag(),
        // rList.get(0).getResultList().getMessage(),
        // null);

        // Hospital hos =new Hospital(
        // rs.getString("yadmnm"),
        // rs.getString("addr"),
        // rs.getString("telno"),
        // rs.getString("rprtworpclicFndttgtyn"),
        // rs.getString("ratpsblyn"),
        // rs.getString("pcrpsblyn"),
        // rs.getString("mgtstadd")
        // );

        // List<Image> images = Arrays.asList(pList.get(0));

        // List<Result> results = Arrays.asList(response);
        // List<Places> places = Arrays.asList(results.get(0).getResultList().get(0));
        // System.out.println(places);

        // DB에 saveAll() 호출

        // 1. HospitalRepository의 findAll() 호출
        // 2. model에 담기
        // model.addAttribute("hospitals", hosList);
    }

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

        placeRepository.save(place);

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

    }

}