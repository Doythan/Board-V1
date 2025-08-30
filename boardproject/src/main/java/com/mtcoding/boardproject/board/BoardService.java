package com.mtcoding.boardproject.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void 게시글수정(Integer id, BoardRequest.UpdateDTO requestDTO) {
        Board board = boardRepository.findById(id).get();
        // 더티 체킹
        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());
    }

    public BoardResponse.DTO 게시글수정폼(Integer id) {
        Board board = boardRepository.findById(id).get();
        BoardResponse.DTO dto = new BoardResponse.DTO(board);
        return dto;
    }

    @Transactional
    public void 게시글삭제(Integer id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 게시글추가(BoardRequest.SaveDTO requestDTO) {
        Board board = requestDTO.toEntity();
        boardRepository.save(board);
    }

    public List<BoardResponse.DTO> 게시글목록() {

        List<Board> boards = boardRepository.findAll();
        List<BoardResponse.DTO> dtoList = boards.stream().map(board -> new BoardResponse.DTO(board)).collect(Collectors.toList());
        return dtoList;
    }

    public BoardResponse.DetailDTO 게시글상세(Integer id) {
        Board board = boardRepository.findById(id).get(); // pk 로 조회
        BoardResponse.DetailDTO dto = new BoardResponse.DetailDTO(board); // DTO 담기기
        return dto;
    }

}
