package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Drinks menu. */
public class DrinksM extends Menu {

	/** Constructor: the Drinks menu */
	public DrinksM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FatFoodsM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		ActionListener a = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drink(p);
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		};

		buttons[1] = new JButton("Koke");
		buttons[1].addActionListener(a);

		buttons[2] = new JButton("Pep-C");
		buttons[2].addActionListener(a);

		buttons[3] = new JButton("Spryte");
		buttons[3].addActionListener(a);

		buttons[4] = new JButton("Mt&Dew");
		buttons[4].addActionListener(a);

		for (int i = 1; i <= 4; i++) {
			buttons[i].setToolTipText("<html>HP Gain: " + commas(FatFoodsM.DRINK_GAIN) +
					"<br />Price: $" + commas(FatFoodsM.DRINK_PRICE) + "</html>");
		}

		formatButtons();
		updateButtons();
	}

	/** Have a drink if there are enough money and time. */
	private void drink(Person p) {
		if (p.getMoney() < FatFoodsM.DRINK_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+FatFoodsM.DRINK_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - FatFoodsM.DRINK_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

}
