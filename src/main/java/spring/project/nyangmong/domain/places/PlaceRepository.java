package spring.project.nyangmong.domain.places;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Places, Integer> {
    @Query(value = "SELECT * FROM places WHERE contentSeq = :contentSeq", nativeQuery = true)
    Places placesDetail(@Param("contentSeq") int contentSeq);

    @Query(value = "SELECT * FROM places WHERE partName = :partName", countQuery = "SELECT * FROM places WHERE partName = :partName", nativeQuery = true)
    Page<Places> searchPartName(@Param("partName") String partName, Pageable page);

    @Query(value = "SELECT * FROM places WHERE partName = :partName ORDER BY contentSeq ASC LIMIT 4 ", nativeQuery = true)
    List<Places> placeTop4(@Param("partName") String partName);

    @Query(value = "SELECT * FROM places WHERE partName = :partName ORDER BY contentSeq ASC LIMIT 3 ", nativeQuery = true)
    List<Places> placeTop3(@Param("partName") String partName);

    @Query(value = "SELECT * FROM places WHERE keyword Like :keyword OR mainFacility LIKE :keyword  ", countQuery = "SELECT * FROM places WHERE keyword Like :keyword OR mainFacility LIKE :keyword", nativeQuery = true)
    Page<Places> searchPlaces(@Param("keyword") String keyword, Pageable page);

    @Query(value = "SELECT count(*) FROM places WHERE partName = :partName", nativeQuery = true)
    long countPartName(@Param("partName") String partName);

    @Query(value = "SELECT count(*) FROM places WHERE keyword Like :keyword OR mainFacility LIKE :keyword  ", nativeQuery = true)
    long countSearch(@Param("keyword") String keyword);
}