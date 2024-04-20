package com.samsung_hackaton.backend.controller;

import com.samsung_hackaton.backend.entity.BoardColumn;
import com.samsung_hackaton.backend.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("column")
@RequiredArgsConstructor
@RequestMapping("/column")
public class ColumnController {
    private final ColumnService columnService;

    @GetMapping
    public List<BoardColumn> getAllColumns() {
        return columnService.getAllColumns();
    }

    @PostMapping("by-id/{id}")
    public BoardColumn createColumn(@RequestBody BoardColumn boardColumn) {
        return columnService.createColumn(boardColumn);
    }

    @GetMapping("by-id/{id}")
    public BoardColumn getColumn(@PathVariable long id) {
        return columnService.getColumn(id);
    }

    @PutMapping("by-id/{id}")
    public BoardColumn updateColumn(@PathVariable long id, @RequestBody BoardColumn boardColumn) {
        return columnService.updateColumn(id, boardColumn);
    }

    @DeleteMapping("by-id/{id}")
    public void deleteColumn(@PathVariable long id) {
        columnService.deleteColumn(id);
    }

    @PutMapping("{listId}/add-task/{taskId}")
    public BoardColumn addTask(@PathVariable long listId, @PathVariable long taskId) {
        return columnService.addTask(listId, taskId);
    }

    @DeleteMapping("{listId}/remove-task/{taskId}")
    public BoardColumn removeTask(@PathVariable long listId, @PathVariable long taskId) {
        return columnService.removeTask(listId, taskId);
    }

    public BoardColumn linkBoard(long listId, long boardId) {
        return null;  // TODO ненужный метод
    }

    @DeleteMapping("{listId}/unlink-board/{boardId}")
    public BoardColumn unlinkBoard(@PathVariable long listId, @PathVariable long boardId) {
        return columnService.unlinkBoard(listId, boardId);
    }
}
