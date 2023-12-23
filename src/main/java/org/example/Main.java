package org.example;

import java.util.Scanner;
import org.example.api.AIEngine;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.game.*;

public class Main {

  public static void main(String[] args) {
    GameEngine gameEngine = new GameEngine();
    RuleEngine ruleEngine = new RuleEngine();
    AIEngine aiEngine = new AIEngine();
    Board board = gameEngine.start("TicTacToe");
    int row, col;
    Scanner scanner = new Scanner(System.in);

    while (!ruleEngine.getState(board).isOver()) {
      Player human = new Player("O");
      System.out.println("Make your move");
      System.out.println(board);

      row = scanner.nextInt();
      col = scanner.nextInt();

      Move humanMove = new Move(new Cell(row, col), human);
      gameEngine.move(board, humanMove);
      System.out.println(board.toString());
      if (!ruleEngine.getState(board).isOver()) {
        Player computer = new Player("X");
        Move computerMove = aiEngine.suggestMove(computer, board);
        System.out.println("Computer moved with X");
        gameEngine.move(board, computerMove);
        System.out.println(board);
      }
    }
    System.out.println("GameResult " + ruleEngine.getState(board));
    System.out.println(board);
  }
}
