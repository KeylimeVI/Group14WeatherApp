package use_case.log_in;

public interface LogInController {

	/**
	 * Attempts to log in with the given username and password Strings.
	 * @param username
	 * @param password
	 * @return true if log in was successful, otherwise return false
	 */
	public boolean attemptLogIn(String username, String password);
}
