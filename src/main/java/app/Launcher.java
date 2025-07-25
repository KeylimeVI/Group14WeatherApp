package app;

import javax.swing.SwingUtilities;

import view.ViewManager;

public class Launcher {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ViewManager viewManager = new ViewManager();
				viewManager.view(ViewManager.ViewState.LAUNCH);
			}
		});
	}
}
