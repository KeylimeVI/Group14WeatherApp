package use_case.sign_up;

import user_handler.UserHandler;

public class SignUpCase implements SignUpController {
	@Override
	public boolean attemptSignUp(String username, String password) {

		if (UserHandler.exist(username)) {
			System.out.println("user already exist");
			return false;
		}

		UserHandler.add(username, password);
		return true;
	}

}
