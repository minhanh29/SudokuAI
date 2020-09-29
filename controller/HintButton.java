package controller;

import game.*;
import ai.*;
import java.util.Stack;
import java.awt.Color;

public class HintButton extends Button {
	private BackTrackSolver solver;
	private Stack<Integer[]> hints;
	private int[][] sudoku;

	public HintButton(Handler handler, String title, double x, double y, int width, int height)
	{
		super(handler, title, x, y, width, height);
		this.sudoku = new int[9][9];
		update();
	}


	public void onClick()
	{
		if (hasChanged())
			update();

		if (!hints.isEmpty())
		{
			Integer[] cell = hints.pop();
			handler.getSudoku().setHintValue(cell[0], cell[1], solver.getSolution()[cell[0]][cell[1]], Color.YELLOW);
		}
	}


	// update the sudoku and solver
	private void update()
	{
			Utils.copyArray(handler.getSudoku().getSudoku(), sudoku);
			if (ControlArea.checkedButton() == 1)
				solver = new BackTrackSolver(handler.getSudoku().getSudoku());
			if (ControlArea.checkedButton() == 2)
				solver = new HeuristicSolver(handler.getSudoku().getSudoku());
			if (solver.solve())
				hints = solver.getHints();
	}


	// check if the sudoku has changed
	private boolean hasChanged()
	{
		for (int row = 0; row < sudoku.length; row++)
			for (int col = 0; col < sudoku[0].length; col++)
				if (sudoku[row][col] != handler.getSudoku().getSudoku()[row][col])
					return true;
		return false;
	}
}
