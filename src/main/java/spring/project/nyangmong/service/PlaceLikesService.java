package spring.project.nyangmong.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.placelikes.PlaceLikesRepository;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class PlaceLikesService {

    private final PlaceLikesRepository placelikesRepository;
    private final UserRepository userRepository;

    @Transactional
    public void placelikes(long boardsId, String loginEmail) {
        User user = userRepository.findUserByEmail(loginEmail);
        placelikesRepository.placelikes(boardsId, user.getId());
    }

    @Transactional
    public void unplacelikes(long boardsId, String loginEmail) {
        User user = userRepository.findUserByEmail(loginEmail);
        placelikesRepository.unplacelikes(boardsId, user.getId());
    }
}
