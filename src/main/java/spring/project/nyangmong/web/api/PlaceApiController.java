package spring.project.nyangmong.web.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.service.PlaceService;
import spring.project.nyangmong.util.MakePoints;
import spring.project.nyangmong.web.dto.places.MapSearchDto;

@RequiredArgsConstructor
@RestController
public class PlaceApiController {
    private final PlaceService placeService;

    @GetMapping("/api/place/points")
    public ResponseEntity<?> loadPoints() {

        List<Places> places = placeService.전체보기();

        MakePoints mp = new MakePoints();

        List<List<String>> points = mp.make(places);

        for (Places place : places) {
            List<String> point = new ArrayList<>();
            point.add(place.getLatitude());
            point.add(place.getLongitude());
            points.add(point);
        }

        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @PostMapping("/api/place/search")
    public ResponseEntity<?> totalSearch(@RequestBody MapSearchDto mapsearchDto) {

        System.out.println("응답Dto : " + mapsearchDto);

        System.out.println("keyword : " + mapsearchDto.getKeyword());

        List<Places> places = placeService.총검색(mapsearchDto);

        // System.out.println("plcaes크기 : " + places.size());

        MakePoints mp = new MakePoints();

        List<List<String>> points = mp.make(places);

        // System.out.println("points : " + points);

        return new ResponseEntity<>(points, HttpStatus.OK);
    }
}
