package spring.project.nyangmong.util;

import java.util.ArrayList;
import java.util.List;

import spring.project.nyangmong.domain.places.Places;

public class MakePoints {

    public List<List<String>> make(List<Places> places) {
        List<List<String>> points = new ArrayList<>();

        for (Places place : places) {
            List<String> point = new ArrayList<>();
            point.add(place.getLatitude());
            point.add(place.getLongitude());
            points.add(point);
        }
        return points;
    }
}
