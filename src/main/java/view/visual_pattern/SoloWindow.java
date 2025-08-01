package view.visual_pattern;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class SoloWindow {

	protected JFrame frame;

	protected ActionListener actionListener;

	public void show() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.toFront();
	};

	public void hide() {
		frame.setVisible(false);
	};

	public void close() {
		frame.setVisible(false);
		frame.dispose();
	};

	public abstract void setActionListener(ActionListener actionListener);

	public ActionListener getActionListener() {
		return actionListener;
	};
}
