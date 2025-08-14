package use_case.log_in;

import user_handler.UserHandler;

public class LogInCase implements LogInController {
	
	@Override
	public boolean attemptLogIn(String username, String password) {

		if (UserHandler.exist(username)) {
			if (UserHandler.passwordCorrect(username, password)) {
				System.out.println("login successful");
				return true;
			}
			System.out.println("password incorrect");
		}

		return false;
	}
}
