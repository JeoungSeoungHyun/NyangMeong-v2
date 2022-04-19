package spring.project.nyangmong.web.dto.members.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDto {
    private String userName;
    private String password;
    private String email;
}
