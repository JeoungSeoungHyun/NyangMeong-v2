package spring.project.nyangmong.web.dto.members.boards;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.web.dto.members.comment.CommentResponseDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailResponseDto {
    private Boards boards;
    private List<CommentResponseDto> comments;
    private boolean auth;
    private Integer boardLikesId;
}