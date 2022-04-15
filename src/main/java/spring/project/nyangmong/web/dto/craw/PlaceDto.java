package spring.project.nyangmong.web.dto.craw;

import java.util.List;

import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.places.Places;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceDto {
    private Integer contentSeq;
    private String areaName;
    private String partName;
    private String title;
    private String keyword;
    private String address;
    private String tel;
    private String latitude;
    private String longitude;
    private String usedTime;
    private String homePage;
    private String content;
    private String provisionSupply;
    private String petFacility;
    private String restaurant;
    private String parkingLog;
    private String mainFacility;
    private String usedCost;
    private String policyCautions;
    private String emergencyResponse;
    private String memo;
    private String bathFlag;
    private String provisionFlag;
    private String petFlag;
    private int petWeight;
    private String petBreed;
    private String emergencyFlag;
    private String entranceFlag;
    private String parkingFlag;
    private String inOutFlag;
    private List<ImageDto> imageList;

    @Builder
    public Places builder() {
        return Places.builder()
                .contentSeq(contentSeq)
                .areaName(areaName)
                .partName(partName)
                .title(title)
                .keyword(keyword)
                .address(address)
                .tel(tel)
                .latitude(latitude)
                .longitude(longitude)
                .usedTime(usedTime)
                .homePage(homePage)
                .content(content)
                .provisionSupply(provisionSupply)
                .petFacility(petFacility)
                .restaurant(restaurant)
                .parkingLog(parkingLog)
                .mainFacility(mainFacility)
                .usedCost(usedCost)
                .policyCautions(policyCautions)
                .emergencyResponse(emergencyResponse)
                .memo(memo)
                .bathFlag(bathFlag)
                .provisionFlag(provisionFlag)
                .petFlag(petFlag)
                .petWeight(petWeight)
                .petBreed(petBreed)
                .emergencyFlag(emergencyFlag)
                .entranceFlag(entranceFlag)
                .parkingFlag(parkingFlag)
                .inOutFlag(inOutFlag)
                .build();
    }

}