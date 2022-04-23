package spring.project.nyangmong.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.boards.BoardsRepository;
import spring.project.nyangmong.domain.placelikes.PlaceLikesRepository;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.domain.user.UserRepository;
import spring.project.nyangmong.web.dto.members.boards.BoardsInfoDto;

@RequiredArgsConstructor
@Service // 컴포넌트 스캔시에 IoC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트임. 기능 모임
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final PlaceLikesRepository placelikesRepository;
    private final UserRepository userRepository;

    @Transactional
    public void 글수정하기(Boards boards, Integer id) {
        Optional<Boards> boardsOp = boardsRepository.findById(id);
        if (boardsOp.isPresent()) {
            Boards boardsEntity = boardsOp.get();
            boardsEntity.setTitle(boards.getTitle());
            boardsEntity.setContent(boards.getContent());
        }
    } // 더티체킹 완료 (수정됨)

    @Transactional
    public void 글삭제하기(Integer id) {
        boardsRepository.deleteById(id);
    }

    public Boards 글상세보기(Integer id) {
        Optional<Boards> boardsOp = boardsRepository.findById(id);

        if (boardsOp.isPresent()) {
            return boardsOp.get();
        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다");
        }
        // 조회수 증가

        // 인기 게시물 처리~!!
    }

    public Page<Boards> 게시글목록(Integer page) {
        PageRequest pq = PageRequest.of(page, 3, Sort.by(Direction.DESC, "id"));
        return boardsRepository.findAll(pq);
    }

    @Transactional
    public void 글쓰기(Boards boards) {
        boardsRepository.save(boards);
    }

    /**
     * boardsinfodto를 반환한다.
     * 
     * param boardsId 현재 포스트의 id
     * param loginEmail 현재 로그인한 사용자의 email
     * return 현재 포스트의 정보와 로그인한 사용자와의 관계를 담은 dto 반환
     */
    // @Transactional
    // public BoardsInfoDto getBoardsInfoDto(long boardsId, String loginEmail) {
    // BoardsInfoDto boardsInfoDto = new BoardsInfoDto();
    // boardsInfoDto.setId(boardsId);

    // Boards boards = boardsRepository.findBoardsById(boardsId);
    // boardsInfoDto.setTag(boards.getTag());
    // boardsInfoDto.setText(boards.getText());
    // boardsInfoDto.setCreatedate(boards.getCreateDate());

    // 포스트 정보 요청시 포스트 엔티티의 likesCount도 설정해준다.
    // boards.setPlaceLikesCount(boards.getPlaceLikesCount());
    // boardsInfoDto.setLikesCount(boards.getPlaceLikesCount());

    // User user = userRepository.findUserByEmail(loginEmail);
    // if (user.getId() == boards.getUser().getId())
    // boardsInfoDto.setUploader(true);
    // else
    // boardsInfoDto.setUploader(false);

    // if (placelikesRepository.findPlaceLikesByBoardsAndUser(boards, user) != null)
    // boardsInfoDto.setLikeState(true);
    // else
    // boardsInfoDto.setLikeState(false);

    // return boardsInfoDto;
    // }

    // User user = userRepository.findUserById(id);
    // boardsRepository.save(Boards.builder()
    // .tag(boardsUploadDto.getTag())
    // .text(boardsUploadDto.getText())
    // .user(user)
    // .PlaceLikesCount(0)
    // .build());
}