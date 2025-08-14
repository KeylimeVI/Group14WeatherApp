package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.visual_pattern.ButtonPanel;
import view.visual_pattern.QueryC;
import view.visual_pattern.SoloWindow;

public class SignUpWindow extends SoloWindow {

	private Button signUpButton;
	private Button backButton;
	private QueryC<TextField> queryUsername;
	private QueryC<TextField> queryPassword;

	public enum Actions {
		SIGNUP, BACK
	}
	private Actions currentAction = Actions.BACK;

	public SignUpWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Sign Up");
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 200);
		frame.setLayout(new BorderLayout(12, 12));

		// ~~~ Title ~~~ //
		JLabel title = new JLabel("Sign Up");
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
		signUpButton = new Button("Sign Up");
		backButton = new Button("Back");
		
		buttonPanel.add(signUpButton);
		buttonPanel.add(backButton);

		setLocalButtonListeners();
		frame.add(titleHolder, BorderLayout.NORTH);
		frame.add(questionHolder, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public String getUsernameString() {
		return queryUsername.getComponent().getText();
	}

	public String getPasswordString() {
		return queryPassword.getComponent().getText();
	}

	public Actions getCurrentAction() {
		return currentAction;
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;

		signUpButton.addActionListener(actionListener);
		backButton.addActionListener(actionListener);
	}

	@Override
	protected void setLocalButtonListeners() {
		signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				currentAction = Actions.SIGNUP;
			}
		});
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				currentAction = Actions.BACK;
			}
		});
	}
}
