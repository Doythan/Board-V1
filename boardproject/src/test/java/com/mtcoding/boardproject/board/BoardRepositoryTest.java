package com.mtcoding.boardproject.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test() {
        // given
        int id = 2;
        // when
        Board board = boardRepository.findById(id).get();
        // eye
        System.out.println("=======================");
        System.out.println("Board Title : " + board.getTitle());
        System.out.println("Board Content : " + board.getContent());
    }

    @Test
    public void findAll_test(){
        //given

        //when
        List<Board> boards = boardRepository.findAll();
        //eye
        System.out.println("=======================");
        System.out.println("Board Count : " + boards.size());
        System.out.println("Board 1 title :" +boards.get(0).getTitle());
        System.out.println("Board 2 title :" +boards.get(1).getTitle());
    }

    @Test
    public void save_test(){
        //given
        Board board = Board.builder()
                .title("title 3")
                .content("Spring Unit Test")
                .build();
        //when
        boardRepository.save(board);
        //eye
        List<Board> boards = boardRepository.findAll();
        System.out.println("=======================");
        System.out.println("Board Count : " + boards.size());
        System.out.println("Board ID: " + boards.get(2).getId());
        System.out.println("Board Title: " + boards.get(2).getTitle());
        System.out.println("Board Content: " + boards.get(2).getContent());
    }

    @Test
    public void deleteById_test(){
        //given
        int id = 2;
        //when
        boardRepository.deleteById(id);
        //eye
        List<Board> boards = boardRepository.findAll();
        System.out.println("=======================");
        System.out.println("Board count : " + boards.size());
    }

}
