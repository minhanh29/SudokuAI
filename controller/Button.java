package controller;

import ui.*;
import java.awt.Graphics;
import game.*;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;

public abstract class Button {
	protected String text;
	protected int x, y;
	protected int width, height, border_size;
	protected Handler handler;
	protected Rectangle bound;
	protected Font font;
	protected Color background, hoverBg, clickBg, textColor;
	protected boolean clickable;
	private long lastT, time, limitT;


	public Button(Handler handler, String text, double x, double y, int width, int height)
	{
		ControlArea.addButton(this);

		this.text = text;
		this.x = (int) x;
		this.y = (int) y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		border_size = 2;

		clickable = true;

		bound = new Rectangle((int) x, (int) y, width, height);
		font = new Font("Helvetica", Font.BOLD, 20);
		hoverBg = new Color(184, 231, 255);
		clickBg = new Color(69, 150, 255);
		textColor = Text.color;

		lastT = 0;
		time = 0;
		limitT = 200;
	}

	public void tick()
	{
		background = Color.WHITE;
		if (bound.contains(handler.getMouseManager().getX(), handler.getMouseManager().getY()))
		{
			background = hoverBg;
			if (handler.getMouseManager().isClicked())
			{
				background = clickBg;
				if (clickable)
				{
					clickable = false;
					onClick();
				}
			}
		}

		// delay between clicking
		if (!clickable)
		{
			if (lastT== 0)
				lastT= System.currentTimeMillis();
			time += System.currentTimeMillis() - lastT;
			lastT= System.currentTimeMillis();

			if (time > limitT)
			{
				time = 0;
				lastT = 0;
				clickable = true;
			}
		}

	}

	public void render(Graphics g)
	{
		g.setColor(textColor);
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(background);
		g.fillRect(x + border_size, y + border_size, width - 2 * border_size, height - 2 * border_size);
		Text.drawString(g, text, (int) (x + width/2.0), (int) (y + height/2.0), true, textColor, font);
	}

	public abstract void onClick();
}
