package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import use_case.log_in.LogInController;
import use_case.sign_up.SignUpController;
import view.Splash;
import view.view_manager.LaunchViewManager;
import view.view_manager.ViewManager;

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

				LaunchViewManager launchViewManager = viewManager.getLaunchViewManager();

				launchViewManager.setLogInController(logInController);
				launchViewManager.setSignUpController(signUpController);

				try {
					Splash splash = new Splash();
					splash.setVisible(true);
					ActionListener timerListener = new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							splash.close();
							launchViewManager.view(LaunchViewManager.ViewState.LAUNCH);
						}
						
					};
					Timer timer = new Timer(3000, timerListener);
					timer.setRepeats(false);
					timer.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
