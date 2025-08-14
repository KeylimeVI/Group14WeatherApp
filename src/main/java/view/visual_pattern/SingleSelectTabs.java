package view.visual_pattern;

import java.awt.Color;

public abstract class SingleSelectTabs extends TabsPanel {

	private int currentTab;

	public SingleSelectTabs(Color BGColor, Color HLColor, Color midHLColor) {
		super(BGColor, HLColor, midHLColor);
	}

	/**
	 * Returns the currently highlighted tab
	 * @return the currently highlighted {@code SingleTab}
	 */
	public SingleTab getCurrentTab() {
		return getTab(currentTab);
	}

	/**
	 * Returns the index of the currently highlighted tab
	 * @return the index of the currently highlighted {@code SingleTab}
	 */
	public int getCurrentTabIndex() {
		return currentTab;
	}

	/**
	 * Selects a tab; highlights one tab and dehighlights all others.
	 * Purely visual.
	 * @param index
	 */
	public void selectSingleTab(int index) {
		getCurrentTab().dehighlight();
		currentTab = index;
		tabs.get(index).highlight();
	}

	@Override
	protected void followClick(int index) {
		selectSingleTab(index);
	}
}
