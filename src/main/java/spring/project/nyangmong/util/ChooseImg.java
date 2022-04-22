package spring.project.nyangmong.util;

import java.util.ArrayList;
import java.util.List;

import spring.project.nyangmong.domain.places.Places;

public class ChooseImg {

    public static List<String> imgList(List<Places> places) {
        System.out.println("사이즈 : " + places.size());
        List<String> images = new ArrayList<>();
        for (Places place : places) {
            System.out.println("타이틀 : " + place.getTitle());
            images.add(place.getImageList().get(0).getImgurl());
        }
        System.out.println("이미지 : " + images.size());
        return images;
    }
}
