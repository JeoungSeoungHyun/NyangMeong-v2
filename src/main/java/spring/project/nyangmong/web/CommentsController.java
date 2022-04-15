package spring.project.nyangmong.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.CommentService;
import spring.project.nyangmong.web.dto.members.ResponseDto;

@RequiredArgsConstructor
@Controller
public class CommentsController {
    private final CommentService commentService;
    private final HttpSession session;

    // data를 리턴하면 CommentApiController를 원래 만들어야 한다.
    @DeleteMapping("/s/api/comment/{id}")
    public @ResponseBody ResponseDto<?> deleteById(@PathVariable Integer id) {

        // 세션의 id와 comment의 userId와 비교
        User principal = (User) session.getAttribute("principal");

        commentService.댓글삭제(id, principal);
        return new ResponseDto<>(1, "성공", null);
    }

    @PostMapping("/s/post/{postId}/comment")
    public String write(@PathVariable Integer boardstId, Comment comment) { // x-www-form~~
        User principal = (User) session.getAttribute("principal");

        comment.setUser(principal);
        commentService.댓글쓰기(comment, boardstId);
        return "redirect:/post/" + boardstId;
    }

}
