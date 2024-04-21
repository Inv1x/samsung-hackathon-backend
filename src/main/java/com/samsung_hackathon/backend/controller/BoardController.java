package com.samsung_hackathon.backend.controller;

import com.samsung_hackathon.backend.controller.dto.BoardDto;
import com.samsung_hackathon.backend.entity.Board;
import com.samsung_hackathon.backend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("board")
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<BoardDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    @PostMapping
    public BoardDto createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @GetMapping("by-id/{id}")
    public BoardDto getBoard(@PathVariable long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("by-id/{id}")
    public BoardDto updateBoard(@PathVariable long id, @RequestBody Board board) {
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("by-id/{id}")
    public void deleteBoard(@PathVariable long id) {
        boardService.deleteBoard(id);
    }

    @PutMapping("{boardId}/add-column/{columnId}")
    public BoardDto addColumn(@PathVariable long boardId, @PathVariable long columnId) {
        return boardService.addColumn(boardId, columnId);
    }

    @DeleteMapping("{boardId}/remove-column/{columnId}")
    public BoardDto removeColumn(@PathVariable long boardId, @PathVariable long columnId) {
        return boardService.removeColumn(boardId, columnId);
    }

    @PutMapping("{boardId}/add-collaborator/{collaboratorId}")
    public BoardDto addCollaborator(@PathVariable long boardId, @PathVariable long collaboratorId) {
        return boardService.addCollaborator(boardId, collaboratorId);
    }

    @DeleteMapping("{boardId}/remove-collaborator/{collaboratorId}")
    public BoardDto removeCollaborator(@PathVariable long boardId, @PathVariable long collaboratorId) {
        return boardService.removeCollaborator(boardId, collaboratorId);
    }
}
