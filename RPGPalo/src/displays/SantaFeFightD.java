package displays;

import javax.swing.JLabel;

import gui.Display;
import user.GymOpponent;
import user.Person;

/** A class for the Santa Fe Fight display. */
public class SantaFeFightD extends Display {

	/** Constructor: the Santa Fe Fight display */
	public SantaFeFightD(Person p, GymOpponent o, int attackPoints) {
		super(p);
		JLabel l = new JLabel("<html><p style=\"text-decoration: underline\">You</p>" +
				"HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) +
				"<br />Attack Points: " + attackPoints +
				"<br /><br /><p style=\"text-decoration: underline\">Opponent</p>" +
				"HP: " + commas(o.getHp()) + " / " + commas(o.getHpmax()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
