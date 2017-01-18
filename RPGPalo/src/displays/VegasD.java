package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Vegas display. */
public class VegasD extends Display {

	/** Constructor: the Vegas display */
	public VegasD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to Las Vegas, famous for its casinos."
				+ "<br /><br />Day " + commas(p.getDay())
				+ "<br />Hours Left: " + commas(p.getHour())
				+ "<br />Cash: $" + commas(p.getMoney())
				+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}
}
