package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Burgers menu. */
public class BurgersM extends Menu {

	/** Constructor: the Burgers menu */
	public BurgersM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FatFoodsM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[1] = new JButton("McWhopper");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mcwhopper(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[1].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.MCWHOPPER_GAIN) +
				"<br />Price: $" + commas(FatFoodsM.MCWHOPPER_PRICE) + "</html>");

		buttons[2] = new JButton("Cheeseburger");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cheeseburger(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[2].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.CHEESEBURGER_GAIN) +
				"<br />Price: $" + commas(FatFoodsM.CHEESEBURGER_PRICE) + "</html>");

		buttons[3] = new JButton("Hamburger");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hamburger(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[3].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.HAMBURGER_GAIN) +
				"<br />Price: $" + commas(FatFoodsM.HAMBURGER_PRICE) + "</html>");

		formatButtons();
		updateButtons();
	}

	/** Eat a McWhopper if there are enough money and time. */
	private void mcwhopper(Person p) {
		if (p.getMoney() < FatFoodsM.MCWHOPPER_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.MCWHOPPER_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.MCWHOPPER_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

	/** Eat a Cheeseburger if there are enough money and time. */
	private void cheeseburger(Person p) {
		if (p.getMoney() < FatFoodsM.CHEESEBURGER_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.CHEESEBURGER_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.CHEESEBURGER_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

	/** Eat a Hamburger if there are enough money and time. */
	private void hamburger(Person p) {
		if (p.getMoney() < FatFoodsM.HAMBURGER_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.HAMBURGER_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.HAMBURGER_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

}
