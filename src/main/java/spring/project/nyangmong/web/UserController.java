package spring.project.nyangmong.web;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.UserService;
import spring.project.nyangmong.web.dto.members.user.JoinDto;

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

    // 로그인
    @PostMapping("/login")
    public String login(User user) {
        User userEntity = userService.로그인(user);
        session.setAttribute("principal", userEntity);
        return "redirect:/";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(JoinDto joinDto) {
        userService.회원가입(joinDto);
        return "redirect:/loginForm";
    }

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

    // 마이페이지 (회원정보 수정페이지)
    @GetMapping("/s/user/{id}/detail")
    public String userDetail(@PathVariable Integer id, Model model) {
        User userEntity = userService.회원정보(id);
        model.addAttribute("user", userEntity);
        return "pages/user/userChange";
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
