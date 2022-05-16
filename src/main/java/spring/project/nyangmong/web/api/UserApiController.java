package spring.project.nyangmong.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.UserService;
import spring.project.nyangmong.util.RespScript;
import spring.project.nyangmong.util.Script;
import spring.project.nyangmong.web.dto.members.ResponseDto;
import spring.project.nyangmong.web.dto.members.user.UpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;
    private final HttpServletResponse response;

    // 프로필 이미지 수정
    @PutMapping("/s/api/user/{id}/profile-img")
    public ResponseEntity<?> profileImgUpdate(@PathVariable Integer id, MultipartFile userImgurl) {
        // 권한체크하기 - 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");

        // 권한이 없으면
        if (principal.getId() != id) {
            String scriptMsg = Script.back("프로필 사진을 수정할 권한이 없습니다.");
            RespScript.스크립트로응답하기(scriptMsg, response);
        }

        userService.프로필이미지변경(id, userImgurl, session); // 세션값 변경하기
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/s/api/user/{id}/delete")
    public @ResponseBody ResponseDto<?> userDelete(@PathVariable Integer id) {
        // 권한체크하기 - 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");

        // 권한이 없으면
        if (principal.getId() != id) {
            String scriptMsg = Script.back("회원탈퇴할 권한이 없습니다.");
            RespScript.스크립트로응답하기(scriptMsg, response);
        }

        userService.회원탈퇴(id);
        session.invalidate();
        return new ResponseDto<>(1, "성공", null);
    }

    // 회원 정보 수정
    @PutMapping("/s/api/user/{id}/update")
    public ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
        // 권한체크하기 - 세션의 아이디와 {id}를 비교
        User principal = (User) session.getAttribute("principal");

        // 권한이 없으면
        if (principal.getId() != id) {
            String scriptMsg = Script.back("해당 회원의 정보를 수정할 권한이 없습니다.");
            RespScript.스크립트로응답하기(scriptMsg, response);
        }

        userService.회원수정(id, updateDto, session);
        return new ResponseDto<>(1, "성공", null);
    }
}