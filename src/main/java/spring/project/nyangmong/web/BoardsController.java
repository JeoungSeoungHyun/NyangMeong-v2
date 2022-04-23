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

    // PageRequest pq = PageRequest.of(page, 3);
    // model.addAttribute("boardswrite", boardsRepository.findAll(pq));
    // return "pages/post/jarangWriteForm";

    // User principal = (User) session.getAttribute("principal");

    // List<CommentResponseDto> comments = new ArrayList<>();

    // for (Comment comment : boardsEntity.getComments()) {
    // CommentResponseDto dto = new CommentResponseDto();
    // dto.setComment(comment);

    // if (principal != null) {
    // if (principal.getId() == comment.getId()) {
    // dto.setAuth(true); // or false
    // } else {
    // dto.setAuth(false); // or false
    // }
    // } else {
    // dto.setAuth(false); // or false
    // }
    // comments.add(dto);
    // }
    // model.addAttribute("comments", comments);
    // model.addAttribute("boardsId", id);
    // return "pages/post/jarangDetail";
    // }

    // 페이지 주기
    // /s 붙었으니까 자동으로 인터셉터가 인증 체크함. (완료)
    @GetMapping("/s/boards/write-form")
    public String writeForm(@RequestParam(defaultValue = "0") Integer page, Model model) {
        PageRequest pq = PageRequest.of(page, 3);
        model.addAttribute("boardswrite", boardsRepository.findAll(pq));
        return "pages/post/jarangWriteForm";
    }

    @GetMapping("/s/notice/write-form")
    public String noticewriteForm(@RequestParam(defaultValue = "0") Integer page, Model model) {
        PageRequest pq = PageRequest.of(page, 3);
        model.addAttribute("noticewrite", boardsRepository.findAll(pq));
        return "pages/post/noticeWriteForm";
    }



}

