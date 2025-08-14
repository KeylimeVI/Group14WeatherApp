package view.app_windows;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private MainSettings mainSettings;

	private JPanel tabSelectorHolder;
	private JPanel tabHolder;
	private ButtonPanel buttonPanel;

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
					case 0:
						viewTab(Tabs.HOME);
						break;
					case 1:
						viewTab(Tabs.SETTINGS);
						break;
				}
			}
			
		};
		viewTabs.addTab("Home");
		viewTabs.addTab("Settings");
		
		viewTabs.selectSingleTab(0);

		// ~~~ Thing to hold tabs ~~~ //
		tabSelectorHolder = new JPanel();
		tabSelectorHolder.setBackground(BGColor);
		tabSelectorHolder.add(viewTabs);

		// ~~~ Home ~~~ //
		mainHome = initMainHome(BGColor);

		// ~~~ Settings ~~~ //
		mainSettings = initMainSettings(BGColor);

		// ~~~ TabHolder ~~~ //
		tabHolder = new JPanel();
		tabHolder.setBackground(BGColor);
		tabHolder.add(mainHome);
		tabHolder.add(mainSettings);

		// ~~~ Exit Button ~~~ //
		exitButton = new Button("Exit");
		buttonPanel = new ButtonPanel();
		buttonPanel.setBackground(BGColor);

		buttonPanel.add(exitButton);

		setLocalButtonListeners();

		frame.add(tabSelectorHolder, BorderLayout.NORTH);
		frame.add(tabHolder, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.pack();
	}

	public Actions getCurrentAction() {
		return currentAction;
	}

	public void viewTab(Tabs tab) {
		switch (tab) {
			case HOME:
				mainSettings.setVisible(false);

				mainHome.setVisible(true);
				break;
			case SETTINGS:
				mainHome.setVisible(false);

				mainSettings.setVisible(true);
				break;
		}
		frame.revalidate();
		frame.repaint();
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

	private MainSettings initMainSettings(Color BGColor) {
		return new MainSettings(BGColor) {

			@Override
			public void onClick(PanelButtons buttonClicked) {
				switch (buttonClicked) {
					case LIGHT_MODE:
						setLightMode();
						break;
					case DARK_MODE:
						setDarkMode();
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
		
		tabSelectorHolder.setBackground(BGColor);
		tabHolder.setBackground(BGColor);
		buttonPanel.setBackground(BGColor);
		mainHome.setColors(BGColor);
		mainSettings.setColors(BGColor);
		viewTabs.setColors(BGColor, HLColor, midHLColor);
	}

	public void setDarkMode() {
		setColors(new Color(0x002000), new Color(0x00ff00), new Color(0x008000));
	}

	public void setLightMode() {
		setColors(new Color(0xffffc0), new Color(0xc0c000), new Color(0xf0f000));
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
