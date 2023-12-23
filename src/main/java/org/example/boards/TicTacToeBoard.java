package org.example.boards;

import org.example.game.*;

public class TicTacToeBoard implements Board {

  public String[][] cells = new String[3][3];

  public String getCell(int row, int col) {
    return cells[row][col];
  }

  public void setCell(Cell cell, String symbol) {
    if (cells[cell.getRow()][cell.getCol()] == null) {
      cells[cell.getRow()][cell.getCol()] = symbol;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public String getSymbol(int row, int col) {
    return cells[row][col];
  }

  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result += cells[i][j] == null ? "-" : cells[i][j];
      }
      result += "\n";
    }
    return result;
  }

  @Override
  public void move(Move move) {

    setCell(move.getCell(), move.getPlayer().symbol());
  }

  @Override
  public TicTacToeBoard copy() {
    TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
    for (int i = 0; i < 3; i++) {
      //            for (int j = 0; j < 3; j++) {
      //                ticTacToeBoard.cells[i][j] = cells[i][j];
      //            }
      System.arraycopy(cells[i], 0, ticTacToeBoard.cells[i], 0, 3);
    }
    return ticTacToeBoard;
  }
}
