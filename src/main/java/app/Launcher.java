package app;

import javax.swing.SwingUtilities;

import view.LaunchWindow;

public class Launcher {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LaunchWindow launch = new LaunchWindow();
				launch.show();
			}
		});
	}
}
