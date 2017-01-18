package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Fat Foods Work menu. */
public class FatFoodsWorkM extends Menu {

	/** Constructor: the Fat Foods Work menu */
	public FatFoodsWorkM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FatFoodsM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[1] = new JButton("1 Hour");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				work1(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[2] = new JButton("X Hours");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workX(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[3] = new JButton("All Day");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workAllDay(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Work for 1 hour if there is enough time. */
	private void work1(Person p) {
		if (p.getHour() < 1) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHour(p.getHour() - 1);
		p.setMoney(p.getMoney() + Person.FAT_FOODS_SALARY);
	}

	/** Work for user-inputted number of hours if possible. */
	private void workX(Person p) {
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
		p.setMoney(p.getMoney() + inputHour*Person.FAT_FOODS_SALARY);
	}

	/** Work as many 1-hour shifts as possible. */
	private void workAllDay(Person p) {
		int workHour = (int) p.getHour();
		p.setHour(p.getHour() - workHour);
		p.setMoney(p.getMoney() + workHour*Person.FAT_FOODS_SALARY);
	}

}
