package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import use_case.log_in.LogInController;
import use_case.sign_up.SignUpController;
import view.app_windows.MainWindow;
import view.app_windows.TimelineWindow;

public class ViewManager {
	private LaunchWindow launchWindow;
	private LogInWindow logInWindow;
	private SignUpWindow signUpWindow;

	private MainWindow mainWindow;

	private ActionListener launchListener;
	private ActionListener logInListener;
	private ActionListener signUpListener;

	private ActionListener mainWindowListener;

	private LogInController logInController;
	private SignUpController signUpController;
	
	public enum ViewState {
		LAUNCH, LOGIN, SIGNUP, MAIN
	}
	
	public ViewManager() {
		initialize();
	}

	private void initialize() {
		launchWindow = new LaunchWindow();
		logInWindow = new LogInWindow();
		signUpWindow = new SignUpWindow();

		launchListener = makeLaunchListener();
		logInListener = makeLogInListener();
		signUpListener = makeSignUpListener();

		launchWindow.setActionListener(launchListener);
		logInWindow.setActionListener(logInListener);
		signUpWindow.setActionListener(signUpListener);
	}

	public void view(ViewState viewState) {
		hideAll();
		
		switch (viewState) {
			case LAUNCH:
				launchWindow.show();
				break;
			case LOGIN:
				logInWindow.show();
				break;
			case SIGNUP:
				signUpWindow.show();
				break;
			case MAIN:
				mainWindow = MainWindowLoader.makeMainWindow();
				mainWindowListener = makeMainWindowListener();
				mainWindow.setActionListener(mainWindowListener);
				mainWindow.show();
				break;
		}
	}

	private ActionListener makeLaunchListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == launchWindow.getExitButton()) {
					closeAll();
				} else if (evtSource == launchWindow.getLogInButton()) {
					view(ViewState.LOGIN);
				} else if (evtSource == launchWindow.getSignUpButton()) {
					view(ViewState.SIGNUP);
				}
			}
		};
	}

	private ActionListener makeLogInListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == logInWindow.getBackButton()) {
					view(ViewState.LAUNCH);
				} else if (evtSource == logInWindow.getLogInButton()) {
					String username = logInWindow.getUsernameString();
					String password = logInWindow.getPasswordString();
					
					if (logInController.attemptLogIn(username, password)) {
						view(ViewState.MAIN);
					}
				}
			}
		};
	}

	private ActionListener makeSignUpListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == signUpWindow.getBackButton()) {
					view(ViewState.LAUNCH);
				} else if (evtSource == signUpWindow.getSignUpButton()) {
					String username = signUpWindow.getUsernameString();
					String password = signUpWindow.getPasswordString();

					signUpController.attemptSignUp(username, password);
				}
			}
		};
	}

	private ActionListener makeMainWindowListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				switch (mainWindow.getAction()) {
					case EXIT:
						closeAll();
						closeAllMain();
				}
			}
		};
	}

	public void hideAll() {
		launchWindow.hide();
		logInWindow.hide();
		signUpWindow.hide();
	}

	public void closeAll() {
		launchWindow.close();
		logInWindow.close();
		signUpWindow.close();
	}

	public void closeAllMain() {
		mainWindow.close();
	}

	public void setLogInController(LogInController logInController) {
		this.logInController = logInController;
	}

	public void setSignUpController(SignUpController signUpController) {
		this.signUpController = signUpController;
	}
}

class MainWindowLoader {
	
	public static MainWindow makeMainWindow() {
		return new MainWindow();
	}

	public static TimelineWindow makeTimelineWindow() {
		return new TimelineWindow();
	}
}