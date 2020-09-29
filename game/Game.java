package game;

import window.*;
import states.*;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	private Window window;
	private String title;
	private int width, height;

	private Thread thread;
	private boolean running = false;

	private Graphics g;
	private BufferStrategy bs;

	// managers
	KeyManager keyManager;
	MouseManager mouseManager;

	// states
	GameState gameState;

	// handler
	private Handler handler;

	public Game(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;

		mouseManager = new MouseManager();
		keyManager = new KeyManager();
	}


	public synchronized void start()
	{
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}


	public synchronized void stop()
	{
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}


	public void run()
	{
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1)
			{
				tick();
				render();

				delta = 0;
			}
		}

		stop();
	}


	private void init()
	{
		window = new Window(width, height, title);
		window.getJFrame().addKeyListener(keyManager);
		window.getJFrame().addMouseListener(mouseManager);
		window.getJFrame().addMouseMotionListener(mouseManager);
		window.getCanvas().addMouseListener(mouseManager);
		window.getCanvas().addMouseMotionListener(mouseManager);

		handler = new Handler(this);

		gameState = new GameState(handler);
		State.setState(gameState);
	}


	private void tick()
	{
		if (State.getState() != null)
			State.getState().tick();
	}


	private void render()
	{
		bs = window.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			window.getCanvas().createBufferStrategy(2);
			return;
		}

		g = bs.getDrawGraphics();

		// clear screen
		g.clearRect(0, 0, width, height);

		if (State.getState() != null)
			State.getState().render(g);

		bs.show();
		g.dispose();
	}


	// GETTERS
	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public MouseManager getMouseManager()
	{
		return mouseManager;
	}

	public KeyManager getKeyManager()
	{
		return keyManager;
	}

	public Graphics getGraphics()
	{
		return g;
	}

	public BufferStrategy getBufferStrategy()
	{
		return bs;
	}

	public Window getWindow()
	{
		return window;
	}
}
