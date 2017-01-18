package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Withdraw display. */
public class WithdrawD extends Display {

	/** Constructor: a Withdraw display */
	public WithdrawD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Balance: $" + commas(p.getBank()) +
				"<br />Cash: $" + commas(p.getMoney()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
