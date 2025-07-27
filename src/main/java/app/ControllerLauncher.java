package app;

import use_case.log_in.LogInController;
import use_case.sign_up.SignUpController;

public class ControllerLauncher {

	public static LogInController makeLogInController() {

		return new LogInController();
	}

	public static SignUpController makeSignUpController() {
		
		return new SignUpController();
	}
}
