package tool;

import java.awt.Color;

public class ColorTool {
	/**
	 * Takes 2 colors c1 and c2 and mixes them with fac.
	 * fac = 0 would have in c1 being the result.
	 * fac = 1 would have in c2 being the result.
	 * fac being somewhere in between will mix the 2 colors.
	 * 
	 * @param c1 the first color
	 * @param c2 the second color
	 * @param fac a number in [0, 1] for mixing
	 * @return a mix of {@code c1} and {@code c2}
	 */
	public static Color mixColor(Color c1, Color c2, double fac) {
		int resultR = (int) (c1.getRed() * (1.0 - fac) + c2.getRed() * fac);
		int resultG = (int) (c1.getGreen() * (1.0 - fac) + c2.getGreen() * fac);
		int resultB = (int) (c1.getBlue() * (1.0 - fac) + c2.getBlue() * fac);

		return new Color(resultR, resultG, resultB);
	}

	/**
	 * Returns the average of the R, G, and B values
	 * 
	 * @param c
	 * @return average of R, G, and B
	 */
	public static int getValue(Color c) {
		return (c.getRed() + c.getGreen() + c.getBlue()) / 3;
	}
}
