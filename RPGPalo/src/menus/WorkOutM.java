package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Work Out menu. */
public class WorkOutM extends Menu {

	/** Constructor: the Work Out menu */
	public WorkOutM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.GymM(rpg));
				rpg.setDisplay(new displays.GymD(p));
			}
		});

		buttons[1] = new JButton("1 Hour");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workOut1(p);
				rpg.setDisplay(new displays.GymD(p));
			}
		});

		buttons[2] = new JButton("X Hours");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workOutX(p);
				rpg.setDisplay(new displays.GymD(p));
			}
		});

		buttons[3] = new JButton("All Day");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workOutAllDay(p);
				rpg.setDisplay(new displays.GymD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Work out for 1 hour if there is enough time. */
	private void workOut1(Person p) {
		if (p.getHour() < 1) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHour(p.getHour() - 1);
		p.setHpmax(p.getHpmax() + 1);
	}

	/** Work out for user-inputted number of hours if possible. */
	private void workOutX(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of hours you would like to work out. You must enter a<br />"
				+ "whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Work Out", l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long inputHour;
		try {
			inputHour = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (inputHour < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (inputHour > p.getHour()) {
			l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setHour(p.getHour() - inputHour);
		p.setHpmax(p.getHpmax() + inputHour);
	}

	/** Work out as many 1-hour periods as possible. */
	private void workOutAllDay(Person p) {
		int workOutHour = (int) p.getHour();
		p.setHour(p.getHour() - workOutHour);
		p.setHpmax(p.getHpmax() + workOutHour);
	}

}
