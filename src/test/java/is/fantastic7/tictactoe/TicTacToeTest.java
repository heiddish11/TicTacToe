package is.fantastic7.tictactoe;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.Scanner;
import static java.lang.System.out;


public class TicTacToeTest 
{
	private TicTacToe t;
	String printBoard;

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("is.fantastic7.tictactoe.TicTacToeTest");
	}

	@Before
	public void setup() {
		t = new TicTacToe();
		printBoard = "-------------\n| - | - | - | \n-------------\n| - | - | - | \n-------------\n| - | - | - | \n-------------\n";
	}

	@Test
	public void testSetStartPlayer() {
		t.setStartPlayer();
		assertEquals('x', t.playerX);
	}

	@Test
	public void testTicTacToeConstructor() {
		t.initializeBoard();
		for(int i = 0; i < 3; i++) {
			assertEquals(3, t.board[i].length);
		}	
	}

	@Test
	public void testNewBoard() {
		t.initializeBoard();
		t.newBoard();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				assertEquals('-', t.board[i][j]);
			}
		}
	}

	@Test
	public void testDisplayBoard() {
		t.initializeBoard();
		t.newBoard();
		String actual = t.displayBoard();
		String expected = printBoard;
		assertEquals(actual, expected);
	}

	@Test
	public void testCheckIfBoardNotFull() {
		t.initializeBoard();
		t.newBoard();
		t.setStartPlayer();
		boolean actual = t.checkIfBoardFull();
		assertEquals(actual, false);
	}

	@Test
	public void testRegisterMove() {
		t.initializeBoard();
		t.newBoard();
		t.setStartPlayer();
		String expected = "-------------\n| x | - | - | \n-------------\n| - | - | - | \n-------------\n| - | - | - | \n-------------\n";
		t.registerMove(0,0);
		String actual = t.displayBoard();
		assertEquals(actual, expected);
	}

	@Test
	public void testSwitchPlayer() {
		t.setStartPlayer();
		t.switchPlayer();
		assertEquals('o', t.playerX);
		t.switchPlayer();
		assertEquals('x', t.playerX);
	}

	@Test
	public void testCheckNoWin() {
		t.initializeBoard();
		t.newBoard();
		boolean actual = t.checkIfWin();
		assertFalse(actual);
	}

	@Test
	public void testCheckIfWinRow() {
		t.initializeBoard();
		t.newBoard();
		t.setStartPlayer();
		for(int i = 0; i < 3; i++) {
			t.registerMove(0,i);
		}
		boolean actual = t.checkIfWin();
		assertTrue(actual);
	}

	@Test
	public void testCheckIfWinCol() {
		t.initializeBoard();
		t.newBoard();
		t.setStartPlayer();
		for(int i = 0; i < 3; i++) {
			t.registerMove(i,0);
		}
		boolean actual = t.checkIfWin();
		assertTrue(actual);
	}

	@Test
	public void testCheckIfWinAcrossUpBottom() {
		t.initializeBoard();
		t.newBoard();
		t.setStartPlayer();
		for(int i = 0; i < 3; i++) {
			t.registerMove(i,i);
		}
		boolean actual = t.checkIfWin();
		assertTrue(actual);
	}

	@Test
	public void testCheckIfWinAcrossBottomUp() {
		t.initializeBoard();
		t.newBoard();
		t.setStartPlayer();

		t.registerMove(0,2);
		t.registerMove(1,1);
		t.registerMove(2,0);
		
		boolean actual = t.checkIfWin();
		assertTrue(actual);
	}

	@Test
	public void testCheckIfWinGameFinished() {
		this.testCheckIfWinAcrossUpBottom();
		boolean actual = t.gameFinished();
		assertTrue(actual);
	}

	@Test
	public void testCheckIfGameFinishedBoardFull() {
		t.initializeBoard();
		t.newBoard();
		t.setStartPlayer();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				t.registerMove(i,j);	
			} 
		}
		boolean actual = t.gameFinished();
		assertTrue(actual);
	}

}
