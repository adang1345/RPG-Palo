package displays;

import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Fat Foods display. */
public class FatFoodsD extends Display {

	/** Constructor: the Fat Foods display */
	public FatFoodsD(Person p) {
		super(p);
		JLabel l = new JLabel("<html>Welcome to Fat Foods, the healthiest and most popular restaurant" +
				"<br />in the world." +
				"<br /><br />Hours Left: " + commas(p.getHour()) +
				"<br />Cash: $" + commas(p.getMoney()) +
				"<br />HP: " + commas(p.getHp()) + " / " + commas(p.getHpmax()) + "</html>");
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

}
