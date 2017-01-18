package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the DD Club menu. */
public class DDClubM extends Menu {

	/** Constructor: the DD Club menu */
	public DDClubM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BarM(rpg));
				rpg.setDisplay(new displays.BarD(p));
			}
		});

		if (!p.getClub()) {
			buttons[1] = new JButton("<html>I want to join<br />the club.</html>");
			buttons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					joinClub(p);
					rpg.setMenu(new menus.DDClubM(rpg));
					rpg.setDisplay(new displays.DDClubD(p));
				}
			});

			buttons[2] = new JButton("<html>I am here to<br />destroy you!</html>");
			buttons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					destroy(p);
				}
			});
		} else {
			buttons[1] = new JButton("Buy Drugs");
			buttons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rpg.setMenu(new menus.BuyDrugsM(rpg));
					rpg.setDisplay(new displays.DDClubD(p));
				}
			});

			buttons[2] = new JButton("Sell Drugs");
			buttons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rpg.setMenu(new menus.SellDrugsM(rpg));
					rpg.setDisplay(new displays.DDClubD(p));
				}
			});
		}
		formatButtons();
		updateButtons();
	}

	/** Join club if user agrees and has enough money. */
	private void joinClub(Person p) {
		JLabel l = new JLabel("<html>You must pay a membership fee of $" + commas(Person.DD_CLUB_PRICE) +
				" to join. Do you want to pay<br />now?</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "DD Club", l, "Yes", "No", 410, 105);
		if (c.getChoice() && p.getMoney()>=Person.DD_CLUB_PRICE) {
			l = new JLabel("Welcome to the Drug Dealing Club. You are now an official member.");
			new MessageDialog(rpg, "DD Club", l, 400, 90);
			p.setClub(true);
			p.setMoney(p.getMoney() - Person.DD_CLUB_PRICE);
		} else if (c.getChoice()) {
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
		}
	}

	/** Get shot and die. */
	private void destroy(Person p) {
		JLabel l = new JLabel("Angry at your comment, a club member shoots you in the head.");
		new MessageDialog(rpg, "DD Club", l, 370, 90);
		p.die(rpg);
	}

}
