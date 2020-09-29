package ai;

import java.util.Queue;
import java.util.Stack;

public interface SudokuSolver {
	// solve the puzzle
	// return true if there is a solution, false otherwise
	public boolean solve();

	// get the solution array
	public int[][] getSolution();

	// return the positions that the solver has traversed
	public Queue<Integer[]> getRoute();

	// return the positions to give the hints
	public Stack<Integer[]> getHints();
}
