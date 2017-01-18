package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Save display. */
public class SaveD extends Display {

	/** Constructor: the Save display */
	public SaveD(Person p) {
		super(p);
		JLabel startMessage = new JLabel("Select a save slot.");
		startMessage.setFont(DISPLAY_FONT);
		startMessage.setForeground(DISPLAY_COLOR);
		add(startMessage);
	}

}
