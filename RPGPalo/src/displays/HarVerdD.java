package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Har-Verd display. */
public class HarVerdD extends Display {

	/** Constructor: the Har-Verd display */
	public HarVerdD(Person p) {
		super(p);

		String position;
		String payRate;
		switch (p.getHjob()) {
		case 1:
			position = "Lab Assistant";
			payRate = "30,000";
			break;
		case 2:
			position = "Teaching Assistant";
			payRate = "50,000";
			break;
		case 3:
			position = "Assitant Professor";
			payRate = "100,000";
			break;
		case 4:
			position = "Professor";
			payRate = "200,000";
			break;
		case 5:
			position = "Vice President";
			payRate = "450,000";
			break;
		default: // case 6
			position = "President";
			payRate = "1,000,000";
		}

		JLabel l;
		if (p.getHjob() == 0) {
			l = new JLabel("<html>Welcome to Har-Verd, the best college in the world."
					+ "<br /><br />Day " + commas(p.getDay())
					+ "<br />Hours Left: " + commas(p.getHour())
					+ "<br />Cash: $" + commas(p.getMoney())
					+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax())
					+ "<br />Intelligence: " + commas(p.getSmart()) + "</html>");
		} else {
			l = new JLabel("<html>Welcome to Har-Verd, the best college in the world."
					+ "<br /><br />Day " + commas(p.getDay())
					+ "<br />Hours Left: " + commas(p.getHour())
					+ "<br />Cash: $" + commas(p.getMoney())
					+ "<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax())
					+ "<br />Intelligence: " + commas(p.getSmart())
					+ "<br /><br />Position: " + position
					+ "<br />Monthly Rate: $" + payRate + "</html>");
		}
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
