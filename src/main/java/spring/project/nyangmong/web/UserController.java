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

    // @GetMapping("/joinForm")
    // public String joinForm() {
    // return "user/joinForm";
    // }

    // @GetMapping("/loginForm")
    // public String loginForm() {
    // return "user/loginForm";
    // }

    @GetMapping("/user-username/{username}/exists")
    public ResponseEntity<Boolean> checkusernameDuplicate(@PathVariable String username) {
        return ResponseEntity.ok(userService.checkuserNameDuplicate(username));
    }
}
