package menus;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Save menu. */
public class SaveM extends Menu {

	/** Constructor: the Save menu */
	public SaveM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(p));
			}
		});

		buttons[1] = new JButton("Slot 1");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(1);
			}
		});

		buttons[2] = new JButton("Slot 2");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(2);
			}
		});

		buttons[3] = new JButton("Slot 3");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(3);
			}
		});

		buttons[4] = new JButton("Slot 4");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(4);
			}
		});

		buttons[5] = new JButton("Slot 5");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(5);
			}
		});

		buttons[6] = new JButton("Slot 6");
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(6);
			}
		});

		buttons[7] = new JButton("Slot 7");
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(7);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Save the game to save slot i. If slot i already contains a saved game,
	 * ask for a confirmation before overwriting. This procedure works by
	 * deserializing a Person array, where each element of the array represents
	 * a save slot. The Person array is then updated and reserialized. */
	private void save(int i) {
		// deserialize save file into Person array
		Person[] saves;
		try {
			FileInputStream fileIn = new FileInputStream(RPG.SAVE_DIRECTORY);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			saves = (Person[]) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			JLabel l = new JLabel("<html>The save file has been corrupted. For the issue to be fixed, the game<br />"
					+ "must close.</html>");
			new MessageDialog(rpg, "Save", l, 410, 105);
			rpg.dispose();
			return;
		}
		int slotIndex = i - 1;

		// confirm overwrite if save slot is already filled
		if (saves[slotIndex] != null) {
			JLabel l = new JLabel("This slot already contains a save state. Do you want to overwrite it?");
			ChoiceDialog c = new ChoiceDialog(rpg, "Confirm Overwrite", l, "Yes", "No", 395, 90);
			if (!c.getChoice()) return;
		}

		// save in slot and go back to main menu
		saves[slotIndex] = rpg.getPerson();
		try {
			FileOutputStream fileOut = new FileOutputStream(RPG.SAVE_DIRECTORY);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(saves);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			JLabel l = new JLabel("<html>The save file has been corrupted. For the issue to be fixed, the game<br />"
					+ "must close.</html>");
			new MessageDialog(rpg, "Save", l, 410, 105);
			rpg.dispose();
			return;
		}
		JLabel l = new JLabel("Save was successful.");
		new MessageDialog(rpg, "Save", l, 110, 90);
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(rpg.getPerson()));
	}

}
