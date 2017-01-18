package menus;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Load menu. */
public class LoadM extends Menu {

	/** Constructor: the Load menu
	 * fromStart is whether this Menu is being created from the Start menu. */
	public LoadM(RPG rpg, boolean fromStart) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fromStart) {
					rpg.setMenu(new menus.StartM(rpg));
					rpg.setDisplay(new displays.StartD(p));
				} else {
					rpg.setMenu(new menus.MainM(rpg));
					rpg.setDisplay(new displays.MainD(p));
				}
			}
		});

		buttons[1] = new JButton("Slot 1");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(1);
			}
		});

		buttons[2] = new JButton("Slot 2");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(2);
			}
		});

		buttons[3] = new JButton("Slot 3");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(3);
			}
		});

		buttons[4] = new JButton("Slot 4");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(4);
			}
		});

		buttons[5] = new JButton("Slot 5");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(5);
			}
		});

		buttons[6] = new JButton("Slot 6");
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(6);
			}
		});

		buttons[7] = new JButton("Slot 7");
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(7);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Load the game from save slot i. If slot i contains no save information,
	 * then display message saying this. This procedure works by deserializing a
	 * Person array, where each element of the array represents a save slot.
	 * Save information is then extracted from the array. */
	private void load(int i) {
		// deserialize save file into Person array
		Person[] saves;
		try {
			FileInputStream fileIn = new FileInputStream(RPG.SAVE_DIRECTORY);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			saves = (Person[]) in.readObject();
			in.close();
			fileIn.close();
		} catch(Exception e) {
			JLabel l = new JLabel("<html>The save file has been corrupted. For the issue to be fixed, the game<br />"
					+ "must close.</html>");
			new MessageDialog(rpg, "Load", l, 410, 105);
			rpg.dispose();
			return;
		}
		int slotIndex = i - 1;

		// notify if slot contains nothing to load, then quit
		if (saves[slotIndex] == null) {
			JLabel l = new JLabel("This slot is empty. No saved game can be loaded from it.");
			new MessageDialog(rpg, "Load", l, 330, 90);
			return;
		}

		// load from slot and go back to main menu
		rpg.setPerson(saves[slotIndex]);
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(rpg.getPerson()));
	}

}
