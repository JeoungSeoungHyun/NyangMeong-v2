package spring.project.nyangmong.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.service.CommentService;
import spring.project.nyangmong.web.dto.members.comment.CommentResponseDto;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    /* UPDATE */
    @PutMapping({ "/posts/{id}/comments/{id}" })
    public ResponseEntity update(@PathVariable Integer id, @RequestBody CommentResponseDto dto) {
        commentService.댓글수정(id, dto);
        return ResponseEntity.ok(id);
    }
}