package displays;

import javax.swing.JLabel;

import gui.Display;
import user.BarOpponent;
import user.Person;

/** A class for the Fight display. */
public class FightD extends Display {

	/** Constructor: the Fight display */
	public FightD(Person p, BarOpponent o) {
		super(p);
		JLabel l = new JLabel("<html><p style=\"text-decoration: underline\">You</p>" +
				"HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) +
				"<br /><br /><p style=\"text-decoration: underline\">Opponent</p>" +
				"HP: " + commas(o.getHp()) + " / " + commas(o.getHpmax()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
