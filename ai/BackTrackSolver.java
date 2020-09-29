package ai;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BackTrackSolver implements SudokuSolver {
	protected int[][] sudoku;
	protected Queue<Integer[]> route;
	protected Stack<Integer[]> hints;

	public BackTrackSolver(int[][] sudoku)
	{
		this.sudoku = new int[9][9];
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				this.sudoku[row][col] = sudoku[row][col];
		route = (Queue<Integer[]>) (new LinkedList<Integer[]>());
		hints = new Stack<Integer[]>();
	}


	public Queue<Integer[]> getRoute()
	{
		return route;
	}


	public Stack<Integer[]> getHints()
	{
		return hints;
	}


	public int[][] getSolution()
	{
		return sudoku;
	}


	// check if the given sudoku is valid
	protected boolean isValidPuzzle()
	{
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
			{
				if (sudoku[row][col] == 0)
					continue;
				for (int i = 0; i < 9; i++)
				{
					// check row
					if (i != col && sudoku[row][i] == sudoku[row][col]) return false;

					// check column
					if (i != row && sudoku[i][col] == sudoku[row][col]) return false;

					// check 3x3 square
					if (3 * (row/3) + i/3 != row && 3 * (col/3) + i%3 != col && sudoku[3 * (row/3) + i/3][3 * (col/3) + i%3] == sudoku[row][col]) return false;
			}
					}
		return true;
	}

	// check if value is valid for a certain cell
	protected boolean isValid(int value, int row, int col, int[][] sudoku_t)
	{
		for (int i = 0; i < 9; i++)
		{
			// check row
			if (sudoku_t[row][i] == value) return false;

			// check column
			if (sudoku_t[i][col] == value) return false;

			// check 3x3 square
			if (sudoku_t[3 * (row/3) + i/3][3 * (col/3) + i%3] == value) return false;
		}

		return true;
	}


	public boolean solve()
	{
		if (!isValidPuzzle())
			return false;

		return solve(sudoku);
	}


	// solve the sudoku using backtracking
	protected boolean solve(int[][] sudoku_t)
	{
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
			{
				if (sudoku_t[row][col] != 0)
					continue;
				for (int num = 1; num <= 9; num++)
				{
					if (isValid(num, row, col, sudoku_t))
					{
						sudoku_t[row][col] = num;
						Integer[] cell = {row, col, num};
						route.offer(cell);
						if (solve(sudoku_t))
						{
							hints.push(cell);
							return true;
						}

						// there are no solutions for this num
						sudoku_t[row][col] = 0;
						Integer[] cell2 = {row, col, 0};
						route.offer(cell2);
					}
				}

				return false;
			}

		return true;
	}
}
