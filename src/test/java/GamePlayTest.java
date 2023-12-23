import org.example.api.AIEngine;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.game.Board;
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


  private void playGame(Board board, int[][] firstPlayermoves,int[][] secondPlayerMoves) {
    int next = 0;
    while (!ruleEngine.getState(board).isOver()) {
      Player human = new Player("O");
      int row = firstPlayermoves[next][0];
      int col = firstPlayermoves[next][1];
      next++;
      Move humanMove = new Move(new Cell(row, col), human);
      gameEngine.move(board, humanMove);
      System.out.println(board.toString());
      if (!ruleEngine.getState(board).isOver()) {
        Player computer = new Player("X");
        int sRow = secondPlayerMoves[next][0];
        int sCol = secondPlayerMoves[next][1];
        Move computerMove = new Move(new Cell(sRow,sCol),computer);
        gameEngine.move(board, computerMove);
      }
    }
  }

  @Test
  public void checkforRowWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
    int secondPlayermoves[][] = new int[][] {{0,0},{0,1},{0,2}};
    playGame(board, moves,secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

  @Test
  public void checkforColWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
    int secondPlayermoves[][] = new int[][] {{1,0},{1,1},{1,2}};
    playGame(board, moves,secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

  @Test
  public void checkforDiagWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
    int secondPlayermoves[][] = new int[][] {{1,0},{1,2},{2,0}};
    playGame(board, moves,secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

  @Test
  public void checkforRevDiagWin() {
    Board board = gameEngine.start("TicTacToe");
    int moves[][] = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
    int secondPlayermoves[][] = new int[][] {{0,0},{1,2},{2,0}};
    playGame(board, moves,secondPlayermoves);
    Assertions.assertTrue(ruleEngine.getState(board).isOver());
    Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
  }

//   @Test
//   public void checkforSecondPlayerWin(){
//       Board board = gameEngine.start("TicTacToe");
//       int moves[][] = new int[][]{{1,0},{1,1},{2,0}};
//       int secondPlayermoves[][] = new int[][] {{0,0},{0,1},{0,2}};
//       playGame(board,moves,secondPlayermoves);
//       Assertions.assertTrue(ruleEngine.getState(board).isOver());
//       Assertions.assertEquals(ruleEngine.getState(board).getWinner(),"X");
//   }

}
