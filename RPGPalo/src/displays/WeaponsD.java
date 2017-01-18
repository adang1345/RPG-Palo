package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Weapons display. */
public class WeaponsD extends Display {

	/** Constructor: the Weapons display */
	public WeaponsD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Cash: $" + commas(p.getMoney()) +
				"<br /><br />Gun Level: " + (p.getGun()==0 ? "Not Owned" : p.getGun()) +
				"<br />Bullets: " + commas(p.getBullet()) +
				"<br />Dagger Type: " + daggerType(p) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

	/** Get the dagger type in words. */
	private String daggerType(Person p) {
		switch (p.getDag()) {
		case 0:
			return "Not Owned";
		case 1:
			return "Wood";
		case 2:
			return "Bronze";
		case 3:
			return "Iron";
		case 4:
			return "Steel";
		case 5:
			return "Silver";
		case 6:
			return "Gold";
		case 7:
			return "Divine";
		}
		throw new RuntimeException();
	}

}
