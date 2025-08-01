package view.app_windows;

import java.awt.event.ActionListener;

import view.visual_pattern.SoloWindow;

public class TimelineWindow extends SoloWindow {

	public TimelineWindow() {
		initialize();
	}

	private void initialize() {
		
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	@Override
	protected void setLocalButtonListeners() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setLocalButtonListeners'");
	}
}
