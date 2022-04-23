package spring.project.nyangmong.web.dto.members.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUserDto {
    
    private String userId;
    private String userName;
    private String password;
    private String email;
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
