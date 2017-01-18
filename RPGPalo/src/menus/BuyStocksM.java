package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Buy Stocks menu. */
public class BuyStocksM extends Menu {

	/** Constructor: a Buy Stocks menu */
	public BuyStocksM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.ComputerM(rpg));
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[1] = new JButton("Buy AIJ");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy("AIJ", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[2] = new JButton("Buy DCE");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy("DCE", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[3] = new JButton("Buy NID");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy("NID", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[4] = new JButton("Buy ABC");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy("ABC", p);
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Buy a user-inputted number of stocks if possible. s is the name of the
	 * stock to buy.
	 * Precondition: s is either "AIJ", "DCE", "NID", or "ABC". */
	private void buy(String s, Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of shares of " + s + " you would like to buy. You must enter<br />"
				+ "a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Buy " + s, l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long buyAmount;
		try {
			buyAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (buyAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (buyAmount*price(s, p) > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - buyAmount*price(s, p));
		switch (s) {
		case "AIJ":
			p.setBaij(p.getBaij() + buyAmount);
			break;
		case "DCE":
			p.setBdce(p.getBdce() + buyAmount);
			break;
		case "NID":
			p.setBnid(p.getBnid() + buyAmount);
			break;
		case "ABC":
			p.setBabc(p.getBabc() + buyAmount);
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

}
