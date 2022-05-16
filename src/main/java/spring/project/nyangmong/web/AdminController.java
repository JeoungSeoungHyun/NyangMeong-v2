package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.handle.ex.CustomException;
import spring.project.nyangmong.service.AdminService;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.service.CommentService;
import spring.project.nyangmong.web.dto.members.admin.AdminUserDto;
import spring.project.nyangmong.web.dto.members.boards.JarangRespDto;
import spring.project.nyangmong.web.dto.members.boards.WriteNoticeDto;
import spring.project.nyangmong.web.dto.members.comment.CommentDto;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final CommentService commentService;
    private final BoardsService boardsService;
    private final HttpSession session;

    private final AdminService adminService;

    // 관리자 회원가입 페이지
    @GetMapping("/admin/join-form")
    public String adminjoinForm() {
        return "pages/admin/adminJoinForm";
    }

    // 관리자 회원가입
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

    // 관리자 메인페이지
    @GetMapping("/s/admin/main")
    public String adminMain() {
        return "pages/admin/adminMain";
    }

    // 공지사항 관리페이지
    @GetMapping("/s/admin/notice-manage")
    public String adminNotice(@RequestParam(defaultValue = "0") Integer page, Model model) {
        List<Boards> boards = boardsService.공지사항목록(page);
        // 응답의 DTO를 만들어서 <- posts 를 옮김. (라이브러리 있음)
        model.addAttribute("notice", boards);
        return "/pages/admin/noticeManage";
    }

    // 댕냥자랑 관리페이지
    @GetMapping("/s/admin/jarang-manage")
    public String adminJarang(@RequestParam(defaultValue = "0") Integer page, Model model) {
        JarangRespDto dto = boardsService.게시글목록(page);
        // 응답의 DTO를 만들어서 <- posts 를 옮김. (라이브러리 있음)
        model.addAttribute("jarang", dto);
        return "/pages/admin/JarangManage";
    }

     // 댓글관리게시판
    @GetMapping("/s/admin/comment-manage")
    public String adminComment(@RequestParam(defaultValue = "0") Integer page, Model model) {
        CommentDto dto = commentService.댓글목록(page);
        // 응답의 DTO를 만들어서 <- posts 를 옮김. (라이브러리 있음)
        model.addAttribute("comment", dto);
        return "/pages/admin/commentManage";
    }

    // 공지사항 쓰기 페이지
    @GetMapping("/s/admin/write-form")
    public String adminNoticeWriteForm() {
        return "pages/post/noticeWriteForm";
    }

    // 공지사항 쓰기
    @PostMapping("/s/notice")
    public String writeNotice(WriteNoticeDto writeDto) {
        // System.out.println("Dto : " + writeDto);
        User principal = (User) session.getAttribute("principal");

        boardsService.공지사항쓰기(writeDto, principal);

        return "redirect:/notice";
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
        return "redirect:/s/admin/comment-manage";
    }

}
