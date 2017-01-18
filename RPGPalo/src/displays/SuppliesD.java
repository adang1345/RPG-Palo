package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Supplies display. */
public class SuppliesD extends Display {

	/** Constructor: the Supplies display */
	public SuppliesD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Cash: $" + commas(p.getMoney()) +
				"<br />Home Level: " + p.getHupgrade() +
				"<br /><br />Bed: " + (p.getBed()==0 ? "Not Owned" : "Owned") +
				"<br />TV: " + (p.getTv()==0 ? "Not Owned" : "Owned") + 
				"<br />Computer: " + (!p.getCom() ? "Not Owned" : "Owned") +
				"<br />Treadmill: " + (!p.getTread() ? "Not Owned" : "Owned") +
				"<br />Bookcase: " + (!p.getBook() ? "Not Owned" : "Owned") + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
