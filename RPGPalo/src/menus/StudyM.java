package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.InputDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Study menu. */
public class StudyM extends Menu {

	/** Constructor: the Study menu */
	public StudyM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.CollegeM(rpg));
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[1] = new JButton("1 Hour");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				study1(p);
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[2] = new JButton("X Hours");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studyX(p);
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[3] = new JButton("All Day");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studyAllDay(p);
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Study for 1 hour if there is enough time. */
	private void study1(Person p) {
		if (p.getHour() < 1) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHour(p.getHour() - 1);
		p.setSmart(p.getSmart() + 1);
	}

	/** Study for user-inputted number of hours if possible. */
	private void studyX(Person p) {
		// obtain the user-inputted value
		JLabel l = new JLabel("<html>Enter the number of hours you would like to study. You must enter a<br />"
				+ "whole number.</html>");
		InputDialog f = new InputDialog(rpg, "Study", l, 400, 130, 34);
		String input = f.getInput();
		if (input == null) return; // stop if nothing was inputted

		// process the value, raising error messages and quitting if necessary
		long inputHour;
		try {
			inputHour = Long.parseLong(input);
		} catch(NumberFormatException e) {
			l = new JLabel("Invalid input.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		if (inputHour < 0) {
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
		p.setSmart(p.getSmart() + inputHour);
	}

	/** Study as many 1-hour periods as possible. */
	private void studyAllDay(Person p) {
		int studyHour = (int) p.getHour();
		p.setHour(p.getHour() - studyHour);
		p.setSmart(p.getSmart() + studyHour);
	}

}
