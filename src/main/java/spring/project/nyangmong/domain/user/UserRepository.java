package spring.project.nyangmong.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE userId = :userId AND password = :password", nativeQuery = true)
    User mLogin(@Param("userId") String userId, @Param("password") String password);

    boolean existsByuserId(String userId);

    // 이메일로 아이디 찾기
    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    Optional<User> findId(@Param("email") String email);

    // 아이디와 이메일로 비밀번호 찾기
    @Query(value = "SELECT * FROM user WHERE userId = :userId AND email = :email", nativeQuery = true)
    Optional<User> findPw(@Param("userId") String userId, @Param("email") String email);

    User findUserByEmail(String email);

    User findUserById(Long id);
}