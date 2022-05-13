package spring.project.nyangmong.web.dto.members.boards;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WriteJarangDto {
    private String title;
    private String content;
    private MultipartFile thumnailFile;

    // 세션 객체를 받아야 함. 그래서 Dto를 User 오브젝트로 변경해야 save(user)가 가능!!
    public Boards toEntity(User principal, String thumnail) {
        Boards board = new Boards();
        board.setTitle(title);
        board.setContent(content);
        board.setUser(principal);
        board.setPageCount(0); // 여기서 초기값 해주는게 나을 듯 함!!
        board.setThumnail(thumnail);
        board.setClassification("댕냥이 자랑");
        return board;
    }
}