package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Deposit menu. */
public class DepositM extends Menu {

	/** Constructor: the Deposit menu */
	public DepositM(RPG rpg) {
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
				depositAmount(p);
				rpg.setDisplay(new displays.DepositD(p));
			}
		});

		buttons[2] = new JButton("All");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositAll(p);
				rpg.setDisplay(new displays.DepositD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Deposit user-inputted amount into the bank if possible. */
	public void depositAmount(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the amount of cash you would like to deposit. You must enter a<br />"
				+ "whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Deposit", l, 420, 130, 35);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long depositAmount;
		try {
			depositAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (depositAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (depositAmount > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - depositAmount);
		p.setBank(p.getBank() + depositAmount);
	}

	/** Deposit all cash into bank. */
	public void depositAll(Person p) {
		p.setBank(p.getBank() + p.getMoney());
		p.setMoney(0);
	}

}
