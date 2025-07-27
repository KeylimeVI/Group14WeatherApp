package use_case.sign_up;

public class SignUpCase implements SignUpController {

	@Override
	public boolean attemptSignUp(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("So unfortunately, we do not have a proper sign up system right now, " + username + ", who wants to set their password to " + password + ".");
		throw new UnsupportedOperationException("Unimplemented method 'attemptSignUp'");
	}
	
}
