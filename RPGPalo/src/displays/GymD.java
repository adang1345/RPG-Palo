package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Gym display. */
public class GymD extends Display {

	/** Constructor: the Gym display */
	public GymD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
				"<br />Cash: $" + commas(p.getMoney()) +
				"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
