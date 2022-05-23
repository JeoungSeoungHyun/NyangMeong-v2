package spring.project.nyangmong.domain.placelikes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceLikesRepository extends JpaRepository<PlaceLikes, Integer> {

    @Query(value = "SELECT * FROM placeLikes WHERE userId=:userId AND placesId=:placesId", nativeQuery = true)
    Optional<PlaceLikes> mFindUserIdAndPlacesId(@Param("userId") Integer userId, @Param("placesId") Integer placesId);

}