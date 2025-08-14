package use_case.log_in;

import user_handler.UserHandler;

public class LogInCase implements LogInController {
	
	@Override
	public boolean attemptLogIn(String username, String password) {
		// TODO: access use_case of the software here for actual login
		// this is just a place holder to print some stuff as an example
		System.out.println("You are:\t\t" + username);
		System.out.println("Your password is:\t" + "*".repeat(password.length()));

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
