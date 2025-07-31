package view.app_windows;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.SoloWindow;
import view.visual_pattern.ButtonPanel;

public class MainWindow implements SoloWindow {

	private JFrame frame;

	private Button exitButton;

	private ActionListener actionListener;

	public enum MainAction {
		EXIT
	}
	private MainAction currentAction = MainAction.EXIT;

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
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAction = MainAction.EXIT;
			}
		});

		ButtonPanel buttonPanel = new ButtonPanel();

		buttonPanel.add(exitButton);

		frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public MainAction getAction() {
		return currentAction;
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

		exitButton.addActionListener(actionListener);
	}

	@Override
	public ActionListener getActionListener() {
		
		return actionListener;
	}
	
}
