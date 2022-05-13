package spring.project.nyangmong.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.service.PetService;
import spring.project.nyangmong.web.dto.members.pet.PetUpdateDto;

@RequiredArgsConstructor
@RestController
public class PetApiController {

    private final PetService petService;

    // 반려동물 정보 수정
    @PutMapping("/s/api/pet/{petId}/update")
    public ResponseEntity<?> update(@PathVariable Integer petId, @RequestBody PetUpdateDto petUpdateDto) {
        petService.반려동물수정(petId, petUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
