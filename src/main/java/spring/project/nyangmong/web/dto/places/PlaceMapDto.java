package spring.project.nyangmong.web.dto.places;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.places.Places;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceMapDto {
    private List<Places> places;
    // 위도
    private String latitude;
    // 경도
    private String longitude;
    // 분야이름
    private String partName;
}