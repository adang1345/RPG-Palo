package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Weapons menu. */
public class WeaponsM extends Menu {

	/** Constructor: the Weapons menu */
	public WeaponsM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MallM(rpg));
				rpg.setDisplay(new displays.MallD(p));
			}
		});

		if (p.getGun() == 0) {
			buttons[1] = new JButton("Buy Gun");
			buttons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buyGun(p);
					rpg.setMenu(new menus.WeaponsM(rpg));
					rpg.setDisplay(new displays.WeaponsD(p));
				}
			});
			buttons[1].setToolTipText("Buy a gun for $" + commas(Person.NEW_GUN_PRICE) + ". A gun increases the chance that you will conduct a successful robbery.");
		} else {
			buttons[1] = new JButton("Upgrade Gun");
			buttons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					upgradeGun(p);
					rpg.setDisplay(new displays.WeaponsD(p));
				}
			});
			buttons[1].setToolTipText("Upgrade gun for $" + commas(Person.UPGRADE_GUN_PRICE) + ". A better gun increases the chance that you will conduct a successful robbery.");
		}

		buttons[2] = new JButton("Buy Bullets");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyBullets(p);
				rpg.setDisplay(new displays.WeaponsD(p));
			}
		});
		buttons[2].setToolTipText("Buy bullets for $" + commas(Person.BULLET_PRICE) + " each.");

		buttons[3] = new JButton("Buy Dagger");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BuyDaggersM(rpg));
				rpg.setDisplay(new displays.BuyDaggerD(p));
			}
		});
		buttons[3].setToolTipText("A dagger increases damage to your opponent during close-range combat.");

		formatButtons();
		updateButtons();
	}

	/** Buy a new gun if there is enough money. */
	private void buyGun(Person p) {
		if (p.getMoney() < Person.NEW_GUN_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setGun(1);
		p.setMoney(p.getMoney() - Person.NEW_GUN_PRICE);
	}

	/** Upgrade gun if there exist more upgrades and there is enough money. */
	private void upgradeGun(Person p) {
		if (p.getGun() == 5) {
			JLabel l = new JLabel("No more upgrades are available.");
			new MessageDialog(rpg, "Error", l, 200, 90);
			return;
		} else if (p.getMoney() < Person.UPGRADE_GUN_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setGun(p.getGun() + 1);
		p.setMoney(p.getMoney() - Person.UPGRADE_GUN_PRICE);
	}

	/** Buy a user-inputted number of bullets if possible. */
	private void buyBullets(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Bullets are $" + commas(Person.BULLET_PRICE) + " each. How many would you like to buy? You must enter a<br />"
				+ "whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Buy Bullets", l, 420, 130, 36);
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
		} else if (buyAmount*Person.BULLET_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - buyAmount*Person.BULLET_PRICE);
		p.setBullet(p.getBullet() + buyAmount);
	}

}
