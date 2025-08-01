package view.view_manager;

public class ViewManager {

	private LaunchViewManager launchViewManager;
	private MainViewManager mainViewManager;
	
	public ViewManager() {
		initialize();
	}

	private void initialize() {

		mainViewManager = new MainViewManager() {

			@Override
			public void view(ViewState viewState) {
				switch (viewState) {
					case MAIN:
						mainViewManager.getMainWindow().show();
						break;
					case TIMELINE:
						break;
				}
			}
		};

		launchViewManager = new LaunchViewManager() {
			@Override
			public void view(ViewState viewState) {
				hideAll();

				switch (viewState) {
					case LAUNCH:
						getLaunchWindow().show();
						break;
					case LOGIN:
						getLogInWindow().show();
						break;
					case SIGNUP:
						getSignUpWindow().show();
						break;
					case MAIN:
						mainViewManager.startMainWindows();
						mainViewManager.view(MainViewManager.ViewState.MAIN);
						launchViewManager.closeAll();
						break;
				}
			}
		};
	}

	public LaunchViewManager getLaunchViewManager() {
		return launchViewManager;
	}

	public MainViewManager getMainViewManager() {
		return mainViewManager;
	}
}
