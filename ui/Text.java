package ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;

public class Text {
	public static Color color = new Color((float) (21/255.0), (float) (86/255.0), (float) (133/255.0));

	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color color, Font font)
	{
		g.setColor(color);
		g.setFont(font);
		int x = xPos;
		int y = yPos;

		// center at (x, y)
		if (center)
		{
			FontMetrics fmt = g.getFontMetrics(font);
			x = xPos - fmt.stringWidth(text) / 2;
			y = (yPos - fmt.getHeight() / 2) + fmt.getAscent();
		}

		g.drawString(text, x, y);
	}
}
