package view.app_windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.SoloWindow;

public class MainWindow implements SoloWindow {

	private JFrame frame;

	public MainWindow() {
		initialize();
	}

	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameY = (int) screenSize.getHeight() / 2;
		int frameX = (int) ((double) frameY / 1.5);

		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Weather Or Not");
		frame.setSize(frameX, frameY);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(12, 12));
	}

	@Override
	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void hide() {
		frame.setVisible(false);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'close'");
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setActionListener'");
	}

	@Override
	public ActionListener getActionListener() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getActionListener'");
	}
	
}
