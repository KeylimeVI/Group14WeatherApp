package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame {

	private JLabel label;

	public Splash() throws IOException {
		BufferedImage splashImage = ImageIO.read(new File("images\\Splash.png"));
		label = new JLabel(new ImageIcon(splashImage));
		add(label);
		setSize(500, 500);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		pack();
	}

	public void close() {
		setVisible(false);
		dispose();
	}
}
