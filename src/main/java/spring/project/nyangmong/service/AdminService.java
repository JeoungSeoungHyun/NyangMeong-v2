package spring.project.nyangmong.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.user.UserRepository;
import spring.project.nyangmong.web.dto.members.admin.AdminUserDto;


@Service
@RequiredArgsConstructor
public class AdminService {
    
    private final UserRepository userRepository;
    
    @Transactional
    public void 관리자회원가입(AdminUserDto adminUserDto) {
        userRepository.save(adminUserDto.toEntity());
        
    }
}
