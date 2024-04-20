package com.samsung_hackaton.backend.service.impl;

import com.samsung_hackaton.backend.dao.BoardRepository;
import com.samsung_hackaton.backend.dao.ColumnRepository;
import com.samsung_hackaton.backend.dao.UserRepository;
import com.samsung_hackaton.backend.entity.Board;
import com.samsung_hackaton.backend.entity.BoardColumn;
import com.samsung_hackaton.backend.entity.User;
import com.samsung_hackaton.backend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    private final ColumnRepository columnRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board createBoard(Board board) {
        return boardRepository.saveAndFlush(board);
    }

    @Override
    public Board getBoard(long id) {
        Board board;

        try {
            board = boardRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Board with ID " + id + " not found!", e);
        }

        return board;
    }

    @Override
    public Board updateBoard(long id, Board board) {
        Board newBoard;

        try {
            newBoard = boardRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Board with ID " + id + " not found!", e);
        }

        newBoard.setTitle(board.getTitle());

        return boardRepository.saveAndFlush(newBoard);
    }

    @Override
    public void deleteBoard(long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Board addColumn(long boardId, long columnId) {;
        Board board;
        BoardColumn boardColumn;

        try {
            board = boardRepository.findById(boardId).get();
            boardColumn = columnRepository.findById(columnId).get();
        } catch (NoSuchElementException e) {
            if (boardRepository.findById(boardId).isEmpty()) {
                throw new RuntimeException("Board with ID " + boardId + " not found!", e);
            }
            else {
                throw new RuntimeException("Column with ID " + columnId + " not found!", e);
            }
        }

        board.getColumns().add(boardColumn);
        boardColumn.setBoard(board);

        return boardRepository.saveAndFlush(board);
    }

    @Override
    public Board removeColumn(long boardId, long columnId) {
        Board board;
        BoardColumn boardColumn;

        try {
            board = boardRepository.findById(boardId).get();
            boardColumn = columnRepository.findById(columnId).get();
        } catch (NoSuchElementException e) {
            if (boardRepository.findById(boardId).isEmpty()) {
                throw new RuntimeException("Board with ID " + boardId + " not found!", e);
            }
            else {
                throw new RuntimeException("Column with ID " + columnId + " not found!", e);
            }
        }

        board.getColumns().remove(boardColumn);
        boardColumn.setBoard(null);

        return boardRepository.saveAndFlush(board);
    }

    @Override
    public Board addCollaborator(long boardId, long collaboratorId) {
        Board board;
        User user;

        try {
            board = boardRepository.findById(boardId).get();
            user = userRepository.findById(collaboratorId).get();
        } catch (NoSuchElementException e) {
            if (boardRepository.findById(boardId).isEmpty()) {
                throw new RuntimeException("Board with ID " + boardId + " not found!", e);
            }
            else {
                throw new RuntimeException("User with ID " + collaboratorId + " not found!", e);
            }
        }

        board.getCollaborators().add(user);
        user.getBoards().add(board); // тут так или вызвать linkBoard???

        return boardRepository.saveAndFlush(board);
    }

    @Override
    public Board removeCollaborator(long boardId, long collaboratorId) {
        Board board;
        User user;

        try {
            board = boardRepository.findById(boardId).get();
            user = userRepository.findById(collaboratorId).get();
        } catch (NoSuchElementException e) {
            if (boardRepository.findById(boardId).isEmpty()) {
                throw new RuntimeException("Board with ID " + boardId + " not found!", e);
            }
            else {
                throw new RuntimeException("User with ID " + collaboratorId + " not found!", e);
            }
        }

        board.getCollaborators().remove(user);
        user.getBoards().remove(board);

        return boardRepository.saveAndFlush(board);
    }
}
