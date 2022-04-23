package spring.project.nyangmong.web.dto.members.boards;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardsInfoDto {
    private long id;
    private String text;
    private String tag;
    private LocalDateTime createdate;
    private long likesCount;
    private boolean likeState;
    private boolean uploader;
    private String postImgUrl;
}