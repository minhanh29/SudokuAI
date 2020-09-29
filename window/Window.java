package window;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class Window {
	private JFrame frame;
	private JPanel panel;
	private Canvas canvas;

	private String title;
	private int width, height;

	public Window(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;

		createWindow();
	}

	private void createWindow()
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(30, 144, 255));

		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // center the frame
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();
	}


	public Canvas getCanvas()
	{
		return canvas;
	}

	public JFrame getJFrame()
	{
		return frame;
	}
}
