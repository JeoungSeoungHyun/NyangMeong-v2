package spring.project.nyangmong.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.UserService;
import spring.project.nyangmong.web.dto.members.ResponseDto;
import spring.project.nyangmong.web.dto.members.user.UpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;

    // 회원 탈퇴
    @DeleteMapping("/s/api/user/{id}/delete")
    public @ResponseBody ResponseDto<?> userDelete(@PathVariable Integer id) {

        // 권한 - 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");
        if (principal.getId() != id) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        } else {
            return new ResponseDto<>(1, "성공", null);
        }

    }

    // 회원 정보 수정
    @PutMapping("/s/api/user/{id}/update")
    public ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {

        // 권한 - 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");
        if (principal.getId() != id) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        User userEntity = userService.회원수정(id, updateDto);
        session.setAttribute("principal", userEntity); // 세션 변경하기
        return new ResponseDto<>(1, "성공", null);
    }
}
