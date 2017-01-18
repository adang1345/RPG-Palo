package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.Menu;
import gui.RPG;
import user.Person;

/** A class for the File menu. */
public class FileM extends Menu {

	/** Constructor: the File menu */
	public FileM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Quit");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});

		buttons[2] = new JButton("Save");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SaveM(rpg));
				rpg.setDisplay(new displays.SaveD(p));
			}
		});

		buttons[3] = new JButton("Load");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});

		buttons[4] = new JButton("Delete");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.DeleteM(rpg));
				rpg.setDisplay(new displays.DeleteD(p));
			}
		});

		buttons[5] = new JButton("Restart");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});

		buttons[6] = new JButton("About");
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.about();
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Ask whether the user is sure about loading a game. If so, go to Load
	 * menu and display. Otherwise, go to Main menu and display. */
	private void load() {
		JLabel l = new JLabel("<html>Are you sure you want to load a game? You will lose your current game<br />"
				+ "if you did not save.</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "Confirm Load", l, "Yes", "No", 420, 105);
		if (c.getChoice()) {
			rpg.setMenu(new menus.LoadM(rpg, false));
			rpg.setDisplay(new displays.LoadD(rpg.getPerson()));
		} else {
			rpg.setMenu(new menus.MainM(rpg));
			rpg.setDisplay(new displays.MainD(rpg.getPerson()));
		}
	}

	/** Ask whether the user is sure about restarting the game. If so, restart it. */
	private void restart() {
		JLabel l = new JLabel("<html>Are you sure you want to restart? You will lose your progress if you did<br />"
				+ "not save.</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "Confirm Restart", l, "Yes", "No", 420, 105);
		if (c.getChoice()) {
			rpg.resetPerson();
			rpg.setMenu(new menus.StartM(rpg));
			rpg.setDisplay(new displays.StartD(rpg.getPerson()));
		} else {
			rpg.setMenu(new menus.MainM(rpg));
			rpg.setDisplay(new displays.MainD(rpg.getPerson()));
		}
	}

	/** Ask whether the user is sure about quitting. If so, quit the game. */
	private void quit() {
		JLabel l = new JLabel("<html>Are you sure you want to quit? You will lose your progress if you did not<br />"
				+ "save.</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "Confirm Quit", l, "Yes", "No", 420, 105);
		if (c.getChoice()) {
			rpg.dispose();
		} else {
			rpg.setMenu(new menus.MainM(rpg));
			rpg.setDisplay(new displays.MainD(rpg.getPerson()));
		}
	}

}
