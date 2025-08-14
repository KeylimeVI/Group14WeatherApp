package app;

import use_case.log_in.LogInCase;
import use_case.log_in.LogInController;
import use_case.sign_up.SignUpCase;
import use_case.sign_up.SignUpController;

public class ControllerLauncher {

	public static LogInController makeLogInController() {

		return new LogInCase();
	}

	public static SignUpController makeSignUpController() {
		
		return new SignUpCase();
	}
}
