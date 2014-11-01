package is.fantastic7.tictactoe;
import java.util.Scanner;
import static java.lang.System.out;

public class TicTacToe {

		public static void main(String[] args) {
		TicTacToe newGame = new TicTacToe();

		System.out.println("Welcome to TicTacToe! \nPick the first number from 1 to 3, press enter and then pick the second number from 1 to 3:");
		Scanner in = new Scanner(System.in);
		newGame.setStartPlayer();
		newGame.initializeBoard();
		newGame.newBoard();
		newGame.displayBoard();

		//If no win is determined or a draw, then switch to player 'o'
		int x;
		int o;

		do {
			x = 1;
			o = 1;

			if(in.hasNextInt()) {
				x = in.nextInt();
				o = in.nextInt();
			}

			if((x > 3 || x < 1)) {
				System.out.println("Illegal move, insert numbers between 1 and 3");
				continue;
			}
			else {
				// Check if space is already occupied
				if (newGame.board[x-1][o-1] == 'x' || newGame.board[x-1][o-1] == 'o')
                {
                	System.out.println("space is full, please insert to a empty position");
                	continue;
                }
				newGame.registerMove(x-1,o-1);
				newGame.switchPlayer();
				newGame.displayBoard();
				System.out.println("Make your next move");
			}

			if(newGame.checkIfWin()) {
				System.out.println("Congratulations We Have a Winner!!!");
			}
			if(newGame.checkIfBoardFull()){
				System.out.println("No Winner,You are Equally Smart!!");
			}

		}
		while(!newGame.gameFinished());
		System.exit(0);
	} 

	
	public char[][] board;
	public char playerX;
	public TicTacToe() {}


	public void setStartPlayer() {
		this.playerX = 'x';
	}

	public void initializeBoard() {
		board = new char[3][3];
	}

	public void newBoard() {
		for(int rows = 0; rows < 3; rows++) {
			for(int cols = 0; cols < 3; cols++) {
				board[rows][cols] = '-';
			}
		}
	}

	//Board is displayed in current state
	public String displayBoard()
	{
		String printBoard = "";
		printBoard += "-------------\n";

		for(int rows = 0; rows < 3; rows++) {	
			printBoard +="| ";
			for(int cols = 0; cols < 3; cols++) {
				printBoard += board[rows][cols];
				printBoard += " | ";
			}

			printBoard += "\n";
			printBoard += "-------------\n";
		}

		System.out.println(printBoard);
		return printBoard;
	}

	//Check if any cell of the board still contains no value, represented by '-' if so 
	//the game continues else the board is full
	public boolean checkIfBoardFull()
	{
		boolean boardFull = true;

		for(int rows = 0; rows < 3; rows++) {
			for(int cols = 0; cols < 3; cols++) {
				if(board[rows][cols] == '-') {
					boardFull = false;
				}
			}
		}
		return boardFull;
	}

	//Register the current players moves on a specific cell on the board defined by
	//either 'x' or 'o'. The cell is defined by a input of a specific row and column.
	public boolean registerMove(int row, int col) {
		if ((row >= 0) && (row < 3)) {
        	if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = playerX;
                    return true;
                }
            }
        }
        return false;
	}

	//Create a function that switches between 'x' and 'o' to indicate
	//different users
	public void switchPlayer() {
		if(playerX == 'x') {
			playerX = 'o';
		}
		else {
			playerX = 'x';
		}
	}

	//A function to call all the other functions that monitor if a win is found
	//anywhere accross the entire board. Returns true if a win is found and false otherwise.
	public boolean checkIfWin() {
		return ( checkIfWinCols() || checkIfWinRows() || checkIfWinAcross()); 
	}

	//Go through all columns to check for a win, return true if a win is found
	private boolean checkIfWinCols() {
		for(int cols = 0; cols < 3; cols++) {
			if(checkIfTrueWin(board[0][cols], board[1][cols], board[2][cols]) == true) {
				return true;
			}
		}
		return false;
	}

	//Go through all rows to check for a win, return true if a win is found
	private boolean checkIfWinRows() {
		for(int rows = 0; rows < 3; rows++) {
			if(checkIfTrueWin(board[rows][0], board[rows][1], board[rows][2]) == true) {
				return true;
			}
		}
		return false;
	}

	//Check for a win across the board, return true is a win is found
	private boolean checkIfWinAcross() {
		return ((checkIfTrueWin(board[0][2], board[1][1], board[2][0]) == true) || (checkIfTrueWin(board[0][0], board[1][1], board[2][2]) == true));
	}

	//Make sure that the win is true, that is only the same variables, all 'x' or all 'o'
	//and not an empty cell
	private boolean checkIfTrueWin(char cell1, char cell2, char cell3) {
		return((cell1 != '-') && (cell1 == cell2) && (cell2 == cell3));
	}

	//Checking if the game is finished by checking if either there is a win or
	//the board is full and there is a draw
	public boolean gameFinished()
	{
		return this.checkIfWin() || this.checkIfBoardFull();
	}
	
}





