package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Har-Verd menu. */
public class HarVerdM extends Menu {
	public static final int COURSE_PRICE = 50000; // the price of taking a course

	/** Constructor: the Har-Verd menu */
	public HarVerdM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BostonM(rpg));
				rpg.setDisplay(new displays.BostonD(p));
			}
		});

		buttons[1] = new JButton("Take Course");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeCourse(p);
			}
		});
		buttons[1].setToolTipText("<html>Take a 30-day course.<br />Price: $" +
				commas(COURSE_PRICE));

		if (p.getHjob() == 0) {
			buttons[2] = new JButton("Apply for Job");
			buttons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					apply(p);
					rpg.setMenu(new menus.HarVerdM(rpg));
					rpg.setDisplay(new displays.HarVerdD(p));
				}
			});
		} else {
			buttons[2] = new JButton("Work");
			buttons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					work(p);
				}
			});
			buttons[2].setToolTipText("Work for 30 days.");

			buttons[3] = new JButton("<html><p style=\"text-align:center\">Ask for<br />Promotion</p></html>");
			buttons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					promote(p);
					rpg.setDisplay(new displays.HarVerdD(p));
				}
			});
		}
		formatButtons();
		updateButtons();
	}

	/** Take a course for 30 days if no loan is active and there is enough money. */
	private void takeCourse(Person p) {
		if (p.getLoan() != 0) {
			JLabel l = new JLabel("You owe the bank money. Repay your loan before taking a course.");
			new MessageDialog(rpg, "Take Course", l, 390, 90);
			return;
		} else if (p.getMoney() < COURSE_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setMoney(p.getMoney() - COURSE_PRICE);

		int score; char grade; int intelligenceGain;
		switch (Person.randInt(1, 8)) {
		case 1: case 2:
			score = 90 + Person.randInt(0, 10);
			grade = 'A';
			intelligenceGain = 8000;
			break;
		case 3: case 4:
			score = 80 + Person.randInt(0, 9);
			grade = 'B';
			intelligenceGain = 6500;
			break;
		case 5: case 6:
			score = 70 + Person.randInt(0, 9);
			grade = 'C';
			intelligenceGain = 5000;
			break;
		case 7:
			score = 60 + Person.randInt(0, 9);
			grade = 'D';
			intelligenceGain = 2000;
			break;
		default: // case 8
			score = Person.randInt(0, 59);
			grade = 'F';
			intelligenceGain = 300;
		}

		p.sleepHarVerd(30);
		p.setSmart(p.getSmart() + intelligenceGain);
		JLabel l = new JLabel("<html>You go through the 30-day course and receive a score of " +
				score + "/" + grade + ". You gain<br />" +
				intelligenceGain + " intelligence.</html>");
		new MessageDialog(rpg, "Take Course", l, 420, 105);
		rpg.setMenu(new menus.HarVerdM(rpg));
		rpg.setDisplay(new displays.HarVerdD(p));
	}

	/** Apply for a job. */
	private void apply(Person p) {
		if (p.getSmart() < 20000) {
			JLabel l = new JLabel("<html>Unfortunately, you are not smart enough to get a job here. You need<br />"
					+ "20,000 intelligence.</html>");
			new MessageDialog(rpg, "Fail", l, 400, 105);
			return;
		}
		JLabel l = new JLabel("You are hired as a Lab Assistant.");
		new MessageDialog(rpg, "Success", l, 210, 90);
		p.setHjob(1);
	}

	/** Get a promotion if intelligence is high enough and one is available.
	 * Precondition: person currently has a job at Har-Verd */
	private void promote(Person p) {
		JLabel l;
		switch (p.getHjob()) {
		case 1:
			if (p.getSmart() < 50000) {
				l = new JLabel("To be promoted, you need 50,000 intelligence.");
				new MessageDialog(rpg, "Fail", l, 280, 90);
				return;
			}
			l = new JLabel("You are promoted to Teaching Assistant.");
			new MessageDialog(rpg, "Success", l, 250, 90);
			break;
		case 2:
			if (p.getSmart() < 100000) {
				l = new JLabel("To be promoted, you need 100,000 intelligence.");
				new MessageDialog(rpg, "Fail", l, 280, 90);
				return;
			}
			l = new JLabel("You are promoted to Assitant Professor.");
			new MessageDialog(rpg, "Success", l, 245, 90);
			break;
		case 3:
			if (p.getSmart() < 180000) {
				l = new JLabel("To be promoted, you need 180,000 intelligence.");
				new MessageDialog(rpg, "Fail", l, 280, 90);
				return;
			}
			l = new JLabel("You are promoted to Professor.");
			new MessageDialog(rpg, "Success", l, 200, 90);
			break;
		case 4:
			if (p.getSmart() < 300000) {
				l = new JLabel("To be promoted, you need 300,000 intelligence.");
				new MessageDialog(rpg, "Fail", l, 280, 90);
				return;
			}
			l = new JLabel("You are promoted to Vice President.");
			new MessageDialog(rpg, "Success", l, 225, 90);
			break;
		case 5:
			if (p.getSmart() < 700000) {
				l = new JLabel("To be promoted, you need 700,000 intelligence.");
				new MessageDialog(rpg, "Fail", l, 280, 90);
				return;
			}
			l = new JLabel("You are promoted to President.");
			new MessageDialog(rpg, "Success", l, 200, 90);
			break;
		default: // case 6
			l = new JLabel("No more promotions are available.");
			new MessageDialog(rpg, "Promotion", l, 215, 90);
			return;
		}
		p.setHjob(p.getHjob() + 1);
	}

	/** Work for 30 days if no loan is active.
	 * Precondition: Person has a job at Har-Verd. */
	private void work(Person p) {
		if (p.getLoan() != 0) {
			JLabel l = new JLabel("You owe the bank money. Repay your loan before working.");
			new MessageDialog(rpg, "Take Course", l, 350, 90);
			return;
		}

		int salary;
		switch (p.getHjob()) {
		case 1:
			salary = 30000;
			break;
		case 2:
			salary = 50000;
			break;
		case 3:
			salary = 100000;
			break;
		case 4:
			salary = 200000;
			break;
		case 5:
			salary = 450000;
			break;
		default: // case 6
			salary = 1000000;
		}
		p.setMoney(p.getMoney() + salary);

		p.sleepHarVerd(30);
		rpg.setMenu(new menus.HarVerdM(rpg));
		rpg.setDisplay(new displays.HarVerdD(p));
	}

}
