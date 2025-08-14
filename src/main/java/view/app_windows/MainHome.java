package view.app_windows;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class MainHome extends JPanel {
	
	private Button plannerButton;
	private Button weatherButton;

	public enum PanelButtons {
		PLANNER, WEATHER
	}

	public MainHome(Color BGColor) {
		initialize(BGColor);
	}

	private void initialize(Color BGColor) {
		
		setBackground(BGColor);

		setLayout(new FlowLayout(FlowLayout.CENTER));

		plannerButton = new Button("Show Planner");
		weatherButton = new Button("Show Weather");

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == plannerButton) {
					onClick(PanelButtons.PLANNER);
				} else if (evtSource == weatherButton) {
					onClick(PanelButtons.WEATHER);
				}
			}
			
		};

		plannerButton.addActionListener(actionListener);
		weatherButton.addActionListener(actionListener);

		add(plannerButton);
		add(weatherButton);
	}

	public Button getPlannerButton() {
		return plannerButton;
	}

	public Button getWeatherButton() {
		return weatherButton;
	}

	public void setActionListener(ActionListener actionListener) {
		plannerButton.addActionListener(actionListener);
		weatherButton.addActionListener(actionListener);
	}

	public void setColors(Color BGColor) {
		setBackground(BGColor);
	}

	public abstract void onClick(PanelButtons buttonClicked);
}
