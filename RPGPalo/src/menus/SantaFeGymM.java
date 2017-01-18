package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.GymOpponent;
import user.Person;

/** A class for the Santa Fe Gym menu. */
public class SantaFeGymM extends Menu {

	/** Constructor: the Santa Fe Gym menu */
	public SantaFeGymM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SantaFeM(rpg));
				rpg.setDisplay(new displays.SantaFeD(p));
			}
		});

		buttons[1] = new JButton("Work Out");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SantaFeWorkOutM(rpg));
			}
		});
		buttons[1].setToolTipText("Work out for $" + commas(SantaFeWorkOutM.WORK_OUT_PRICE) +
				" per hour.");

		buttons[2] = new JButton("Fight");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fight(p);
			}
		});
		buttons[2].setToolTipText("Start a fight.");

		formatButtons();
		updateButtons();
	}

	/** Fight if there is enough time. */
	private void fight(Person p) {
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHour(p.getHour() - 0.5);
		p.setFightnum(p.getFightnum() + 1);
		GymOpponent o = new GymOpponent(p);

		int attackPoints;
		if (p.getHpmax() <= 1000) attackPoints = 1;
		else if (p.getHpmax() <= 2000) attackPoints = 2;
		else if (p.getHpmax() <= 5000) attackPoints = 3;
		else if (p.getHpmax() <= 10000) attackPoints = 4;
		else if (p.getHpmax() <= 500000) attackPoints = 5;
		else if (p.getHpmax() <= 1000000) attackPoints = 6;
		else attackPoints = 15;

		rpg.setMenu(new menus.SantaFeFightM(rpg, o, attackPoints));
		rpg.setDisplay(new displays.SantaFeFightD(p, o, attackPoints));
	}

}
