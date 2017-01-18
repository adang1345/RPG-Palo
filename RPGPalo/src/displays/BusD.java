package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Bus display. */
public class BusD extends Display {

	/** Constructor: the Bus display */
	public BusD(Person p) {
		super(p);
		JLabel l;
		if (p.getMoney() + p.getBank() < 1000000) {
			l = new JLabel("This bus station is for millionaires only, so get out!");
		} else {
			l = new JLabel("<html>Welcome to the bus station, which can transport you away from" +
					"<br />this boring town." +
					"<br /><br />Hours Left: " + commas(p.getHour()) +
					"<br />Cash: $" + commas(p.getMoney()) + "</html>");
		}
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
