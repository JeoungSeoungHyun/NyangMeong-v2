package spring.project.nyangmong.domain.places;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Places, Integer> {
    @Query(value = "SELECT * FROM places WHERE contentSeq = :contentSeq", nativeQuery = true)
    Places placesDetail(@Param("contentSeq") int contentSeq);

    @Query(value = "SELECT * FROM places WHERE partName = :partName", countQuery = "SELECT * FROM places WHERE partName = :partName", nativeQuery = true)
    Page<Places> searchPartName(@Param("partName") String partName, Pageable page);

    @Query(value = "SELECT * FROM places WHERE partName = :partName ORDER BY contentSeq ASC LIMIT 4 ", nativeQuery = true)
    List<Places> placeTop4(@Param("partName") String partName);

    @Query(value = "SELECT * FROM places WHERE partName = :partName ORDER BY contentSeq ASC LIMIT 3 ", nativeQuery = true)
    List<Places> placeTop3(@Param("partName") String partName);

    @Query(value = "SELECT * FROM places WHERE keyword Like :keyword OR mainFacility LIKE :keyword  ", countQuery = "SELECT * FROM places WHERE keyword Like :keyword OR mainFacility LIKE :keyword", nativeQuery = true)
    Page<Places> searchPlaces(@Param("keyword") String keyword, Pageable page);

    @Query(value = "SELECT count(*) FROM places WHERE partName = :partName", nativeQuery = true)
    long countPartName(@Param("partName") String partName);

    // 총검색 쿼리
    @Query(value = "SELECT * FROM places WHERE areaName Like %:keyword% OR partName Like %:keyword% OR title Like %:keyword% OR keyword Like %:keyword% OR address Like %:keyword% OR petFacility Like %:keyword%  OR mainFacility Like %:keyword% OR policyCautions Like %:keyword%", nativeQuery = true)
    List<Places> totalSearch(@Param("keyword") String keyword);

    // 4개 category 선택 쿼리
    @Query(value = "SELECT * FROM (SELECT * FROM places WHERE areaName Like %:keyword% OR partName Like %:keyword% OR title Like %:keyword% OR keyword Like %:keyword% OR address Like %:keyword% OR  petFacility Like %:keyword%  OR mainFacility Like %:keyword% OR policyCautions Like %:keyword%) a, (SELECT * FROM places WHERE partName =:partName1 OR  partName =:partName2 OR  partName =:partName3 OR  partName =:partName4)b WHERE a.contentSeq = b.contentSeq", nativeQuery = true)
    List<Places> totalSearch(
            @Param("keyword") String keyword,
            @Param("partName1") String partName1,
            @Param("partName2") String partName2,
            @Param("partName3") String partName3,
            @Param("partName4") String partName4);

    // 3개 category 선택 쿼리
    @Query(value = "SELECT * FROM (SELECT * FROM places WHERE areaName Like %:keyword% OR partName Like %:keyword% OR title Like %:keyword% OR keyword Like %:keyword% OR address Like %:keyword% OR  petFacility Like %:keyword%  OR mainFacility Like %:keyword% OR policyCautions Like %:keyword%) a, (SELECT * FROM places WHERE partName =:partName1 OR  partName =:partName2 OR  partName =:partName3)b WHERE a.contentSeq = b.contentSeq", nativeQuery = true)
    List<Places> totalSearch(
            @Param("keyword") String keyword,
            @Param("partName1") String partName1,
            @Param("partName2") String partName2,
            @Param("partName3") String partName3);

    // 2개 category 선택 쿼리
    @Query(value = "SELECT * FROM (SELECT * FROM places WHERE areaName Like %:keyword% OR partName Like %:keyword% OR title Like %:keyword% OR keyword Like %:keyword% OR address Like %:keyword% OR  petFacility Like %:keyword%  OR mainFacility Like %:keyword% OR policyCautions Like %:keyword%) a, (SELECT * FROM places WHERE partName =:partName1 OR  partName =:partName2)b WHERE a.contentSeq = b.contentSeq", nativeQuery = true)
    List<Places> totalSearch(
            @Param("keyword") String keyword,
            @Param("partName1") String partName1,
            @Param("partName2") String partName2);

    // 1개 category 선택 쿼리
    @Query(value = "SELECT * FROM (SELECT * FROM places WHERE areaName Like %:keyword% OR partName Like %:keyword% OR title Like %:keyword% OR keyword Like %:keyword% OR address Like %:keyword% OR  petFacility Like %:keyword%  OR mainFacility Like %:keyword% OR policyCautions Like %:keyword%) a, (SELECT * FROM places WHERE partName =:partName1)b WHERE a.contentSeq = b.contentSeq", nativeQuery = true)
    List<Places> totalSearch(
            @Param("keyword") String keyword,
            @Param("partName1") String partName1);

}