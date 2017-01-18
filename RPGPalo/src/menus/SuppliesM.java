package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Supplies menu. */
public class SuppliesM extends Menu {

	/** Constructor: the Supplies menu */
	public SuppliesM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.HomeDPoeM(rpg));
				rpg.setDisplay(new displays.HomeDPoeD(p));
			}
		});

		buttons[1] = new JButton("Bed");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bed(p);
				rpg.setDisplay(new displays.SuppliesD(p));
			}
		});
		buttons[1].setToolTipText("Buy a bed for $" + commas(Person.BED_PRICE) + ". A bed lets you gain more HP when you sleep.");

		if (p.getHupgrade() >= 2) {
			buttons[2] = new JButton("TV");
			buttons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tv(p);
					rpg.setDisplay(new displays.SuppliesD(p));
				}
			});
			buttons[2].setToolTipText("Buy a TV for $" + commas(Person.TV_PRICE) + ".");

			buttons[3] = new JButton("Computer");
			buttons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					computer(p);
					rpg.setDisplay(new displays.SuppliesD(p));
				}
			});
			buttons[3].setToolTipText("Buy a computer for $" + commas(Person.COMPUTER_PRICE) + ".");
		}

		if (p.getHupgrade() == 3) {
			buttons[4] = new JButton("Treadmill");
			buttons[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					treadmill(p);
					rpg.setDisplay(new displays.SuppliesD(p));
				}
			});
			buttons[4].setToolTipText("Buy a treadmill for $" + commas(Person.TREADMILL_PRICE) + ". A treadmill allows your maximum HP to increase by 1 when you sleep.");

			buttons[5] = new JButton("Bookcase");
			buttons[5].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bookcase(p);
					rpg.setDisplay(new displays.SuppliesD(p));
				}
			});
			buttons[5].setToolTipText("Buy a bookcase for $" + commas(Person.BOOKCASE_PRICE) + ". A bookcase allows your intelligence to increase by 1 when you sleep.");
		}

		formatButtons();
		updateButtons();
	}

	/** Buy a bed if not owned and there is enough money. */
	private void bed(Person p) {
		if (p.getBed() != 0) {
			JLabel l = new JLabel("You already have a bed.");
			new MessageDialog(rpg, "Error", l, 150, 90);
			return;
		} else if (p.getMoney() < Person.BED_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setBed(1);
		p.setMoney(p.getMoney() - Person.BED_PRICE);
	}

	/** Buy a TV if not owned and there is enough money. */
	private void tv(Person p) {
		if (p.getTv() != 0) {
			JLabel l = new JLabel("You already have a TV.");
			new MessageDialog(rpg, "Error", l, 140, 90);
			return;
		} else if (p.getMoney() < Person.TV_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setTv(1);
		p.setMoney(p.getMoney() - Person.TV_PRICE);
	}

	/** Buy a computer if not owned and there is enough money. */
	private void computer(Person p) {
		if (p.getCom()) {
			JLabel l = new JLabel("You already have a computer.");
			new MessageDialog(rpg, "Error", l, 180, 90);
			return;
		} else if (p.getMoney() < Person.COMPUTER_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setCom(true);
		p.setMoney(p.getMoney() - Person.COMPUTER_PRICE);
	}

	/** Buy a treadmill if not owned and there is enough money. */
	private void treadmill(Person p) {
		if (p.getTread()) {
			JLabel l = new JLabel("You already have a treadmill.");
			new MessageDialog(rpg, "Error", l, 180, 90);
			return;
		} else if (p.getMoney() < Person.TREADMILL_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setTread(true);
		p.setMoney(p.getMoney() - Person.TREADMILL_PRICE);
	}

	/** Buy a bookcase if not owned and there is enough money. */
	private void bookcase(Person p) {
		if (p.getBook()) {
			JLabel l = new JLabel("You already have a bookcase.");
			new MessageDialog(rpg, "Error", l, 180, 90);
			return;
		} else if (p.getMoney() < Person.BOOKCASE_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setBook(true);
		p.setMoney(p.getMoney() - Person.BOOKCASE_PRICE);
	}

}
