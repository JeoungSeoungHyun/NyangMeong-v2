package spring.project.nyangmong.web.dto.count;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountDto {
    private List<ResultList> resultList;
    private Integer totalCount;
    private String message;
}