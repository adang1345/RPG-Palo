package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Computer display. */
public class ComputerD extends Display {

	/** Constructor: the Computer display */
	public ComputerD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Cash: $" + commas(p.getMoney()) +
				"<br /><br />AIJ Price: $" + commas(p.getAij()) + 
				"<br />DCE Price: $" + commas(p.getDce()) + 
				"<br />NID Price: $" + commas(p.getNid()) + 
				"<br />ABC Price: $" + commas(p.getAbc()) + 
				"<br /><br />AIJ Shares Owned: " + commas(p.getBaij()) + 
				"<br />DCE Shares Owned: " + commas(p.getBdce()) + 
				"<br />NID Shares Owned: " + commas(p.getBnid()) + 
				"<br />ABC Shares Owned: " + commas(p.getBabc()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
