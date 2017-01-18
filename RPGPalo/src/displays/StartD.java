package displays;

import javax.swing.JLabel;

import gui.Display;
import gui.RPG;
import user.Person;

/** A class for the Start display. */
public class StartD extends Display {

	/** Constructor: the Start display */
	public StartD(Person p) {
		super(p);
		JLabel startMessage = new JLabel("Welcome to RPG Palo " + RPG.VERSION +
				" by Aohan Dang.");
		startMessage.setFont(DISPLAY_FONT);
		startMessage.setForeground(DISPLAY_COLOR);
		add(startMessage);
	}

}
