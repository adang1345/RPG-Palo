package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Lottery display. */
public class LotteryD extends Display {

	/** Constructor: the Lottery display */
	public LotteryD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Which lottery game would you like to play? All tickets are $" +
				commas(Person.LOTTERY_PRICE) + " each.<br /><br />Cash: $" + commas(p.getMoney()));
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
