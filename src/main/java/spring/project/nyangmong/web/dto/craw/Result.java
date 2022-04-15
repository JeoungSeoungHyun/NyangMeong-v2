package spring.project.nyangmong.web.dto.craw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.places.Places;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private PlaceDto resultList;
    private String message;
}