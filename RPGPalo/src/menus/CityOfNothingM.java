package menus;

import java.awt.event.*;
import javax.swing.JButton;

import gui.Menu;
import gui.RPG;

/** A class for the City of Nothing menu. */
public class CityOfNothingM extends Menu {

	/** Constructor: the City of Nothing menu */
	public CityOfNothingM(RPG rpg) {
		super(rpg);

		buttons[0] = new JButton("Quit");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.dispose();
			}
		});

		formatButtons();
		updateButtons();
	}

}
