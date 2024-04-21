package com.samsung_hackathon.backend.service.impl;

import com.samsung_hackathon.backend.controller.dto.BoardDto;
import com.samsung_hackathon.backend.dao.BoardRepository;
import com.samsung_hackathon.backend.dao.ColumnRepository;
import com.samsung_hackathon.backend.dao.UserRepository;
import com.samsung_hackathon.backend.entity.Board;
import com.samsung_hackathon.backend.entity.BoardColumn;
import com.samsung_hackathon.backend.entity.User;
import com.samsung_hackathon.backend.mapper.BoardMapper;
import com.samsung_hackathon.backend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    private final ColumnRepository columnRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream().map(BoardMapper::toBoardDto).collect(Collectors.toList());
    }

    @Override
    public BoardDto createBoard(Board board) {
        return BoardMapper.toBoardDto(boardRepository.saveAndFlush(board));
    }

    @Override
    public BoardDto getBoard(long id) {
        Board board;

        try {
            board = boardRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Board with ID " + id + " not found!", e);
        }

        return BoardMapper.toBoardDto(board);
    }

    @Override
    public BoardDto updateBoard(long id, Board board) {
        Board newBoard;

        try {
            newBoard = boardRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Board with ID " + id + " not found!", e);
        }

        newBoard.setTitle(board.getTitle());

        return BoardMapper.toBoardDto(boardRepository.saveAndFlush(newBoard));
    }

    @Override
    public void deleteBoard(long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public BoardDto addColumn(long boardId, long columnId) {;
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

        return BoardMapper.toBoardDto(boardRepository.saveAndFlush(board));
    }

    @Override
    public BoardDto removeColumn(long boardId, long columnId) {
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

        return BoardMapper.toBoardDto(boardRepository.saveAndFlush(board));
    }

    @Override
    public BoardDto addCollaborator(long boardId, long collaboratorId) {
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

        return BoardMapper.toBoardDto(boardRepository.saveAndFlush(board));
    }

    @Override
    public BoardDto removeCollaborator(long boardId, long collaboratorId) {
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

        return BoardMapper.toBoardDto(boardRepository.saveAndFlush(board));
    }
}
