package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Desserts menu. */
public class DessertsM extends Menu {

	/** Constructor: the Desserts menu */
	public DessertsM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FatFoodsM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[1] = new JButton("Cake");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cake(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[1].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.CAKE_GAIN) +
				"<br />Price: $" + commas(FatFoodsM.CAKE_PRICE) + "</html>");

		buttons[2] = new JButton("Cookie");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cookie(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[2].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.COOKIE_GAIN) +
				"<br />Price: $" + commas(FatFoodsM.COOKIE_PRICE) + "</html>");

		formatButtons();
		updateButtons();
	}

	/** Eat a Cake if there are enough money and time. */
	private void cake(Person p) {
		if (p.getMoney() < FatFoodsM.CAKE_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.CAKE_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.CAKE_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

	/** Eat a Cookie if there are enough money and time. */
	private void cookie(Person p) {
		if (p.getMoney() < FatFoodsM.COOKIE_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.COOKIE_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.COOKIE_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

}
