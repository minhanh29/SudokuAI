package controller;

import game.Handler;
import ai.*;
import ui.MessageBox;
import java.awt.Graphics;

public class SolveButton extends Button {
	private SudokuSolver solver;
	private RouteAnimation routeAnimation;
	private MessageBox messageBox;
	private long timer, lastTime, limitTime;

	public SolveButton(Handler handler, RouteAnimation routeAnimation, String text, double x, double y, int width, int height) {
		super(handler, text, x, y, width, height);
		this.routeAnimation = routeAnimation;
		limitTime = 3000;
		timer = 0;
		lastTime = 0;
	}

	@Override
	public void onClick() {
		if (ControlArea.checkedButton() == 1)
			solver = new BackTrackSolver(handler.getSudoku().getSudoku());
		if (ControlArea.checkedButton() == 2)
			solver = new HeuristicSolver(handler.getSudoku().getSudoku());

		if(solver.solve())
		{
			routeAnimation.setRoute(solver.getRoute());
			routeAnimation.setSolving(true);
		}
		else
		{
			float m_width = (float) 1.0/2 * handler.getWidth();
			float m_height = (float) 1.0/4 * handler.getHeight();
			messageBox = new MessageBox(100, 250, (int)m_width, (int)m_height, "No Solutions Found!");
		}

	}


	@Override
	public void render(Graphics g)
	{
		super.render(g);
		if (messageBox != null)
		{
			messageBox.render(g);
			if (lastTime == 0)
				lastTime = System.currentTimeMillis();
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();

			if (timer > limitTime)
			{
				timer = 0;
				lastTime = 0;
				messageBox = null;
			}
		}
	}
}
