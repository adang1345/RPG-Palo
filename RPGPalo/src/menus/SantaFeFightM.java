package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.GymOpponent;
import user.Person;

/** A class for the Santa Fe Fight menu. */
public class SantaFeFightM extends Menu {
	private int attackPoints; // the Attack Points the person has
	private int originalAttackPoints; // the Attack Points the person had originally

	/** Constructor: the Santa Fe Fight menu */
	public SantaFeFightM(RPG rpg, GymOpponent o, int attackPoints) {
		super(rpg);
		Person p = rpg.getPerson();
		this.attackPoints = attackPoints;
		this.originalAttackPoints = attackPoints;

		buttons[0] = new JButton("Flee");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SantaFeGymM(rpg));
				rpg.setDisplay(new displays.SantaFeGymD(p));
			}
		});

		buttons[1] = new JButton("Stand");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stand(p, o);
			}
		});
		buttons[1].setToolTipText("End your turn and let the opponent attack.");

		buttons[2] = new JButton("Punch");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack(p, o, 1);
			}
		});
		buttons[2].setToolTipText("<html>Punch the opponent.<br />Cost: 1 Attack Point</html>");

		buttons[3] = new JButton("Kick");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack(p, o, 2);
			}
		});
		buttons[3].setToolTipText("<html>Kick the opponent.<br />Cost: 2 Attack Points</html>");

		buttons[4] = new JButton("Flame Ball");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack(p, o, 3);
			}
		});
		buttons[4].setToolTipText("<html>Shoot a flame ball at the opponent.<br />Cost: 3 Attack Points</html>");

		buttons[5] = new JButton("Energy Blast");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack(p, o, 4);
			}
		});
		buttons[5].setToolTipText("<html>Shoot an energy blast at the opponent.<br />Cost: 4 Attack Points</html>");

		formatButtons();
		updateButtons();
	}

	/** Let the opponent attack. */
	private void stand(Person p, GymOpponent o) {
		o.attack(p);
		attackPoints = originalAttackPoints;
		rpg.setDisplay(new displays.SantaFeFightD(p, o, attackPoints));
		if (p.getHp() == 0) p.die(rpg);
	}

	/** Attack the opponent with a move that uses i attack points.
	 * Precondition: i is in 1..4. */
	private void attack(Person p, GymOpponent o, int i) {
		if (attackPoints < i) {
			JLabel l = new JLabel("Not enough attack points.");
			new MessageDialog(rpg, "Fight", l, 170, 90);
			return;
		}
		p.gymAttack(o, i);
		attackPoints -= i;
		rpg.setDisplay(new displays.SantaFeFightD(p, o, attackPoints));
		if (o.getHp() == 0) {
			long winMoney = o.winMoney(p);
			JLabel l = new JLabel("<html>You win! You find $" + commas(winMoney) + "<br />"
					+ "in the unconscious guy's wallet.</html>");
			new MessageDialog(rpg, "Fight", l, 200, 105);
			p.setMoney(p.getMoney() + winMoney);
			rpg.setMenu(new menus.SantaFeGymM(rpg));
			rpg.setDisplay(new displays.SantaFeGymD(p));
		} else if (attackPoints == 0) stand(p, o);
	}

}
