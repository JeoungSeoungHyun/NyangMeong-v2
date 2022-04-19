package spring.project.nyangmong.domain.image;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<PublicDataImage, Integer> {
    @Query(value = "SELECT * FROM PublicDataImage WHERE contentSeq = :contentSeq", nativeQuery = true)
    List<PublicDataImage> ImagecontentSeq(@Param("contentSeq") int contentSeq);

    @Query(value = "SELECT * FROM PublicDataImage WHERE contentSeq = :contentSeq ORDER BY id ASC LIMIT 1 ", nativeQuery = true)
    PublicDataImage showUpImage(@Param("contentSeq") Integer contentSeq);

}
