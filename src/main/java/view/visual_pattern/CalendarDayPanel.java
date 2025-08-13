package view.visual_pattern;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarDayPanel extends JPanel {

	private JLabel label;
	
	private Color BGColor;
	private Color HLColor;
	private Color midHlColor;

	public CalendarDayPanel(String text, Color BGColor, Color HLColor, Color midHLColor) {
		initialize(text, BGColor, HLColor, midHLColor);
	}

	private void initialize(String text, Color BGColor, Color HLColor, Color midHLColor) {

		setColors(BGColor, HLColor, midHLColor);

		// TODO
		label = new JLabel(text);
		add(label);

		Dimension size = new Dimension(64, 64);

		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}

	/**
	 * Updates all the colors from the attributes
	 */
	public void updateColors() {
		setBackground(BGColor);
	}

	/**
	 * Set and update the colors of the day
	 * @param BGColor
	 * @param HLColor
	 * @param midHLColor
	 */
	public void setColors(Color BGColor, Color HLColor, Color midHLColor) {
		this.BGColor = BGColor;
		this.HLColor = HLColor;
		this.midHlColor = midHLColor;
		updateColors();
	}
}
