package spring.project.nyangmong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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
}
