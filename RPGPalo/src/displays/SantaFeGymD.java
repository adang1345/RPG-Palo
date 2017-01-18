package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Santa Fe Gym display. */
public class SantaFeGymD extends Display {

	/** Constructor: the Santa Fe Gym display */
	public SantaFeGymD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to the best gym in the world."
				+ "<br /><br />Hours Left: " + commas(p.getHour())
				+ "<br />Cash: $" + commas(p.getMoney())
				+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax())
				+ "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
