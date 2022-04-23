package spring.project.nyangmong.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    // 펫 정보를 가져오기
    @Query(value = "SELECT * FROM pet WHERE userId = :userId", nativeQuery = true)
    Pet findByuserId(@Param("userId") int userId);
}