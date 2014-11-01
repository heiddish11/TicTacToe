package is.fantastic7.tictactoe;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;

public class TicTacToeWeb implements SparkApplication 
{
	public static void main(String[] args)
	{
		SparkApplication tictactoe = new TicTacToeWeb();
		String port = System.getenv("PORT");
		if (port != null)
		{
			setPort(Integer.valueOf(port));
		}
		tictactoe.init();
	}

	public void init()
	{
		final TicTacToe newGame = new TicTacToe();

		post(new Route("/")
		{
			@Override
			public Object handle(Request request, Response response)
			{
				newGame.initializeBoard();
				String board = newGame.displayBoard();
				return board;
			}
		});

		/*post(new Route("/player")
		{
			@Override
			public Object handle(Request request, Response response)
			{

			}
		}*/
	}
}