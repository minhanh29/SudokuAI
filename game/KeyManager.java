package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private String character;

	public KeyManager()
	{
		character = "";
	}

	public String getInput()
	{
		return character;
	}

	public void keyPressed(KeyEvent e)
	{
		char c = (char) e.getKeyCode();
		character = "" + c;
	}


	public void keyReleased(KeyEvent e)
	{
		character = "";
	}


	public void keyTyped(KeyEvent e)
	{

	}
}
