package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Sides menu. */
public class SidesM extends Menu {

	/** Constructor: the Sides menu */
	public SidesM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FatFoodsM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[1] = new JButton("Fries");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fries(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[1].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.FRIES_GAIN) +
				"<br />Price: $" + commas(FatFoodsM.FRIES_PRICE) + "</html>");

		buttons[2] = new JButton("Onion Rings");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onionRings(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[2].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.ONIONRINGS_GAIN) +
				"<br />Price: $" + commas(FatFoodsM.ONIONRINGS_PRICE) + "</html>");

		formatButtons();
		updateButtons();
	}

	/** Eat Fries if there are enough money and time. */
	private void fries(Person p) {
		if (p.getMoney() < FatFoodsM.FRIES_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.FRIES_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.FRIES_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

	/** Eat Onion Rings if there are enough money and time. */
	private void onionRings(Person p) {
		if (p.getMoney() < FatFoodsM.ONIONRINGS_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.ONIONRINGS_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.ONIONRINGS_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

}
