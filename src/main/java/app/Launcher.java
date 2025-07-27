package app;

import javax.swing.SwingUtilities;

import use_case.log_in.LogInController;
import use_case.sign_up.SignUpController;
import view.ViewManager;

public class Launcher {
	
	public static void main(String[] args) {
		// ~~~ Controllers ~~~ //
		LogInController logInController = ControllerLauncher.makeLogInController();
		SignUpController signUpController = ControllerLauncher.makeSignUpController();

		// ~~~ ViewManager ~~~ //
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ViewManager viewManager = new ViewManager();
				viewManager.view(ViewManager.ViewState.LAUNCH);
			}
		});
	}
}
