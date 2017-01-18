package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Home display. */
public class HomeD extends Display {

	/** Constructor: the Home display */
	public HomeD(Person p) {
		super(p);
		String loanMessage = p.getLoan() == 0 ? "" : "Days to Pay Back Loan: " + commas(Person.MAX_LOANDAY-p.getLoanday());
		JLabel l = new JLabel("<html>Day " + commas(p.getDay()) +
				"<br />Hours Left: " + commas(p.getHour()) +
				"<br />Cash: $" + commas(p.getMoney()) +
				"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) +
				"<br />Intelligence: " + commas(p.getSmart()) + 
				"<br /><br />" + loanMessage + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
