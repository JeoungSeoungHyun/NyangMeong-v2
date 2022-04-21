package spring.project.nyangmong.domain.placelikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.user.User;

// - 원래 PlaceLikes의 엔티티에서는 JPA사용시 Boards, User 객체를 찾아 사용해 주어야 한다.

// -
// 이 과정이
// 귀찮으므로 id정보로
// 직접 기능을
// 구현할 수
// 있는 쿼리를
// 만들어 주었다.
public interface PlaceLikesRepository extends JpaRepository<PlaceLikes, Long> {
    PlaceLikes findPlaceLikesByBoardsAndUser(Boards boards, User user);

    @Modifying
    @Query(value = "INSERT INTO PlaceLikes(boards_id, user_id) VALUES(:boardsId, :userId)", nativeQuery = true)
    void placelikes(long boardsId, long userId);

    @Modifying
    @Query(value = "DELETE FROM PlaceLikes WHERE boards_id = :boardsId AND user_id = :userId", nativeQuery = true)
    void unplacelikes(long boardsId, long userId);
}
