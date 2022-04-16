package spring.project.nyangmong.domain.places;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Places, Integer> {
    @Query(value = "SELECT * FROM places WHERE contentSeq = :contentSeq", nativeQuery = true)
    Places placesDetail(@Param("contentSeq") String contentSeq);
}