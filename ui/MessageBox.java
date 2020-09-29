package ui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class MessageBox {
	String message;
	int width, height, x, y, border;
	private Font font;

	public MessageBox(float x, float y, int width, int height, String message)
	{
		this.x = (int) x;
		this.y = (int) y;
		this.width = width;
		this.height = height;
		this.message = message;
		border = 3;
		font = new Font("Helvetica", Font.BOLD, 30);
	}


	public void tick()
	{

	}

	public void render(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);   // border
		g.setColor(Color.WHITE);
		g.fillRect(x+border, y+border, width-2*border, height-2*border);
		Text.drawString(g, message, (int)(x + width/2.0), (int)(y + height/2.0), true, Color.RED, font);
	}
}
