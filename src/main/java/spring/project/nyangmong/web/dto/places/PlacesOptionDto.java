package spring.project.nyangmong.web.dto.places;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.places.Places;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlacesOptionDto {
    private Places Places;
    private boolean bathFlagShow;
    private boolean parkingFlagShow;
    private boolean entranceFlagShow;
    private boolean petWeightShow;
    private boolean emergencyFlagShow;
    private boolean provisionFlagShow;
    private boolean inOutFlagShow;
    private boolean petFlagShow;
}