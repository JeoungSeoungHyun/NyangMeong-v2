package spring.project.nyangmong.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import spring.project.nyangmong.web.dto.members.boards.DetailResponseDto;
import spring.project.nyangmong.web.dto.members.boards.NoticeListRespDto;
import spring.project.nyangmong.web.dto.members.comment.CommentResponseDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {
    private final BoardsService boardsService;
    private final HttpSession session;
    private final BoardsRepository boardsRepositoty;

    @GetMapping("/boards/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Boards boardsEntity = boardsService.글상세보기(id);
        User principal = (User) session.getAttribute("principal");

                boolean auth = false;
                if (principal != null) {
                    if (principal.getId() == boardsEntity.getUser().getId()) {
                        auth = true;
                    }
                }
                DetailResponseDto detailResponseDto = new DetailResponseDto(boardsEntity, auth);
                detailResponseDto.setBoards(boardsEntity);
                
        List<CommentResponseDto> comments = new ArrayList<>();

        for (Comment comment : boardsEntity.getComments()) {
            CommentResponseDto dto = new CommentResponseDto();
            dto.setComment(comment);

            if (principal != null) {
                if (principal.getId() == comment.getUser().getId()) {
                    dto.setAuth(true); // or false
                } else {
                    dto.setAuth(false); // or false
                }
            } else {
                dto.setAuth(false); // or false
            }
            comments.add(dto);
        }

        model.addAttribute("boards", detailResponseDto);
        model.addAttribute("comments", comments);
       
        return "pages/post/jarangDetail";
    }

    // 댕냥이 자랑 글쓰기 페이지 이동
    // /s 붙었으니까 자동으로 인터셉터가 인증 체크함. (완료)
    @GetMapping("/s/boards/write-form")
    public String writeForm() {
        return "pages/post/jarangWriteForm";
    }

    // 댕냥이 자랑 글리스트 전달 메서드
    @GetMapping("/boards")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page) {
        List<Boards> boards = boardsService.게시글목록(page);
        model.addAttribute("boards", boards);
        return "/pages/post/jarangList";
    }

    // 공지사항 글리스트 전달 메서드
    @GetMapping("/notice")
    public String adminNotice(Model model, @RequestParam(defaultValue = "0") Integer page) {
        List<Boards> boards = boardsService.공지사항목록(page);
        List<NoticeListRespDto> noticeDto = new ArrayList<>();
        int size = boards.size();
        for (int i = 0; i < size; i++) {
            noticeDto.add(new NoticeListRespDto(size - i, boards.get(i)));
        }
        model.addAttribute("notices", noticeDto);
        return "/pages/post/noticeList";
    }

    //댕냥이자랑 글 수정 페이지
    @GetMapping("/s/boards/{id}/update-form")
    public String jarangUpdateForm() {
        return "/pages/post/jarangUpdateForm";
    }

    //공지사항 글 수정 페이지
    @GetMapping("/s/notice/{id}/update-form")
    public String noticeUpdateForm() {
        return "/pages/post/noticeUpdateForm";
    }
}