package spring.project.nyangmong.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.web.dto.craw.PlaceDto;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Places 상세보기(Integer id) {
        Optional<Places> placesOp = placeRepository.findById(id);

        if (placesOp.isPresent()) {
            return placesOp.get();
        } else {
            throw new RuntimeException("해당 관광정보를 찾을 수 없습니다");
        }
    }
}