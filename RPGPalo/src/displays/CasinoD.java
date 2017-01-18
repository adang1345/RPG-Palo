package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Casino display. */
public class CasinoD extends Display {

	/** Constructor: the Casino display */
	public CasinoD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to the casino. How may we empty your pockets today?"
				+ "<br /><br />Cash: $" + commas(p.getMoney()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
