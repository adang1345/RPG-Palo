package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Bank display. */
public class BankD extends Display {

	/** Constructor: the Bank display */
	public BankD(Person p) {
		super(p);
		String loanMessage = p.getLoan() == 0 ? "" : 
			"Days to Pay Back Loan: " + (commas(Person.MAX_LOANDAY-p.getLoanday()) +
					"<br />Loan Debt: $" + commas(p.getLoan()));
		JLabel l = new JLabel("<html>Balance: $" + commas(p.getBank()) +
				"<br />Cash: $" + commas(p.getMoney()) +
				"<br /><br />" + loanMessage + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
