package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.visual_pattern.ButtonPanel;
import view.visual_pattern.SoloWindow;

public class LaunchWindow extends SoloWindow {

	private Button logInButton;
	private Button signUpButton;
	private Button exitButton;

	public enum Actions {
		EXIT, LOGIN, SIGNUP
	}

	private Actions currentAction = Actions.EXIT;

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

		setLocalButtonListeners();
		frame.add(titleHolder, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public Actions getCurrentAction() {
		return currentAction;
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;

		logInButton.addActionListener(actionListener);
		signUpButton.addActionListener(actionListener);
		exitButton.addActionListener(actionListener);
	}

	@Override
	protected void setLocalButtonListeners() {
		logInButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAction = Actions.LOGIN;
			}
		});

		signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAction = Actions.SIGNUP;
			}
		});

		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAction = Actions.EXIT;
			}
		});
	}
}
