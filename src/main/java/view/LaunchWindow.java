package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LaunchWindow implements SoloWindow {
	
	private JFrame frame;

	private Button logInButton;
	private Button signUpButton;
	private Button exitButton;

	private ActionListener actionListener;

	public LaunchWindow() {
		initialize();
	}

	public void initialize() {
		// ~~~ Setting Up The Frame ~~~ //
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

		// ~~~ Title ~~~ //
		// TODO: replace with image so it looks nicer rather than just a line of text
		JLabel title = new JLabel("Weather Or Not");
		JPanel titleHolder = new JPanel();
		titleHolder.add(title);

		// ~~~ Buttons ~~~ //
		logInButton = new Button("Log In");
		signUpButton = new Button("Sign Up");
		exitButton = new Button("Exit");

		ButtonPanel buttonPanel = new ButtonPanel();

		buttonPanel.add(logInButton);
		buttonPanel.add(signUpButton);
		buttonPanel.add(exitButton);

		frame.add(titleHolder, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public Button getLogInButton() {
		return logInButton;
	}

	public Button getSignUpButton() {
		return signUpButton;
	}

	public Button getExitButton() {
		return exitButton;
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

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;

		logInButton.addActionListener(actionListener);
		signUpButton.addActionListener(actionListener);
		exitButton.addActionListener(actionListener);
	}

	@Override
	public ActionListener getActionListener() {
		return actionListener;
	}
}
