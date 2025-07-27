package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import use_case.log_in.LogInController;
import use_case.sign_up.SignUpController;

public class ViewManager {
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
	
	public ViewManager() {
		initialize();
	}

	private void initialize() {
		launchWindow = new LaunchWindow();
		logInWindow = new LogInWindow();
		signUpWindow = new SignUpWindow();

		launchListener = new ActionListener() {

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
		logInListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == logInWindow.getBackButton()) {
					view(ViewState.LAUNCH);
				} else if (evtSource == logInWindow.getLogInButton()) {
					String username = logInWindow.getUsernameString();
					String password = logInWindow.getPasswordString();
					
					logInController.attemptLogIn(username, password);
				}
			}
			
		};
		signUpListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == signUpWindow.getBackButton()) {
					view(ViewState.LAUNCH);
				}
			}
			
		};

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
				// TODO: obviously important to have the app actually exist.
				System.out.println("You've entered the main window");
				break;
		}
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
}
