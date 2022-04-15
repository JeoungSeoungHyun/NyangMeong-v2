package spring.project.nyangmong.web.dto.members.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinDto {
    private String userName;
    private String password;
    private String phoneNum;

    public User toEntity() {
        User user = new User();
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setPhoneNum(this.phoneNum);
        return user;
    }
}
