package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Sell Stocks menu. */
public class SellStocksM extends Menu {

	/** Constructor: the Sell Stocks menu */
	public SellStocksM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.ComputerM(rpg));
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[1] = new JButton("Sell AIJ");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sell("AIJ", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[2] = new JButton("Sell DCE");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sell("DCE", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[3] = new JButton("Sell NID");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sell("NID", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[4] = new JButton("Sell ABC");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sell("ABC", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Sell a user-inputted number of stocks if possible. s is the name of the
	 * stock to sell.
	 * Precondition: s is either "AIJ", "DCE", "NID", or "ABC". */
	private void sell(String s, Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of shares of " + s + " you would like to sell. You must enter<br />"
				+ "a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Sell " + s, l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long sellAmount;
		try {
			sellAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (sellAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (sellAmount > amountOwned(s, p)) {
			l = new JLabel("You do not own that many shares.");
			new MessageDialog(rpg, "Error", l, 205, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() + sellAmount*price(s, p));
		switch (s) {
		case "AIJ":
			p.setBaij(p.getBaij() - sellAmount);
			break;
		case "DCE":
			p.setBdce(p.getBdce() - sellAmount);
			break;
		case "NID":
			p.setBnid(p.getBnid() - sellAmount);
			break;
		case "ABC":
			p.setBabc(p.getBabc() - sellAmount);
		}
	}

	/** Return the price of stock s.
	 * Precondition: s is either "AIJ", "DCE", "NID", or "ABC". */
	private int price(String s, Person p) {
		switch (s) {
		case "AIJ":
			return p.getAij();
		case "DCE":
			return p.getDce();
		case "NID":
			return p.getNid();
		case "ABC":
			return p.getAbc();
		}
		throw new IllegalArgumentException();
	}

	/** Return the amount of stock s owned.
	 * Precondition: s is either "AIJ", "DCE", "NID", or "ABC". */
	private long amountOwned(String s, Person p) {
		switch (s) {
		case "AIJ":
			return p.getBaij();
		case "DCE":
			return p.getBdce();
		case "NID":
			return p.getBnid();
		case "ABC":
			return p.getBabc();
		}
		throw new IllegalArgumentException();
	}

}
