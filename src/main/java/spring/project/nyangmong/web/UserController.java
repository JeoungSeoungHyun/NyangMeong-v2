package spring.project.nyangmong.web;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // @GetMapping("/logout")
    // public String logout() {
    // session.invalidate(); // 세션 무효화 (세션 아이디 영역의 데이터를 다 삭제해)
    // return "redirect:/";
    // }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "pages/user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "pages/user/loginForm";
    }

    @GetMapping("/user-username/{username}/exists")
    public ResponseEntity<Boolean> checkusernameDuplicate(@PathVariable String username) {
        return ResponseEntity.ok(userService.checkuserNameDuplicate(username));
    }

    // 추후 api <로그인을 인증해야해서> 옮겨야하는 맵핑
    // 유저가 즐겨찾기 한 장소 - 일단은 mapping만 해둔 상태
    @GetMapping("/user/favlist")
    public String likeList() {

        return "pages/list/favoriteList";
    }

    // 유저가 마음에 들어한 댕냥이자랑 게시판 - 일단은 mapping만 해둔 상태.
    @GetMapping("/user/boardlike")
    public String boardLikeList() {

        return "pages/list/likeList";
    }

    // 유저가 적은 댓글 보기 - 일단은 mapping만 해둔 상태
    @GetMapping("/user/commentlist")
    public String commentList() {

        return "pages/list/commentlist";
    }

    // 유저가 상세보기 - 일단은 mapping만 해둔 상태
    @GetMapping("/user/detail")
    public String userDetail() {

        return "pages/detail/userDetail";
    }

    // 아이디 찾기- 일단은 mapping만 해둔 상태
    @GetMapping("/find/id")
    public String findId() {

        return "pages/user/findIdForm";
    }

    // 비밀번호 찾기 - 일단은 mapping만 해둔 상태
    @GetMapping("/find/pw")
    public String findPw() {

        return "pages/user/findPwForm";
    }

}
