package view.view_manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import use_case.log_in.LogInController;
import use_case.sign_up.SignUpController;
import view.LaunchWindow;
import view.LogInWindow;
import view.SignUpWindow;

public abstract class LaunchViewManager {

	private LaunchWindow launchWindow;
	private LogInWindow logInWindow;
	private SignUpWindow signUpWindow;

	private ActionListener launchListener;
	private ActionListener logInListener;
	private ActionListener signUpListener;

	private LogInController logInController;
	private SignUpController signUpController;
	
	public enum ViewState {
		LAUNCH, LOGIN, SIGNUP, MAIN
	}

	public LaunchViewManager() {
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

	public abstract void view(ViewState viewState);

	private ActionListener makeLaunchListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (launchWindow.getCurrentAction()) {
					case LOGIN:
						view(ViewState.LOGIN);
						break;
					case SIGNUP:
						view(ViewState.SIGNUP);
						break;
					case EXIT:
						closeAll();
						break;
				}
			}
		};
	}

	private ActionListener makeLogInListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch (logInWindow.getCurrentAction()) {
					case LOGIN:
						String username = logInWindow.getUsernameString();
						String password = logInWindow.getPasswordString();

						if (logInController.attemptLogIn(username, password)) {
						view(ViewState.MAIN);
						}
						break;
					case BACK:
						view(ViewState.LAUNCH);
						break;
				}
			}
		};
	}

	private ActionListener makeSignUpListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (signUpWindow.getCurrentAction()) {
					case SIGNUP:
						String username = signUpWindow.getUsernameString();
						String password = signUpWindow.getPasswordString();

						signUpController.attemptSignUp(username, password);
						break;
					case BACK:
						view(ViewState.LAUNCH);
						break;
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

	public void setLogInController(LogInController logInController) {
		this.logInController = logInController;
	}

	public void setSignUpController(SignUpController signUpController) {
		this.signUpController = signUpController;
	}

	public LaunchWindow getLaunchWindow() {
		return launchWindow;
	}

	public LogInWindow getLogInWindow() {
		return logInWindow;
	}

	public SignUpWindow getSignUpWindow() {
		return signUpWindow;
	}
}
