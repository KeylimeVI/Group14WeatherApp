package view;

import java.awt.event.ActionListener;

public interface SoloWindow {

	public void show();

	public void hide();

	public void close();

	public void setActionListener(ActionListener actionListener);

	public ActionListener getActionListener();
}
