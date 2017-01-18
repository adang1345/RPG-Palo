package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.RPG;
import user.Person;

/** A class for the Main menu. */
public class MainM extends Menu {

	/** Constructor: the Main menu */
	public MainM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("File");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FileM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Home");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.HomeM(rpg));
				rpg.setDisplay(new displays.HomeD(p));
			}
		});

		buttons[2] = new JButton("Bank");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BankM(rpg));
				rpg.setDisplay(new displays.BankD(p));
			}
		});

		buttons[3] = new JButton("Mall");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MallM(rpg));
				rpg.setDisplay(new displays.MallD(p));
			}
		});

		buttons[4] = new JButton("PaloTech");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.PaloTechM(rpg));
				rpg.setDisplay(new displays.PaloTechD(p));
			}
		});

		buttons[5] = new JButton("College");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.CollegeM(rpg));
				rpg.setDisplay(new displays.CollegeD(p));
			}
		});

		buttons[6] = new JButton("Gym");
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.GymM(rpg));
				rpg.setDisplay(new displays.GymD(p));
			}
		});

		buttons[7] = new JButton("Bus");
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BusM(rpg));
				rpg.setDisplay(new displays.BusD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

}
