package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Prison menu. */
public class PrisonM extends Menu {

	/** Constructor: the Prison menu */
	public PrisonM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();
		p.setBjail(true);

		buttons[0] = new JButton("Sleep");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sleep(p);
			}
		});

		buttons[1] = new JButton("Try to Escape");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escape(p);
			}
		});

		formatButtons();
		updateButtons();
	}


	/** Sleep in prison. One day of sleep results in loss of 1 hp. If hp reaches
	 * zero, then die. */
	private void sleep(Person p) {
		if (p.sleepPrison()) {
			rpg.setDisplay(new displays.PrisonD(p));
		} else {
			rpg.setDisplay(new displays.PrisonD(p));
			p.die(rpg);
		}
	}

	/** Attempt to escape the prison. */
	private void escape(Person p) {
		int a = Person.randInt(1, 7);
		if (a == 1) {
			p.newIdentity();
			JLabel l = new JLabel("<html>You successfully escape the prison. You kill someone and steal his" +
					"<br />identity, taking all his assets.");
			new MessageDialog(rpg, "Success", l, 400, 105);
			rpg.setMenu(new menus.MainM(rpg));
			rpg.setDisplay(new displays.MainD(p));
			return;
		}

		int b = Person.randInt(1, 15);
		JLabel l;
		switch (b) {
		case 1:
			l = new JLabel("<html>You use toilet paper to construct a rope. You climb onto the roof and<br />"
					+ "attempt to lower yourself down. You fall, and security guards return<br />"
					+ "you to your cell.</html>");
			new MessageDialog(rpg, "Failed", l, 410, 120);
			break;
		case 2:
			l = new JLabel("<html>When the guards are distracted, you run out the door and into the woods.<br />"
					+ "A wild animal attacks. The next thing you see is the wall of your cell.</html>");
			new MessageDialog(rpg, "Failed", l, 430, 100);
			break;
		case 3:
			l = new JLabel("The guard catches you and shoots pepper spray at you.");
			new MessageDialog(rpg, "Failed", l, 330, 90);
			break;
		case 4:
			l = new JLabel("<html>You jump into the lake to swim to freedom, but an alligator attacks.<br />"
					+ "Guards see you scream for help.</html>");
			new MessageDialog(rpg, "Failed", l, 400, 105);
			break;
		case 5:
			l = new JLabel("<html>You figure that the only way to escape is through death. You swallow<br />"
					+ "4,000 pills, each intended to reduce your HP by 5,000. However, the pills<br />"
					+ "fail, and you suffer only a loss of 10 HP.</html>");
			new MessageDialog(rpg, "Failed", l, 425, 120);
			break;
		case 6:
			l = new JLabel("You are shot in the leg while climbing the fence.");
			new MessageDialog(rpg, "Failed", l, 290, 90);
			break;
		case 7:
			l = new JLabel("<html>You starve yourself to get thin and try to squeeze through the bars of<br />"
					+ "your cell. You get stuck halfway, and a guard works for 4 hours to get<br />"
					+ "you back in.</html>");
			new MessageDialog(rpg, "Failed", l, 420, 120);
			break;
		case 8:
			l = new JLabel("<html>You volunteer to participate in a medical research study, hoping that the<br />"
					+ "prison's medical facility has lower security. Unfortunately, the<br />"
					+ "researchers accidentally give you rat poison instead of the new<br />"
					+ "experimental HIV vaccine.</html>");
			new MessageDialog(rpg, "Failed", l, 430, 135);
			break;
		case 9:
			l = new JLabel("<html>All your muscles cramp up when you make a run for the exit. You<br />"
					+ "collapse onto the floor in pain.</html>");
			new MessageDialog(rpg, "Failed", l, 390, 105);
			break;
		case 10:
			l = new JLabel("<html>Figuring death is the only escape, you commit suicide. The doctors<br />"
					+ "resurrect you because they believe you have not served a long enough<br />"
					+ "sentence.</html>");
			new MessageDialog(rpg, "Failed", l, 420, 120);
			break;
		case 11:
			l = new JLabel("<html>You grow your hair until it is 70 feet long. You make a rope with it and<br />"
					+ "climb down the roof. Unfortunately, the hair breaks, and guards hear you<br />"
					+ "fall.</html>");
			new MessageDialog(rpg, "Failed", l, 430, 120);
			break;
		case 12:
			l = new JLabel("<html>You dig a hole in the wall. As you are crawling through it, the hole caves<br />"
					+ "in and traps you. You scream for help, and help comes.</html>");
			new MessageDialog(rpg, "Failed", l, 425, 105);
			break;
		case 13:
			l = new JLabel("You wake up in a strange unlocked room. You exit and find yourself free!");
			new MessageDialog(rpg, "Escape", l, 425, 90);
			l = new JLabel("<html>But then you discover that you were simply a part of a cruel psychological<br />"
					+ "experiment that the doctors ran to determine whether you have thoughts<br />"
					+ "of escaping. The doctors regret that you cannot be given a longer prison<br />"
					+ "sentence for your behavior.</html>");
			new MessageDialog(rpg, "Failed", l, 430, 135);
			break;
		case 14:
			l = new JLabel("<html>You take a drug that removes your pulse. Guards think you are dead, so<br />"
					+ "they set your body on fire before you get a chance to escape. You<br />"
					+ "scream and are discovered to be alive.</html>");
			new MessageDialog(rpg, "Failed", l, 420, 120);
			break;
		case 15:
			l = new JLabel("<html>You sneak into a time machine and escape into the past. However, you<br />"
					+ "accidentally kill your great-great-great-great-grandfather. A paradox<br />"
					+ "occurs, and somehow you end up back in jail. Even I don't understand<br />"
					+ "how this happened.</html>");
			new MessageDialog(rpg, "Failed", l, 420, 135);
		}
		p.setHp(Math.max(0, p.getHp()-10));
		p.setDay(p.getDay()+1);
		rpg.setDisplay(new displays.PrisonD(p));
		if (p.getHp() == 0) p.die(rpg);
	}

}
