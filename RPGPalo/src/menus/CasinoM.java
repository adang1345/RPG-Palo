package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Casino menu. */
public class CasinoM extends Menu {

	/** Constructor: the Casino menu */
	public CasinoM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MallM(rpg));
				rpg.setDisplay(new displays.MallD(p));
			}
		});

		buttons[1] = new JButton("Coin Flip");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coinFlip(p);
				rpg.setDisplay(new displays.CasinoD(p));
			}
		});

		buttons[2] = new JButton("Loser");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loser(p);
				rpg.setDisplay(new displays.CasinoD(p));
			}
		});

		buttons[3] = new JButton("Winner");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winner(p);
				rpg.setDisplay(new displays.CasinoD(p));
			}
		});

		buttons[4] = new JButton("Lottery");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.LotteryM(rpg));
				rpg.setDisplay(new displays.LotteryD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Play the coin flip game. */
	private void coinFlip(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>I will toss a coin. How much are you betting? You must enter a whole<br />"
				+ "number.</html>");
		InputDialog f = new InputDialog(rpg, "Coin Toss", l, 410, 130, 35);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long betAmount;
		try {
			betAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (betAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (betAmount > p.getMoney()) {
			l = new JLabel("You do not have that much money.");
			new MessageDialog(rpg, "Error", l, 210, 90);
			return;
		}

		// ask if user will bet on heads or tails, then update fields
		l = new JLabel("Do you bet on heads or tails?");
		ChoiceDialog c = new ChoiceDialog(rpg, "Coin Flip", l, "Heads", "Tails", 180, 90);
		char choice = c.getChoice() ? 'h' : 't';
		char result = Person.randInt(0, 1)==0 ? 'h' : 't';
		if (choice == 'h' && result == 'h') {
			l = new JLabel("I rolled a heads. You win!");
			new MessageDialog(rpg, "Coin Flip", l, 160, 90);
			p.setMoney(p.getMoney() + betAmount);
		} else if (choice == 'h' && result == 't') {
			l = new JLabel("I rolled a tails. You lose.");
			new MessageDialog(rpg, "Coin Flip", l, 150, 90);
			p.setMoney(p.getMoney() - betAmount);
		} else if (choice == 't' && result == 'h') {
			l = new JLabel("I rolled a heads. You lose.");
			new MessageDialog(rpg, "Coin Flip", l, 160, 90);
			p.setMoney(p.getMoney() - betAmount);
		} else {
			l = new JLabel("I rolled a tails. You win!");
			new MessageDialog(rpg, "Coin Flip", l, 150, 90);
			p.setMoney(p.getMoney() + betAmount);
		}
	}

	/** Play the Loser game if player has enough money. */
	private void loser(Person p) {
		// quit if player has less than 50 cash
		if (p.getMoney() < 50) {
			JLabel l = new JLabel("You need at least $50 to play this game. The stakes need to be higher!");
			new MessageDialog(rpg, "Loser", l, 410, 90);
			return;
		}

		// play the game
		JLabel l = new JLabel("<html>Welcome to the Loser game, where there is a 99% chance you will lose<br />"
				+ "all your cash and a 1% chance you will win $10,000.</html>");
		new MessageDialog(rpg, "Loser", l, 420, 105);
		if (Person.randInt(1, 100) == 1) {
			l = new JLabel("Against the odds, you win $10,000.");
			new MessageDialog(rpg, "Loser", l, 210, 90);
			p.setMoney(p.getMoney() + 10000);
		} else {
			l = new JLabel("You lose all your cash!");
			new MessageDialog(rpg, "Loser", l, 140, 90);
			p.setMoney(0);
		}
	}

	/** Play the Winner game. */
	private void winner(Person p) {
		JLabel l = new JLabel("You win $900. Do you want it now?");
		ChoiceDialog c = new ChoiceDialog(rpg, "Winner", l, "Yes", "No", 210, 90);
		if (c.getChoice() && p.getLoan() == 0) { // if no loan is active, add a $900 loan
			l = new JLabel("<html>You think this casino will simply give you free money? You owe the bank<br />"
					+ "a $900 loan for your insolence. You have " + Person.MAX_LOANDAY + " days to pay back this loan.</html>");
			new MessageDialog(rpg, "Winner", l, 420, 105);
			p.setLoan(900);
		} else if (c.getChoice()) { // if loan is active, add $900 to current loan
			l = new JLabel("<html>You think this casino will simply give you free money? For your<br />"
					+ "insolence, you owe the bank an additional $900 on top of your current<br />"
					+ "loan.</html>");
			new MessageDialog(rpg, "Winner", l, 410, 120);
			p.setLoan(p.getLoan() + 900);
		}
	}

}
