package view.view_manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.app_windows.MainWindow;

public abstract class MainViewManager {
	
	private MainWindow mainWindow;
	private ActionListener mainWindowListener;


	public enum ViewState {
		MAIN, TIMELINE
	}

	public MainViewManager() {
	}

	public void startMainWindows() {
		mainWindow = new MainWindow();

		mainWindowListener = makeMainWindowListener();
		mainWindow.setActionListener(mainWindowListener);

	}

	public abstract void view(ViewState viewState);

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void closeAllMain() {
		mainWindow.close();
	}

	private ActionListener makeMainWindowListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: add actions for MainWindow
				switch (mainWindow.getCurrentAction()) {
					case EXIT:
						closeAllMain();
				}
			}
		};
	}

	private ActionListener makeTimelineWindowListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: add actions for TimelineWindow
				throw new UnsupportedOperationException("timelineWindowListener has no ears");
			}
		};
	}
}

