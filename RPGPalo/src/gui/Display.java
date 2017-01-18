package gui;

import java.awt.*;
import javax.swing.*;

import user.Person;

/** A class describing the display screen. */
public abstract class Display extends JPanel {
	public static final int DISPLAY_HEIGHT = RPG.WINDOW_HEIGHT - Menu.MENU_HEIGHT;
	public static final Font DISPLAY_FONT = new Font("Arial", Font.BOLD, 30);
	public static final Color DISPLAY_COLOR = Color.BLACK;

	/** Constructor: A display with left-justified FlowLayout. */
	public Display(Person p) {
		super(new FlowLayout(FlowLayout.LEFT));
		setBackground(RPG.BACKGROUND_COLOR);
		setPreferredSize(new Dimension(RPG.WINDOW_WIDTH, DISPLAY_HEIGHT));
	}

	/** Insert commas every 3 digits in an int or long. */
	public static String commas(long i) {
		String s = String.valueOf(i);
		String t = "";
		for (int x = 1; x <= s.length(); x++) {
			if (x%3==1 && x!=1 && s.charAt(s.length()-x)!='-') t = "," + t;
			t = s.charAt(s.length()-x) + t;
		}
		return t;
	}

	/** Insert commas every 3 digits in a double.
	 * Precondition: d contains exactly 1 decimal digit, and 0 < d < 9999999.9. */
	public static String commas(double d) {
		String s = String.valueOf(d);
		String t = "";
		for (int x = 1; x <= s.length(); x++) {
			if (x%3==0 && x!=3) t = "," + t;
			t = s.charAt(s.length()-x) + t;
		}
		return t;
	}

}
