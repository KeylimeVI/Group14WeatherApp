package view.app_windows;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.SoloWindow;

public class MainWindow implements SoloWindow {

	private JFrame frame;

	public MainWindow() {
		initialize();
	}

	private void initialize() {
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'show'");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'hide'");
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
