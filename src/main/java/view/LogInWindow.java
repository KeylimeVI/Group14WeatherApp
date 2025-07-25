package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogInWindow implements SoloWindow {

	private JFrame frame;

	public LogInWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Log In");
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout(12, 12));

		// ~~~ Title ~~~ //
		JLabel title = new JLabel("Log In");
		JPanel titleHolder = new JPanel();
		titleHolder.add(title);
		
		frame.add(titleHolder, BorderLayout.NORTH);
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
