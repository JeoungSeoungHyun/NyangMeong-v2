package spring.project.nyangmong.web.dto.members.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.comment.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponseDto {
    private Comment comment;
    private boolean auth;
}