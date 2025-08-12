package view.visual_pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tool.ColorTool;

public abstract class SingleTab extends JPanel {

	private JPanel labelPanel;
	private JLabel label;
	private JPanel highlight;
	private Color BGColor;
	private Color HLColor;
	private Color midHLColor;

	private static final int HIGHLIGHT_SIZE = 6;

	private boolean isHighlighted = false;

	public SingleTab(String text, Color BGColor, Color HLColor, Color midHLColor) {
		initialize(text, BGColor, HLColor, midHLColor);
	}

	public SingleTab(String text) {
		initialize(text, Color.WHITE, Color.BLACK, Color.WHITE);
	}

	private void initialize(String text, Color BGColor, Color HLColor, Color midHLColor) {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.BGColor = BGColor;
		this.HLColor = HLColor;
		this.midHLColor = midHLColor;

		labelPanel = new JPanel();
		label = new JLabel(text);
		labelPanel.add(label);
		labelPanel.setBackground(BGColor);
		add(labelPanel);

		highlight = new JPanel();
		highlight.setBackground(BGColor);
		Dimension hlSize = new Dimension(labelPanel.getPreferredSize().width, HIGHLIGHT_SIZE);
		highlight.setPreferredSize(hlSize);
		highlight.setMinimumSize(hlSize);
		highlight.setMaximumSize(hlSize);
		add(highlight);

		updateTextColor();

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClick();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// do nothing
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// do nothing
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// do nothing
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing
			}

		});
	}

	/**
	 * Highlights the tab by changing the colors
	 */
	public void highlight() {
		isHighlighted = true;
		updateTextColor();
		labelPanel.setBackground(midHLColor);
		highlight.setBackground(HLColor);
	}

	/**
	 * Dehighlights the tab by changing the colors
	 */
	public void dehighlight() {
		isHighlighted = false;
		updateTextColor();
		labelPanel.setBackground(BGColor);
		highlight.setBackground(BGColor);
	}

	/**
	 * Runs the {@code dehilight()} or {@code highlight()} method
	 *  depending on the current state of {@code isHighlighted} 
	 */
	public void toggleHighlight() {
		if (isHighlighted) {
			dehighlight();
		} else {
			highlight();
		}
	}

	/**
	 * Updates the tab with the new given colors
	 * @param BGColor
	 * @param HLColor
	 */
	public void setColors(Color BGColor, Color HLColor, Color midHLColor) {
		this.BGColor = BGColor;
		this.HLColor = HLColor;
		this.midHLColor = midHLColor;
		
		if (isHighlighted) {
			highlight.setBackground(HLColor);
			labelPanel.setBackground(midHLColor);
		} else {
			highlight.setBackground(BGColor);
			labelPanel.setBackground(BGColor);
		}
		updateTextColor();
	}

	/**
	 * Returns if the tab is highlighted
	 * @return isHighlighted
	 */
	public boolean isHighlighted() {
		return isHighlighted;
	}

	/**
	 * If the midHLColor is too light or dark,
	 * the text will update after running this method so
	 * it can still be readable.
	 */
	private void updateTextColor() {
		if (isHighlighted) {
			if (ColorTool.getValue(midHLColor) < 127) {
				label.setForeground(Color.WHITE);
			} else {
				label.setForeground(Color.BLACK);
			}
		} else {
			if (ColorTool.getValue(BGColor) < 127) {
				label.setForeground(Color.WHITE);
			} else {
				label.setForeground(Color.BLACK);
			}
		}
	}

	/**
	 * A method that runs when the tab is clicked.
	 */
	public abstract void onClick();
}
