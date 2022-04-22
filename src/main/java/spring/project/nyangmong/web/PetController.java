package spring.project.nyangmong.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.handle.ex.CustomException;
import spring.project.nyangmong.service.PetService;
import spring.project.nyangmong.web.dto.members.pet.PetUpdateDto;

@RequiredArgsConstructor
@Controller
public class PetController {

    private final PetService petService;
    private final HttpSession session;

    // 펫 정보 수정(추가)
    @PostMapping("/s/user/{userId}/pet/update")
    public String petUpdate(@PathVariable Integer userId, PetUpdateDto petUpdateDto) {

        // 권한 - 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");
        if (principal.getId() == userId) {
            petService.펫수정(userId, petUpdateDto);
            return "redirect:/s/user/{userId}/update-form";
        } else {
            throw new CustomException("펫 정보 수정 권한이 없습니다.");
        }
    }
}
