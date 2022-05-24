package spring.project.nyangmong.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.domain.user.UserRepository;
import spring.project.nyangmong.util.UtilFileUpload;
import spring.project.nyangmong.web.dto.members.user.IdFindReqDto;
import spring.project.nyangmong.web.dto.members.user.PwFindReqDto;
import spring.project.nyangmong.web.dto.members.user.UpdateDto;

@RequiredArgsConstructor
@Service // 컴포넌트 스캔시에 IoC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트임. 기능 모임
public class UserService {
    private final UserRepository userRepository;

    // 프로필 사진 변경하기
    @Transactional
    public void 프로필이미지변경(Integer id, MultipartFile file, HttpSession session) {
        // 1. 파일을 upload 폴더에 저장완료
        String userImgurl = UtilFileUpload.write(file);

        // 2. 해당 경로를 User 테이블에 update 하면 됨.
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.setUserImgurl(userImgurl);
            // 세션값 변경
            session.setAttribute("principal", userEntity);
        }
    } // 영속화된 userEntity 변경 후 더티체킹완료됨.

    // 회원 탈퇴하기
    @Transactional
    public void 회원탈퇴(Integer id) {
        userRepository.deleteById(id);
    }

    // 아이디 찾기
    @Transactional
    public String 아이디찾기(IdFindReqDto idFindReqDto) {
        // 1. email 이 같은 것이 있는지 체크 (DB)
        Optional<User> userOp = userRepository.findId(idFindReqDto.getEmail());

        // 2. 같은 email이 있으면 DB에서 가져오기
        if (userOp.isPresent()) {
            User userEntity = userOp.get(); // 영속화
            return userEntity.getUserId(); // 아이디 리턴
        } else {
            return null;
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
            return userEntity.getPassword(); // 비밀번호 리턴
        } else {
            return null;
        }
    }

    // 회원정보 데이터 가져오기
    public User 회원정보보기(Integer id) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            return userOp.get();
        } else {
            return null;
        }
    }

    // 회원정보 수정
    @Transactional
    public void 회원수정(Integer id, UpdateDto updateDto, HttpSession session) {

        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            // 영속화된 오브젝트 수정
            User userEntity = userOp.get();
            userEntity.setUserName(updateDto.getUserName());
            userEntity.setPassword(updateDto.getPassword());
            userEntity.setEmail(updateDto.getEmail());
            session.setAttribute("principal", userEntity); // 세션 변경하기
        }
    }

    // 회원가입
    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    // 로그인하기
    public User 로그인(User user) {
        // 로그인 처리 쿼리를 JPA에서 제공해주지 않는다.
        // SELECT * FROM user WHERE username=:username AND password = :password
        User userEntity = userRepository.mLogin(user.getUserId(), user.getPassword());
        return userEntity;
    }

    // 회원가입 시 유저아이디 중복체크하기
    public boolean 유저아이디중복체크(String userId) {
        Optional<User> userOp = userRepository.findByUserId(userId);

        if (userOp.isPresent()) {
            return false; // 이미 아이디 있으면 false
        } else {
            return true; // 아이디 없으면 true
        }
    }

    // 회원가입 시 이메일 중복체크하기
    public boolean 이메일중복체크(String email) {
        Optional<User> userOp = userRepository.findByEmail(email);

        if (userOp.isPresent()) {
            return false; // 이미 이메일 있으면 false
        } else {
            return true; // 이메일 없으면 true
        }
    }
}