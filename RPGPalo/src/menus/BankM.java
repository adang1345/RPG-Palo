package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Bank menu. */
public class BankM extends Menu {

	/** Constructor: the Bank menu */
	public BankM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Deposit");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.DepositM(rpg));
				rpg.setDisplay(new displays.DepositD(p));
			}
		});

		buttons[2] = new JButton("Withdraw");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.WithdrawM(rpg));
				rpg.setDisplay(new displays.WithdrawD(p));
			}
		});

		if (p.getLoan() == 0) {
			buttons[3] = new JButton("Loan");
			buttons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loan(p);
					rpg.setMenu(new menus.BankM(rpg));
					rpg.setDisplay(new displays.BankD(p));
				}
			});
		} else {
			buttons[3] = new JButton("Repay");
			buttons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					repay(p);
					rpg.setMenu(new menus.BankM(rpg));
					rpg.setDisplay(new displays.BankD(p));
				}
			});
		}

		buttons[4] = new JButton("Rob");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rob(p);
			}
		});
		buttons[4].setToolTipText("Try to rob the bank.");

		formatButtons();
		updateButtons();
	}

	/** Loan a user-inputted amount from the bank if possible.
	 * Precondition: No loan is currently active. */
	private void loan(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the amount you would like to loan. You can loan no less than $" + commas(Person.MIN_LOAN) + "<br />"
				+ "and no more than $" + commas(Person.MAX_LOAN) + ". You must enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Loan", l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long loanAmount;
		try {
			loanAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (loanAmount < Person.MIN_LOAN) {
			l = new JLabel("You can loan no less than $" + commas(Person.MIN_LOAN) + ".");
			new MessageDialog(rpg, "Error", l, 195, 90);
			return;
		} else if (loanAmount > Person.MAX_LOAN) {
			l = new JLabel("You can loan no more than $" + commas(Person.MAX_LOAN) + ".");
			new MessageDialog(rpg, "Error", l, 240, 90);
			return;
		}

		// update fields and notify user when to pay back loan
		l = new JLabel("You have " + commas(Person.MAX_LOANDAY) + " days to pay back your loan.");
		new MessageDialog(rpg, "Loan", l, 240, 90);
		p.setMoney(p.getMoney() + loanAmount);
		p.setLoan(loanAmount);
	}

	/** Repay a user-inputted amount of the loan if possible.
	 * Precondition: A loan is currently active. */
	private void repay(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the amount of the loan you would like to repay. You must enter a<br />"
				+ "whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Repay", l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		// If more repay money is offered than the loaned amount, just repay the
		// loan in full.
		long repayAmount;
		try {
			repayAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (repayAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (repayAmount > p.getLoan()) {
			repayAmount = p.getLoan();
		} else if (repayAmount > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - repayAmount);
		p.setLoan(p.getLoan() - repayAmount);
		if (p.getLoan() == 0) p.setLoanday(0);
	}

	/** If no loaded gun is owned, ask for confirmation before robbing bank.
	 * Quit if not confirmed or less than 3 hours remains. In all other cases,
	 * rob bank and go to main menu and display. */
	private void rob(Person p) {
		if (p.getGun() == 0 || p.getBullet() == 0) {
			JLabel l = new JLabel("You do not have a loaded gun! Are you sure you want to rob the bank?");
			ChoiceDialog c = new ChoiceDialog(rpg, "No Loaded Gun", l, "Yes", "No", 405, 90);
			if (!c.getChoice()) return;
		} else if (p.getHour() < 3) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		int robprob; // percent chance of robbery success
		if (p.getGun() == 0 || p.getBullet() == 0) robprob = 10;
		else if (p.getGun() == 1) robprob = 50;
		else if (p.getGun() == 2) robprob = 75;
		else if (p.getGun() == 3) robprob = 83;
		else if (p.getGun() == 4) robprob = 90;
		else robprob = 97;

		if (Person.randInt(1, 100) <= robprob) { // robbery is success
			p.setRobnum(p.getRobnum() + 1);
			p.setHour(0);
			p.setBullet(Math.max(p.getBullet()-Person.randInt(1,5), 0));
			int robmoney = Person.randInt(1, 25*p.getRobnum());
			p.setMoney(p.getMoney() + robmoney);
			JLabel l = new JLabel("You did it! You escaped with $" + commas(robmoney) + ".");
			new MessageDialog(rpg, "Success", l, 220, 90);
		} else { // robbery is a failure
			JLabel l = new JLabel("You got caught! You go to jail for 5 days.");
			new MessageDialog(rpg, "Failed", l, 240, 90);
			int sleepCode = p.sleepJail(5);
			if (sleepCode == 1) {
				p.loanPenalty(rpg);
				return;
			}
		}
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(p));
	}

}
