package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Main display. */
public class MainD extends Display {

	/** Constructor: the Main display */
	public MainD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Day " + commas(p.getDay()) +
				"<br />Hours Left: " + commas(p.getHour()) +
				"<br />Cash: $" + commas(p.getMoney()) +
				"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) +
				"<br />Intelligence: " + commas(p.getSmart()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
