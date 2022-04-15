package spring.project.nyangmong.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.boards.BoardsRepository;

@RequiredArgsConstructor
@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;

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
    }

    public Page<Boards> 게시글목록(Integer page) {
        PageRequest pq = PageRequest.of(page, 3, Sort.by(Direction.DESC, "id"));
        return boardsRepository.findAll(pq);
    }

    @Transactional
    public void 글쓰기(Boards boards) {
        boardsRepository.save(boards);
    }
}
