package displays;

import javax.swing.JLabel;

import gui.Display;

/** A class for the Deposit display. */
public class DepositD extends Display {

	/** Constructor: the Deposit display */
	public DepositD(user.Person p) {
		super(p);
		JLabel l = new JLabel("<html>Balance: $" + commas(p.getBank()) +
				"<br />Cash: $" + commas(p.getMoney()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
