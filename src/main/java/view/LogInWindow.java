package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.visual_pattern.ButtonPanel;
import view.visual_pattern.QueryC;
import view.visual_pattern.SoloWindow;

public class LogInWindow extends SoloWindow {

	private Button logInButton;
	private Button backButton;
	private QueryC<TextField> queryUsername;
	private QueryC<TextField> queryPassword;

	public LogInWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Log In");
		frame.setSize(500, 200);
		frame.setLayout(new BorderLayout(12, 12));

		// ~~~ Title ~~~ //
		JLabel title = new JLabel("Log In");
		JPanel titleHolder = new JPanel();
		titleHolder.add(title);

		// ~~~ Questions ~~~ //
		queryUsername = new QueryC<TextField>("Username", new TextField(36));
		queryPassword = new QueryC<TextField>("Password", new TextField(36));

		JPanel questionHolder = new JPanel();
		questionHolder.setLayout(new BoxLayout(questionHolder, BoxLayout.Y_AXIS));
		questionHolder.add(queryUsername);
		questionHolder.add(queryPassword);

		// ~~~ Buttons ~~~ //
		ButtonPanel buttonPanel = new ButtonPanel();
		logInButton = new Button("Log In");
		backButton = new Button("Back");
		
		buttonPanel.add(logInButton);
		buttonPanel.add(backButton);
		
		frame.add(titleHolder, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(questionHolder, BorderLayout.CENTER);
	}

	public Button getLogInButton() {
		return logInButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public String getUsernameString() {
		return queryUsername.getComponent().getText();
	}

	public String getPasswordString() {
		return queryPassword.getComponent().getText();
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;

		logInButton.addActionListener(actionListener);
		backButton.addActionListener(actionListener);
	}
}
