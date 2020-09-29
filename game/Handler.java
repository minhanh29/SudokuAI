package game;

import sudoku.Sudoku;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Handler {
	private Game game;
	private Sudoku sudoku;

	public Handler(Game game)
	{
		this.game = game;
	}

	public BufferStrategy getBufferStrategy()
	{
		return game.getBufferStrategy();
	}

	public Graphics getGraphics()
	{
		return game.getGraphics();
	}

	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}

	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}

	public Game getGame()
	{
		return game;
	}

	public int getWidth()
	{
		return game.getWidth();
	}

	public int getHeight()
	{
		return game.getHeight();
	}

	public void setSudoku(Sudoku sudoku)
	{
		this.sudoku = sudoku;
	}

	public Sudoku getSudoku()
	{
		return sudoku;
	}
}
