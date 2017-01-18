package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Santa Fe display. */
public class SantaFeD extends Display {
	
	/** Constructor: the Santa Fe display */
	public SantaFeD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to Santa Fe."
				+ "<br /><br />Day " + commas(p.getDay())
				+ "<br />Hours Left: " + commas(p.getHour())
				+ "<br />Cash: $" + commas(p.getMoney())
				+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax())
				+ "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
