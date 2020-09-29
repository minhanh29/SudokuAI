package states;

import java.awt.Graphics;
import game.*;

public abstract class State
{
	private static State currentState = null;
	protected Handler handler;

	public State(Handler handler)
	{
		this.handler = handler;
	}

	public static void setState(State state)
	{
		currentState = state;
	}


	public static State getState()
	{
		return currentState;
	}

	// update the state
	public abstract void tick();

	// render the images
	public abstract void render(Graphics graphics);
}
