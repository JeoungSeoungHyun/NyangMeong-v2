package spring.project.nyangmong.web.dto.craw;

import org.aspectj.internal.lang.annotation.ajcPrivileged;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.image.Image;
import spring.project.nyangmong.domain.places.Places;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDto {
    private String image;
}