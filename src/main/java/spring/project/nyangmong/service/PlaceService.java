package spring.project.nyangmong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.web.dto.places.MapSearchDto;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final ImageRepository imageRepository;

    public Places 상세보기(Integer contentSeq) {
        Optional<Places> placesOp = placeRepository.findById(contentSeq);

        if (placesOp.isPresent()) {
            return placesOp.get();
        } else {
            throw new RuntimeException("해당 관광정보를 찾을 수 없습니다");
        }
    }

    public List<Places> 전체보기() {
        List<Places> plist = placeRepository.findAll();
        return plist;
    }

    public boolean 옵션표시(String yesOrNO) {
        if (yesOrNO.equals("Y")) {
            return true;
        }
        return false;
    }

    public List<Places> 총검색(MapSearchDto mapSearchDto) {

        String keyword = mapSearchDto.getKeyword();

        List<String> categorys = new ArrayList<>();

        if (mapSearchDto.getCafe().equals("true")) {
            categorys.add("식음료");
        }
        if (mapSearchDto.getActivity().equals("true")) {
            categorys.add("체험");
        }
        if (mapSearchDto.getHospital().equals("true")) {
            categorys.add("동물병원");
        }
        if (mapSearchDto.getHotel().equals("true")) {
            categorys.add("숙박");
        }
        if (mapSearchDto.getSpot().equals("true")) {
            categorys.add("관광지");
        }
        System.out.println("쿼리검색 카테고리 개수 : " + categorys.size());

        if (categorys.size() == 5) {
            List<Places> list = placeRepository.totalSearch(keyword);
            System.out.println("검색 결과 사이즈 : " + list.size());
            return list;
        } else if (categorys.size() == 4) {
            List<Places> list = placeRepository.totalSearch(keyword, categorys.get(0), categorys.get(1),
                    categorys.get(2),
                    categorys.get(3));
            System.out.println("검색 결과 사이즈 : " + list.size());
            return list;
        } else if (categorys.size() == 3) {
            List<Places> list = placeRepository.totalSearch(keyword, categorys.get(0), categorys.get(1),
                    categorys.get(2));
            System.out.println("검색 결과 사이즈 : " + list.size());
            return list;
        } else if (categorys.size() == 2) {
            List<Places> list = placeRepository.totalSearch(keyword, categorys.get(0), categorys.get(1));
            System.out.println("검색 결과 사이즈 : " + list.size());
            return list;
        } else if (categorys.size() == 1) {
            System.out.println("검색 카테고리 : " + categorys.get(0));
            List<Places> list = placeRepository.totalSearch(keyword, categorys.get(0));
            System.out.println("검색 결과 사이즈 : " + list.size());
            return list;
        }
        return null;
    }
}
