package spring.project.nyangmong.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.service.BoardsService;
import spring.project.nyangmong.service.PlaceLikesService;
import spring.project.nyangmong.web.dto.members.ResponseDto;
import spring.project.nyangmong.web.dto.members.boards.DetailResponseDto;
import spring.project.nyangmong.web.dto.members.boards.WriteDto;

@RequiredArgsConstructor
@RestController
public class BoardsApiController {
    private final BoardsService boardsService;
    private final PlaceLikesService placelikesService;

    private final HttpSession session;

    // UPDATE 글수정 /post/{id} - 글상세보기 페이지가기 - 인증 O
    @PutMapping("/s/api/boards/{id}/update")
    public @ResponseBody ResponseDto<String> update(@PathVariable Integer id, @RequestBody Boards boards) {

        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return new ResponseDto<String>(-1, "로그인 되지 않았습니다.", null);
        }

        // 권한
        Boards boardsEntity = boardsService.글상세보기(id);

        if (boardsEntity.getUser().getId() != principal.getId()) {
            return new ResponseDto<String>(-1, "해당 게시글을 수정할 권한이 없습니다.", null);
        }

        boardsService.글수정하기(boards, id);

        return new ResponseDto<String>(1, "수정 성공", null);
    }

    @DeleteMapping("/s/api/boards/{id}/delete")
    public ResponseDto<?> deleteById(@PathVariable Integer id) {
        boardsService.글삭제하기(id);

        return new ResponseDto<>(1, "성공", null);
    }

    @PostMapping("/s/boards/write")
    public ResponseDto<?> write(@RequestBody WriteDto writeDto) {

        User principal = (User) session.getAttribute("principal");
        Boards boards = writeDto.toEntity(principal);
        // 원래는 그냥 dto바로 넘겼는데, 지금 dto를 넘기면 session값 두개 넘겨야 해서 하나로 합쳐서 넘김
        boardsService.글쓰기(boards);

        return new ResponseDto<>(1, "성공", null);
    }

    @PostMapping("/s/user/{id}/boardlike")
    public void likes(@PathVariable long boardsId, Authentication authentication) {
        placelikesService.placelikes(boardsId, authentication.getUsername());
    }

    @DeleteMapping("/s/user/{id}/unboardlike")
    public void unLikes(@PathVariable long boardsId, Authentication authentication) {
        placelikesService.unplacelikes(boardsId, authentication.getUsername());
    }
}
