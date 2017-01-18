package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Withdraw menu. */
public class WithdrawM extends Menu {

	/** Constructor: the Withdraw menu */
	public WithdrawM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BankM(rpg));
				rpg.setDisplay(new displays.BankD(p));
			}
		});

		buttons[1] = new JButton("Amount");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawAmount(p);
				rpg.setDisplay(new displays.WithdrawD(p));
			}
		});

		buttons[2] = new JButton("All");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawAll(p);
				rpg.setDisplay(new displays.WithdrawD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Withdraw user-inputted amount from bank if possible. */
	private void withdrawAmount(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the amount of cash you would like to withdraw. You must enter a<br />"
				+ "whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Withdraw", l, 420, 130, 35);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long withdrawAmount;
		try {
			withdrawAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (withdrawAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (withdrawAmount > p.getBank()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() + withdrawAmount);
		p.setBank(p.getBank() - withdrawAmount);
	}

	/** Withdraw all money from bank. */
	private void withdrawAll(Person p) {
		p.setMoney(p.getMoney() + p.getBank());
		p.setBank(0);
	}

}
