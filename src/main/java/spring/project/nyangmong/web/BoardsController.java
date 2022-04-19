package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        for (Comment comment : boardsEntity.getCommentList()) {
            CommentResponseDto commentDto = new CommentResponseDto();
            commentDto.setComment(comment);

            if (principal != null) {
                if (principal.getId() == comment.getUser().getId()) {
                    commentDto.setAuth(true); // or false
                } else {
                    commentDto.setAuth(false); // or false
                }
            } else {
                commentDto.setAuth(false); // or false
            }

            comments.add(commentDto);
        }

        model.addAttribute("comments", comments);
        model.addAttribute("boardId", id);
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

    @GetMapping({"/boards"})
    public String home() {
        return "pages/list/jarangList";
    }

}
