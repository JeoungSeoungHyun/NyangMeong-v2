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

    //관리자 - 게시글 상세페이지에서 댓글삭제
    @DeleteMapping("/s/api/admin/comment")
    public @ResponseBody ResponseDto<?> deleteAll() {
        commentService.댓글전체삭제();
        return new ResponseDto<>(1,"성공", null);
    }

    @PostMapping("/s/boards/{boardsId}/comment")
    public String write(@PathVariable Integer boardsId, Comment comment) {
        User principal = (User) session.getAttribute("principal");
        comment.setUser(principal);
        commentService.댓글쓰기(comment, boardsId);
        return "redirect:/boards/" + boardsId;
    }

}