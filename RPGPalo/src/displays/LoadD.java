package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Load display. */
public class LoadD extends Display {

	/** Constructor: the Load display */
	public LoadD(Person p) {
		super(p);
		JLabel startMessage = new JLabel("Select a slot to load a game.");
		startMessage.setFont(DISPLAY_FONT);
		startMessage.setForeground(DISPLAY_COLOR);
		add(startMessage);
	}

}
