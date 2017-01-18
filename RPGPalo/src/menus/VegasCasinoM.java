package menus;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Las Vegas Casino menu. */
public class VegasCasinoM extends Menu {
	private ArrayList<String> deck;
	
	/** Constructor: the Las Vegas Casino menu */
	public VegasCasinoM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();
		deck = new ArrayList<String>(Arrays.asList("2", "2", "2", "2", "3", "3",
				"3", "3", "4", "4", "4", "4", "5", "5", "5", "5", "6", "6", "6",
				"6", "7", "7", "7", "7", "8", "8", "8", "8", "9", "9", "9", "9",
				"10", "10", "10", "10", "J", "J", "J", "J", "Q", "Q", "Q", "Q",
				"K", "K", "K", "K", "A", "A", "A", "A"));

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.VegasM(rpg));
				rpg.setDisplay(new displays.VegasD(p));
			}
		});

		buttons[1] = new JButton("Die Toss");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dieToss(p);
				rpg.setDisplay(new displays.VegasCasinoD(p));
			}
		});

		buttons[2] = new JButton("Fail");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fail(p);
			}
		});

		buttons[3] = new JButton("Blackjack");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blackjack(p);
			}
		});

		buttons[4] = new JButton("Mystery Box");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mysteryBox(p);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Play the Die Toss game if there is enough time. */
	private void dieToss(Person p) {
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// obtain the user-inputted value
		JLabel l = new JLabel("<html>I will roll a die. How much are you betting? You must enter a whole<br />"
				+ "number.</html>");
		InputDialog f = new InputDialog(rpg, "Die Toss", l, 390, 130, 34);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long betAmount;
		try {
			betAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (betAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (betAmount > p.getMoney()) {
			l = new JLabel("You do not have that much money.");
			new MessageDialog(rpg, "Error", l, 210, 90);
			return;
		}

		// obtain the user-inputted bet number
		l = new JLabel("Which number do you bet I will roll? (1 through 6)");
		f = new InputDialog(rpg, "Die Toss", l, 300, 115, 25);
		input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the user-inputted bet number, raising error messages and
		// quitting if necessary
		int betNumber;
		try {
			betNumber = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (betNumber < 1 || betNumber > 6) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// tell user whether game was won or lost, then set fields
		int rolledNumber = Person.randInt(1, 6);
		if (rolledNumber == betNumber) {
			l = new JLabel("I rolled a " + rolledNumber + ". You win!");
			new MessageDialog(rpg, "Die Toss", l, 100, 90);
			p.setMoney(p.getMoney() + betAmount);
		} else {
			l = new JLabel("I rolled a " + rolledNumber + ". You lose.");
			new MessageDialog(rpg, "Die Toss", l, 100, 90);
			p.setMoney(p.getMoney() - betAmount);
		}
		p.setHour(p.getHour() - 0.5);
	}

	/** Play Fail if there is enough time. */
	private void fail(Person p) {
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		rpg.setMenu(new menus.FailM(rpg));
		rpg.setDisplay(new displays.FailD(p));
	}

	/** Open the Mystery Box if there is enough time. */
	private void mysteryBox(Person p) {
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		int i = Person.randInt(1, 10);
		JLabel l;
		switch (i) {
		case 1:
			l = new JLabel("You open the box and find $100,000.");
			new MessageDialog(rpg, "Mystery Box", l, 220, 90);
			p.setMoney(p.getMoney() + 100000);
			break;
		case 2:
			l = new JLabel("You open the box and find 30 hours of free time.");
			new MessageDialog(rpg, "Mystery Box", l, 290, 90);
			p.setHour(p.getHour() + 30);
			break;
		case 3:
			l = new JLabel("You open the box and find 500 free intelligence.");
			new MessageDialog(rpg, "Mystery Box", l, 285, 90);
			p.setSmart(p.getSmart() + 500);
			break;
		case 4:
			l = new JLabel("You open the box and find a restoration of all your health.");
			new MessageDialog(rpg, "Mystery Box", l, 340, 90);
			p.setHp(p.getHpmax());
			break;
		case 5:
			l = new JLabel("You open the box and find 1,000 free max HP.");
			new MessageDialog(rpg, "Mystery Box", l, 270, 90);
			p.setHpmax(p.getHpmax() + 1000);
			break;
		case 6:
			l = new JLabel("You open the box and find a loss of $100,000.");
			new MessageDialog(rpg, "Mystery Box", l, 270, 90);
			p.setMoney(Math.max(p.getMoney()-100000, 0));
			break;
		case 7:
			l = new JLabel("You open the box and find a loss of all your time.");
			new MessageDialog(rpg, "Mystery Box", l, 290, 90);
			p.setHour(0.5);
			break;
		case 8:
			l = new JLabel("You open the box and find a loss of your bed and TV.");
			new MessageDialog(rpg, "Mystery Box", l, 310, 90);
			p.setBed(0);
			p.setTv(0);
			break;
		case 9:
			l = new JLabel("<html>You open the box and find a loss of your freedom. You go to jail for 30<br />"
					+ "days.</html>");
			new MessageDialog(rpg, "Mystery Box", l, 410, 105);
			int sleepCode = p.sleepJail(30);
			if (sleepCode == 1) {
				p.loanPenalty(rpg);
				return;
			}
			rpg.setMenu(new menus.MainM(rpg));
			rpg.setDisplay(new displays.MainD(p));
			return;
		case 10:
			l = new JLabel("You open the box and find a loss of your gun.");
			new MessageDialog(rpg, "Mystery Box", l, 270, 90);
			p.setGun(0);
		}
		p.setHour(p.getHour() - 0.5);
		rpg.setDisplay(new displays.VegasCasinoD(p));
	}

	/** Play Blackjack if there is enough time. */
	private void blackjack(Person p) {
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// obtain the user-inputted bet value
		JLabel l = new JLabel("How much are you betting? You must enter a whole number.");
		InputDialog f = new InputDialog(rpg, "Blackjack", l, 370, 115, 31);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long betAmount;
		try {
			betAmount = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (betAmount < 0) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (betAmount > p.getMoney()) {
			l = new JLabel("You do not have that much money.");
			new MessageDialog(rpg, "Error", l, 210, 90);
			return;
		}
		ArrayList<String> personCards = new ArrayList<String>();
		personCards.add(randCard());
		personCards.add(randCard());
		ArrayList<String> opponentCards = new ArrayList<String>();
		opponentCards.add(randCard());
		rpg.setMenu(new menus.BlackjackM(rpg, betAmount, deck, personCards, opponentCards));
		rpg.setDisplay(new displays.BlackjackD(p, personCards, opponentCards));
		p.setHour(p.getHour() - 0.5);
	}
	
	/** Return and remove a random card from a deck. */
	private String randCard() {
		String newCard = deck.get(Person.randInt(0, deck.size()-1));
		deck.remove(newCard);
		return newCard;
	}

}
