package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Rob display. */
public class RobD extends Display {

	/** Constructor: the Rob display */
	public RobD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Which part of the mall do you want to rob? Some sections<br />"
				+ "are more secure than others.</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
