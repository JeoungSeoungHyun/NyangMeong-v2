package spring.project.nyangmong.web.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;

@RequiredArgsConstructor
@RestController
public class PlaceApiController {

    private final PlaceRepository placeRepository;

    @GetMapping("/api/place/points")
    public ResponseEntity<?> loadPoints() {
        List<List<String>> points = new ArrayList<>();

        List<Places> places = placeRepository.findAll();

        for (Places place : places) {
            List<String> point = new ArrayList<>();
            point.add(place.getLatitude());
            point.add(place.getLongitude());
            points.add(point);
        }

        return new ResponseEntity<>(points, HttpStatus.OK);
    }

}
