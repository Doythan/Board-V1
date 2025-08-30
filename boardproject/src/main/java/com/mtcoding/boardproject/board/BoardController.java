package com.mtcoding.boardproject.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards/{id}/update")
    public String updateById(@PathVariable Integer id, BoardRequest.UpdateDTO requestDTO) {
        boardService.게시글수정(id, requestDTO);
        return "redirect:/boards/" + id;
    }

    @PostMapping("/boards/{id}/delete")
    public String deleteById(@PathVariable Integer id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }

    @PostMapping("/boards/save")
    public String save(BoardRequest.SaveDTO requestDTO) {
        boardService.게시글추가(requestDTO);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<BoardResponse.DTO> boards = boardService.게시글목록();
        request.setAttribute("models", boards);
        return "index";
    }

    @GetMapping("/boards/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/boards/{id}")
    public String detail(HttpServletRequest request ,@PathVariable Integer id) {
        BoardResponse.DetailDTO board = boardService.게시글상세(id);
        request.setAttribute("model", board);
        return "board/detail";
    }

    @GetMapping("/boards/{id}/update-form")
    public String updateForm(HttpServletRequest request, @PathVariable Integer id) {
        BoardResponse.DTO board = boardService.게시글수정폼(id);
        request.setAttribute("model", board);
        return "board/update-form";
    }
}
