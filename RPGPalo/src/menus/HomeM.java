package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Home menu. */
public class HomeM extends Menu {

	/** Constructor: the Home menu */
	public HomeM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Sleep");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sleepCode = p.sleep(1);
				rpg.setDisplay(new displays.HomeD(p));
				if (sleepCode == 1) p.die(rpg);
				else if (sleepCode == 2) p.loanPenalty(rpg);
			}
		});

		buttons[2] = new JButton("Watch TV");
		buttons[2].addActionListener(new ActionListener() {
			/** If a TV is not owned, then display message saying this. Otherwise,
			 * if there is less than half an hour, then display message saying
			 * there is not enough time. Otherwise, if TV bill is owed, ask for
			 * payment. Otherwise, go to TV menu and TV display. */
			public void actionPerformed(ActionEvent e) {
				if (p.getTv() == 0) {
					noTv();
					return;
				} else if (p.getHour() < 0.5) {
					noTimeForTV();
					return;
				} else if (p.getTv() == 1 && p.getTvt() >= Person.TV_BILL_TIMES) {
					askTvPayment(p);
					return;
				}
				rpg.setMenu(new menus.TvM(rpg));
				rpg.setDisplay(new displays.TvD(p));
			}
		});

		buttons[3] = new JButton("Computer");
		buttons[3].addActionListener(new ActionListener() {
			/** If a computer is owned, then go to the computer menu and display.
			 * Otherwise, display message saying that no computer is owned. */
			public void actionPerformed(ActionEvent e) {
				if (p.getCom()) {
					rpg.setMenu(new menus.ComputerM(rpg));
					rpg.setDisplay(new displays.ComputerD(p));
				} else {
					noComputer();
				}
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Display message saying that there is no TV owned. */
	private void noTv() {
		JLabel l = new JLabel("You do not own a TV.");
		new MessageDialog(rpg, "No TV", l, 140, 90);
	}

	/** Ask for a TV bill payment. If payment is successful, allow TV access. */
	private void askTvPayment(Person p) {
		// Ask whether user would like to pay bill
		int bill = p.getJob()*3000 + 500;
		JLabel l = new JLabel("<html>You have watched TV for " + p.getTvt()/2 +
				" hours since the last paid bill. To continue,<br />"
				+ "you must pay a TV bill of $" + commas(bill) + ". Pay now?</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "TV Bill", l, "Yes", "No", 420, 105);

		if (!c.getChoice()) return; // stop if user doesn't want to pay bill
		if (p.getMoney() >= bill) { // pay bill if enough money
			p.setMoney(p.getMoney()-bill);
			p.setTvt(0);
			rpg.setMenu(new menus.TvM(rpg));
			rpg.setDisplay(new displays.TvD(p));
		} else { // notify and quit if not enough money
			l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 140, 90);
		}
	}

	/** Display a message saying that there is not enough time to watch TV. */
	private void noTimeForTV() {
		JLabel l = new JLabel("Not enough time.");
		new MessageDialog(rpg, "Error", l, 100, 90);
	}

	/** Display a message saying that there is no computer. */
	private void noComputer() {
		JLabel l = new JLabel("You do not own a computer.");
		new MessageDialog(rpg, "No Computer", l, 170, 90);
	}

}
