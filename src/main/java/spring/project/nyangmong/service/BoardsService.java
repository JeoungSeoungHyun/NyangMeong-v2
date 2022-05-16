package spring.project.nyangmong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.boards.BoardsRepository;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.placelikes.PlaceLikesRepository;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.domain.user.UserRepository;
import spring.project.nyangmong.handle.ex.CustomApiException;
import spring.project.nyangmong.handle.ex.CustomException;
import spring.project.nyangmong.util.UtilFileUpload;
import spring.project.nyangmong.web.dto.members.boards.DetailResponseDto;
import spring.project.nyangmong.web.dto.members.boards.JarangRespDto;
import spring.project.nyangmong.web.dto.members.boards.WriteJarangDto;
import spring.project.nyangmong.web.dto.members.boards.WriteNoticeDto;
import spring.project.nyangmong.web.dto.members.comment.CommentResponseDto;

@RequiredArgsConstructor
@Service // 컴포넌트 스캔시에 IoC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트임. 기능 모임
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final PlaceLikesRepository placelikesRepository;
    private final UserRepository userRepository;

    @Transactional
    public void 글수정하기(WriteJarangDto writeJarangDto, Integer id, User principal) {
        Boards boardsEntity = null;
        String thumnail = null;

        // 글확인
        Optional<Boards> boardsOp = boardsRepository.findById(id);
        if (boardsOp.isPresent()) {
            boardsEntity = boardsOp.get();
        } else {
            throw new CustomApiException("해당 게시글을 찾을 수 없습니다");
        }

        // 권한 확인
        boolean auth = mCheckAuth(principal, boardsEntity.getUser());
        if (!auth) {
            throw new CustomApiException("수정 권한이 없습니다.");
        }

        // 썸네일 변경 확인
        if (writeJarangDto.getThumnailFile() != null) {
            thumnail = UtilFileUpload.write(writeJarangDto.getThumnailFile());
<<<<<<< HEAD
=======

>>>>>>> 7db8e8c84f714c890a6508c4ea604bd30929427f
            boardsEntity.setThumnail(thumnail);
        }

        // boards 수정
        boardsEntity.setTitle(writeJarangDto.getTitle());
        boardsEntity.setContent(writeJarangDto.getContent());

        boardsRepository.save(boardsEntity);

    } // 더티체킹 완료 (수정됨)

    @Transactional
    public void 글삭제하기(Integer id) {
        boardsRepository.deleteById(id);
    }

    public DetailResponseDto 글상세보기(Integer id, User principal) {
        Optional<Boards> boardsOp = boardsRepository.findById(id);
        Boards boardsEntity = null;

        // 게시글 확인
        if (boardsOp.isPresent()) {
            boardsEntity = boardsOp.get();
        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다");
        }

        // 게시글 수정,삭제 권한 확인
        boolean boardAuth = mCheckAuth(principal, boardsEntity.getUser());

        // 댓글 수정,삭제 권한 확인
        List<CommentResponseDto> comments = new ArrayList<>();

        for (Comment comment : boardsEntity.getComments()) {
            CommentResponseDto dto = new CommentResponseDto();
            dto.setComment(comment);

            boolean auth = mCheckAuth(principal, comment.getUser());
            dto.setAuth(auth);
            comments.add(dto);
        }
        DetailResponseDto detailResponseDto = new DetailResponseDto(boardsEntity, comments, boardAuth);
        return detailResponseDto;
        // 조회수 증가

        // 인기 게시물 처리~!!
    }

    public Boards 글한건보기(Integer id, User principal) {
        Optional<Boards> boardsOp = boardsRepository.findById(id);
        Boards boardsEntity = null;

        // 게시글 확인
        if (boardsOp.isPresent()) {
            boardsEntity = boardsOp.get();
        } else {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다");
        }

        // 권한 확인
        boolean auth = mCheckAuth(principal, boardsEntity.getUser());

        if (auth) {
            return boardsEntity;
        } else {
            throw new CustomException("수정 권한이 없습니다.");
        }
    }

    public JarangRespDto 게시글목록(Integer page) {
        Pageable pq = PageRequest.of(page, 8, Sort.by(Direction.DESC, "id"));

        Page<Boards> boardsEntity = boardsRepository.listJarang(pq);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < boardsEntity.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        JarangRespDto jarangRespDto = new JarangRespDto(
                boardsEntity,
                boardsEntity.getNumber() - 1,
                boardsEntity.getNumber() + 1, pageNumbers);

        return jarangRespDto;
    }

    public List<Boards> 공지사항목록(Integer page) {
        PageRequest pq = PageRequest.of(page, 15, Sort.by(Direction.DESC, "id"));
        return boardsRepository.listNotice(pq);
    }

    @Transactional
    public void 글쓰기(WriteJarangDto writeJarangDto, User principal) {
        // 이미지 파일 저장 (UUID로 변경해서 저장)
        String thumnail = null;

        if (!writeJarangDto.getThumnailFile().isEmpty()) {
            thumnail = UtilFileUpload.write(writeJarangDto.getThumnailFile());
<<<<<<< HEAD
=======

>>>>>>> 7db8e8c84f714c890a6508c4ea604bd30929427f
        }

        // boards DB 저장
        Boards boards = writeJarangDto.toEntity(principal, thumnail);
        boardsRepository.save(boards);
    }

    @Transactional
    public void 공지사항쓰기(WriteNoticeDto writeNoticeDto, User principal) {

        // boards DB 저장
        Boards boards = writeNoticeDto.toEntity(principal);
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

    // 권한 확인
    private boolean mCheckAuth(User principal, User user) {
        boolean auth = false;
        if (principal != null) {
            if (principal.getId() == user.getId()) {
                auth = true;
            }
        }
        return auth;
    }

}