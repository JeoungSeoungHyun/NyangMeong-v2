package spring.project.nyangmong.web.dto.members.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PwFindReqDto {

    @Size(max = 20)
    @NotBlank
    private String userId;

    @Size(max = 60)
    @Email
    @NotBlank
    private String email;
}