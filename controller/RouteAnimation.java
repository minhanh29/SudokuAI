package controller;

import java.util.Queue;

public class RouteAnimation {
	protected int speed;
	protected long timer, lastTime;
	private Queue<Integer[]> route;
	private Integer[] cell;
	private boolean solving;

	public RouteAnimation(Queue<Integer[]> route, int speed)
	{
		this.speed = speed;
		this.route = route;
		cell = null;
		solving = false;

		timer = 0;
		lastTime = 0;
	}


	public void tick()
	{
		if (lastTime == 0)
			lastTime = System.currentTimeMillis();
		if (solving && route != null)
		{
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();

			if (timer > speed)
			{
				timer = 0;
				if (!route.isEmpty())
					cell = route.poll();
				else
				{
					solving = false;
					cell = null;
				}
			}
		}
	}


	public void setSolving(boolean solve)
	{
		solving = solve;
	}

	public void setRoute(Queue<Integer[]> route)
	{
		this.route = route;
	}

	public Integer[] getCurrentCell()
	{
		return cell;
	}
}

