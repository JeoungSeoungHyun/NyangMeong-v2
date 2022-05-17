package spring.project.nyangmong.web.dto.members.admin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUserDto {
    // 회원가입 시 유효성 체크를 위한 validation 어노테이션 사용

    @Pattern(regexp = "[a-zA-Z1-9]{4,20}", message = "유저아이디는 한글이 들어갈 수 없습니다.")
    @NotBlank // 공백 불가능
    @Size(min = 4, max = 12, message = "아이디는 4자 이상 12자 이하로 입력해주세요.")
    private String userId;

    @NotBlank
    @Size(min = 4, max = 12, message = "닉네임은 4자 이상 12자 이하로 입력해주세요.")
    private String userName;

    @NotBlank
    @Size(min = 4, max = 12, message = "비밀번호는 4자 이상 12자 이하로 입력해주세요.")
    private String password;

    @Size(min = 8, max = 100)
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 11, max = 11, message = "정확한 관리자 코드를 입력해주세요.")
    private String adminCode;

    public User toEntity() {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserAuth("true");
        return user;
    }
}
