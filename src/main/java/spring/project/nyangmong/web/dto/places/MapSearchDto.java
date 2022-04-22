package spring.project.nyangmong.web.dto.places;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MapSearchDto {

    private String keyword;
    private String cafe;
    private String hotel;
    private String activity;
    private String hospital;
    private String spot;
}
