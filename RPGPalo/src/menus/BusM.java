package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Bus menu. */
public class BusM extends Menu {

	/** Constructor: the Bus menu */
	public BusM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		if (p.getMoney() + p.getBank() >= 1000000) {
			buttons[1] = new JButton("Chicago");
			buttons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chicago(p);
				}
			});
			buttons[1].setToolTipText("<html>Take a 5-hour bus ride to Chicago.<br />Cost: $"
					+ commas(Person.CHICAGO_PRICE) + "</html>");

			buttons[2] = new JButton("Las Vegas");
			buttons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vegas(p);
				}
			});
			buttons[2].setToolTipText("<html>Take a 16-hour bus ride to Las Vegas.<br />Cost: $"
					+ commas(Person.VEGAS_PRICE) + "</html>");

			buttons[3] = new JButton("Boston");
			buttons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boston(p);
				}
			});
			buttons[3].setToolTipText("<html>Take a 2-hour bus ride to Boston.<br />Cost: $"
					+ commas(Person.BOSTON_PRICE) + "</html>");

			buttons[4] = new JButton("Santa Fe");
			buttons[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					santaFe(p);
				}
			});
			buttons[4].setToolTipText("<html>Take a 14-hour bus ride to Santa Fe.<br />Cost: $"
					+ commas(Person.SANTA_FE_PRICE) + "</html>");
		}

		formatButtons();
		updateButtons();
	}

	/** Go to Chicago if there are enough money and time. */
	private void chicago(Person p) {
		if (p.getMoney() < Person.CHICAGO_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 5) {
			JLabel l = new JLabel("The Chicago bus has left. Come back tomorrow at an earlier time.");
			new MessageDialog(rpg, "Error", l, 390, 90);
			return;
		}
		p.setMoney(p.getMoney() - Person.CHICAGO_PRICE);
		p.setHour(p.getHour() - 5);
		rpg.setMenu(new menus.ChicagoM(rpg));
		rpg.setDisplay(new displays.ChicagoD(p));
	}

	/** Go to Las Vegas if there are enough money and time. */
	private void vegas(Person p) {
		if (p.getMoney() < Person.VEGAS_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 16) {
			JLabel l = new JLabel("The Las Vegas bus has left. Come back tomorrow at an earlier time.");
			new MessageDialog(rpg, "Error", l, 400, 90);
			return;
		}
		p.setMoney(p.getMoney() - Person.VEGAS_PRICE);
		p.setHour(p.getHour() - 16);
		rpg.setMenu(new menus.VegasM(rpg));
		rpg.setDisplay(new displays.VegasD(p));
	}

	/** Go to Boston if there are enough money and time. */
	private void boston(Person p) {
		if (p.getMoney() < Person.BOSTON_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 2) {
			JLabel l = new JLabel("The Boston bus has left. Come back tomorrow at an earlier time.");
			new MessageDialog(rpg, "Error", l, 390, 90);
			return;
		}
		p.setMoney(p.getMoney() - Person.BOSTON_PRICE);
		p.setHour(p.getHour() - 2);
		rpg.setMenu(new menus.BostonM(rpg));
		rpg.setDisplay(new displays.BostonD(p));
	}

	/** Go to Santa Fe if there are enough money and time. */
	private void santaFe(Person p) {
		if (p.getMoney() < Person.SANTA_FE_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 14) {
			JLabel l = new JLabel("The Santa Fe bus has left. Come back tomorrow at an earlier time.");
			new MessageDialog(rpg, "Error", l, 390, 90);
			return;
		}
		p.setMoney(p.getMoney() - Person.SANTA_FE_PRICE);
		p.setHour(p.getHour() - 14);
		rpg.setMenu(new menus.SantaFeM(rpg));
		rpg.setDisplay(new displays.SantaFeD(p));
	}

}
