package is.fantastic7.tictactoe;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;


public class TicTacToeWeb implements SparkApplication 
{
	public static void main(String[] args)
	{
		staticFileLocation("/public");
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

		get(new Route("/board")
		{
			@Override
			public Object handle(Request request, Response response)
			{
				newGame.initializeBoard();
				String board = newGame.displayBoard();
				return board;
			}
		});

		post(new Route("/click")
		{
			@Override
			public Object handle(Request request, Response response)
			{
				//request.params("id eða name sem þarf að vera í forminu")
				System.out.println(request.toString());
				newGame.initializeBoard();
				
				String board = newGame.displayBoard();
				return request.toString();
			}
		});

		/*post(new Route("/fill")
		{
			@Override
			public Object handle(Request request, Response response)
			{
				newGame.newBoard();
				String boardFill = newGame.displayBoard();
				return boardFill;
			}
		});

		/*get(new Route("/move")
		{
			@Override
			public Object handle(Request request, Response response)
			{
				newGame.regiserMove();
				String addMove = newGame.displayBoard();
				return addMove;
			}
		});*/

		/*post(new Route("/player")
		{
			@Override
			public Object handle(Request request, Response response)
			{

			}
		}*/
	}
}