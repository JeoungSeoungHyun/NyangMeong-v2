package spring.project.nyangmong.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.domain.user.UserRepository;
import spring.project.nyangmong.handle.ex.CustomException;
import spring.project.nyangmong.web.dto.members.user.IdFindReqDto;
import spring.project.nyangmong.web.dto.members.user.JoinDto;
import spring.project.nyangmong.web.dto.members.user.PwFindReqDto;
import spring.project.nyangmong.web.dto.members.user.UpdateDto;

@RequiredArgsConstructor
@Service // 컴포넌트 스캔시에 IoC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트임. 기능 모임
public class UserService {
    private final UserRepository userRepository;

    // 아이디 찾기
    @Transactional
    public String 아이디찾기(IdFindReqDto idFindReqDto) {
        // 1. email 이 같은 것이 있는지 체크 (DB)
        Optional<User> userOp = userRepository.findId(idFindReqDto.getEmail());

        // 2. 같은 email이 있으면 DB에서 가져오기
        if (userOp.isPresent()) {
            User userEntity = userOp.get(); // 영속화
            // System.out.println("=====================" + userEntity.getUserId());
            return userEntity.getUserId(); // 아이디 리턴
        } else {
            throw new CustomException("해당 이메일이 존재하지 않습니다.");
        }
    }

    // 비밀번호 찾기
    @Transactional
    public String 패스워드찾기(PwFindReqDto pwFindReqDto) {
        // 1. id, email 이 같은 것이 있는지 체크 (DB)
        Optional<User> userOp = userRepository.findPw(pwFindReqDto.getUserId(), pwFindReqDto.getEmail());

        // 2. 같은 id, email이 있으면 DB에서 가져오기
        if (userOp.isPresent()) {
            User userEntity = userOp.get(); // 영속화
            // System.out.println("=====================" + userEntity.getPassword());
            return userEntity.getPassword(); // 비밀번호 리턴
        } else {
            throw new CustomException("해당 아이디 또는 이메일이 존재하지 않습니다.");
        }
    }

    // 회원정보 데이터 가져오기
    public User 회원정보보기(Integer id) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            return userOp.get();
        } else {
            System.out.println("아이디를 찾을 수 없습니다.");
            return null;
        }
    }

    // 회원정보 수정
    @Transactional
    public void 회원수정(Integer id, UpdateDto updateDto) {

        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            // 영속화된 오브젝트 수정
            User userEntity = userOp.get();
            userEntity.setUserName(updateDto.getUserName());
            userEntity.setPassword(updateDto.getPassword());
            userEntity.setEmail(updateDto.getEmail());
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다.");
        }
    }

    // 회원가입
    @Transactional
    public void 회원가입(JoinDto joinDto) {
        userRepository.save(joinDto.toEntity());
    }

    // 로그인하기
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