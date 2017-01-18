package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the DD Club display. */
public class DDClubD extends Display {

	/** Constructor: the DD Club display */
	public DDClubD(Person p) {
		super(p);
		JLabel l;
		if (p.getClub()) {
			l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
					"<br />Cash: $" + commas(p.getMoney()) +
					"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) +
					"<br /><br />Heroin: " + commas(p.getHeroin()) +
					"<br />Crack: " + commas(p.getCrack()) +
					"<br />Beer: " + commas(p.getBeer()) + "</html>");
		} else {
			l = new JLabel("Why are you in our club?");
		}
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
