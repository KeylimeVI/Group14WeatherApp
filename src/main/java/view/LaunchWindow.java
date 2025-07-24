package view;

import java.awt.*;
import javax.swing.JFrame;

public class LaunchWindow implements SoloWindow {
	
	private JFrame frame;

	public LaunchWindow() {
		initialize();
	}

	public void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameY = (int) screenSize.getHeight() / 2;
		int frameX = (int) ((double) frameY / 1.5);

		frame = new JFrame();

		frame.setTitle("Weather Or Not");
		frame.setSize(frameX, frameY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	@Override
	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void hide() {
		frame.setVisible(false);
	}
}
