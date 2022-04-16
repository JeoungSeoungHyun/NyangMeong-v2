package spring.project.nyangmong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/main")
    public String main() {
        return "pages/welcomePage";
    }


    @GetMapping("/testjuwon")
    public String detail() {
        return "pages/user/findPwForm";
    }

}
