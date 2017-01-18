package menus;

import java.awt.Desktop;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Start menu. */
public class StartM extends Menu {

	/** Constructor: the Start menu */
	public StartM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("New Game");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Load Game");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.LoadM(rpg, true));
				rpg.setDisplay(new displays.LoadD(p));
			}
		});

		buttons[2] = new JButton("About");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.about();
			}
		});

		buttons[3] = new JButton("<html><p style=\"text-align:center\">Contact"
				+ "<br />Developer</p></html>");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contact();
			}
		});
		buttons[3].setToolTipText("Send an email to Aohan to offer bug reports, suggestions, or comments.");

		buttons[4] = new JButton("Quit");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Quit the program. */
	private void quit() {
		rpg.dispose();
	}

	/** Contact developer via email. */
	private void contact() {
		String s = "mailto:adang1345@gmail.com?subject=RPG Palo " + RPG.VERSION;
		s = s.replace(" ", "%20");
		try {
			Desktop.getDesktop().browse(new URL(s).toURI());
		} catch (Exception e) {
			JLabel l = new JLabel("Your browser failed to open.");
			new MessageDialog(rpg, "Error", l, 180, 90);
		}
	}

}
