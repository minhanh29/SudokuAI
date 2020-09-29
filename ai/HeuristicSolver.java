package ai;

import java.util.PriorityQueue;

public class HeuristicSolver extends BackTrackSolver {
	Value[][] values;
	private PriorityQueue<Value> blankCells;

	public HeuristicSolver(int[][] sudoku)
	{
		super(sudoku);
		values = new Value[9][9];
		blankCells = new PriorityQueue<Value>();
	}


	// find the solution
	public boolean solve(int[][] sudoku_t)
	{
		updateValues();
		if (!blankCells.isEmpty())
		{
			Value vals = blankCells.poll();
			int row = vals.getRow(), col = vals.getCol();

			for (Integer num : vals.getValues())
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

			// no solutions
			return false;
		}

		return true;
	}


	// find values for all the cells
	private void updateValues()
	{
		blankCells.clear();
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
			{
				if (sudoku[row][col] == 0)
				{
					Value val = possibleValues(row, col);
					blankCells.offer(val);
					values[row][col] = val;
				}
			}
	}


	// return all the possible values for the current cell
	private Value possibleValues(int row, int col)
	{
		Value result = new Value(row, col);
		if (sudoku[row][col] != 0)
			return result;
		for (int i = 1; i <= 9; i++)
			if (isValid(i, row, col, sudoku))
				result.add(i);

		return result;
	}
}
