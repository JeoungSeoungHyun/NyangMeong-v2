package spring.project.nyangmong.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.domain.user.UserRepository;
import spring.project.nyangmong.web.dto.members.user.JoinDto;

@RequiredArgsConstructor
@Service // 컴포넌트 스캔시에 IoC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트임. 기능 모임
public class UserService {
    private final UserRepository userRepository;

    // 회원정보 데이터 가져오기
    public User 회원정보(Integer id) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            return userOp.get();
        } else {
            System.out.println("아이디를 찾을 수 없습니다.");
            return null;
        }
    }

    // 회원가입
    @Transactional
    public void 회원가입(JoinDto joinDto) {
        userRepository.save(joinDto.toEntity());
    }

    // 테스트중
    // 로그인
    public User 로그인(User user) {
        // 로그인 처리 쿼리를 JPA에서 제공해주지 않는다.
        // SELECT * FROM user WHERE username=:username AND password = :password
        User userEntity = userRepository.mLogin(user.getUserId(), user.getPassword());
        if (userEntity == null) {
            throw new RuntimeException("아이디 또는 패스워드가 틀렸습니다.");
        } else {
            return userEntity;
        }
    }

    public boolean checkuserNameDuplicate(String userId) {
        return userRepository.existsByuserId(userId);
    }
}