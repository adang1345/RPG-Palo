package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.BarOpponent;
import user.Person;

/** A class for the Bar menu. */
public class BarM extends Menu {

	/** Constructor: the Bar menu */
	public BarM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MallM(rpg));
				rpg.setDisplay(new displays.MallD(p));
			}
		});

		buttons[1] = new JButton("Drink Beer");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinkBeer(p);
				rpg.setDisplay(new displays.BarD(p));
			}
		});
		buttons[1].setToolTipText("<html>HP Gain: " + commas(Person.BEER_GAIN) +
				"<br />Price: $" + commas(Person.BEER_PRICE) + "</html>");

		buttons[2] = new JButton("Fight");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fight(p);
			}
		});
		buttons[2].setToolTipText("Start a bar fight.");

		buttons[3] = new JButton("Caffeine");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyCaffeine(p);
				rpg.setDisplay(new displays.BarD(p));
			}
		});
		buttons[3].setToolTipText("Buy caffeine pills for $" + commas(Person.CAFFEINE_PRICE) +
				" each. A pill subtracts " + commas(Person.CAFFEINE_HP_LOSS) +
				" HP when you sleep and gives you a next day " +
				commas(Person.CAFFEINE_HOUR) + " hours long.");

		buttons[4] = new JButton("DD Club");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DDClub(p);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Drink beer if there are enough money and time. */
	private void drinkBeer(Person p) {
		if (p.getMoney() < Person.BEER_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHp(Math.min(p.getHp()+Person.BEER_GAIN, p.getHpmax()));
		p.setMoney(p.getMoney() - Person.BEER_PRICE);
		p.setHour(p.getHour() - 0.5);
	}

	/** Buy a user-inputted number of caffeine pills if possible. */
	private void buyCaffeine(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Caffeine pills are $" + commas(Person.CAFFEINE_PRICE) + " each. How many would you like to buy? You must<br />"
				+ "enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Buy Caffeine", l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long buyAmount;
		try {
			buyAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (buyAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (buyAmount*Person.CAFFEINE_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - buyAmount*Person.CAFFEINE_PRICE);
		p.setCaffeine(p.getCaffeine() + buyAmount);
	}

	/** Start a bar fight if there is enough time. */
	private void fight(Person p) {
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHour(p.getHour() - 0.5);
		p.setFightnum(p.getFightnum() + 1);
		BarOpponent o = new BarOpponent(p);
		rpg.setMenu(new menus.FightM(rpg, o));
		rpg.setDisplay(new displays.FightD(p, o));
	}

	/** Try to enter the DD Club. */
	private void DDClub(Person p) {
		if (p.getGun() == 0 || p.getBullet() == 0) {
			DDClubNoLoadedGun(p);
			return;
		} else if (Person.randInt(1, 20) == 1 && p.getClub()) {
			policeRaid(p);
			return;
		}
		rpg.setMenu(new menus.DDClubM(rpg));
		rpg.setDisplay(new displays.DDClubD(p));

	}

	/** Get beaten almost to death for entering DD Club without loaded gun. */
	private void DDClubNoLoadedGun(Person p) {
		JLabel l = new JLabel("<html>You enter the Drug Dealing Club without a loaded gun. A fight breaks<br />"
				+ "out and you are beaten almost to death.</html>");
		new MessageDialog(rpg, "DD Club", l, 400, 105);
		p.setHour(0);
		p.setHp(1);
		rpg.setDisplay(new displays.BarD(p));
	}

	/** Encounter a police raid at DD Club. */
	private void policeRaid(Person p) {
		JLabel l = new JLabel("<html>Police raid the club. They confiscate your gun, cash, and drugs. You go<br />"
				+ "to jail for 10 days.</html>");
		new MessageDialog(rpg, "DD Club", l, 410, 105);
		p.setBullet(0);
		p.setGun(0);
		p.setMoney(0);
		p.setBeer(0);
		p.setCrack(0);
		p.setHeroin(0);
		int sleepCode = p.sleepJail(10);
		if (sleepCode == 1) {
			rpg.setDisplay(new displays.BarD(p));
			p.loanPenalty(rpg);
			return;
		}
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(p));
	}

}
