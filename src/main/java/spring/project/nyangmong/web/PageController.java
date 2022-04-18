package spring.project.nyangmong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({ "/", "main", "mainPage" })
    public String main() {

        return "pages/mainPage";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "pages/welcomePage";
    }

    @GetMapping("/welcomePage")
    public String welcomePage() {
        return "pages/welcomePage";
    }

    @GetMapping("/jarangDetail")
    public String jarangDetail() {
        return "pages/post/jarangDetail";
    }

    @GetMapping("/jarangList")
    public String jarangList() {
        return "pages/post/jarangList";
    }

    @GetMapping("/jarangWriteForm")
    public String jarangWriteForm() {
        return "pages/post/jarangWriteForm";
    }

    @GetMapping("/noticeList")
    public String main6() {
        return "pages/post/noticeList";
    }

    @GetMapping("/noticeWriteForm")
    public String noticeWriteForm() {
        return "pages/post/noticeWriteForm";
    }

    @GetMapping("/favoriteList")
    public String favoriteList() {
        return "pages/user/favoriteList";
    }

    @GetMapping("/findIdForm")
    public String findIdForm() {
        return "pages/user/findIdForm";
    }

    @GetMapping("/findPwForm")
    public String findPwForm() {
        return "pages/user/findPwForm";
    }

    // @GetMapping("/joinForm")
    // public String joinForm() {
    // return "pages/user/joinForm";
    // }

    @GetMapping("/likeList")
    public String likeList() {
        return "pages/user/likeList";
    }

    // @GetMapping("/loginForm")
    // public String loginForm() {
    // return "pages/user/loginForm";
    // }

    @GetMapping("/userChange")
    public String userChange() {
        return "pages/user/userChange";
    }

    @GetMapping("/userDetail")
    public String userDetail() {
        return "pages/user/userDetail";
    }

    @GetMapping("/commentList")
    public String commentList() {
        return "pages/user/commentList";
    }

    @GetMapping("/noticeDetail")
    public String noticeDetail() {
        return "pages/post/noticeDetail";
    }

    @GetMapping("/noticeUpdateForm")
    public String noticeUpdateForm() {
        return "pages/post/noticeUpdateForm";
    }

}
