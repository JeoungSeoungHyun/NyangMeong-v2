package spring.project.nyangmong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

<<<<<<< HEAD
    @GetMapping({ "/", "main" })
    public String main() {
        return "pages/mainPage";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "pages/welcomePage";
    }

    @GetMapping("/outline")
    public String outline() {
        return "pages/list/outlineList";
    }
=======
    @GetMapping("/mainPage")
    public String mainPage() {
        return "pages/mainPage";
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

    @GetMapping("/activityList")
    public String activityList() {
        return "pages/place/activityList";
    }

    @GetMapping("/cafeList")
    public String cafeList() {
        return "pages/place/cafeList";
    }

    @GetMapping("/hospitalList")
    public String hospitalList() {
        return "pages/place/hospitalList";
    }

    @GetMapping("/hotelList")
    public String hotelList() {
        return "pages/place/hotelList";
    }

    @GetMapping("/outlineList")
    public String outlineList() {
        return "pages/place/outlineList";
    }

    @GetMapping("/placeDetail")
    public String placeDetail() {
        return "pages/place/placeDetail";
    }

    @GetMapping("/spotList")
    public String spotList() {
        return "pages/place/spotList";
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

    @GetMapping("/joinForm")
    public String joinForm() {
        return "pages/user/joinForm";
    }

    @GetMapping("/likeList")
    public String likeList() {
        return "pages/user/likeList";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "pages/user/loginForm";
    }

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

>>>>>>> dfdea1f47fffd5e2a7e10e4e43189e9b7a538023
}
