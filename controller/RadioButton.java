package controller;

import game.Handler;
import java.awt.Graphics;
import java.awt.Color;
import ui.Text;

public abstract class RadioButton extends Button {
	protected boolean isChecked;
	protected int radius;
	protected Color buttonColor;
	protected int mode;

	public RadioButton(Handler handler, String title, double x, double y, int width, int height, boolean checked)
	{
		super(handler, title, x, y, width, height);
		radius = (int) (height / 4.0);
		buttonColor = Color.BLACK;

		ControlArea.addRadioButton(this);
		isChecked = checked;

		mode = 0;
	}


	@Override
	public void render(Graphics g)
	{
		// draw the circle
		g.setColor(buttonColor);
		drawCenteredCircle(g, x + radius, (int)(y + height/2.0), radius);
		g.setColor(Color.WHITE);
		drawCenteredCircle(g, x + radius, (int)(y + height/2.0), radius-3);

		if (isChecked)
		{
			g.setColor(buttonColor);
			drawCenteredCircle(g, x + radius, (int)(y + height/2.0), radius-5);

		}

		// draw the title
		Text.drawString(g, text, (int) (x + radius*2 + 20), (int) (y + radius+ height/2.0 - 5), false, textColor, font);
	}


	public void drawCenteredCircle(Graphics g, int x, int y, int r) {
		x = (int)(x-r);
		y = (int)(y-r);
		g.fillOval(x,y,r*2,r*2);
	}


	public void onClick()
	{
		for (RadioButton b : ControlArea.radioButtons)
			b.isChecked = false;
		isChecked = true;
	}


	public int getMode()
	{
		return mode;
	}


	public boolean isChecked()
	{
		return isChecked;
	}
}
