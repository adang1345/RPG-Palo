package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Chicago display. */
public class ChicagoD extends Display {

	/** Constructor: the Chicago display */
	public ChicagoD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to Chicago, famous for its stores."
				+ "<br /><br />Day " + commas(p.getDay())
				+ "<br />Hours Left: " + commas(p.getHour())
				+ "<br />Cash: $" + commas(p.getMoney())
				+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
