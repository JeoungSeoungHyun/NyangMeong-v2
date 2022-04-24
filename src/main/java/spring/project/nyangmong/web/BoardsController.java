package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.boards.BoardsRepository;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.web.dto.members.comment.CommentResponseDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {
    private final BoardsService boardsService;
    private final HttpSession session;
    private final BoardsRepository boardsRepository;

    @GetMapping("/boards/{id}")
    public String detail(@PathVariable Integer id, Model model) {
              return "pages/detail/jarangDetail";

    }

    @GetMapping("/notice/{id}")
    public String noticedetail(@PathVariable Integer id, Model model) {
        PageRequest pq = PageRequest.of(id, 3);
        // Boards boardsEntity = boardsService.글상세보기(id);
        // List<Boards> boards = boardsRepository.findAll(pq);
        // WriteDto dto = new WriteDto();
        // dto.setContent(boards);
        model.addAttribute("noticedetail", boardsRepository.findAll(pq));
        return "pages/post/noticeDetail";
    }


    // 댕냥이 자랑 글쓰기 페이지 이동
    // /s 붙었으니까 자동으로 인터셉터가 인증 체크함. (완료)
    @GetMapping("/s/boards/write-form")
    public String writeForm() {
        return "pages/post/jarangWriteForm";
    }

    // 댕냥이 자랑 글리스트 전달 메서드 (페이징 처리 필요)
    @GetMapping("/boards")
    public String list(@RequestParam(defaultValue = "0") Integer page, Model model) {
        PageRequest pq = PageRequest.of(page, 10);
        Page<Boards> boards = boardsService.게시글목록(page);
        // 응답의 DTO를 만들어서 <- posts 를 옮김. (라이브러리 있음)
        model.addAttribute("board", boards);
        return "/pages/post/jarangList";
    }

    @GetMapping("/notice")
    public String notice() {
        return "/pages/post/noticeList";
    }

}

