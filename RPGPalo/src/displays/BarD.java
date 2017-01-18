package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Bar display. */
public class BarD extends Display {

	/** Constructor: the Bar display */
	public BarD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
				"<br />Cash: $" + commas(p.getMoney()) +
				"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) +
				"<br />Caffeine: " + commas(p.getCaffeine()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
