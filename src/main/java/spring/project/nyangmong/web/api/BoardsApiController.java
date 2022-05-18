package spring.project.nyangmong.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.service.PlaceLikesService;
import spring.project.nyangmong.web.dto.members.ResponseDto;

@RequiredArgsConstructor
@RestController
public class BoardsApiController {
    private final BoardsService boardsService;
    private final PlaceLikesService placelikesService;
    private final HttpSession session;

    // @GetMapping("/s/api/boards/{id}")
    // public ResponseDto<?> list(Integer page) {
    // Page<Boards> boards = boardsService.게시글목록(page);
    // 응답의 DTO를 만들어서 <- boards 를 옮김. (라이브러리 있음)
    // return new ResponseDto<>(1, "성공", boards);
    // }

    @DeleteMapping("/s/api/boards/{id}")
    public ResponseDto<?> deleteById(@PathVariable Integer id) {
        boardsService.글삭제하기(id);

        return new ResponseDto<>(1, "성공", null);
    }

    // 공지사항 글 수정
    @PutMapping("/s/api/notice/{id}")
    public String noticeUpdate() {
        return null;
    }

    // 공지사항 글 삭제
    @DeleteMapping("/s/api/notice/{id}")
    public String noticeDelete() {
        return null;
    }

    //자랑 게시판 검색
       @GetMapping("/api/jarang/keywordlist")
    public ResponseEntity<?> list(String mykeyword, Integer page,
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Boards> boards = boardsService.검색글목록보기(mykeyword, pageable);

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }


    // @PostMapping("/s/user/{id}/boardlike")
    // public void likes(@PathVariable long boardsId, Authentication
    // authentication){
    // placelikesService.placelikes(boardsId, authentication.getUsername());
    // }

    // @DeleteMapping("/s/user/{id}/unboardlike")
    // public void unLikes(@PathVariable long boardsId, Authentication
    // authentication) {
    // placelikesService.unplacelikes(boardsId, authentication.getUsername());
    // }
}