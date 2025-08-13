package data_access;

import java.awt.Color;

import java.io.File;

public class ConfigAccess {
	
	private File file;

	public ConfigAccess(File file) {
		initialize(file);
	}
	
	public ConfigAccess() {
		// TODO: load a default file
		throw new UnsupportedOperationException("No default config access method set up");
	}

	private void initialize(File file) {
		setFile(file);
	}

	/**
	 * Sets the file to read the configurations from
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Returns the BGColor from the set file from {@code setFile()}
	 * @return {@code Color} the BGColor
	 */
	public Color getBGColor() {
		// TODO: load from file
		return new Color(0xffffff);
	}

	/**
	 * Returns the HLColor from the set file from {@code setFile()}
	 * @return {@code Color} the HLColor
	 */
	public Color getHLColor() {
		// TODO: load from file
		return new Color(0x00ff00);
	}

	/**
	 * Returns the midHLColor from the set file from {@code setFile()}
	 * @return {@code Color} the midHLColor
	 */
	public Color getMidHLColor() {
		// TODO: load from file
		return new Color(0x008000);
	}
}
