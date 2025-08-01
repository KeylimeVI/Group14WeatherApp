package view.app_windows;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.visual_pattern.ButtonPanel;
import view.visual_pattern.SoloWindow;

public class MainWindow extends SoloWindow {

	private Button exitButton;

	public enum Actions {
		EXIT
	}
	private Actions currentAction = Actions.EXIT;

	public MainWindow() {
		initialize();
	}

	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameY = (int) screenSize.getHeight() / 2;
		int frameX = (int) ((double) frameY * 1.5);

		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Weather Or Not");
		frame.setSize(frameX, frameY);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(12, 12));

		// ~~~ Exit Button ~~~ //
		exitButton = new Button("Exit");
		ButtonPanel buttonPanel = new ButtonPanel();

		buttonPanel.add(exitButton);

		setLocalButtonListeners();
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public Actions getCurrentAction() {
		return currentAction;
	}

	@Override
	public void setActionListener(ActionListener actionListener) {

		this.actionListener = actionListener;

		exitButton.addActionListener(actionListener);
	}

	@Override
	protected void setLocalButtonListeners() {
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAction = Actions.EXIT;
			}
		});
	}
}
