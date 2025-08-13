package view.visual_pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JPanel;

public class CalendarWeekPanel extends JPanel {
	
	private Color BGColor;
	private Color HLColor;
	private Color midHlColor;

	private Vector<CalendarDayPanel> days;

	public CalendarWeekPanel(Color BGColor, Color HLColor, Color midHLColor) {
		initialize(7, BGColor, HLColor, midHLColor);
	}

	private void initialize(int dayCount, Color BGColor, Color HLColor, Color midHLColor) {

		this.BGColor = BGColor;
		this.HLColor = HLColor;
		this.midHlColor = midHLColor;

		days = new Vector<CalendarDayPanel>();

		setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));

		for (int i = 0; i < dayCount; i++) {
			CalendarDayPanel newDay = new CalendarDayPanel("day", BGColor, HLColor, midHLColor);
			days.add(newDay);
			add(newDay);
		}

		Dimension dayPanelSize = days.get(0).getPreferredSize();
		Dimension size = new Dimension(dayCount * dayPanelSize.width, dayPanelSize.height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}

	public CalendarDayPanel getCalendarDayPanel(int index) {
		return days.get(index);
	}

	/**
	 * Updates all the colors from the attributes
	 */
	public void updateColors() {
		int dayCount = days.size();
		for (int i = 0; i < dayCount; i++) {
			days.get(i).updateColors();
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
