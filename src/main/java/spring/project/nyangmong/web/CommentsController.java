package spring.project.nyangmong.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
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
    @DeleteMapping("/s/api/comment/{id}/delete")
    public @ResponseBody ResponseDto<?> deleteById(@PathVariable Integer id) {
        // 세션의 id와 comment의 userId와 비교
        User principal = (User) session.getAttribute("principal");
        commentService.댓글삭제(id, principal);
        return new ResponseDto<>(1, "성공", null);
    }

    @PostMapping("/s/boards/{boardsId}/comment")
    public String write(@PathVariable Integer boardsId, Comment comment) {
        User principal = (User) session.getAttribute("principal");
        comment.setUser(principal);
        commentService.댓글쓰기(comment, boardsId);
        return "redirect:/boards/" + boardsId;
    }

    // 댓글 수정
    // @PutMapping("/s/api/comment/{id}/update")
    // public @ResponseBody ResponseDto<String> update(@PathVariable Integer id) {

    // // 인증
    // User principal = (User) session.getAttribute("principal");
    // if (principal == null) {
    // return new ResponseDto<String>(-1, "로그인 되지 않았습니다.", null);
    // }

    // // 권한
    // Boards boardsEntity = boardsService.글상세보기(id);

    // if (boardsEntity.getUser().getId() != principal.getId()) {
    // return new ResponseDto<String>(-1, "해당 댓글을 수정할 권한이 없습니다.", null);
    // }

    // commentService.댓글수정(boards, id);

    // return new ResponseDto<String>(1, "수정 성공", null);
    // }
}