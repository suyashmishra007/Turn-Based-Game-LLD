package org.example.api;

import org.example.boards.TicTacToeBoard;
import org.example.game.*;

public class GameEngine {
    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    };
    public void move(Board board, Move move) {
        if (board instanceof TicTacToeBoard) {
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    };

};