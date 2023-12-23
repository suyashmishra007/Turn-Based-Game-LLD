package org.example.api;

import org.example.boards.TicTacToeBoard;
import org.example.game.Board;
import org.example.game.GameState;

public class RuleEngine {
    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            boolean rowComplete = true;
            boolean colComplete = false;
            boolean diagComplete = false;
            boolean revDiagComplete = false;
            String firstCharacter = "-";

            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(i, 0);
                rowComplete =  firstCharacter != null;

                if(firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(i, j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if (rowComplete) {
                    break;
                }
            }

            if (rowComplete) {
                return new GameState(true, firstCharacter);
            }

            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(0, i);
                colComplete = firstCharacter != null;
                if(firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(j, i))) {
                            colComplete = false;
                            break;
                        }
                    }
                }
                if (colComplete) {
                    break;
                }
            }

            if (colComplete) {
                return new GameState(true, firstCharacter);
            }

            firstCharacter = board1.getCell(0, 0);
            diagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getCell(i, i))) {
                    diagComplete = false;
                }
            }
            if (diagComplete) {
                return new GameState(true, firstCharacter);
            }

            firstCharacter = board1.getCell(0, 2);
            revDiagComplete = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if (firstCharacter != null && !firstCharacter.equals(board1.getCell(i, 2 - i))) {
                    revDiagComplete = false;
                }
            }

            if (revDiagComplete) {
                return new GameState(true, firstCharacter);
            }

            int countOfFilledCells = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, firstCharacter);
            } else {
                return new GameState(false, firstCharacter);
            }

        } else {
            return new GameState(false, "-");
        }
    };
}

