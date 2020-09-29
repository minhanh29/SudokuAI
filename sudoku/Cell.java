package sudoku;

import java.awt.Graphics;
import java.awt.Color;
import ui.Text;
import java.awt.Font;
import java.awt.Rectangle;
import game.*;

public class Cell {
	private int width, value;
	private double x, y;
	private int row, col;
	private Font font;
	private Color backGround, customBg, textColor;
	private Rectangle bound;
	private Handler handler;
	private Sudoku sudoku;
	private boolean hovering, clicked;

	public static int[] POINTER = null;

	public Cell(Handler handler, Sudoku sudoku, int value, double x, double y, int width, int row, int col)
	{
		this.handler = handler;
		this.sudoku = sudoku;
		clicked = hovering = false;

		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
		this.width = width;
		this.value = value;

		bound = new Rectangle((int)x, (int)y, width, width);

		font = new Font("Helvetica", Font.PLAIN, width/2);
		backGround = new Color((float) (196/255.0), (float) (230/255.0), (float) (255/255.0));
		textColor = new Color((float) (21/255.0), (float) (86/255.0), (float) (133/255.0));
		customBg = null;
	}


	public void tick()
	{
		if (bound.contains(handler.getMouseManager().getX(), handler.getMouseManager().getY()))
			hovering = true;
		else
			hovering = false;

		if (bound.contains(handler.getMouseManager().getX(), handler.getMouseManager().getY()) &&
				handler.getMouseManager().isClicked())
		{
			clicked = true;
		}

		if (clicked)
			onClick();
	}


	public void render(Graphics g)
	{
		String text = "" + value;
		Color bg = backGround;

		if (customBg != null)
			bg = customBg;

		if (value == 0)
		{
			bg = Color.WHITE;
			text = "";
		}

		if (hovering)
			bg = Color.YELLOW;

		if (clicked)
			bg = Color.GREEN;

		if (POINTER != null && row == POINTER[0] && col == POINTER[1])
			bg = Color.GREEN;

		g.setColor(bg);
		g.fillRect((int) x, (int) y, width, width);
		Text.drawString(g, text, (int) (x + width/2.0), (int) (y + width/2.0), true, textColor, font);
	}


	private void onClick()
	{
		String input = handler.getKeyManager().getInput();
		if (input != "")
		{
			try {
				int val = Integer.parseInt(input);
				if (val >= 0 && val <= 9)
				{
					value = val;
					sudoku.setCellValue(row, col, value);
				}
			}
		catch (NumberFormatException e) {}
			clicked = false;
		}
	}


	public void setValue(int val)
	{
		value = val;
	}

	// set custom background
	public void setCustomBg(Color color)
	{
		customBg = color;
	}

}
