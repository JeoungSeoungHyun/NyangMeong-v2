package spring.project.nyangmong.web.dto.members.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.pet.Pet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetUpdateDto {
    private String petName;
    private String petGender;
    private String petSpices;
    private String petAge;

    public Pet toEntity() {
        Pet pet = new Pet();
        pet.setPetName(petName);
        pet.setPetGender(petGender);
        pet.setPetSpices(petSpices);
        pet.setPetAge(petAge);
        return pet;
    }
}