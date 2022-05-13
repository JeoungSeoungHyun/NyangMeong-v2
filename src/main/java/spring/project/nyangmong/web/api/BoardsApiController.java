package spring.project.nyangmong.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.service.PlaceLikesService;
import spring.project.nyangmong.web.dto.members.ResponseDto;
import spring.project.nyangmong.web.dto.members.boards.WriteJarangDto;

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

    // 자랑 UPDATE 글수정 /post/{id} - 글상세보기 페이지가기 - 인증 O
    @PutMapping("/s/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
            WriteJarangDto writeJarangDto) {

        User principal = (User) session.getAttribute("principal");

        boardsService.글수정하기(writeJarangDto, id, principal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

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