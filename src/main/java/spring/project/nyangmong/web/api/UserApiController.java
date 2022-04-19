package spring.project.nyangmong.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boardlikes.BoardLikes;
import spring.project.nyangmong.domain.fav.Fav;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.UserService;
import spring.project.nyangmong.web.dto.members.ResponseDto;
import spring.project.nyangmong.web.dto.members.user.JoinDto;
import spring.project.nyangmong.web.dto.members.user.LoginDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;

}
