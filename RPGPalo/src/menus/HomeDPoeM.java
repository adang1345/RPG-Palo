package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Home D-Poe menu. */
public class HomeDPoeM extends Menu {

	/** Constructor: the Home D-Poe menu */
	public HomeDPoeM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MallM(rpg));
				rpg.setDisplay(new displays.MallD(p));
			}
		});

		buttons[1] = new JButton("Upgrade Home");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upgradeHome(p);
				rpg.setDisplay(new displays.HomeDPoeD(p));
			}
		});
		buttons[1].setToolTipText("Upgrade home for $" + commas(Person.HOME_UPGRADE_PRICE) +
				". Upgrading allows you to buy more supplies.");

		buttons[2] = new JButton("Shop Supplies");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopSupplies(p);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Upgrade home if there exist another upgrade and enough money. */
	private void upgradeHome(Person p) {
		if (p.getHupgrade() == 3) {
			JLabel l = new JLabel("No more upgrades are available.");
			new MessageDialog(rpg, "Error", l, 200, 90);
			return;
		} else if (p.getMoney() < Person.HOME_UPGRADE_PRICE) {
			JLabel l = new JLabel("Not enough money.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}
		p.setHupgrade(p.getHupgrade() + 1);
		p.setMoney(p.getMoney() - Person.HOME_UPGRADE_PRICE);
	}


	/** If home level is 0, then give error saying that no supplies are
	 * available. Otherwise, go to Supplies menu and display. */
	private void shopSupplies(Person p) {
		if (p.getHupgrade() == 0) {
			JLabel l = new JLabel("No supplies are available. You must upgrade your home first.");
			new MessageDialog(rpg, "No Supplies", l, 360, 90);
			return;
		}
		rpg.setMenu(new menus.SuppliesM(rpg));
		rpg.setDisplay(new displays.SuppliesD(p));
	}

}
