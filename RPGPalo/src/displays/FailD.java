package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Fail display. Fail is a game accessible from the Las Vegas
 * casino. */
public class FailD extends Display {

	/** Constructor: the Fail display */
	public FailD(Person p) {
		super(p);
		JLabel l = new JLabel("Pick a case. One of them contains $1,000,000.");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
