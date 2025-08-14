package view.app_windows;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class MainSettings extends JPanel {
	
	private Button lightModeButton;
	private Button darkModeButton;

	public enum PanelButtons {
		LIGHT_MODE, DARK_MODE
	}

	public MainSettings(Color BGColor) {
		initialize(BGColor);
	}

	private void initialize(Color BGColor) {
		
		setVisible(false);
		setBackground(BGColor);

		setLayout(new FlowLayout(FlowLayout.CENTER));

		lightModeButton = new Button("Light Mode");
		darkModeButton = new Button("Dark Mode");

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == lightModeButton) {
					onClick(PanelButtons.LIGHT_MODE);
				} else if (evtSource == darkModeButton) {
					onClick(PanelButtons.DARK_MODE);
				}
			}
			
		};

		lightModeButton.addActionListener(actionListener);
		darkModeButton.addActionListener(actionListener);

		add(lightModeButton);
		add(darkModeButton);
	}

	public Button getPlannerButton() {
		return lightModeButton;
	}

	public Button getWeatherButton() {
		return darkModeButton;
	}

	public void setActionListener(ActionListener actionListener) {
		lightModeButton.addActionListener(actionListener);
		darkModeButton.addActionListener(actionListener);
	}

	public void setColors(Color BGColor) {
		setBackground(BGColor);
	}

	public abstract void onClick(PanelButtons buttonClicked);
}
