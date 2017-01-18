package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Mall menu. */
public class MallM extends Menu {

	/** Constructor: the Mall menu */
	public MallM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Leave");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Weapons");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.WeaponsM(rpg));
				rpg.setDisplay(new displays.WeaponsD(p));
			}
		});

		buttons[2] = new JButton("Fat Foods");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FatFoodsM(rpg));
				rpg.setDisplay(new displays.FatFoodsD(p));
			}
		});

		buttons[3] = new JButton("Home D-Poe");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.HomeDPoeM(rpg));
				rpg.setDisplay(new displays.HomeDPoeD(p));
			}
		});

		buttons[4] = new JButton("Bar");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.BarM(rpg));
				rpg.setDisplay(new displays.BarD(p));
			}
		});

		buttons[5] = new JButton("Casino");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.CasinoM(rpg));
				rpg.setDisplay(new displays.CasinoD(p));
			}
		});

		buttons[6] = new JButton("Rob");
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rob(p);
			}
		});
		buttons[6].setToolTipText("Try to rob the mall.");

		formatButtons();
		updateButtons();
	}

	/** Rob the mall if there is enough time and after user confirmation if no
	 * loaded gun is owned. */
	private void rob(Person p) {
		if (p.getHour() < 10) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		} else if (p.getGun()==0 || p.getBullet()==0) {
			JLabel l = new JLabel("You do not have a loaded gun! Are you sure you want to rob the mall?");
			ChoiceDialog c = new ChoiceDialog(rpg, "No Loaded Gun", l, "Yes", "No", 405, 90);
			if (!c.getChoice()) return;
		}
		rpg.setMenu(new menus.RobM(rpg));
		rpg.setDisplay(new displays.RobD(p));
	}

}
