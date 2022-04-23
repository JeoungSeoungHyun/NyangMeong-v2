package spring.project.nyangmong.web.dto.places;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.image.PublicDataImage;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageListDto {
    private List<PublicDataImage> publicDataImage;
    private PublicDataImage ShowupImage;
}