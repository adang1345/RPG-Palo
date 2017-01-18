package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Buy Drugs menu. */
public class BuyDrugsM extends Menu {
	/** Constructor: the Buy Drugs menu */
	public BuyDrugsM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.DDClubM(rpg));
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});

		buttons[1] = new JButton("Heroin");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyHeroin(p);
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});
		buttons[1].setToolTipText("Buy heroin for $" + commas(Person.HEROIN_PRICE) + " per gram.");

		buttons[2] = new JButton("Crack");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyCrack(p);
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});
		buttons[2].setToolTipText("Buy crack for $" + commas(Person.CRACK_PRICE) + " per gram.");

		buttons[3] = new JButton("Beer");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyBeer(p);
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});
		buttons[3].setToolTipText("Buy beer for $" + commas(Person.BEER_BOTTLE_PRICE) + " per bottle.");

		formatButtons();
		updateButtons();
	}

	/** Buy a user-inputted amount of heroin if possible. */
	private void buyHeroin(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of grams of heroin you would like to buy. You must<br />"
				+ "enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "DD Club", l, 400, 130, 34);
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
		} else if (buyAmount*Person.HEROIN_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - Person.HEROIN_PRICE*buyAmount);
		p.setHeroin(p.getHeroin() + buyAmount);
	}

	/** Buy a user-inputted amount of crack if possible. */
	private void buyCrack(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of grams of crack you would like to buy. You must<br />"
				+ "enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "DD Club", l, 400, 130, 34);
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
		} else if (buyAmount*Person.CRACK_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - Person.CRACK_PRICE*buyAmount);
		p.setCrack(p.getCrack() + buyAmount);
	}

	/** Buy a user-inputted amount of crack if possible. */
	private void buyBeer(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of bottles of beer you would like to buy. You must<br />"
				+ "enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "DD Club", l, 400, 130, 34);
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
		} else if (buyAmount*Person.BEER_BOTTLE_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - Person.BEER_BOTTLE_PRICE*buyAmount);
		p.setBeer(p.getBeer() + buyAmount);
	}

}
