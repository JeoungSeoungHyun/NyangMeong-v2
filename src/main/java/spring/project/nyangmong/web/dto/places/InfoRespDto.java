package spring.project.nyangmong.web.dto.places;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.places.Places;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoRespDto {

    private Places place;
    private String image;
}
