package spring.project.nyangmong.service;

import java.util.Optional;

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

    // 펫 정보 등록하기
    @Transactional
    public void 펫등록하기(Integer userId, Pet pet) {
        // 유저아이디가 있으면 save
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            pet.setUser(userEntity);
        } else {
            throw new RuntimeException("해당 유저는 존재하지 않아 등록할 수 없습니다.");
        }
        petRepository.save(pet);
    }

    // 펫 정보 수정
    // 수정페이지에서 펫 정보를 입력할 때 최초 입력은 insert, 이후는 update
    @Transactional
    public void 펫수정(Integer userId, PetUpdateDto petUpdateDto) {

        // 2. 유저아이디와 펫정보가 있는지 확인
        Optional<User> userOp = userRepository.findById(userId);
        Pet petEntity = petRepository.findByuserId(userId);

        // 3. DB에 저장
        if (userOp.isPresent()) {
            // 최초 등록 시 펫 정보 추가 (Insert)
            if (petEntity != null) {
                petEntity.setPetName(petUpdateDto.getPetName());
                petEntity.setPetGender(petUpdateDto.getPetGender());
                petEntity.setPetAge(petUpdateDto.getPetAge());
                petEntity.setPetSpices(petUpdateDto.getPetSpices());
            }
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다.");
        }
    }
}