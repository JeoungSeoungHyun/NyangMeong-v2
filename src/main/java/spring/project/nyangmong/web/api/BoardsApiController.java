package spring.project.nyangmong.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.web.dto.members.ResponseDto;

@RequiredArgsConstructor
@RestController
public class BoardsApiController {
    private final BoardsService boardsService;
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

}