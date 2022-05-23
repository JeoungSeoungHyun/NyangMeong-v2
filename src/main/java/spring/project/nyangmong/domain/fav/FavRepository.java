package spring.project.nyangmong.domain.fav;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavRepository extends JpaRepository<Fav, Integer> {

    @Query(value = "SELECT * FROM fav WHERE userId=:userId AND placesId=:placesId", nativeQuery = true)
    Optional<Fav> mFindUserIdAndPlacesId(@Param("userId") Integer userId, @Param("placesId") Integer placesId);

}
