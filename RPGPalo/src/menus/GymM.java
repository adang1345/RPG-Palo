package menus;

import java.awt.event.*;
import javax.swing.JButton;

import gui.Menu;
import gui.RPG;
import user.Person;

/** A class for the Gym menu. */
public class GymM extends Menu {

	/** Constructor: the Gym menu */
	public GymM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Work Out");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.WorkOutM(rpg));
				rpg.setDisplay(new displays.GymD(p));
			}
		});

		buttons[2] = new JButton("Gym Class");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.GymClassM(rpg));
				rpg.setDisplay(new displays.GymD(p));
			}
		});
		buttons[2].setToolTipText("Take a gym class for $" + commas(Person.GYM_CLASS_PRICE) + " per hour.");

		formatButtons();
		updateButtons();
	}

}
