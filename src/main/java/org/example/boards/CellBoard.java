package org.example.boards;

// Interface Segeration : Specifc set of board classes will extend these responsibilities.
//  All board like chess , sudoku , Tictactoe.
public interface CellBoard extends Board{
    String getSymbol(int i ,int j);
}
