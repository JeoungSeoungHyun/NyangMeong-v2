package spring.project.nyangmong.web.dto.places;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.places.Places;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceListDto {
    private List<Places> places;
    private String title;
    private String address;
    private String imgurl;
}
