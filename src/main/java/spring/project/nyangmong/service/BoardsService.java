package spring.project.nyangmong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boardlikes.BoardLikes;
import spring.project.nyangmong.domain.boardlikes.BoardLikesRepository;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.boards.BoardsRepository;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.comment.CommentRepository;
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
    private final BoardLikesRepository boardLikesRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void 글수정하기(WriteJarangDto writeJarangDto, Integer boardsId, User principal) {
        String thumnail = null;

        // 글확인
        Boards boardsEntity = mFinBoards(boardsId);

        // 권한 확인
        boolean auth = mCheckAuth(principal, boardsEntity.getUser());
        if (!auth) {
            throw new CustomApiException("수정 권한이 없습니다.");
        }

        // 썸네일 변경 확인
        if (writeJarangDto.getThumnailFile() != null) {
            thumnail = UtilFileUpload.write(writeJarangDto.getThumnailFile());
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

    public DetailResponseDto 글상세보기(Integer boardsId, User principal) {
        BoardLikes boardLikesEntity = null;
        Integer boardLikesId = null;

        // 게시글 확인
        Boards boardsEntity = mFinBoards(boardsId);
        // 게시글 수정,삭제 권한 확인
        boolean boardAuth = mCheckAuth(principal, boardsEntity.getUser());
        // 댓글 수정,삭제 권한 확인
        List<CommentResponseDto> commentRespDto = new ArrayList<>();

        List<Comment> comments = commentRepository.mFindByBoardsId(boardsId);

        for (Comment comment : comments) {
            CommentResponseDto dto = new CommentResponseDto();
            dto.setComment(comment);

            boolean auth = mCheckAuth(principal, comment.getUser());
            dto.setAuth(auth);
            commentRespDto.add(dto);
        }
        // 좋아요 정보 확인
        if (principal != null) {
            Optional<BoardLikes> boardLikesOp = boardLikesRepository.mFindBoardIdAndUserId(boardsEntity.getId(),
                    principal.getId());
            if (boardLikesOp.isPresent()) {
                boardLikesEntity = boardLikesOp.get();
                boardLikesId = boardLikesEntity.getId();
            }
        }
        DetailResponseDto detailResponseDto = new DetailResponseDto(
                boardsEntity,
                commentRespDto,
                boardAuth,
                boardLikesId);
        return detailResponseDto;
    }

    public Boards 수정게시글찾기(Integer boardsId, User principal) {

        // 게시글 확인
        Boards boardsEntity = mFinBoards(boardsId);

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

    public JarangRespDto 공지사항목록(Integer page) {
        PageRequest pq = PageRequest.of(page, 15, Sort.by(Direction.DESC, "id"));

        Page<Boards> noticeEntity = boardsRepository.listNotice(pq);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < noticeEntity.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        JarangRespDto jarangRespDto = new JarangRespDto(
                noticeEntity,
                noticeEntity.getNumber() - 1,
                noticeEntity.getNumber() + 1, pageNumbers);
        return jarangRespDto;
    }

    @Transactional
    public void 글쓰기(WriteJarangDto writeJarangDto, User principal) {
        // 이미지 파일 저장 (UUID로 변경해서 저장)
        String thumnail = null;

        if (!writeJarangDto.getThumnailFile().isEmpty()) {
            thumnail = UtilFileUpload.write(writeJarangDto.getThumnailFile());
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

    // 좋아요
    @Transactional
    public BoardLikes 좋아요(Integer boardsId, User principal) {
        BoardLikes boardLikes = new BoardLikes();

        // 게시글 찾기
        Boards boardsEntity = mFinBoards(boardsId);

        boardLikes.setUser(principal);
        boardLikes.setBoards(boardsEntity);

        return boardLikesRepository.save(boardLikes);
    }

    // 좋아요 취소
    @Transactional
    public boolean 좋아요취소(Integer boardLikesId, User principal) {

        // 좋아요 정보 확인
        BoardLikes boardLikesEntity = mFindBoardLikes(boardLikesId);

        // 좋아요 취소 권한 확인
        if (principal.getId() == boardLikesEntity.getUser().getId()) {
            boardLikesRepository.delete(boardLikesEntity);
            return true;
        } else {
            throw new CustomApiException("좋아요 취소 권한이 없습니다.");
        }
    }

    // 관리자공지사항삭제
    @Transactional
    public boolean 관리자공지사항삭제(List<Integer> ids, User principal) {

        // 권한(관리자) 확인
        if (principal.getUserAuth() != null) {
            for (Integer id : ids) {
                boardsRepository.deleteById(id);
            }
            return true;
        } else {
            throw new CustomException("권한이 없습니다.");
        }
    }

    // 관리자게시글삭제
    @Transactional
    public boolean 관리자게시글삭제(List<Integer> ids, User principal) {
        // 권한(관리자) 확인
        if (principal.getUserAuth() != null) {
            for (Integer id : ids) {
                System.out.println("변경 아이디 : " + id);
                boardsRepository.deleteById(id);
            }
            return true;
        } else {
            throw new CustomException("권한이 없습니다.");
        }

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

    // 좋아요 찾기
    private BoardLikes mFindBoardLikes(Integer boardLikesId) {
        Optional<BoardLikes> boardLikesOp = boardLikesRepository.findById(boardLikesId);
        if (boardLikesOp.isPresent()) {
            return boardLikesOp.get();
        } else {
            throw new CustomException("해당 좋아요 정보가 존재하지 않습니다.");
        }
    }

    // 게시글 찾기
    private Boards mFinBoards(Integer boardsId) {
        Optional<Boards> boardsOp = boardsRepository.findById(boardsId);
        if (boardsOp.isPresent()) {
            return boardsOp.get();
        } else {
            throw new CustomException("해당 게시글이 존재하지 않습니다.");
        }
    }

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