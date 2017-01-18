package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the TV display. */
public class TvD extends Display {

	/** Constructor: the TV display */
	public TvD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
				"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) +
				"<br />Intelligence: " + commas(p.getSmart()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
