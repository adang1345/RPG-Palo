package menus;

import java.awt.event.*;
import javax.swing.JButton;

import gui.Menu;
import gui.RPG;
import user.Person;

/** A class for the Fat Foods menu. */
public class FatFoodsM extends Menu {
	public static final int MCWHOPPER_GAIN = 30; // hp gain from McWhopper
	public static final int MCWHOPPER_PRICE = 10; // price of McWhopper
	public static final int HAMBURGER_GAIN = 15; // hp gain from hamburger
	public static final int HAMBURGER_PRICE = 6; // price of hamburger
	public static final int CHEESEBURGER_GAIN = 20; // hp gain from cheeseburger
	public static final int CHEESEBURGER_PRICE = 8; // price of cheeseburger
	public static final int FRIES_GAIN = 8; // hp gain from fries
	public static final int FRIES_PRICE = 3; // price of fries
	public static final int ONIONRINGS_GAIN = 9; // hp gain from fries
	public static final int ONIONRINGS_PRICE = 4; // price of fries
	public static final int DRINK_GAIN = 9; // hp gain from drink
	public static final int DRINK_PRICE = 4; // price of drink
	public static final int CAKE_GAIN = 8; // hp gain from cake
	public static final int CAKE_PRICE = 3; // price of cake
	public static final int COOKIE_GAIN = 20; // hp gain from cookie
	public static final int COOKIE_PRICE = 10; // price of cookie

	/** Constructor: the Fat Foods menu */
	public FatFoodsM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MallM(rpg));
				rpg.setDisplay(new displays.MallD(p));
			}
		});

		buttons[1] = new JButton("Burgers");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BurgersM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[2] = new JButton("Sides");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SidesM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[3] = new JButton("Drinks");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.DrinksM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[4] = new JButton("Desserts");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.DessertsM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[5] = new JButton("Work");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FatFoodsWorkM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});
		buttons[5].setToolTipText("Work for $" + Person.FAT_FOODS_SALARY + " per hour.");

		formatButtons();
		updateButtons();
	}

}
