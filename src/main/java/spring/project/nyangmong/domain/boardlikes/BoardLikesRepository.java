package spring.project.nyangmong.domain.boardlikes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardLikesRepository extends JpaRepository<BoardLikes, Integer> {

    @Query(value = "SELECT * FROM boardLikes WHERE boardId=:boardId AND userId=:userId", nativeQuery = true)
    Optional<BoardLikes> mFindBoardIdAndUserId(@Param("boardId") Integer boardId, @Param("userId") Integer userId);
}
