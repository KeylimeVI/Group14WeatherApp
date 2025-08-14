package view.app_windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;

import view.visual_pattern.CalendarWeekPanel;

public class CalendarPanel extends JPanel {

	private Color BGColor;
	private Color HLColor;
	private Color midHlColor;
	
	private Vector<CalendarWeekPanel> weeks;

	public CalendarPanel(Color BGColor, Color HLColor, Color midHLColor) {
		initialize(6, BGColor, HLColor, midHLColor);
	}

	private void initialize(int weekCount, Color BGColor, Color HLColor, Color midHLColor) {

		this.BGColor = BGColor;
		this.HLColor = HLColor;
		this.midHlColor = midHLColor;

		weeks = new Vector<CalendarWeekPanel>();

		setLayout(new GridLayout(weekCount, 1, 0, 12));

		for (int i = 0; i < weekCount; i++) {
			CalendarWeekPanel newWeek = new CalendarWeekPanel(BGColor, HLColor, midHLColor);
			weeks.add(newWeek);
			add(newWeek);
		}

		Dimension weekPanelSize = weeks.get(0).getPreferredSize();
		Dimension size = new Dimension(weekPanelSize.width, weekCount * weekPanelSize.height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);	
	}

	/**
	 * Updates all the colors from the attributes
	 */
	public void updateColors() {
		int weekCount = weeks.size();
		for (int i = 0; i < weekCount; i++) {
			weeks.get(i).updateColors();
		}
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
