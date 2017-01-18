package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the College display. */
public class CollegeD extends Display {

	/** Constructor: the College display */
	public CollegeD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
				"<br />Cash: $" + commas(p.getMoney()) +
				"<br />Intelligence: " + commas(p.getSmart()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
