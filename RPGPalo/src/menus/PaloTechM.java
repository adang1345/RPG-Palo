package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the PaloTech menu. */
public class PaloTechM extends Menu {

	/** Constructor: the PaloTech menu */
	public PaloTechM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		if (p.getJob() == 0) {
			buttons[1] = new JButton("Apply for Job");
			buttons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					apply(p);
					rpg.setMenu(new menus.PaloTechM(rpg));
					rpg.setDisplay(new displays.PaloTechD(p));
				}
			});
		} else {
			buttons[1] = new JButton("Work");
			buttons[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rpg.setMenu(new menus.PaloTechWorkM(rpg));
					rpg.setDisplay(new displays.PaloTechD(p));
				}
			});

			buttons[2] = new JButton("<html><p style=\"text-align:center\">Ask for<br />Promotion</p></html>");
			buttons[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					promote(p);
					rpg.setMenu(new menus.PaloTechM(rpg));
					rpg.setDisplay(new displays.PaloTechD(p));
				}
			});
		}

		formatButtons();
		updateButtons();
	}

	/** Apply for a job if intelligence is high enough. */
	private void apply(Person p) {
		if (p.getSmart() < 20) {
			JLabel l = new JLabel("<html>Unfortunately, you are not smart enough to get a job here. You need 20<br />"
					+ "intelligence.</html>");
			new MessageDialog(rpg, "Fail", l, 410, 105);
			return;
		}
		JLabel l = new JLabel("You are hired as a Sales Clerk.");
		new MessageDialog(rpg, "Success", l, 190, 90);
		p.setJob(1);
	}

	/** Get a promotion if intelligence is high enough.
	 * Precondition: person currently has a job at PaloTech */
	private void promote(Person p) {
		JLabel l;
		switch (p.getJob()) {
		case 1:
			if (p.getSmart() < 50) {
				l = new JLabel("To be promoted, you need 50 intelligence.");
				new MessageDialog(rpg, "Fail", l, 250, 90);
				return;
			}
			l = new JLabel("You are promoted to Manager.");
			new MessageDialog(rpg, "Success", l, 190, 90);
			break;
		case 2:
			if (p.getSmart() < 100) {
				l = new JLabel("To be promoted, you need 100 intelligence.");
				new MessageDialog(rpg, "Fail", l, 255, 90);
				return;
			}
			l = new JLabel("You are promoted to Software Engineer.");
			new MessageDialog(rpg, "Success", l, 245, 90);
			break;
		case 3:
			if (p.getSmart() < 180) {
				l = new JLabel("To be promoted, you need 180 intelligence.");
				new MessageDialog(rpg, "Fail", l, 255, 90);
				return;
			}
			l = new JLabel("You are promoted to Executive Assistant.");
			new MessageDialog(rpg, "Success", l, 250, 90);
			break;
		case 4:
			if (p.getSmart() < 400) {
				l = new JLabel("To be promoted, you need 400 intelligence.");
				new MessageDialog(rpg, "Fail", l, 255, 90);
				return;
			}
			l = new JLabel("You are promoted to CEO.");
			new MessageDialog(rpg, "Success", l, 160, 90);
			break;
		case 5:
			if (p.getSmart() < 500) {
				l = new JLabel("To be promoted, you need 500 intelligence.");
				new MessageDialog(rpg, "Fail", l, 255, 90);
				return;
			}
			l = new JLabel("You get a government job.");
			new MessageDialog(rpg, "Success", l, 160, 90);
			break;
		default:
			if (p.getSmart() < p.getJob()*105) {
				l = new JLabel("To be promoted, you need " + commas(p.getJob()*105) + " intelligence.");
				new MessageDialog(rpg, "Fail", l, 270, 90);
				return;
			}
			l = new JLabel("You get a better government job.");
			new MessageDialog(rpg, "Success", l, 200, 90);
		}
		p.setJob(p.getJob() + 1);
	}

}
