package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.BarOpponent;
import user.Person;

/** A class for the Fight menu. */
public class FightM extends Menu {

	/** Constructor: the Fight menu */
	public FightM(RPG rpg, BarOpponent o) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Flee");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BarM(rpg));
				rpg.setDisplay(new displays.BarD(p));
			}
		});

		buttons[1] = new JButton("Attack");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack(p, o);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Proceed with the battle. */
	private void attack(Person p, BarOpponent o) {
		p.barAttack(o);
		if (o.getHp() == 0) { // win the battle
			rpg.setDisplay(new displays.FightD(p, o));
			long winMoney = o.winMoney(p);
			JLabel l = new JLabel("<html>You win! You find $" + commas(winMoney) + "<br />"
					+ "in the unconscious guy's wallet.</html>");
			new MessageDialog(rpg, "Fight", l, 200, 105);
			p.setMoney(p.getMoney() + winMoney);
			rpg.setMenu(new menus.BarM(rpg));
			rpg.setDisplay(new displays.BarD(p));
			return;
		}
		o.attack(p);
		rpg.setDisplay(new displays.FightD(p, o));
		if (p.getHp() == 0) {
			p.die(rpg);
		}
	}

}
