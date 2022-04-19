package spring.project.nyangmong.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    // 로그아웃하기
    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 영역 전체를 날리는 것
        return "redirect:/";
    }

    // 로그인
    @PostMapping("/login")
    public String login(User user, HttpServletResponse response) {

        User userEntity = userService.로그인(user);
        session.setAttribute("principal", userEntity);

        // Remember me - userId 쿠키에 저장
        if (user.getRemember() != null && user.getRemember().equals("on")) {
            response.addHeader("Set-Cookie", "remember=" + user.getUserId());
        }
        return "redirect:/";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(JoinDto joinDto) {
        userService.회원가입(joinDto);
        return "redirect:/login-form";
    }

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String joinForm() {
        return "pages/user/joinForm";
    }

    // 로그인 페이지
    @GetMapping("/login-form")
    public String loginForm(HttpServletRequest request, Model model) {
        // 쿠키로 아이디 기억하기
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies(); // JSessionID, remember 2개를 내부적으로 split 해주는 메서드
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember")) {
                    model.addAttribute("remember", cookie.getValue());
                }
            }
        }
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
