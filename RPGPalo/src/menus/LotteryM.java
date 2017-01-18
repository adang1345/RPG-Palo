package menus;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Lottery menu. */
public class LotteryM extends Menu {

	/** Constructor: the Lottery menu */
	public LotteryM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.CasinoM(rpg));
				rpg.setDisplay(new displays.CasinoD(p));
			}
		});

		buttons[1] = new JButton("Power 2");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				power2(p);
				rpg.setDisplay(new displays.LotteryD(p));
			}
		});
		buttons[1].setToolTipText("Win $" + commas(Person.POWER2_WIN) + " for selecting a correct 2-digit number.");

		buttons[2] = new JButton("Power 3");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				power3(p);
				rpg.setDisplay(new displays.LotteryD(p));
			}
		});
		buttons[2].setToolTipText("Win $" + commas(Person.POWER3_WIN) + " for selecting a correct 3-digit number.");

		buttons[3] = new JButton("Cash 5");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash5(p);
				rpg.setDisplay(new displays.LotteryD(p));
			}
		});
		buttons[3].setToolTipText("<html>Buy a ticket containing 5 two-digit numbers. Your cash prizes depend on how many matches<br />"
				+ "there are between your ticket and the winning set of numbers. Highest prize is $1 quadrillion.</html>");

		formatButtons();
		updateButtons();
	}

	/** Play the Power 2 game if there is enough money. */
	private void power2(Person p) {
		if (p.getMoney() < 10) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// obtain the user-inputted value
		JLabel l = new JLabel("Choose a 2-digit number between 00 and 99, inclusively.");
		InputDialog f = new InputDialog(rpg, "Power 2", l, 330, 115, 28);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		int number;
		try {
			number = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (number < 0 || number > 99) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - Person.LOTTERY_PRICE);
		int result = Person.randInt(0, 99);
		if (number == result) {
			l = new JLabel("You win! The winning number is " + result + ".");
			new MessageDialog(rpg, "Power 2", l, 215, 90);
			p.setMoney(p.getMoney() + Person.POWER2_WIN);
		} else {
			l = new JLabel("You lose. The winning number is " + result + ".");
			new MessageDialog(rpg, "Power 2", l, 215, 90);
		}
	}

	/** Play the Power 3 game if there is enough money. */
	private void power3(Person p) {
		if (p.getMoney() < 10) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// obtain the user-inputted value
		JLabel l = new JLabel("Choose a 3-digit number between 000 and 999, inclusively.");
		InputDialog f = new InputDialog(rpg, "Power 3", l, 350, 115, 30);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		int number;
		try {
			number = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (number < 0 || number > 999) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - Person.LOTTERY_PRICE);
		int result = Person.randInt(0, 999);
		if (number == result) {
			l = new JLabel("You win! The winning number is " + result + ".");
			new MessageDialog(rpg, "Power 3", l, 220, 90);
			p.setMoney(p.getMoney() + Person.POWER3_WIN);
		} else {
			l = new JLabel("You lose. The winning number is " + result + ".");
			new MessageDialog(rpg, "Power 3", l, 220, 90);
		}
	}

	/** Play the Cash 5 game if there is enough money. */
	private void cash5(Person p) {
		if (p.getMoney() < 10) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// generate player numbers, winning numbers, and their intersection
		ArrayList<Integer> playerNumbers = new ArrayList<Integer>(5);
		ArrayList<Integer> winningNumbers = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) {
			playerNumbers.add(Person.randInt(0, 99));
			winningNumbers.add(Person.randInt(0, 99));
		}
		ArrayList<Integer> intersection = new ArrayList<Integer>(playerNumbers);
		intersection.retainAll(winningNumbers);

		// give message about outcome of lottery and set fields
		p.setMoney(p.getMoney() - Person.LOTTERY_PRICE);
		JLabel l;
		switch (intersection.size()) {
		case 0:
			l = new JLabel("<html>Your numbers are " + stringArrayList(playerNumbers) +
					".<br />Winning numbers are " + stringArrayList(winningNumbers) +
					".<br />You have no matching numbers. You lose.</html>");
			new MessageDialog(rpg, "Cash 5", l, 250, 120);
			break;
		case 1: case 2: case 3: case 4:
			l = new JLabel("<html>Your numbers are " + stringArrayList(playerNumbers) +
					".<br />Winning numbers are " + stringArrayList(winningNumbers) +
					".<br />You have at least 1 matching number. You win $" + Person.CASH5_WIN1 + ".</html>");
			new MessageDialog(rpg, "Cash 5", l, 310, 120);
			p.setMoney(p.getMoney() + Person.CASH5_WIN1);
			break;
		case 5:
			l = new JLabel("<html>Your numbers are " + stringArrayList(playerNumbers) +
					".<br />Winning numbers are " + stringArrayList(winningNumbers) +
					".<br />You have 5 matching numbers. You win $" + Person.CASH5_WIN5 + ".</html>");
			new MessageDialog(rpg, "Cash 5", l, 360, 120);
			p.setMoney(p.getMoney() + Person.CASH5_WIN5);
		}
	}

	/** Print an ArrayList of Integers surrounded by brackets and delimited by commas.
	 * Precondition: a has at least 1 element. */
	private static String stringArrayList(ArrayList<Integer> a) {
		String s = "[";
		for (int i = 0; i < a.size()-1; i++) {
			s += a.get(i) + ", ";
		}
		s += a.get(a.size()-1) + "]";
		return s;
	}

}
