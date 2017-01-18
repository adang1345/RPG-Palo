package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Fail menu. Fail is a game accessible in the Las Vegas casino. */
public class FailM extends Menu {

	/** Constructor: the Fail menu
	 * Precondition: at least 0.5 hour is left */
	public FailM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Quit Game");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.VegasCasinoM(rpg));
				rpg.setDisplay(new displays.VegasCasinoD(p));
			}
		});

		buttons[1] = new JButton("Case 1");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickCase(p, 1);
				rpg.setMenu(new menus.VegasCasinoM(rpg));
				rpg.setDisplay(new displays.VegasCasinoD(p));
			}
		});

		buttons[2] = new JButton("Case 2");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickCase(p, 2);
				rpg.setMenu(new menus.VegasCasinoM(rpg));
				rpg.setDisplay(new displays.VegasCasinoD(p));
			}
		});

		buttons[3] = new JButton("Case 3");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickCase(p, 3);
				rpg.setMenu(new menus.VegasCasinoM(rpg));
				rpg.setDisplay(new displays.VegasCasinoD(p));
			}
		});

		buttons[4] = new JButton("Case 4");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickCase(p, 4);
				rpg.setMenu(new menus.VegasCasinoM(rpg));
				rpg.setDisplay(new displays.VegasCasinoD(p));
			}
		});

		buttons[5] = new JButton("Case 5");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pickCase(p, 5);
				rpg.setMenu(new menus.VegasCasinoM(rpg));
				rpg.setDisplay(new displays.VegasCasinoD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Play the game after the user picks a case. 
	 * Precondition: i is in 1..5. */
	private void pickCase(Person p, int chosenCase) {
		p.setHour(p.getHour() - 0.5);
		int winningCase = Person.randInt(1, 5);
		if (winningCase == chosenCase) {
			JLabel l = new JLabel("You win $1,000,000.");
			new MessageDialog(rpg, "Fail", l, 100, 90);
			p.setMoney(p.getMoney() + 1000000);
			return;
		}
		int x = Person.randInt(1, 10);
		if (x >= 1 && x <= 7) {
			JLabel l = new JLabel("<html>You lose. The $1,000,000 was in Case " + winningCase + ". You fail by losing all your money,<br />"
					+ "on hand and in the bank.</html>");
			new MessageDialog(rpg, "Fail", l, 430, 105);
			p.setMoney(0);
			p.setBank(0);
		} else if (x == 8 || x == 9) {
			JLabel l = new JLabel("<html>You lose. The $1,000,000 was in Case " + winningCase + ". You fail by losing almost every<br />"
					+ "material possession except your money.</html>");
			new MessageDialog(rpg, "Fail", l, 420, 105);
			p.setGun(0);
			p.setBullet(0);
			p.setHupgrade(0);
			p.setBed(0);
			p.setTv(0);
			p.setCom(false);
			p.setBaij(0);
			p.setBdce(0);
			p.setBnid(0);
			p.setBabc(0);
			p.setBeer(0);
			p.setCrack(0);
			p.setHeroin(0);
			p.setTime(false);
			p.setTread(false);
			p.setBook(false);
			p.setDag(0);
		} else if (p.getLoan() == 0) {
			JLabel l = new JLabel("<html>You lose. The $1,000,000 was in Case " + winningCase + ". You fail by owing the bank a<br />"
					+ "$1,000,000 loan. You have " + Person.MAX_LOANDAY + " days to pay it back.</html>");
			new MessageDialog(rpg, "Fail", l, 410, 105);
			p.setLoan(1000000);
		} else {
			JLabel l = new JLabel("<html>You lose. The $1,000,000 was in Case " + winningCase + ". You fail by owing the bank an<br />"
					+ "additional $1,000,000 on top of your current loan. You have " + (Person.MAX_LOANDAY-p.getLoanday()) + " days to<br />"
					+ "pay it back.</html>");
			new MessageDialog(rpg, "Fail", l, 420, 120);
			p.setLoan(p.getLoan() + 1000000);
		}
	}

}
