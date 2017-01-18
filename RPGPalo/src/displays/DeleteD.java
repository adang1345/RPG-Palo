package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Delete display. */
public class DeleteD extends Display {

	/** Constructor: the Delete display */
	public DeleteD(Person p) {
		super(p);
		JLabel startMessage = new JLabel("Select a slot to delete its saved game.");
		startMessage.setFont(DISPLAY_FONT);
		startMessage.setForeground(DISPLAY_COLOR);
		add(startMessage);
	}

}
