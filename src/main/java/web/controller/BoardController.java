package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.entity.BoardEntity;
import web.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/post.do")
    public boolean postBoard(@RequestBody BoardDto boardDto){
        System.out.println("boardDto = " + boardDto);
        return boardService.postBoard(boardDto);
    }

    @GetMapping("/get.do")
    public List<BoardDto> getBoard(){
        return boardService.getBoard();
    }

    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }

    @DeleteMapping("/delete.do")
    public boolean deleteBoard(){
        return boardService.deleteBoard();
    }
}
