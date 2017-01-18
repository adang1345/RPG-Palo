package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Home D-Poe display. */
public class HomeDPoeD extends Display {

	/** Constructor: the Home D-Poe display */
	public HomeDPoeD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to Home D-Poe, the superstore of household items." +
				"<br /><br />Cash: $" + commas(p.getMoney()) +
				"<br />Home Level: " + p.getHupgrade() + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
