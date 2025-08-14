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

	private SingleSelectTabs viewTabs;
	private MainHome mainHome;

	private Button exitButton;

	public enum Actions {
		EXIT, PLANNER, WEATHER
	}
	private Actions currentAction = Actions.EXIT;

	public enum Tabs {
		HOME, SETTINGS
	}

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

		frame.getContentPane().setBackground(BGColor);
		frame.getRootPane().setBackground(BGColor);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Weather Or Not");
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(12, 12));

		// ~~~ View Tabs ~~~ //
		viewTabs = new SingleSelectTabs(BGColor, HLColor, midHLColor) {

			@Override
			public void onClick() {
				switch (getCurrentTabIndex()) {
					// TODO: implement the tab switching
					case 0:
						System.out.println("Home");
						break;
					case 1:
						System.out.println("Settings");
						break;
				}
			}
			
		};
		viewTabs.addTab("Home");
		viewTabs.addTab("Settings");
		
		viewTabs.selectSingleTab(0);

		// ~~~ Home ~~~ //
		mainHome = initMainHome(BGColor);

		// ~~~ Exit Button ~~~ //
		exitButton = new Button("Exit");
		ButtonPanel buttonPanel = new ButtonPanel();

		buttonPanel.add(exitButton);

		setLocalButtonListeners();

		frame.add(viewTabs, BorderLayout.NORTH);
		frame.add(mainHome, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.pack();
	}

	public Actions getCurrentAction() {
		return currentAction;
	}

	public void viewTab(Tabs tab) {
		switch (tab) {
			case HOME:
				break;
			case SETTINGS:
				break;
		}
	}

	private MainHome initMainHome(Color BGColor) {
		return new MainHome(BGColor) {

			@Override
			public void onClick(PanelButtons buttonClicked) {
				switch (buttonClicked) {
					case PLANNER:
						currentAction = Actions.PLANNER;
						break;
					case WEATHER:
						currentAction = Actions.WEATHER;
						break;
				}
			}
			
		};
	}

	public void setColors(Color BGColor, Color HLColor, Color midHLColor) {
		this.BGColor = BGColor;
		this.HLColor = HLColor;
		this.midHLColor = midHLColor;

		frame.getContentPane().setBackground(BGColor);
		frame.getRootPane().setBackground(BGColor);
		
		mainHome.setColors(BGColor);
		viewTabs.setColors(BGColor, HLColor, midHLColor);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {

		this.actionListener = actionListener;

		exitButton.addActionListener(actionListener);
		mainHome.setActionListener(actionListener);
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
