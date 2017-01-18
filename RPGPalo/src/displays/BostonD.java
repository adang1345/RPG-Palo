package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Boston display. */
public class BostonD extends Display {

	/** Constructor: the Boston display */
	public BostonD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to Boston. If you want more intelligence, then this is"
				+ "<br />the place for you."
				+ "<br /><br />Day " + commas(p.getDay())
				+ "<br />Hours Left: " + commas(p.getHour())
				+ "<br />Cash: $" + commas(p.getMoney())
				+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax())
				+ "<br />Intelligence: " + commas(p.getSmart()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
