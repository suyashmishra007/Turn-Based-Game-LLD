import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.boards.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GamePlayTest {

  GameEngine gameEngine;
  RuleEngine ruleEngine;

  @BeforeEach
  public void setup() {
    gameEngine = new GameEngine();
    ruleEngine = new RuleEngine();
  }

  private void playGame(
    Board board,
    int[][] firstPlayerMoves,
    int[][] secondPlayerMoves
  ) {
    int next = 0;
    while (!ruleEngine.getState(board).isOver()) {
      Player first = new Player("X"), second = new Player("O");
      int row = firstPlayerMoves[next][0];
      int col = firstPlayerMoves[next][1];
      Move firstPlayerMove = new Move(new Cell(row, col), first);
      gameEngine.move(board, firstPlayerMove);
      if (!ruleEngine.getState(board).isOver()) {
        int sRow = secondPlayerMoves[next][0];
        int sCol = secondPlayerMoves[next][1];
        Move secondplayerMove = new Move(new Cell(sRow, sCol), second);
        gameEngine.move(board, secondplayerMove);
      }
      next++;
    }
  }

  @Test
  public void checkforRowWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
    int secondPlayermoves[][] = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };
    playGame(board, moves, secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

  @Test
  public void checkforColWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
    int secondPlayermoves[][] = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 1 } };
    playGame(board, moves, secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

  @Test
  public void checkforDiagWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
    int secondPlayermoves[][] = new int[][] { { 1, 0 }, { 1, 2 }, { 2, 0 } };
    playGame(board, moves, secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

  @Test
  public void checkforRevDiagWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
    int secondPlayermoves[][] = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 } };
    playGame(board, moves, secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

  @Test
  public void checkforSecondPlayerWin() {
    Board board = gameEngine.start("TicTacToe");
    //make moves in a loop
    int[][] moves = new int[][] { { 1, 0 }, { 1, 1 }, { 2, 0 } };
    int[][] secondPlayerMoves = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };
    playGame(board, moves, secondPlayerMoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
  }
}
