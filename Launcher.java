import game.Game;

public class Launcher {
	private static int WIDTH = 1024, HEIGHT = 720;

	public static void main(String[] args)
	{
		Game game = new Game(WIDTH, HEIGHT, "Sudoku Solver");
		game.start();
	}
}
