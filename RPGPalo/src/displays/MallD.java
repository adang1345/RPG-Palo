package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Mall display. */
public class MallD extends Display {

	/** Constructor: the Mall display */
	public MallD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
				"<br />Cash: $" + commas(p.getMoney()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
