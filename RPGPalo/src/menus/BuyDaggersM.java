package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Buy Daggers menu. */
public class BuyDaggersM extends Menu {

	/** Constructor: the Buy Daggers menu */
	public BuyDaggersM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.WeaponsM(rpg));
				rpg.setDisplay(new displays.WeaponsD(p));
			}
		});

		buttons[1] = new JButton("Wood");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(1, p);
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[1].setToolTipText("Buy Wood Dagger for $" + commas(Person.WOOD_DAG_PRICE) + ".");

		buttons[2] = new JButton("Bronze");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(2, p);
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[2].setToolTipText("Buy Bronze Dagger for $" + commas(Person.BRONZE_DAG_PRICE) + ".");

		buttons[3] = new JButton("Iron");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(3, p);
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[3].setToolTipText("Buy Iron Dagger for $" + commas(Person.IRON_DAG_PRICE) + ".");

		buttons[4] = new JButton("Steel");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(4, p);
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[4].setToolTipText("Buy Steel Dagger for $" + commas(Person.STEEL_DAG_PRICE) + ".");

		buttons[5] = new JButton("Silver");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(5, p);
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[5].setToolTipText("Buy Silver Dagger for $" + commas(Person.SILVER_DAG_PRICE) + ".");

		buttons[6] = new JButton("Gold");
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(6, p);
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[6].setToolTipText("Buy Gold Dagger for $" + commas(Person.GOLD_DAG_PRICE) + ".");

		buttons[7] = new JButton("Divine");
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buy(7, p);
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[7].setToolTipText("Buy Divine Dagger for $" + commas(Person.DIVINE_DAG_PRICE) + ".");

		formatButtons();
		updateButtons();
	}

	/** Try to buy the dagger level i. Give error message if the dagger the user
	 * would like to buy is inferior, the user already has the dagger wanted, or
	 * there is not enough money.
	 * Precondition: i is in 1..7. */
	private void buy(int i, Person p) {
		if (i < p.getDag()) {
			JLabel l = new JLabel("<html>You may not buy this dagger because it is inferior to the dagger you<br />"
					+ "already have.</html>");
			new MessageDialog(rpg, "Error", l, 400, 105);
			return;
		} else if (i == p.getDag()) {
			JLabel l = new JLabel("You already have this dagger.");
			new MessageDialog(rpg, "Error", l, 180, 90);
			return;
		} else if (p.getMoney() < price(i)) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setDag(i);
		p.setMoney(p.getMoney() - price(i));
		if (p.getDag() == 7) {
			JLabel l = new JLabel("<html>You now have a divine dagger. No one can kill you in close-range<br />"
					+ "combat, and your earnings from close-range combats increase by a<br />"
					+ "factor of 10.</html>");
			new MessageDialog(rpg, "Divine Dagger", l, 400, 120);
		}
	}

	/** Return the price of dagger level i.
	 * Precondition: i is in 1..7. */
	private long price(int i) {
		switch (i) {
		case 1:
			return Person.WOOD_DAG_PRICE;
		case 2:
			return Person.BRONZE_DAG_PRICE;
		case 3:
			return Person.IRON_DAG_PRICE;
		case 4:
			return Person.STEEL_DAG_PRICE;
		case 5:
			return Person.SILVER_DAG_PRICE;
		case 6:
			return Person.GOLD_DAG_PRICE;
		case 7:
			return Person.DIVINE_DAG_PRICE;
		}
		throw new IllegalArgumentException();
	}

}
