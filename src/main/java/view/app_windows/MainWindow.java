package view.app_windows;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import data_access.ConfigAccess;
import view.visual_pattern.ButtonPanel;
import view.visual_pattern.SingleSelectTabs;
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

		// ~~~ Config ~~~ //
		ConfigAccess config = new ConfigAccess(null);
		Color BGColor = config.getBGColor();
		Color HLColor = config.getHLColor();
		Color midHLColor = config.getMidHLColor();

		// ~~~ Setting up the frame ~~~ //
		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Weather Or Not");
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(12, 12));

		// ~~~ View Tabs ~~~ //
		SingleSelectTabs viewTabs = new SingleSelectTabs(BGColor, HLColor, midHLColor) {

			@Override
			public void onClick() {
				switch (getCurrentTabIndex()) {
					// TODO: implement the tab switching
					case 0:
						System.out.println("Home");
						break;
					case 1:
						System.out.println("Calendar");
						break;
					case 2:
						System.out.println("Settings");
						break;
				}
			}
			
		};
		viewTabs.addTab("Home");
		viewTabs.addTab("Calendar");
		viewTabs.addTab("Settings");
		
		viewTabs.selectSingleTab(0);

		// ~~~ Exit Button ~~~ //
		exitButton = new Button("Exit");
		ButtonPanel buttonPanel = new ButtonPanel();

		buttonPanel.add(exitButton);

		setLocalButtonListeners();

		frame.add(viewTabs);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.pack();
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
