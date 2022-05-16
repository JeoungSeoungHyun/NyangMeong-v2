package spring.project.nyangmong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.boards.BoardsRepository;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.comment.CommentRepository;
import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.web.dto.members.comment.CommentDto;
import spring.project.nyangmong.web.dto.members.comment.CommentResponseDto;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardsRepository boardsRepository;
    // 서비스 DI 하면 안됨

    @Transactional
    public void 댓글삭제(Integer id, User principal) {
        Optional<Comment> commentOp = commentRepository.findById(id);
        if (commentOp.isPresent()) {
            Comment commentEntity = commentOp.get();
            if (principal.getId() != commentEntity.getUser().getId()) {
                throw new RuntimeException("권한이 없습니다");
            }
        } else {
            throw new RuntimeException("해당 댓글이 없습니다");
        }
        commentRepository.deleteById(id);
    }

    @Transactional
    public void 관리자댓글삭제(List<String> ids) {
        for (String id : ids) {
            commentRepository.deleteById(Integer.parseInt(id));
        }
    }

    @Transactional
    public void 댓글쓰기(Comment comment, Integer boardsId) {

        Optional<Boards> boardsOp = boardsRepository.findById(boardsId);

        if (boardsOp.isPresent()) {
            Boards boardsEntity = boardsOp.get();
            comment.setBoards(boardsEntity);
        } else {
            throw new RuntimeException("없는 게시글에 댓글을 작성할 수 없습니다");
        }
        commentRepository.save(comment);
    }

    public CommentDto 댓글목록(Integer page) {
        Pageable pq = PageRequest.of(page, 8, Sort.by(Direction.DESC, "id"));

        Page<Comment> commentsEntity = commentRepository.listComment(pq);
        System.out.println("페이지 수 : " + commentsEntity.getTotalPages());
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < commentsEntity.getTotalPages(); i++) {
            System.out.println(i);
            pageNumbers.add(i);
        }
        CommentDto commentDto = new CommentDto(
                commentsEntity,
                commentsEntity.getNumber() - 1,
                commentsEntity.getNumber() + 1, pageNumbers);

        return commentDto;
    }

    /* UPDATE */
    @Transactional
    public void 댓글수정(Integer userId, CommentResponseDto dto) {
        Comment comment = commentRepository.findByuserId(
                userId);

    }

    // 댓글 수정
    // @Transactional
    // public void 댓글수정(Comment comment, Integer boardsId) {

    // Optional<Boards> boardsOp = boardsRepository.findById(boardsId);

    // if (boardsOp.isPresent()) {
    // Boards boardsEntity = boardsOp.get();
    // comment.setBoards(boardsEntity);
    // } else {
    // throw new RuntimeException("없는 게시글에 댓글을 수정할 수 없습니다");
    // }
    // commentRepository.save(comment);
    // }
}