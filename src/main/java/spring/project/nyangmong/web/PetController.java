package spring.project.nyangmong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.service.PetService;
import spring.project.nyangmong.web.dto.members.pet.PetUpdateDto;

@RequiredArgsConstructor
@Controller
public class PetController {

    private final PetService petService;

    // 펫 정보 수정(추가)
    @PostMapping("/s/user/{userId}/pet/update")
    public String petUpdate(@PathVariable Integer userId, PetUpdateDto petUpdateDto) {
        petService.펫수정(userId, petUpdateDto);
        return "redirect:/s/user/{userId}/update-form";
    }
}
