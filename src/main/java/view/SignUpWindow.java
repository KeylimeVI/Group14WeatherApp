package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class SignUpWindow implements SoloWindow {

	private JFrame frame;

	public SignUpWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Sign Up");
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout(12, 12));
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