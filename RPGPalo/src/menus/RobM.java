package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Display;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the Rob menu. */
public class RobM extends Menu {

	/** Constructor: the Rob menu */
	public RobM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.MallM(rpg));
				rpg.setDisplay(new displays.MallD(p));
			}
		});

		buttons[1] = new JButton("Weapons");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weapons(p);
			}
		});

		buttons[2] = new JButton("Fat Foods");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fatFoods(p);
			}
		});

		buttons[3] = new JButton("Home D-Poe");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeDPoe(p);
			}
		});

		buttons[4] = new JButton("Bar");
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bar(p);
			}
		});

		buttons[5] = new JButton("Casino");
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casino(p);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Rob the weapons store. */
	private void weapons(Person p) {
		if (p.getGun()==0 || p.getBullet()==0) {
			JLabel l = new JLabel("<html>With great foolishness, you try to rob a gun store without a loaded gun.<br />"
					+ "The owner shoots and kills you.</html>");
			new MessageDialog(rpg, "Failed", l, 420, 105);
			p.die(rpg);
			return;
		} else if (p.getGun() == 1) {
			JLabel l = new JLabel("The owner whips out a Gun Level 5 and shoots at you.");
			new MessageDialog(rpg, "Rob", l, 320, 90);
			if (Person.randInt(1,5) == 1) {
				int reward = Person.randInt(1, 10000);
				l = new JLabel("Against the odds, you escape unscathed, taking $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 335, 90);
				p.setMoney(p.getMoney() + reward);
			} else {
				l = new JLabel("You are hit 6 times in the head and chest.");
				new MessageDialog(rpg, "Failed", l, 250, 90);
				p.die(rpg);
				return;
			}
		} else if (p.getGun() == 2) {
			if (Person.randInt(1,10) <= 3) {
				int reward = Person.randInt(1, 10000);
				JLabel l = new JLabel("<html>Against the odds, the owner does not see you. You get away with<br />"
						+ "$" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 380, 105);
				p.setMoney(p.getMoney() + reward);
			} else {
				int losehp = Person.randInt(1, 500);
				JLabel l = new JLabel("The owner sees you and shoots. You lose " + losehp + " HP.");
				new MessageDialog(rpg, "Failed", l, 295, 90);
				p.setHp(Math.max(p.getHp()-losehp, 0));
				if (p.getHp() == 0) {
					p.die(rpg);
					return;
				} else {
					l = new JLabel("You get away with your life but no cash.");
					new MessageDialog(rpg, "Failed", l, 240, 90);
				}
			}
		} else if (p.getGun() == 3) {
			if (Person.randInt(1, 20) <= 9) {
				int reward = Person.randInt(1, 30000);
				JLabel l = new JLabel("You get away with $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 165, 90);
				p.setMoney(p.getMoney() + reward);
			} else {
				int losehp = Person.randInt(1, 300);
				JLabel l = new JLabel("The owner shoots. You lose " + losehp + " HP.");
				new MessageDialog(rpg, "Failed", l, 215, 90);
				p.setHp(Math.max(p.getHp()-losehp, 0));
				if (p.getHp() == 0) {
					p.die(rpg);
					return;
				}
			}
		} else if (p.getGun() == 4) {
			if (Person.randInt(1, 2) == 1) {
				int reward = Person.randInt(1, 50000);
				JLabel l = new JLabel("You kill the owner and steal $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 220, 90);
				p.setMoney(p.getMoney() + reward);
			} else {
				int losehp = Person.randInt(1, 100);
				JLabel l = new JLabel("<html>You have a strong gun, but the owner of the store has a better one. You<br />" +
						"lose " + losehp + " HP.</html>");
				new MessageDialog(rpg, "Failed", l, 420, 105);
				p.setHp(Math.max(p.getHp()-losehp, 0));
				if (p.getHp() == 0) {
					p.die(rpg);
					return;
				}
			}
		} else { // gun level is 5
			if (Person.randInt(1, 5) <= 3) {
				int reward = Person.randInt(1, 100000);
				JLabel l = new JLabel("You kill the owner and steal $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 220, 90);
				p.setMoney(p.getMoney() + reward);
			} else {
				JLabel l = new JLabel("The owner takes out the legendary Gun Level 6 and kills you.");
				new MessageDialog(rpg, "Failed", l, 355, 90);
				p.die(rpg);
				return;
			}
		}
		p.setBullet(p.getBullet() - 1);
		p.setHour(p.getHour() - 10);
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(p));
	}

	/** Rob the Fat Foods restaurant. */
	private void fatFoods(Person p) {
		int reward = 0;
		boolean failed = false;
		if (p.getGun()==0 || p.getBullet()==0) {
			if (Person.randInt(1, 4) == 1) {
				reward = Person.randInt(1, 100);
				JLabel l = new JLabel("You disguise yourself as a worker and take $" + reward + ".");
				new MessageDialog(rpg, "Success", l, 290, 90);
			} else failed = true;
		} else if (p.getGun() == 1) {
			if (Person.randInt(1, 10) <= 3) {
				reward = Person.randInt(1, 200);
				JLabel l = new JLabel("You shoot everyone and take $" + reward + ".");
				new MessageDialog(rpg, "Success", l, 210, 90);
			} else failed = true;
		} else if (p.getGun() == 2) {
			if (Person.randInt(1, 2) == 1) {
				reward = Person.randInt(1, 400);
				JLabel l = new JLabel("You get away with $" + reward + ".");
				new MessageDialog(rpg, "Success", l, 150, 90);
			} else failed = true;
		} else if (p.getGun() == 3) {
			if (Person.randInt(1, 3) <= 2) {
				reward = Person.randInt(1, 500);
				JLabel l = new JLabel("You get away with $" + reward + ".");
				new MessageDialog(rpg, "Success", l, 150, 90);
			} else failed = true;
		} else if (p.getGun() == 4) {
			if (Person.randInt(1, 4) >= 3) {
				reward = Person.randInt(1, 700);
				JLabel l = new JLabel("You get away with $" + reward + ".");
				new MessageDialog(rpg, "Success", l, 150, 90);
			} else failed = true;
		} else { // gun level is 5
			if (Person.randInt(1, 5) != 5) {
				reward = Person.randInt(1, 1000);
				JLabel l = new JLabel("You get away with $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 155, 90);
			} else failed = true;
		}
		if (p.getBullet() > 0) p.setBullet(p.getBullet() - 1);
		if (!failed) {
			p.setMoney(p.getMoney() + reward);
			p.setHour(p.getHour() - 10);
		} else {
			JLabel l = new JLabel("You got caught! You go to jail for 5 days.");
			new MessageDialog(rpg, "Failed", l, 240, 90);
			int sleepCode = p.sleepJail(5);
			if (sleepCode == 1) {
				p.loanPenalty(rpg);
				return;
			}
		}
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(p));
	}

	/** Rob Home D-Poe. */
	private void homeDPoe(Person p) {
		int reward = 0;
		boolean failed = false;
		if (p.getGun()==0 || p.getBullet()==0) {
			if (Person.randInt(1, 7) == 1) {
				reward = Person.randInt(1, 500);
				JLabel l = new JLabel("You apply for a job at the store and steal $" + reward + ".");
				new MessageDialog(rpg, "Success", l, 275, 90);
			} else {
				failed = true;
				int fakeReward = Person.randInt(1, 100000);
				JLabel l = new JLabel("<html>You tell the cashier you want to buy a bed. When she opens the cash<br />"
						+ "register, you knock her out and take $" + commas(fakeReward) + ".</html>");
				new MessageDialog(rpg, "Rob", l, 400, 105);
				l = new JLabel("<html>However, you forget your wallet, which police use to track you down.<br />"
						+ "Police take all your money, and you go to jail for 10 days.</html>");
				new MessageDialog(rpg, "Failed", l, 405, 105);
				p.setMoney(0);
				p.setBank(0);
			}
		} else if (p.getGun() == 1) {
			if (Person.randInt(1, 6) == 1) {
				reward = Person.randInt(1, 1000);
				JLabel l = new JLabel("You shoot everyone in the store and take $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 280, 90);
			} else {
				failed = true;
				JLabel l = new JLabel("The store owner has a better gun than you. You are shot in the heart.");
				new MessageDialog(rpg, "Failed", l, 400, 90);
				p.die(rpg);
				return;
			}
		} else if (p.getGun() == 2) {
			if (Person.randInt(1, 5) == 1) {
				reward = Person.randInt(1, 1000);
				JLabel l = new JLabel("<html>Covered by your 7-ft beard, you enter without fear of security cameras.<br />"
						+ "You get away with $" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 420, 105);
			} else {
				failed = true;
				JLabel l = new JLabel("<html>You lose consciousness after you trip and fall down the basement stairs.<br />"
						+ "You wake up to a 10-day jail sentence.</html>");
				new MessageDialog(rpg, "Failed", l, 430, 105);
			}
		} else if (p.getGun() == 3) {
			if (Person.randInt(1, 4) == 1) {
				reward = Person.randInt(1, 1000);
				JLabel l = new JLabel("<html>At night, you enter the store and shoot a 10-pound bullet at the cash<br />"
						+ "register. It smashes open, and you take $" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 400, 105);
			} else {
				failed = true;
				JLabel l = new JLabel("<html>At night, you enter the store. Security cameras record your actions, and<br />"
						+ "you are arrested. You go to jail for 10 days.</html>");
				new MessageDialog(rpg, "Failed", l, 420, 105);
			}
		} else if (p.getGun() == 4) {
			if (Person.randInt(1, 3) == 1) {
				reward = Person.randInt(1, 1000);
				JLabel l = new JLabel("You threaten everyone with a gun and take $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 300, 90);
			} else {
				failed = true;
				JLabel l = new JLabel("You got caught! You go to jail for 10 days.");
				new MessageDialog(rpg, "Failed", l, 250, 90);
			}
		} else { // gun level is 5
			if (Person.randInt(1, 2) == 1) {
				reward = Person.randInt(1, 1000);
				JLabel l = new JLabel("<html>Your gun is so powerful that you kill everyone in a 5,000-mile radius with<br />"
						+ "one shot. You take $" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 420, 105);
			} else {
				failed = true;
				JLabel l = new JLabel("Your gun is too hard to handle. You accidentally kill yourself.");
				new MessageDialog(rpg, "Failed", l, 350, 90);
				p.die(rpg);
				return;
			}
		}
		if (p.getBullet() > 0) p.setBullet(p.getBullet() - 1);
		if (!failed) {
			p.setMoney(p.getMoney() + reward);
			p.setHour(p.getHour() - 10);
		} else {
			int sleepCode = p.sleepJail(10);
			if (sleepCode == 1) {
				p.loanPenalty(rpg);
				return;
			}
		}
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(p));
	}

	/** Rob the bar. */
	private void bar(Person p) {
		if (p.getGun()==0 || p.getBullet()==0) {
			JLabel l = new JLabel("Everyone in the bar has a loaded gun except you.");
			new MessageDialog(rpg, "Failed", l, 300, 90);
			p.die(rpg);
			return;
		} else if (p.getGun() == 1) {
			if (Person.randInt(1, 10) == 1) {
				int reward = Person.randInt(1, 100000);
				JLabel l = new JLabel("<html>You get lucky and shoot some people with your inferior gun. You steal<br />"
						+ "$" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 410, 105);
				p.setMoney(p.getMoney() + reward);
			} else {
				JLabel l = new JLabel("The bartender shoots you.");
				new MessageDialog(rpg, "Failed", l, 170, 90);
				p.die(rpg);
				return;
			}
		} else if (p.getGun() == 2) {
			if (Person.randInt(1, 8) == 1) {
				int reward = Person.randInt(1, 100000);
				JLabel l = new JLabel("<html>You sneak behind the bartender 15 minutes past closing time and shoot.<br />"
						+ "You take $" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 420, 105);
				p.setMoney(p.getMoney() + reward);
			} else {
				JLabel l = new JLabel("<html>You show your gun. Remember that having a gun is an invitation for<br />"
						+ "others to shoot you.</html>");
				new MessageDialog(rpg, "Failed", l, 400, 105);
				p.die(rpg);
				return;
			}
		} else if (p.getGun() == 3) {
			if (Person.randInt(1, 7) == 1) {
				int reward = Person.randInt(1, 100000);
				JLabel l = new JLabel("<html>After fighting the bartender and losing half your health, you finally win<br />"
						+ "and take $" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 410, 105);
				p.setMoney(p.getMoney() + reward);
				p.setHp(p.getHp()%2==0 ? p.getHp()/2 : p.getHp()/2+1);
			} else {
				JLabel l = new JLabel("You are caught and endure execution by firing squad.");
				new MessageDialog(rpg, "Failed", l, 320, 90);
				p.die(rpg);
				return;
			}
		} else if (p.getGun() == 4) {
			if (Person.randInt(1, 6) == 1) {
				int reward = Person.randInt(1, 100000);
				JLabel l = new JLabel("You hide your face with a ski mask and take $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 315, 90);
				p.setMoney(p.getMoney() + reward);
			} else {
				JLabel l = new JLabel("You are shot 7 times in the heart.");
				new MessageDialog(rpg, "Failed", l, 210, 90);
				p.die(rpg);
				return;
			}
		} else { // gun level is 5
			if (Person.randInt(1, 4) == 1) {
				int reward = Person.randInt(1, 100000);
				JLabel l = new JLabel("You shoot explosive bullets and take $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 275, 90);
				p.setMoney(p.getMoney() + reward);
			} else {
				JLabel l = new JLabel("<html>Everyone in the bar points a legendary Gun Level 6 at you. You ask where<br />"
						+ "they obtained such a weapon, and they answer with \"Die.\"</html>");
				new MessageDialog(rpg, "Failed", l, 425, 105);
				p.die(rpg);
				return;
			}
		}
		p.setBullet(p.getBullet() - 1);
		p.setHour(p.getHour() - 10);
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(p));
	}

	/** Rob the casino. */
	private void casino(Person p) {
		if (p.getGun()==0 || p.getBullet()==0) {
			JLabel l = new JLabel("<html>Casino managers call the police, and you are arrested. After going to<br />"
					+ "court, you are sentenced to 90 days in prison. Additionally, all your<br />"
					+ "cash is confiscated.</html>");
			new MessageDialog(rpg, "Failed", l, 410, 120);
			p.setMoney(0);
			int sleepCode = p.sleepJail(90);
			if (sleepCode == 1) {
				p.loanPenalty(rpg);
				return;
			}
		} else if (p.getGun() == 1) {
			if (Person.randInt(1, 100) == 1) {
				int reward = Person.randInt(1, 100000) + 100000;
				JLabel l = new JLabel("<html>You kill a security guard and are arrested. You play all your cash, drugs,<br />"
						+ "and stocks for a good lawyer. After 3 days, you frame someone else<br />"
						+ "and get $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 420, 120);
				p.setMoney(reward);
				p.setBaij(0);
				p.setBdce(0);
				p.setBnid(0);
				p.setBabc(0);
				p.setHeroin(0);
				p.setCrack(0);
				p.setBeer(0);
				int sleepCode = p.sleepJail(3);
				if (sleepCode == 1) {
					p.loanPenalty(rpg);
					return;
				}
			} else {
				JLabel l = new JLabel("You are sentenced to 3 years in prison for 1st degree murder.");
				new MessageDialog(rpg, "Failed", l, 370, 90);
				int sleepCode = p.sleepJail(1095);
				if (sleepCode == 1) {
					p.loanPenalty(rpg);
					return;
				}
			}
		} else if (p.getGun() == 2) {
			if (Person.randInt(1, 80) == 1) {
				int reward = Person.randInt(1, 1000000) + 1000000;
				JLabel l = new JLabel("You nuke the casino and take $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 250, 90);
				p.setMoney(p.getMoney() + reward);
				p.setHour(p.getHour() - 10);
				p.setBullet(0);
			} else {
				JLabel l = new JLabel("<html>You try to bomb the casino, but the bomb destroys only 20% of the<br />"
						+ "building. You are found guilty and sentenced to 5 years in prison.</html>");
				new MessageDialog(rpg, "Failed", l, 390, 105);
				int sleepCode = p.sleepJail(1826);
				if (sleepCode == 1) {
					p.loanPenalty(rpg);
					return;
				}
			}
		} else if (p.getGun() == 3) {
			if (Person.randInt(1, 50) == 1) {
				int reward = Person.randInt(1, 5000000) + 5000000;
				JLabel l = new JLabel("With your karate skills, you take $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 265, 90);
				p.setMoney(p.getMoney() + reward);
				p.setHour(p.getHour() - 10);
			} else {
				JLabel l = new JLabel("You are sentenced to 10 years in prison for aggravated robbery.");
				new MessageDialog(rpg, "Failed", l, 380, 90);
				int sleepCode = p.sleepJail(3652);
				if (sleepCode == 1) {
					p.loanPenalty(rpg);
					return;
				}
			}
		} else if (p.getGun() == 4) {
			if (Person.randInt(1, 40) == 1) {
				int reward = Person.randInt(1, 10000000) + 10000000;
				JLabel l = new JLabel("<html>The security guards beg for mercy when they see you. They let you take<br />"
						+ "$" + commas(reward) + ".</html>");
				new MessageDialog(rpg, "Success", l, 420, 105);
				p.setMoney(p.getMoney() + reward);
				p.setHour(p.getHour() - 10);
			} else {
				JLabel l = new JLabel("<html>The casino recently added a new security feature: a guardian dragon.<br />"
						+ "You are caught easily and sentenced to 40 years in prison.</html>");
				new MessageDialog(rpg, "Failed", l, 410, 105);
				int sleepCode = p.sleepJail(14610);
				if (sleepCode == 1) {
					p.loanPenalty(rpg);
					return;
				}
			}
		} else { // gun level is 5
			if (Person.randInt(1, 20) == 1) {
				int reward = Person.randInt(1, 1000000000) + 1000000000;
				JLabel l = new JLabel("Wielding your powerful gun, you take $" + commas(reward) + ".");
				new MessageDialog(rpg, "Success", l, 315, 90);
				p.setMoney(p.getMoney() + reward);
				p.setHour(p.getHour() - 10);
			} else {
				JLabel l = new JLabel("The guards catch you and sentence you to eternity in the City of Nothing.");
				new MessageDialog(rpg, "Failed", l, 425, 90);
				rpg.setMenu(new menus.CityOfNothingM(rpg));
				rpg.setDisplay(new Display(p) {});
				return;
			}
		}
		if (p.getBullet() > 0) p.setBullet(p.getBullet() - 1);
		rpg.setMenu(new menus.MainM(rpg));
		rpg.setDisplay(new displays.MainD(p));
	}

}
