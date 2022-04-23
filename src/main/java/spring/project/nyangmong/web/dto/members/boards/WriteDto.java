package spring.project.nyangmong.web.dto.members.boards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WriteDto {
    private String title;
    private String content;

    // 세션 객체를 받아야 함. 그래서 Dto를 User 오브젝트로 변경해야 save(user)가 가능!!
    public Boards toEntity(User principal) {
        // System.out.println("ㅁㄴ아러ㅏㅣㅁㅇ널 : " + principal.getPassword());
        Boards board = new Boards();
        board.setTitle(title);
        // System.out.println("ㅁㄴ아러ㅏㅣㅁㅇ널 : " + board);
        board.setContent(content);
        // System.out.println("ㅁㄴ아러ㅏㅣㅁㅇ널 : " + board);
        board.setUser(principal);
        // System.out.println("ㅁㄴ아러ㅏㅣㅁㅇ널 : " + principal.getPassword());
        board.setPageCount(0); // 여기서 초기값 해주는게 나을 듯 함!!
        return board;
    }
}