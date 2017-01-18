package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the PaloTech Work menu. */
public class PaloTechWorkM extends Menu {

	/** Constructor: the PaloTech Work menu */
	public PaloTechWorkM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		int salary;
		switch (p.getJob()) {
		case 1:
			salary = 10;
			break;
		case 2:
			salary = 20;
			break;
		case 3:
			salary = 50;
			break;
		case 4:
			salary = 100;
			break;
		case 5:
			salary = 500;
			break;
		case 6:
			salary = 600;
			break;
		default:
			salary = 100 * (p.getJob() + 1);
		}

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.PaloTechM(rpg));
				rpg.setDisplay(new displays.PaloTechD(p));
			}
		});

		buttons[1] = new JButton("1 Hour");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				work1(p, salary);
				rpg.setDisplay(new displays.PaloTechD(p));
			}
		});

		buttons[2] = new JButton("X Hours");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workX(p, salary);
				rpg.setDisplay(new displays.PaloTechD(p));
			}
		});

		buttons[3] = new JButton("All Day");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workAllDay(p, salary);
				rpg.setDisplay(new displays.PaloTechD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Work for 1 hour if there is enough time. */
	private void work1(Person p, int salary) {
		if (p.getHour() < 1) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHour(p.getHour() - 1);
		p.setMoney(p.getMoney() + salary);
	}

	/** Work for user-inputted number of hours if possible. */
	private void workX(Person p, int salary) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of hours you would like to work. You must enter a<br />"
				+ "whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Work", l, 400, 130, 34);
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
		p.setMoney(p.getMoney() + inputHour*salary);
	}

	/** Work as many 1-hour shifts as possible. */
	private void workAllDay(Person p, int salary) {
		int workHour = (int) p.getHour();
		p.setHour(p.getHour() - workHour);
		p.setMoney(p.getMoney() + workHour*salary);
	}

}
