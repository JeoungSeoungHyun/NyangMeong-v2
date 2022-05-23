package spring.project.nyangmong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boardlikes.BoardLikes;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.fav.Fav;
import spring.project.nyangmong.domain.fav.FavRepository;
import spring.project.nyangmong.domain.image.ImageRepository;
import spring.project.nyangmong.domain.placelikes.PlaceLikes;
import spring.project.nyangmong.domain.placelikes.PlaceLikesRepository;
import spring.project.nyangmong.domain.places.PlaceRepository;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.handle.ex.CustomApiException;
import spring.project.nyangmong.web.dto.places.MapSearchDto;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final ImageRepository imageRepository;
    private final PlaceLikesRepository placeLikesRepository;
    private final FavRepository favRepository;

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

    public Page<Places> 분류검색(String partName, Pageable page) {
        Page<Places> pList = placeRepository.searchPartName(partName, page);
        return pList;
    }

    public boolean 옵션표시(String yesOrNO) {
        if (yesOrNO.equals("true")) {
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

    // 좋아요
    @Transactional
    public PlaceLikes 좋아요(Integer placesId, User principal) {
        PlaceLikes placeLikes = new PlaceLikes();

        // 장소 찾기
        Places placesEntity = mFindPlaces(placesId);

        placeLikes.setUser(principal);
        placeLikes.setPlaces(placesEntity);

        return placeLikesRepository.save(placeLikes);
    }

    // 좋아요 취소
    @Transactional
    public boolean 좋아요취소(Integer placeLikesId, User principal) {

        // 좋아요 정보 확인
        PlaceLikes placeLikesEntity = mFindPlaceLikes(placeLikesId);

        // 좋아요 취소 권한 확인
        if (principal.getId() == placeLikesEntity.getUser().getId()) {
            placeLikesRepository.delete(placeLikesEntity);
            return true;
        } else {
            throw new CustomApiException("좋아요 취소 권한이 없습니다.");
        }
    }

    // 즐겨찾기
    @Transactional
    public Fav 즐겨찾기(Integer placesId, User principal) {
        Fav fav = new Fav();

        // 장소 찾기
        Places placesEntity = mFindPlaces(placesId);

        fav.setUser(principal);
        fav.setPlaces(placesEntity);

        return favRepository.save(fav);
    }

    // 즐겨찾기 취소
    @Transactional
    public boolean 즐겨찾기취소(Integer favId, User principal) {

        // 즐겨찾기 정보 확인
        Fav favEntity = mFindFav(favId);

        // 즐겨찾기 취소 권한 확인
        if (principal.getId() == favEntity.getUser().getId()) {
            favRepository.delete(favEntity);
            return true;
        } else {
            throw new CustomApiException("즐겨찾기 취소 권한이 없습니다.");
        }
    }

    // 장소 찾기
    private Places mFindPlaces(Integer placesId) {
        Optional<Places> placesOp = placeRepository.findById(placesId);

        if (placesOp.isPresent()) {
            return placesOp.get();
        } else {
            throw new CustomApiException("해당 장소정보가 없습니다.");
        }
    }

    // 장소 좋아요 찾기
    private PlaceLikes mFindPlaceLikes(Integer placeLikesId) {
        Optional<PlaceLikes> placeLikesOp = placeLikesRepository.findById(placeLikesId);

        if (placeLikesOp.isPresent()) {
            return placeLikesOp.get();
        } else {
            throw new CustomApiException("해당 좋아요 정보가 없습니다.");
        }
    }

    // 장소 즐겨찾기 찾기
    private Fav mFindFav(Integer favId) {
        Optional<Fav> favOp = favRepository.findById(favId);

        if (favOp.isPresent()) {
            return favOp.get();
        } else {
            throw new CustomApiException("해당 즐겨찾기 정보가 없습니다.");
        }
    }
}
