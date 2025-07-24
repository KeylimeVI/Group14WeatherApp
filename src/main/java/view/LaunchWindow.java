package view;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Weather Or Not");
		frame.setSize(frameX, frameY);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(12, 12));

		JLabel launcherTitle = new JLabel("Weather Or Not");

		Button loginButton = new Button("Log In");
		Button singupButton = new Button("Sign Up");
		Button exitButton = new Button("Exit");

		JPanel buttonPanel = new ButtonPanel();
		buttonPanel.add(loginButton);
		buttonPanel.add(singupButton);
		buttonPanel.add(exitButton);

		frame.add(launcherTitle, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void hide() {
		frame.setVisible(false);
	}

	@Override
	public void close() {
		frame.setVisible(false);
		frame.dispose();
	}
}
