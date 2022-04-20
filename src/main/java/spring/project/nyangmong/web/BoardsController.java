package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.web.dto.members.comment.CommentResponseDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {
    private final BoardsService boardsService;
    private final HttpSession session;

    @GetMapping("/boards/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Boards boardsEntity = boardsService.글상세보기(id);
        User principal = (User) session.getAttribute("principal");

        List<CommentResponseDto> comments = new ArrayList<>();
        return "pages/detail/jarangDetail";
    }

    @GetMapping("/s/boards/writeForm")
    public String writeForm() {
        return "pages/post/jarangWriteForm";
    }

    @GetMapping("/notice")
    public String noticeRead() {
        return "pages/post/noticeList";
    }

    @GetMapping({ "/boards" })
    public String home() {
        return "pages/list/jarangList";
    }

    @GetMapping("/s/notice/write-form")
    public String noticeWrite() {
        return "pages/post/noticeWriteForm";
    }

}
