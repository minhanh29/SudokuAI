package controller;

import game.Handler;

public class HeuristicButton extends RadioButton {
	public HeuristicButton(Handler handler, String title, double x, double y, int width, int height, boolean checked)
	{
		super(handler, title, x, y, width, height, checked);
		mode = 2;
	}
}
