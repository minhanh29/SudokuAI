package sudoku;

import java.awt.Graphics;
import java.awt.Color;
import game.*;
import controller.RouteAnimation;

public class Sudoku {
	private int width, x, y;
	private int[][] sudoku, backUp;
	private Cell[][] cells;
	private Handler handler;
	private double cell_size;
	private Color pathColor;

	private RouteAnimation routeAnimation;

	public Sudoku(Handler handler, RouteAnimation routeAnimation, int[][] sudoku, int x, int y, int width)
	{
		this.handler = handler;
		this.sudoku = sudoku;
		backUp = new int[9][9];
		Utils.copyArray(sudoku, backUp);
		this.routeAnimation = routeAnimation;
		cells = new Cell[sudoku.length][sudoku.length];

		this.x = x;
		this.y = y;
		this.width = width;

		Cell cell;
		cell_size = width / 9.0;
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				cell = new Cell(handler, this, sudoku[row][col], x + col * cell_size, y + row * cell_size, (int) cell_size, row, col);
				cells[row][col] = cell;
			}
		}

		// pathColor = new Color(255, 244, 89);
		pathColor = Color.WHITE;
	}


	public void tick()
	{
		Cell.POINTER = null;
		routeAnimation.tick();
		if (routeAnimation.getCurrentCell() != null)
		{
			Integer[] cell = routeAnimation.getCurrentCell();
			sudoku[cell[0]][cell[1]] = cell[2];

			// change all the cells' background on the path
			cells[cell[0]][cell[1]].setCustomBg(pathColor);

			// set the current cell as pointer
			int[] current = {cell[0], cell[1]};
			Cell.POINTER = current;
		}
		else
			updateCells();

		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
			{
				if (cells[row][col] != null)
					cells[row][col].tick();
			}


	}


	public void render(Graphics g)
	{
		for (int row = 0; row < sudoku.length; row++)
			for (int col = 0; col < sudoku.length; col++)
			{
				if (cells[row][col] != null)
				{
					cells[row][col].setValue(sudoku[row][col]);
					cells[row][col].render(g);
				}
			}

		// draw border
		g.setColor(Color.BLACK);
		for (int i = 0; i <= 9; i++)
		{
			// draw horizontal lines
			g.drawLine(x, (int) (y + i * cell_size), x + width, (int) (y + i * cell_size));

			// draw vertical lines
			g.drawLine((int) (x + i * cell_size), y, (int) (x + i * cell_size), y + width);

			// make the division lines thicker
			if (i % 3  == 0)
			{
				// draw horizontal lines
				g.drawLine(x, (int) (y + i * cell_size+1), x + width, (int) (y + i * cell_size+1));
				g.drawLine(x, (int) (y + i * cell_size-1), x + width, (int) (y + i * cell_size-1));

				// draw vertical lines
				g.drawLine((int) (x + i * cell_size+1), y, (int) (x + i * cell_size+1), y + width);
				g.drawLine((int) (x + i * cell_size-1), y, (int) (x + i * cell_size-1), y + width);
			}
		}
	}


	// functionalities
	public void reset()
	{
		for (int row = 0; row < sudoku.length; row++)
			for (int col = 0; col < sudoku[0].length; col++)
			{
				backUp[row][col] = 0;
				sudoku[row][col] = 0;
			}
		updateCells();
	}

	public void updateCells()
	{
		for (int row = 0; row < sudoku.length; row++)
			for (int col = 0; col < sudoku[0].length; col++)
				if (sudoku[row][col] == 0)
					cells[row][col].setCustomBg(null);
	}

	public void unsolved()
	{
		Utils.copyArray(backUp, sudoku);
	}

	public void setCellValue(int row, int col, int value)
	{
		sudoku[row][col]= value;
		backUp[row][col] = value;
	}

	public void setHintValue(int row, int col, int value, Color color)
	{
		sudoku[row][col]= value;
		cells[row][col].setCustomBg(color);
	}

	public int[][] getSudoku()
	{
		return sudoku;
	}

	public Cell[][] getCells()
	{
		return cells;
	}
}
