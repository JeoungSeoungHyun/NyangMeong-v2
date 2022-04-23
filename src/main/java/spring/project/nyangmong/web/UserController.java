package spring.project.nyangmong.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.pet.Pet;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.handle.ex.CustomException;
import spring.project.nyangmong.service.PetService;
import spring.project.nyangmong.service.UserService;
import spring.project.nyangmong.util.UtilValid;
import spring.project.nyangmong.web.dto.members.user.IdFindReqDto;
import spring.project.nyangmong.web.dto.members.user.JoinDto;
import spring.project.nyangmong.web.dto.members.user.PwFindReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;
    private final PetService petService;

    // 회원 정보 수정 페이지
    @GetMapping("/s/user/{id}/update-form")
    public String userChangeForm(@PathVariable Integer id, Model model) {
        // 권한
        User principal = (User) session.getAttribute("principal");
        if (principal.getId() == id) {
            User userEntity = userService.회원정보보기(id);
            Pet petEntity = petService.펫정보보기(id);
            model.addAttribute("user", userEntity);
            model.addAttribute("pet", petEntity);
            return "pages/user/userChange";
        } else {
            throw new CustomException("수정할 권한이 없습니다.");
        }
    }

    // 회원 정보 페이지
    @GetMapping("/s/user/{id}/detail")
    public String userDetail(@PathVariable Integer id, Model model) {

        // 권한
        User principal = (User) session.getAttribute("principal");
        if (principal.getId() == id) {
            User userEntity = userService.회원정보보기(id);
            Pet petEntity = petService.펫정보보기(id);
            model.addAttribute("user", userEntity);
            model.addAttribute("pet", petEntity);
            return "pages/user/userDetail";
        } else {
            throw new CustomException("회원 정보 보기 권한이 없습니다.");
        }

    }

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

        // System.out.println("Remember me : " + user.getRemember());

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

    // 아이디 찾기 페이지
    @GetMapping("/find/id-form")
    public String findIdForm() {
        return "pages/user/findIdForm";
    }

    // 아이디 찾기 요청
    @PostMapping("/find/id")
    public String idFind(@Valid IdFindReqDto idFindReqDto, BindingResult bindingResult, Model model) {
        UtilValid.요청에러처리(bindingResult);
        String findUserId = userService.아이디찾기(idFindReqDto);
        model.addAttribute("findUserId", findUserId);
        return "pages/user/showIdForm";
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/find/pw-form")
    public String findPwForm() {
        return "pages/user/findPwForm";
    }

    // 비밀번호 찾기 요청
    @PostMapping("/find/pw")
    public String idFind(@Valid PwFindReqDto pwFindReqDto, BindingResult bindingResult, Model model) {
        UtilValid.요청에러처리(bindingResult);
        String findPassword = userService.패스워드찾기(pwFindReqDto);
        model.addAttribute("findPassword", findPassword);
        return "pages/user/showPwForm";
    }
}