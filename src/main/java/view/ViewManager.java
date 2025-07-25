package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewManager {
	private LaunchWindow launchWindow;
	private LogInWindow logInWindow;
	private SignUpWindow signUpWindow;

	private ActionListener launchListener;
	private ActionListener logInListener;
	private ActionListener signUpListener;
	
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
					System.out.println("Exiting");
					closeAll();
				} else if (evtSource == launchWindow.getLogInButton()) {
					System.out.println("Going to Log In");
					view(ViewState.LOGIN);
				} else if (evtSource == launchWindow.getSignUpButton()) {
					System.out.println("Going to Sign Up");
					view(ViewState.SIGNUP);
				}
			}
			
		};
		logInListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == logInWindow.getBackButton()) {
					System.out.println("Going back to launch window");
					view(ViewState.LAUNCH);
				} else if (evtSource == logInWindow.getLogInButton()) {
					// TODO: access use_case of the software here for actual login
					// this is just a place holder to print some stuff as an example
					System.out.println("You are:\t\t" + logInWindow.getUsernameString());
					System.out.println("Your password is:\t" + "*".repeat(logInWindow.getPasswordString().length()));
				}
			}
			
		};
		signUpListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object evtSource = e.getSource();

				if (evtSource == signUpWindow.getBackButton()) {
					System.out.println("Going back to launch window");
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
}
