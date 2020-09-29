package states;

import ai.SudokuGenerator;

import java.awt.Graphics;
import game.Handler;
import sudoku.Sudoku;
import controller.*;

public class GameState extends State{
	private Sudoku sudoku;
	private Button backtrackButton, heuristicButton, hintButton, resetButton, unsolveButton, solveButton;
	private RouteAnimation routeAnimation;

	public GameState(Handler handler)
	{
		super(handler);

		routeAnimation = new RouteAnimation(null, 10);
		SudokuGenerator generator = new SudokuGenerator("");
		sudoku = new Sudoku(handler, routeAnimation, generator.getSudoku(), 10, 10, handler.getHeight() - 20);

		handler.setSudoku(sudoku);

		// Buttons
		backtrackButton = new BackTrackButton(handler, "Back Track", handler.getHeight() + 50, 30, 200, 50, true);
		heuristicButton = new HeuristicButton(handler, "Heuristic BT", handler.getHeight() + 50, 90, 200, 50, false);
		solveButton = new SolveButton(handler, routeAnimation, "Solve", handler.getHeight() + 50, 170, 200, 50);
		unsolveButton = new UnsolvedButton(handler, "Unsolve", handler.getHeight() + 50, 240, 200, 50);
		resetButton = new ResetButton(handler, "Reset", handler.getHeight() + 50, 310, 200, 50);
		hintButton = new HintButton(handler, "Hint", handler.getHeight() + 50, 380, 200, 50);
	}


	@Override
	public void tick()
	{
		sudoku.tick();
		for (Button button : ControlArea.buttons)
			button.tick();
	}


	@Override
	public void render(Graphics g)
	{
		sudoku.render(g);
		for (Button button : ControlArea.buttons)
			button.render(g);
	}
}
