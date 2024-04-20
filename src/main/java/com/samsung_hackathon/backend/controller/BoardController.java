package com.samsung_hackathon.backend.controller;

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
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @GetMapping("by-id/{id}")
    public Board getBoard(@PathVariable long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("by-id/{id}")
    public Board updateBoard(@PathVariable long id, @RequestBody Board board) {
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("by-id/{id}")
    public void deleteBoard(@PathVariable long id) {
        boardService.deleteBoard(id);
    }

    @PutMapping("{boardId}/add-column/{columnId}")
    public Board addColumn(@PathVariable long boardId, @PathVariable long columnId) {
        return boardService.addColumn(boardId, columnId);
    }

    @DeleteMapping("{boardId}/remove-column/{columnId}")
    public Board removeColumn(@PathVariable long boardId, @PathVariable long columnId) {
        return boardService.removeColumn(boardId, columnId);
    }

    @PutMapping("{boardId}/add-collaborator/{collaboratorId}")
    public Board addCollaborator(@PathVariable long boardId, @PathVariable long collaboratorId) {
        return boardService.addCollaborator(boardId, collaboratorId);
    }

    @DeleteMapping("{boardId}/remove-collaborator/{collaboratorId}")
    public Board removeCollaborator(@PathVariable long boardId, @PathVariable long collaboratorId) {
        return boardService.removeCollaborator(boardId, collaboratorId);
    }
}
