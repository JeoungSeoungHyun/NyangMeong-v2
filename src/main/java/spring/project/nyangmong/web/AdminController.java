package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.handle.ex.CustomException;
import spring.project.nyangmong.service.AdminService;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.service.CommentService;
import spring.project.nyangmong.web.dto.members.admin.AdminUserDto;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private CommentService commentService;
    private final BoardsService boardsService;
    private final HttpSession session;

    private final AdminService adminService;

    @GetMapping("/s/admin/notice-manage")
    public String adminNotice(@RequestParam(defaultValue = "0") Integer page, Model model) {
        List<Boards> boards = boardsService.공지사항목록(page);
        // 응답의 DTO를 만들어서 <- posts 를 옮김. (라이브러리 있음)
        model.addAttribute("notice", boards);
        return "/pages/admin/noticeManage";
    }

    @GetMapping("/admin/join-form")
    public String adminjoinForm() {
        return "pages/admin/adminJoinForm";
    }

    @PostMapping("/admin/join")
    public String adminjoin(AdminUserDto adminUserDto) {
        System.out.println(adminUserDto);

        if (adminUserDto.getAdminCode().equals("juwongenius")) {
            adminService.관리자회원가입(adminUserDto);

            return "redirect:/login-form";
        } else {
            throw new CustomException("관리자코드가 틀렸습니다");
        }
    }

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

    @GetMapping("/s/admin/write-form")
    public String adminNoticeWriteForm() {
        return "pages/post/noticeWriteForm";
    }

    // notice-write는 게시판 합쳐지면 있을 것 같음 일단 보류...
    // notice-detail 도 마찬가지
    // notice-list보기도 마찬가지
    // notice-update 마찬가지
    // notice-delete마찬가지

    // 중복
    @PutMapping("/s/admin/notice-update")
    public String adminNoticeUpdate() {
        return "";
    }

    // 중복
    @GetMapping("/s/admin/notice-delete")
    public String adminNoticeDelete() {
        return "";
    }

    // 중복
    @GetMapping("/s/admin/jarang-delete")
    public String adminJarangDelete() {
        return "";
    }

    // 중복
    @GetMapping("/s/admin/comment-delete")
    public String adminCommentDelete(String id) {
        String[] idParse = id.split(",");
        List<String> ids = new ArrayList<>();
        for (String idSplit : idParse) {
            ids.add(idSplit);
        }
        commentService.관리자댓글삭제(ids);
        return "";
    }

}
