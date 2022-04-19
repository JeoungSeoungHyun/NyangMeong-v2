package spring.project.nyangmong.domain.places;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Places, Integer> {
    @Query(value = "SELECT * FROM places WHERE contentSeq = :contentSeq", nativeQuery = true)
    Places placesDetail(@Param("contentSeq") int contentSeq);

    @Query(value = "SELECT * FROM places WHERE partName = :partName", nativeQuery = true)
    List<Places> searchPartName(@Param("partName") String partName);

    @Query(value = "SELECT * FROM places WHERE partName = :partName ORDER BY contentSeq ASC LIMIT 4 ", nativeQuery = true)
    List<Places> placeTop4(@Param("partName") String partName);

    @Query(value = "SELECT * FROM places WHERE partName = :partName ORDER BY contentSeq ASC LIMIT 3 ", nativeQuery = true)
    List<Places> placeTop3(@Param("partName") String partName);

    @Query(value = "SELECT * FROM places WHERE keyword Like :keyword OR mainFacility LIKE :keyword  ", nativeQuery = true)
    List<Places> searchPlaces(@Param("keyword") String keyword);

    @Query(value = "SELECT count(*) FROM places WHERE partName = :partName", nativeQuery = true)
    long countPartName(@Param("partName") String partName);
}