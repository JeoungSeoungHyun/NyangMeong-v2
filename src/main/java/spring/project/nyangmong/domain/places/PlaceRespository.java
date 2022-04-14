package spring.project.nyangmong.domain.places;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRespository extends JpaRepository<Places, Integer> {

}
