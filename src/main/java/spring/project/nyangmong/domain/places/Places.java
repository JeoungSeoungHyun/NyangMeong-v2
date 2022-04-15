package spring.project.nyangmong.domain.places;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.image.Image;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 콘텐츠 번호
    @Column(length = 30, nullable = false)
    private Integer contentSeq;

    // 지역 명
    @Column(length = 200)
    private String areaName;

    // 분야 명
    @Column(length = 60)
    private String partName;

    // 업체 명
    @Column(length = 200)
    private String title;

    // 검색 키워드
    @Column(length = 300)
    private String keyword;

    // 주소
    @Column(length = 300)
    private String address;

    // 전화번호
    @Column(length = 60)
    private String tel;

    // 위도
    @Column(length = 60)
    private String latitude;

    // 경도
    @Column(length = 60)
    private String longitude;

    // 이용시간
    @Column(length = 60)
    private String usedTime;

    // 홈페이지
    @Column(length = 200)
    private String homePage;

    // 소개
    @Lob
    @Column
    private String content;

    // 비품제공
    @Column(length = 200)
    private String provisionSupply;

    // 반려동물 시설
    @Column(length = 100)
    private String petFacility;

    // 식당
    @Column(length = 100)
    private String restaurant;

    // 주차장 수용
    @Column(length = 30)
    private String parkingLog;

    // 주요시설
    @Column(length = 30)
    private String mainFacility;

    // 이용요금
    @Column(length = 30)
    private String usedCost;

    // 애견정책 및 주의사항
    @Column(length = 300)
    private String policyCautions;

    // 응급상황 대처 여부
    @Column(length = 30)
    private String emergencyResponse;

    // 기타(메모)
    @Lob
    @Column(length = 30)
    private String memo;

    // 목욕시설 (Y/N)
    @Column(length = 3)
    private String bathFlag;

    // 비품제공 (Y/N)
    @Column(length = 3)
    private String provisionFlag;

    // 펫 동반식당 (Y/N)
    @Column(length = 3)
    private String petFlag;

    // 제한 몸무게 (kg)
    @Column
    private int petWeight;

    // 견종 (현재 사용 안함)
    @Column(length = 30)
    private String petBreed;

    // 응급 수칙 (Y/N)
    @Column(length = 3)
    private String emergencyFlag;

    // 입장료 (Y/N)
    @Column(length = 3)
    private String entranceFlag;

    // 주차장 (Y/N)
    @Column(length = 3)
    private String parkingFlag;

    // 실내 외 구분 (IN/OUT)
    @Column(length = 10)
    private String inOutFlag;

    // 이미지 목록
    @JsonIgnoreProperties({ "imageList" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "places", cascade = CascadeType.REMOVE) // 연관관계의 주인의변수명
    private List<Image> imageList;

}
