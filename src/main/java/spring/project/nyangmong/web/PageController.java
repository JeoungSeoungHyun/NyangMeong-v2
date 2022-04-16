package spring.project.nyangmong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
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
        return "pages/place/noticeWriteForm";
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
}
