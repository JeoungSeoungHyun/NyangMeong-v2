package spring.project.nyangmong.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.web.dto.craw.PlaceDto;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
}