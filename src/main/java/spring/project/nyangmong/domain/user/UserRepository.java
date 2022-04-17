package spring.project.nyangmong.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE userId = :userId AND password = :password", nativeQuery = true)
    User mLogin(@Param("userId") String userId, @Param("password") String password);

    boolean existsByuserId(String userId);
}
