package spring.project.nyangmong.web.dto.members.pet;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.pet.Pet;
import spring.project.nyangmong.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetUpdateDto {
    private String petName;
    private String petGender;
    private String petSpices;
    private String petAge;
    private MultipartFile file;
    private Integer userId;

    public Pet toEntity(String petImgurl, User user) {
        Pet pet = new Pet();
        pet.setPetName(petName);
        pet.setPetGender(petGender);
        pet.setPetSpices(petSpices);
        pet.setPetAge(petAge);
        pet.setPetImgurl(petImgurl);
        pet.setUser(user);
        return pet;
    }
}