package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.visual_pattern.QueryC;

public class LogInWindow implements SoloWindow {

	private JFrame frame;

	private Button backButton;

	private ActionListener actionListener;

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

		// ~~~ Question ~~~ //
		QueryC<TextField> queryUsername = new QueryC<TextField>("Username", new TextField(36));
		QueryC<TextField> queryPassword = new QueryC<TextField>("Password", new TextField(36));

		JPanel questionHolder = new JPanel();
		questionHolder.setLayout(new BoxLayout(questionHolder, BoxLayout.Y_AXIS));
		questionHolder.add(queryUsername);
		questionHolder.add(queryPassword);

		// ~~~ back to launch window ~~~ //
		JPanel backButtonHolder = new JPanel();
		backButton = new Button("Back");
		backButtonHolder.add(backButton);
		
		frame.add(titleHolder, BorderLayout.NORTH);
		frame.add(backButtonHolder, BorderLayout.SOUTH);
		frame.add(questionHolder, BorderLayout.CENTER);
	}

	public Button getBackButton() {
		return backButton;
	}

	@Override
	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.toFront();
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
		backButton.addActionListener(actionListener);
	}

	@Override
	public ActionListener getActionListener() {
		return actionListener;
	}
}
