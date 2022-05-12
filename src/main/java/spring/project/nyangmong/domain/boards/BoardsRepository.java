package spring.project.nyangmong.domain.boards;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.project.nyangmong.domain.user.User;

// Repository는 interface로 만들어야 한다.
// 내부에 @Repository 가 구현되어 있어서 생략 가능하다.
public interface BoardsRepository extends JpaRepository<Boards, Integer> {

    List<Boards> findBoardsByUser(User user);

    Boards findBoardsById(long id);

    void deleteBoardsById(long id);

    // 댕냥이 자랑 리스트 찾기
    @Query(value = "SELECT * FROM boards WHERE classification='댕냥이 자랑' ", nativeQuery = true)
    Page<Boards> listJarang(Pageable pq);

    // 공지사항 리스트 찾기
    @Query(value = "SELECT ROW_NUMBER() OVER() AS rownum,b.* FROM boards b WHERE classification='공지사항' ", nativeQuery = true)
    List<Boards> listNotice(Pageable pq);

}