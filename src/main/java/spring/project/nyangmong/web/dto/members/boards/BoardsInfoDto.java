package spring.project.nyangmong.web.dto.members.boards;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
