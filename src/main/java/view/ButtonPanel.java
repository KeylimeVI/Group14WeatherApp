package view;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	public ButtonPanel() {
		initialize();
	}

	public void initialize() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setVisible(true);
	}
}
