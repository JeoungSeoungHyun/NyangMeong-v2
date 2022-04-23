package spring.project.nyangmong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AdminController {
    @GetMapping("/s/admin/main")
    public String adminMain() {
        return "pages/admin/adminMain";
    }

    @GetMapping("/s/admin/comment-manage")
    public String adminComment() {
        return "pages/admin/commentManage";
    }

    @GetMapping("/s/admin/jarang-manage")
    public String adminJarang() {
        return "pages/admin/jarangManage";
    }

    @GetMapping("/s/admin/notice-manage")
    public String adminNotice() {
        return "pages/admin/noticeManage";
    }

    @PostMapping("/s/admin/notice-write")
    public String adminNoticeWrite() {
        return "";
    }

    @PutMapping("/s/admin/notice-update")
    public String adminNoticeUpdate() {
        return "";
    }

    @DeleteMapping("/s/admin/notice-delete")
    public String adminNoticeDelete() {
        return "";
    }

    @DeleteMapping("/s/admin/jarang-delete")
    public String adminJarangDelete() {
        return "";
    }

    @DeleteMapping("/s/admin/comment-delete")
    public String adminCommentDelete() {
        return "";
    }
}