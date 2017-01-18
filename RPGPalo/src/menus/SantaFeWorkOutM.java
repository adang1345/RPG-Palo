package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Santa Fe Work Out menu. */
public class SantaFeWorkOutM extends Menu {
	public static final int WORK_OUT_PRICE = 1000; // hourly price of working out

	/** Constructor: the Santa Fe Work Out menu */
	public SantaFeWorkOutM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SantaFeGymM(rpg));
				rpg.setDisplay(new displays.SantaFeGymD(p));
			}
		});

		buttons[1] = new JButton("1 Hour");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workOut1(p);
				rpg.setDisplay(new displays.SantaFeGymD(p));
			}
		});

		buttons[2] = new JButton("X Hours");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workOutX(p);
				rpg.setDisplay(new displays.SantaFeGymD(p));
			}
		});

		buttons[3] = new JButton("All Day");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workOutAllDay(p);
				rpg.setDisplay(new displays.SantaFeGymD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Work out for 1 hour if there are enough time and money. */
	private void workOut1(Person p) {
		if (p.getMoney() < WORK_OUT_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 1) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (Person.randInt(1, 15) == 1) {
			JLabel l = new JLabel("You work out extra this time and get a bonus.");
			new MessageDialog(rpg, "Work Out", l, 280, 90);
			p.setHpmax(p.getHpmax() + 30);
		}
		p.setHour(p.getHour() - 1);
		p.setMoney(p.getMoney() - WORK_OUT_PRICE);
		p.setHpmax(p.getHpmax() + 20);
	}

	/** Work out for user-inputted number of hours if possible. */
	private void workOutX(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of hours you would like to work out. You must enter<br />"
				+ "a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Work Out", l, 410, 130, 35);
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
		} else if (inputHour*WORK_OUT_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setHour(p.getHour() - inputHour);
		p.setHpmax(p.getHpmax() + 20*inputHour);
		p.setMoney(p.getMoney() - inputHour*WORK_OUT_PRICE);
	}

	/** Work out for as many 1-hour periods as possible. */
	private void workOutAllDay(Person p) {
		long workOutHour = Math.min((int)p.getHour(), p.getMoney()/WORK_OUT_PRICE);
		p.setHour(p.getHour() - workOutHour);
		p.setHpmax(p.getHpmax() + 20*workOutHour);
		p.setMoney(p.getMoney() - workOutHour*WORK_OUT_PRICE);
	}

}
