package view.view_manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.app_windows.MainWindow;
import view.app_windows.TripPlannerWindow;
import view.app_windows.WeatherWindow;

public abstract class MainViewManager {
	
	private MainWindow mainWindow;
	private ActionListener mainWindowListener;

	private TripPlannerWindow tripPlannerWindow;

	private WeatherWindow weatherWindow;

	public enum ViewState {
		MAIN, PLANNER, WEATHER
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
						break;
					case PLANNER:
						view(MainViewManager.ViewState.PLANNER);
						break;
					case WEATHER:
						view(MainViewManager.ViewState.WEATHER);
						break;
				}
			}
		};
	}

	public void startTripPlannerWindow() {
		tripPlannerWindow = new TripPlannerWindow();
	}

	public TripPlannerWindow getTripPlannerWindow() {
		return tripPlannerWindow;
	}

	public void startWeatherWindow() {
		weatherWindow = new WeatherWindow();
	}

	public WeatherWindow getWeatherWindow() {
		return weatherWindow;
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

