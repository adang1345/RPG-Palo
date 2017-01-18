package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Las Vegas Casino display. */
public class VegasCasinoD extends Display {

	/** Constructor: the Las Vegas Casino display */
	public VegasCasinoD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to the best casino in the U.S. You can lose all your"
				+ "<br />money and more in less time than before!"
				+ "<br /><br />Day " + commas(p.getDay())
				+ "<br />Hours Left: " + commas(p.getHour())
				+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax())
				+ "<br />Cash: $" + commas(p.getMoney()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
