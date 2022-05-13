package spring.project.nyangmong.web.dto.members.boards;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JarangRespDto {
    private Page<Boards> boards;
    private Integer prev;
    private Integer next;
    private List<Integer> pageNumbers;
}
