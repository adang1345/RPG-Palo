package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Santa Fe menu. */
public class SantaFeM extends Menu {
	public static final int FOOD_PRICE = 20; // price per pound of food
	public static final int FOOD_HP_GAIN = 100; // hp gain per pound of food

	/** Constructor: the Santa Fe menu */
	public SantaFeM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leave(p);
			}
		});
		buttons[0].setToolTipText("<html>Take a 14-hour bus ride back.<br />Cost: $"
				+ commas(Person.SANTA_FE_PRICE) + "</html>");

		buttons[1] = new JButton("Sleep");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sleep(p);
			}
		});

		buttons[2] = new JButton("Loan");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loan(p);
				rpg.setDisplay(new displays.SantaFeD(p));
			}
		});

		buttons[3] = new JButton("Gym");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SantaFeGymM(rpg));
				rpg.setDisplay(new displays.SantaFeGymD(p));
			}
		});

		buttons[4] = new JButton("Eat Food");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food(p);
				rpg.setMenu(new menus.SantaFeM(rpg));
				rpg.setDisplay(new displays.SantaFeD(p));
			}
		});
		buttons[4].setToolTipText("<html>HP Gain: " + commas(FOOD_HP_GAIN) +
				" / pound<br />Price: $" + commas(FOOD_PRICE) + " / pound</html>");

		formatButtons();
		updateButtons();
	}

	/** Leave Santa Fe if there are enough time and money. */
	private void leave(Person p) {
		if (p.getMoney() < Person.SANTA_FE_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getHour() < 14) {
			JLabel l = new JLabel("The Santa Fe bus has left. Come back tomorrow at an earlier time.");
			new MessageDialog(rpg, "Error", l, 390, 90);
			return;
		}
		p.setMoney(p.getMoney() - Person.SANTA_FE_PRICE);
		p.setHour(p.getHour() - 14);
		rpg.setMenu(new menus.BusM(rpg));
		rpg.setDisplay(new displays.BusD(p));
	}

	/** Sleep. */
	private void sleep(Person p) {
		if (p.getMoney() < 175) {
			JLabel l = new JLabel("<html>You do not have the $175 required to sleep in a hotel. You lose health<br />"
					+ "sleeping outside.</html>");
			new MessageDialog(rpg, "Sleep", l, 410, 105);
			int sleepCode = p.sleepOutside();
			if (sleepCode == 1) {
				p.die(rpg);
				return;
			} else if (sleepCode == 2) {
				p.loanPenalty(rpg);
				return;
			}
		} else {
			p.setMoney(p.getMoney() - 175);
			JLabel l = new JLabel("You sleep in a hotel for $175.");
			new MessageDialog(rpg, "Sleep", l, 180, 90);
			int sleepCode = p.sleepHotel();
			if (sleepCode == 1) {
				p.die(rpg);
				return;
			} else if (sleepCode == 2) {
				p.loanPenalty(rpg);
				return;
			}
		}
		rpg.setDisplay(new displays.SantaFeD(p));
	}

	/** Loan a user-inputted amount from the bank if possible. If a loan is
	 * currently active, add to the loan debt but do not reset the amount of
	 * time left before repayment is due. */
	private void loan(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the amount you would like to loan. You can loan no less than $" + commas(Person.MIN_LOAN) + "<br />"
				+ "and no more than $" + commas(Person.MAX_LOAN) + ". You must enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Loan", l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long loanAmount;
		try {
			loanAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (loanAmount < Person.MIN_LOAN) {
			l = new JLabel("You can loan no less than $" + commas(Person.MIN_LOAN) + ".");
			new MessageDialog(rpg, "Error", l, 195, 90);
			return;
		} else if (loanAmount > Person.MAX_LOAN) {
			l = new JLabel("You can loan no more than $" + commas(Person.MAX_LOAN) + ".");
			new MessageDialog(rpg, "Error", l, 240, 90);
			return;
		}

		// update fields and notify user when to pay back loan
		l = new JLabel("You have " + (Person.MAX_LOANDAY-p.getLoanday()) + " days to pay back your loan.");
		new MessageDialog(rpg, "Loan", l, 240, 90);
		p.setMoney(p.getMoney() + loanAmount);
		p.setLoan(p.getLoan() + loanAmount);
	}

	/** Eat a user-inputted amount of food. */
	private void food(Person p) {
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Food is $" + FOOD_PRICE
				+ " per pound. How many pounds of food do you want to eat?<br />"
				+ "You must enter a whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Eat Food", l, 410, 130, 35);
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
		} else if (buyAmount*FOOD_PRICE > p.getMoney()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setMoney(p.getMoney() - buyAmount*FOOD_PRICE);
		p.setHour(p.getHour() - 0.5);
		p.setHp(Math.min(p.getHp()+buyAmount*FOOD_HP_GAIN, p.getHpmax()));
	}

}
