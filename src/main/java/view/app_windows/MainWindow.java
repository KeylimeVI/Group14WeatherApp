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

	private Color BGColor;
	private Color HLColor;
	private Color midHLColor;

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
		BGColor = config.getBGColor();
		HLColor = config.getHLColor();
		midHLColor = config.getMidHLColor();

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

		// ~~~ Calendar ~~~ //
		// TODO: refactor this and also this should only show when in the calendar tab
		CalendarPanel calendarPanel = new CalendarPanel(BGColor, HLColor, midHLColor);

		// ~~~ Exit Button ~~~ //
		exitButton = new Button("Exit");
		ButtonPanel buttonPanel = new ButtonPanel();

		buttonPanel.add(exitButton);

		setLocalButtonListeners();

		frame.add(viewTabs, BorderLayout.NORTH);
		frame.add(calendarPanel, BorderLayout.CENTER);
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
