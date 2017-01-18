package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the PaloTech display. */
public class PaloTechD extends Display {

	/** Constructor: the PaloTech display */
	public PaloTechD(Person p) {
		super(p);

		String position;
		String payRate;
		switch (p.getJob()) {
		case 1:
			position = "Sales Clerk";
			payRate = "10";
			break;
		case 2:
			position = "Manager";
			payRate = "20";
			break;
		case 3:
			position = "Software Engineer";
			payRate = "50";
			break;
		case 4:
			position = "Executive Assistant";
			payRate = "100";
			break;
		case 5:
			position = "CEO";
			payRate = "500";
			break;
		case 6:
			position = "Government Worker";
			payRate = "600";
			break;
		default:
			position = "Better Government Worker";
			payRate = commas(100*(p.getJob()+1));
		}

		JLabel l;
		if (p.getJob() == 0) {
			l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
					"<br />Cash: $" + commas(p.getMoney()) +
					"<br />Intelligence: " + commas(p.getSmart()) + "</html>");
		} else {
			l = new JLabel("<html>Hours Left: " + commas(p.getHour()) +
					"<br />Cash: $" + commas(p.getMoney()) +
					"<br />Intelligence: " + commas(p.getSmart()) +
					"<br /><br />Position: " + position +
					"<br />Hourly Rate: $" + payRate + "</html>");
		}
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
