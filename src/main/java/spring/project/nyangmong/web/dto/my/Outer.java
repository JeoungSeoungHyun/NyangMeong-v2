package spring.project.nyangmong.web.dto.my;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Outer {

    private List<ResultList> resultList;
    private Integer totalCount;
    private String message;
}
