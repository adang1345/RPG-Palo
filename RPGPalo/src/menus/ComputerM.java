package menus;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Computer menu. */
public class ComputerM extends Menu {

	/** Constructor: the home menu */
	public ComputerM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.HomeM(rpg));
				rpg.setDisplay(new displays.HomeD(p));
			}
		});

		buttons[1] = new JButton("Buy Stocks");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BuyStocksM(rpg));
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[2] = new JButton("Sell Stocks");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.SellStocksM(rpg));
				rpg.setDisplay(new displays.ComputerD(p));
			}
		});

		buttons[3] = new JButton("Cat Videos");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openURL("http://www.youtube.com/results?search_query=cats");
			}
		});

		buttons[4] = new JButton("Dog Videos");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openURL("http://www.youtube.com/results?search_query=dogs");
			}
		});

		buttons[5] = new JButton("Surf Web");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openURL("http://www.google.com");
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Open the URL represented by s in default browser. */
	private void openURL(String s) {
		try {
			Desktop.getDesktop().browse(new URL(s).toURI());
		} catch (Exception e) {
			JLabel l = new JLabel("Your browser failed to open.");
			new MessageDialog(rpg, "Error", l, 180, 90);
		}
	}

}
