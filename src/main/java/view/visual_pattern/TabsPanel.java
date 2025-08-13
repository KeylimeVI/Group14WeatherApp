package view.visual_pattern;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JPanel;

public abstract class TabsPanel extends JPanel {

	protected Color BGColor;
	protected Color HLColor;
	protected Color midHLColor;

	protected Vector<SingleTab> tabs;

	public TabsPanel(Color BGColor, Color HLColor, Color midHLColor) {
		initialize(BGColor, HLColor, midHLColor);
	}

	private void initialize(Color BGColor, Color HLColor, Color midHLColor) {
		this.BGColor = BGColor;
		this.HLColor = HLColor;
		this.midHLColor = midHLColor;
		tabs = new Vector<>();

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

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
				System.out.println("asd");
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing
			}
			
		});
	}
	
	/**
	 * Returns the tab from the given index. Works the same as a Vector
	 * 
	 * @param index
	 * @return the {@code SingleTab} at {@code index}
	 */
	public SingleTab getTab(int index) {
		return tabs.get(index);
	};

	/**
	 * Sets the colors for all the tabs
	 * @param BGColor
	 * @param HLColor
	 */
	public void setColors(Color BGColor, Color HLColor, Color midHLColor) {
		int tabsCount = tabs.size();
		for (int i = 0; i < tabsCount; i++) {
			getTab(i).setColors(BGColor, HLColor, midHLColor);

		}
	}

	/**
	 * Appends a {@code SingleTab} at the end with the given text.
	 * @param text
	 */
	public void addTab(String text) {

		SingleTab newTab = new SingleTab(text, BGColor, HLColor, midHLColor) {
			
			private int index = tabs.size();

			@Override
			public void onClick() {
				followClick(index);
				TabsPanel.this.onClick();
			}
		};
		tabs.add(newTab);
		add(newTab);
	};

	/**
	 * A method run immediately after clicking a tab and
	 * before {@code onClick()} runs at all
	 * @param index the index in {@code this.tabs} of the {@code SingleTab} that was clicked
	 */
	protected abstract void followClick(int index);

	/**
	 * A method run after clicking a tab and after {@code followClick()}
	 */
	public abstract void onClick();
}
