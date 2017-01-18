package menus;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Delete menu. */
public class DeleteM extends Menu {

	/** Constructor: the Delete menu */
	public DeleteM(RPG rpg) {
		super(rpg);

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MainM(rpg));
				rpg.setDisplay(new displays.MainD(rpg.getPerson()));
			}
		});

		buttons[1] = new JButton("Slot 1");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(1);
			}
		});

		buttons[2] = new JButton("Slot 2");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(2);
			}
		});

		buttons[3] = new JButton("Slot 3");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(3);
			}
		});

		buttons[4] = new JButton("Slot 4");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(4);
			}
		});

		buttons[5] = new JButton("Slot 5");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(5);
			}
		});

		buttons[6] = new JButton("Slot 6");
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(6);
			}
		});

		buttons[7] = new JButton("Slot 7");
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(7);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Delete the game saved in slot i. If slot i contains a saved game,
	 * ask for a confirmation before overwriting. Otherwise, give message
	 * saying that there is nothing to delete. */
	private void delete(int i) {
		// deserialize save file into Person array
		Person[] saves;
		try {
			FileInputStream fileIn = new FileInputStream(RPG.SAVE_DIRECTORY);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			saves = (Person[]) in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException | ClassNotFoundException e) {
			throw new RuntimeException("Delete failed.");
		}
		int slotIndex = i - 1;

		// if slot selected has no saved gamed, give message saying so and quit
		if (saves[slotIndex] == null) {
			JLabel l = new JLabel("This slot does not contain a saved game.");
			new MessageDialog(rpg, "Error", l, 245, 90);
			return;
		}

		// confirm deletion
		JLabel l = new JLabel("<html>Are you sure you want to delete this saved game? This action cannot be<br />"
				+ "undone.</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "Confirm Delete", l, "Yes", "No", 420, 105);
		if (!c.getChoice()) return;

		// delete game in slot and go back to main menu
		saves[slotIndex] = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(RPG.SAVE_DIRECTORY);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(saves);
			out.close();
			fileOut.close();
		} catch(IOException e) {
			throw new RuntimeException("Delete failed.");
		}
		l = new JLabel("Delete was successful.");
		new MessageDialog(rpg, "Delete", l, 150, 90);
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(rpg.getPerson()));
	}

}
