package displays;

import javax.swing.*;

import gui.Display;
import user.Person;

/** A class for the Prison display. */
public class PrisonD extends Display {

	/** Constructor: the Prison display */
	public PrisonD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Day " + commas(p.getDay()) +
				"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
