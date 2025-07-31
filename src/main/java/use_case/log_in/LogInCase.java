package use_case.log_in;

public class LogInCase implements LogInController {
	
	/**
	 * Attempts to log in with the given username and password Strings.
	 * @param username
	 * @param password
	 * @return true if log in was successful, otherwise return false
	 */
	@Override
	public boolean attemptLogIn(String username, String password) {
		// TODO: access use_case of the software here for actual login
		// this is just a place holder to print some stuff as an example
		System.out.println("You are:\t\t" + username);
		System.out.println("Your password is:\t" + "*".repeat(password.length()));

		System.out.println("LogInCase is not implemented properly yet.");

		return true;
	}
}
