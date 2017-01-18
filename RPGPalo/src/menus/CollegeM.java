package menus;

import java.awt.event.*;
import javax.swing.JButton;

import gui.Menu;
import gui.RPG;
import user.Person;

/** A class for the College menu. */
public class CollegeM extends Menu {

	/** Constructor: the College menu */
	public CollegeM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Study");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.StudyM(rpg));
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[2] = new JButton("Take Class");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.TakeClassM(rpg));
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});
		buttons[2].setToolTipText("Take a class for $" + commas(Person.COLLEGE_CLASS_PRICE) + " per hour.");

		formatButtons();
		updateButtons();
	}

}
