package spring.project.nyangmong.web.dto.members.boards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeListRespDto {

    private Integer num;
    private Boards boards;
}
