package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Fitness menu. */
public class FitnessM extends Menu {

	/** Constructor: the Fitness menu */
	public FitnessM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.TvM(rpg));
				rpg.setDisplay(new displays.TvD(p));
			}
		});

		buttons[1] = new JButton("Half Hour");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				watchHalf(p);
				rpg.setDisplay(new displays.FitnessD(p));
			}
		});

		buttons[2] = new JButton("X Hours");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				watchX(p);
				rpg.setDisplay(new displays.FitnessD(p));
			}
		});

		buttons[3] = new JButton("All Day");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				watchAllDay(p);
				rpg.setDisplay(new displays.FitnessD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Watch fitness channel for half an hour if there is enough time. */
	private void watchHalf(Person p) {
		// say there is not enough time if that is the case, and quit
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields of p
		p.setHour(p.getHour() - 0.5);
		p.setHpmax(p.getHpmax() + Person.TV_HPMAX_INCREASE);
		if (Person.randInt(1, 30) == 1) {
			JLabel l = new JLabel("You work out extra this time and get a bonus.");
			new MessageDialog(rpg, "Bonus", l, 270, 90);
			p.setHpmax(p.getHpmax() + Person.TV_HPMAX_EXTRA_INCREASE);
		}
		p.setTvt(p.getTvt() + 1);
	}

	/** Watch fitness channel for user-inputted number of hours if possible. */
	private void watchX(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of hours you would like to watch the Fitness Channel.<br />"
				+ "Your number must be a multiple of 0.5.</html>");
		InputDialog f = new InputDialog(rpg, "Fitness", l, 420, 130, 36);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		double inputHour;
		try {
			inputHour = Double.parseDouble(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		double inputHourFracPart = inputHour - (int) inputHour;
		if (inputHour < 0 || inputHourFracPart != 0 && inputHourFracPart != 0.5) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (inputHour > p.getHour()) {
			l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// update fields
		p.setHour(p.getHour() - inputHour);
		p.setHpmax(p.getHpmax() + Person.TV_HPMAX_INCREASE*(int)(inputHour*2));
		p.setTvt(p.getTvt() + (int)(inputHour*2));
	}

	/** Watch fitness channel all day. */
	private void watchAllDay(Person p) {
		p.setHpmax(p.getHpmax() + Person.TV_HPMAX_INCREASE*(int)(p.getHour()*2));
		p.setTvt(p.getTvt() + (int)(p.getHour()*2));
		p.setHour(0);
	}

}
