package spring.project.nyangmong.web.dto.craw;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String petWeight;
    private String petBreed;
    private String emergencyFlag;
    private String entranceFlag;
    private String parkingFlag;
    private String inOutFlag;
    private List<ImageDto> imageList;
}