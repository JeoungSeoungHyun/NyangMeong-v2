package spring.project.nyangmong.web.dto.members.comment;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.comment.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Page<Comment> comment;
    private Integer prev;
    private Integer next;
    private List<Integer> pageNumbers;
}
