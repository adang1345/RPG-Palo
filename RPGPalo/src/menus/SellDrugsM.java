package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.ChoiceDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Sell Drugs menu. */
public class SellDrugsM extends Menu {

	/** Constructor: the Fat Foods menu */
	public SellDrugsM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.DDClubM(rpg));
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});

		buttons[1] = new JButton("Sell Heroin");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellHeroin(p);
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});
		buttons[1].setToolTipText("Take a flight to a foreign country to sell all your heroin.");

		buttons[2] = new JButton("Sell Crack");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellCrack(p);
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});
		buttons[2].setToolTipText("Take a flight to a foreign country to sell all your crack.");

		buttons[3] = new JButton("Sell Beer");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellBeer(p);
				rpg.setDisplay(new displays.DDClubD(p));
			}
		});
		buttons[3].setToolTipText("Take a flight to a foreign country to sell all your beer.");

		formatButtons();
		updateButtons();
	}

	/** Sell a user-inputted amount of heroin if possible. */
	private void sellHeroin(Person p) {
		// quit if hour is less than 10
		if (p.getHour() < 10) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// generate buyer's offer and ask whether user accepts
		// if no heroin is owned, note that instead
		String location = Person.DRUG_PLACES[Person.randInt(0,Person.DRUG_PLACES.length-1)];
		if (p.getHeroin() == 0) {
			JLabel l = new JLabel("<html>You arrive at " + location + " with no heroin to sell. Unless you really enjoy<br />"
					+ "long flights, remember to stock up before boarding the plane.");
			new MessageDialog(rpg, "Sell Heroin", l, 420, 105);
			p.setHour(0);
			return;
		}
		long offer = Person.randLong(0, 750*p.getHeroin());
		JLabel l = new JLabel("<html>You go to " + location + " and are offered<br />$" + commas(offer) +
				"<br />for all your heroin. Do you accept?</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "Sell Heroin", l, "Yes", "No", 230, 120);
		if (!c.getChoice()) {
			p.setHour(0);
			return;
		}

		// update fields
		p.setHour(0);
		p.setHeroin(0);
		p.setMoney(p.getMoney() + offer);
	}

	/** Sell a user-inputted amount of crack if possible. */
	private void sellCrack(Person p) {
		// quit if hour is less than 10
		if (p.getHour() < 10) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// generate buyer's offer and ask whether user accepts
		// if no crack is owned, note that instead
		String location = Person.DRUG_PLACES[Person.randInt(0,Person.DRUG_PLACES.length-1)];
		if (p.getCrack() == 0) {
			JLabel l = new JLabel("<html>You arrive at " + location + " with no crack to sell. Unless you really enjoy<br />"
					+ "long flights, remember to stock up before boarding the plane.");
			new MessageDialog(rpg, "Sell Crack", l, 410, 105);
			p.setHour(0);
			return;
		}
		long offer = Person.randLong(0, 1250*p.getCrack());
		JLabel l = new JLabel("<html>You go to " + location + " and are offered<br />$" + commas(offer) +
				"<br />for all your crack. Do you accept?</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "Sell Crack", l, "Yes", "No", 230, 120);
		if (!c.getChoice()) {
			p.setHour(0);
			return;
		}

		// update fields
		p.setHour(0);
		p.setCrack(0);
		p.setMoney(p.getMoney() + offer);
	}

	/** Sell a user-inputted amount of beer if possible. */
	private void sellBeer(Person p) {
		// quit if hour is less than 10
		if (p.getHour() < 10) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// generate buyer's offer and ask whether user accepts
		// if no beer is owned, note that instead
		String location = Person.DRUG_PLACES[Person.randInt(0,Person.DRUG_PLACES.length-1)];
		if (p.getBeer() == 0) {
			JLabel l = new JLabel("<html>You arrive at " + location + " with no beer to sell. Unless you really enjoy<br />"
					+ "long flights, remember to stock up before boarding the plane.");
			new MessageDialog(rpg, "Sell Beer", l, 400, 105);
			p.setHour(0);
			return;
		}
		long offer = Person.randLong(0, 125*p.getBeer());
		JLabel l = new JLabel("<html>You go to " + location + " and are offered<br />$" + commas(offer) +
				"<br />for all your beer. Do you accept?</html>");
		ChoiceDialog c = new ChoiceDialog(rpg, "Sell Beer", l, "Yes", "No", 230, 120);
		if (!c.getChoice()) {
			p.setHour(0);
			return;
		}

		// update fields
		p.setHour(0);
		p.setBeer(0);
		p.setMoney(p.getMoney() + offer);
	}

}
