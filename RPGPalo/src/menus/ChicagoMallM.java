package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Chicago Mall menu. */
public class ChicagoMallM extends Menu {

	public static final int BIG_BURGER_PRICE = 1500; // cost of Big Burger
	public static final int BIG_BURGER_GAIN = 1000; // hp gain from Big Burger
	public static final int KING_MEAL_PRICE = 5000; // cost of King Meal
	public static final int KING_MEAL_GAIN = 5000; // hp gain from King Meal
	public static final int ULTRABED_PRICE = 20000; // cost of UltraBed
	public static final int TVPRO_PRICE = 100000; // cost of TVPro
	public static final int TIME_WARP_PRICE = 100000000; // cost of Time Warp

	/** Constructor: the Bus menu */
	public ChicagoMallM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.ChicagoM(rpg));
				rpg.setDisplay(new displays.ChicagoD(p));
			}
		});

		buttons[1] = new JButton("Big Burger");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigBurger(p);
				rpg.setDisplay(new displays.ChicagoD(p));
			}
		});
		buttons[1].setToolTipText("<html>HP Gain: " + commas(BIG_BURGER_GAIN) +
				"<br />Price: $" + commas(BIG_BURGER_PRICE) + "</html>");

		buttons[2] = new JButton("King Meal");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kingMeal(p);
				rpg.setDisplay(new displays.ChicagoD(p));
			}
		});
		buttons[2].setToolTipText("<html>HP Gain: " + commas(KING_MEAL_GAIN) +
				"<br />Price: $" + commas(KING_MEAL_PRICE) + "</html>");

		buttons[3] = new JButton("UltraBed");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ultraBed(p);
				rpg.setDisplay(new displays.ChicagoD(p));
			}
		});
		buttons[3].setToolTipText("<html>Buy an UltraBed for $" + commas(ULTRABED_PRICE) + ".<br />"
				+ "An UltraBed restores all your HP when you sleep at home.</html>");

		buttons[4] = new JButton("TVPro");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TVPro(p);
				rpg.setDisplay(new displays.ChicagoD(p));
			}
		});
		buttons[4].setToolTipText("<html>Buy a TVPro for $" + commas(TVPRO_PRICE) + ".<br />"
				+ "A TVPro allows you to watch TV at home without worrying about TV bills.</html>");

		buttons[5] = new JButton("Time Warp");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeWarp(p);
				rpg.setDisplay(new displays.ChicagoD(p));
			}
		});
		buttons[5].setToolTipText("<html>Buy a Time Warp for $" + commas(TIME_WARP_PRICE) + ".<br />"
				+ "A Time Warp gives you " + Person.TIMEWARP_HOUR + " hours per day.</html>");

		formatButtons();
		updateButtons();
	}

	/** Eat a Big Burger if there are enough money and time. */
	private void bigBurger(Person p) {
		if (p.getMoney() < BIG_BURGER_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+BIG_BURGER_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - BIG_BURGER_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

	/** Eat a King Meal if there are enough money and time. */
	private void kingMeal(Person p) {
		if (p.getMoney() < KING_MEAL_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+KING_MEAL_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - KING_MEAL_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

	/** Buy an UltraBed if not owned and there is enough money. */
	private void ultraBed(Person p) {
		if (p.getBed() == 2) {
			JLabel l = new JLabel("You already have an UltraBed.");
			new MessageDialog(rpg, "Error", l, 190, 90);
			return;
		} else if (p.getMoney() < ULTRABED_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setBed(2);
		p.setMoney(p.getMoney() - ULTRABED_PRICE);
	}

	/** Buy a TVPro if not owned and there is enough money. */
	private void TVPro(Person p) {
		if (p.getTv() == 2) {
			JLabel l = new JLabel("You already have a TVPro.");
			new MessageDialog(rpg, "Error", l, 170, 90);
			return;
		} else if (p.getMoney() < TVPRO_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setTv(2);
		p.setMoney(p.getMoney() - TVPRO_PRICE);
	}

	/** Buy a Time Warp if not owned and there is enough money. */
	private void timeWarp(Person p) {
		if (p.getTime()) {
			JLabel l = new JLabel("You already have a Time Warp.");
			new MessageDialog(rpg, "Error", l, 200, 90);
			return;
		} else if (p.getMoney() < TIME_WARP_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setTime(true);
		p.setMoney(p.getMoney() - TIME_WARP_PRICE);
	}

}
