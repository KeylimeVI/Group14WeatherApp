package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignUpWindow implements SoloWindow {

	private JFrame frame;

	private Button backButton;

	private ActionListener actionListener;

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

		// ~~~ Title ~~~ //
		JLabel title = new JLabel("Sign Up");
		JPanel titleHolder = new JPanel();
		titleHolder.add(title);

		frame.add(titleHolder, BorderLayout.NORTH);

		// ~~~ back to launch window ~~~ //
		JPanel backButtonHolder = new JPanel();
		backButton = new Button("Back");
		backButtonHolder.add(backButton);
		
		frame.add(titleHolder, BorderLayout.NORTH);
		frame.add(backButtonHolder, BorderLayout.SOUTH);
	}

	public Button getBackButton() {
		return backButton;
	}

	@Override
	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
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
