package spring.project.nyangmong.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.pet.Pet;
import spring.project.nyangmong.domain.pet.PetRepository;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.domain.user.UserRepository;
import spring.project.nyangmong.web.dto.members.pet.PetUpdateDto;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    // 펫 정보 가져오기
    public Pet 펫정보보기(Integer userId) {
        Pet petEntity = petRepository.findByuserId(userId);
        return petEntity;
    }

    // 펫 정보 등록 및 수정
    @Transactional
    public void 펫수정(Integer userId, PetUpdateDto petUpdateDto) {
        UUID uuid = UUID.randomUUID(); // 범용 고유 식별자
        String requestFileName = petUpdateDto.getFile().getOriginalFilename();
        // System.out.println("전송받은 파일명 : " + requestFileName);

        String petImgurl = uuid + "_" + requestFileName;
        // jar 파일로 구우면 안 돌아감
        Path filePath = Paths.get("src/main/resources/static/upload/" + petImgurl);
        // System.out.println(filePath);

        try {
            Files.write(filePath, petUpdateDto.getFile().getBytes()); // (파일 경로, 이미지(바이트)) - 어느 경로에 어떤 파일 쓸건지

            // DB에는 이미지 경로를 저장
            Optional<User> userOp = userRepository.findById(userId);
            Pet petEntity = petRepository.findByuserId(userId);
            if (userOp.isPresent()) {
                if (petEntity == null) {
                    // 최초 등록 시 펫 정보 추가 (Insert)
                    petRepository.save(petUpdateDto.toEntity(petImgurl, userOp.get()));
                } else {
                    // 이미 등록된 유저면 펫 정보 수정 (Update)
                    petEntity.setPetName(petUpdateDto.getPetName());
                    petEntity.setPetGender(petUpdateDto.getPetGender());
                    petEntity.setPetAge(petUpdateDto.getPetAge());
                    petEntity.setPetSpices(petUpdateDto.getPetSpices());
                    petEntity.setPetImgurl(petImgurl);
                }
            } else {
                throw new RuntimeException("아이디를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
