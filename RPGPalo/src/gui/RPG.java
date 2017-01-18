package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import user.Person;

/** Main class for running the game. */
public class RPG extends JFrame {
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 600;
	public static final String VERSION = "1.0";
	public static final String SAVE_DIRECTORY = System.getProperty("user.home") + "/RPGPaloSave.rsav";
	public static final Color BACKGROUND_COLOR = new Color(70, 150, 200);

	private Menu menu; // The menu of buttons
	private Display display; // The text displayed below the menu
	private Person person; // The player

	/** Constructor: A new RPG game */
	public RPG() {
		setTitle("RPG Palo");
		setIconImage(new ImageIcon(getClass().getResource("/resources/emoji14.png")).getImage());
		getContentPane().setBackground(BACKGROUND_COLOR);
		person = new Person();
		menu = new menus.StartM(this);
		display = new displays.StartD(person);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(menu);
		add(display);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (menu instanceof menus.StartM) {
					dispose();
					return;
				}
				JLabel l = new JLabel("<html>Are you sure you want to quit? You will lose your progress if you did not<br />"
						+ "save.</html>");
				ChoiceDialog c = new ChoiceDialog(RPG.this, "Confirm Quit", l, "Yes", "No", 420, 105);
				if (c.getChoice()) dispose();
			}
		});

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		setVisible(true);
	}

	/** Set a new menu. */
	public void setMenu(Menu m) {
		getContentPane().removeAll();
		menu = m;
		add(m);
		add(display);
		revalidate();
		repaint();
	}

	/** Set a new display. */
	public void setDisplay(Display d) {
		getContentPane().removeAll();
		display = d;
		add(menu);
		add(d);
		revalidate();
		repaint();
	}

	/** Display about information. */
	public void about() {
		JLabel l = new JLabel("<html>RPG Palo " + VERSION + "<br />" +
				"Copyright &copy; 2016 Aohan Dang<br /><br />" +
				"This program is free software: you can redistribute it and/or modify it<br />" +
				"under the terms of the GNU General Public License as published by the<br />" +
				"Free Software Foundation, either version 3 of the License, or (at your<br />" +
				"option) any later version.<br /><br />" +
				"This program is distributed in the hope that it will be useful, but WITHOUT<br />" +
				"ANY WARRANTY; without even the implied warranty of MERCHANTABILITY<br />" +
				"or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public<br />" +
				"License for more details.<br /><br />" +
				"You should have received a copy of the GNU General Public License along<br />" +
				"with this program.  If not, see <span style=\"color:blue\">" +
				"http://www.gnu.org/licenses/</span>.<br /><br />" +
				"You may contact the developer at <span style=\"color:blue\">" +
				"adang1345@gmail.com</span>.</html>");
		new MessageDialog(this, "About RPG Palo " + VERSION, l, 430, 345);
	}

	/** Getter for the person. */
	public Person getPerson() {
		return person;
	}

	/** Setter for the person. */
	public void setPerson(Person p) {
		person = p;
	}

	/** Reset the person. */
	public void resetPerson() {
		person = new Person();
	}

	/** Start a new game. */
	public static void main(String[] args) {
		// set tooltip settings
		ToolTipManager t = ToolTipManager.sharedInstance();
		t.setInitialDelay(50);
		t.setReshowDelay(0);
		t.setDismissDelay(6000);

		// If save file is corrupt or nonexistent, generate default save file
		// with no saved games.
		try {
			FileInputStream fileIn = new FileInputStream(SAVE_DIRECTORY);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Person[] saves = (Person[]) in.readObject();
			in.close();
			fileIn.close();
			FileOutputStream fileOut = new FileOutputStream(SAVE_DIRECTORY);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(saves);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			try {
				FileOutputStream fileOut = new FileOutputStream(SAVE_DIRECTORY);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(new Person[7]);
				out.close();
				fileOut.close();
			} catch (Exception ex) {}
		}

		// start game
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RPG();
			}
		});
	}

}
