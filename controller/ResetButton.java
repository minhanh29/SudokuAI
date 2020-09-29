package controller;

import game.Handler;

public class ResetButton extends Button {
	public ResetButton(Handler handler, String title, double x, double y, int width, int height)
	{
		super(handler, title, x, y, width, height);
	}

	public void onClick()
	{
		handler.getSudoku().reset();
	}

}
