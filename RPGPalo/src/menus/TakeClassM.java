package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Take Class menu. */
public class TakeClassM extends Menu {

	/** Constructor: the Take Class menu */
	public TakeClassM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.CollegeM(rpg));
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[1] = new JButton("1 Hour");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				class1(p);
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[2] = new JButton("X Hours");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classX(p);
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[3] = new JButton("All Day");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classAllDay(p);
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Take class for 1 hour if there are enough time and money. */
	private void class1(Person p) {
		if (p.getMoney() < Person.COLLEGE_CLASS_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 1) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHour(p.getHour() - 1);
		p.setMoney(p.getMoney() - Person.COLLEGE_CLASS_PRICE);
		p.setSmart(p.getSmart() + 2);
	}

	/** Take class for user-inputted number of hours if possible. */
	private void classX(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of hours you would like to take a class. You must<br />"
				+ "enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Study", l, 400, 130, 34);
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
		} else if (inputHour*Person.COLLEGE_CLASS_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setHour(p.getHour() - inputHour);
		p.setSmart(p.getSmart() + 2*inputHour);
		p.setMoney(p.getMoney() - inputHour*Person.COLLEGE_CLASS_PRICE);
	}

	/** Take class for as many 1-hour periods as possible. */
	private void classAllDay(Person p) {
		long classHour = Math.min((int)p.getHour(), p.getMoney()/Person.COLLEGE_CLASS_PRICE);
		p.setHour(p.getHour() - classHour);
		p.setSmart(p.getSmart() + 2*classHour);
		p.setMoney(p.getMoney() - classHour*Person.COLLEGE_CLASS_PRICE);
	}

}
