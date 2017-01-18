package menus;

import java.awt.event.*;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

/** A class for the TV menu. */
public class TvM extends Menu {

	/** Constructor: the TV menu */
	public TvM(RPG rpg) {
		super(rpg);
		Person p = rpg.getPerson();

		buttons[0] = new JButton("Back");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.HomeM(rpg));
				rpg.setDisplay(new displays.HomeD(p));
			}
		});

		buttons[1] = new JButton("News");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				watchNews(p);
				rpg.setDisplay(new displays.TvD(p));
			}
		});

		buttons[2] = new JButton("Fitness");
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpg.setMenu(new menus.FitnessM(rpg));
				rpg.setDisplay(new displays.FitnessD(p));
			}
		});

		buttons[3] = new JButton("Cartoons");
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				watchCartoons(p);
				rpg.setDisplay(new displays.TvD(p));
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Watch the news if there is enough time. */
	private void watchNews(Person p) {
		// say there is not enough time if that is the case, and quit
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// display the news story
		int n = Person.randInt(1, 62);
		JLabel l;
		switch (n) {
		case 1:
			l = new JLabel("<html>Yesterday, a new law was passed requiring car drivers to ignore<br />"
					+ "pedestrians. The death rate by car crashes has climbed 6,500%.</html>");
			new MessageDialog(rpg, "News", l, 390, 105);
			break;
		case 2:
			l = new JLabel("<html>Physicists recently amended the Physics Constitution. Article 316 was<br />"
					+ "repealed. The Law of Universal Gravitation is no longer in effect.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 3:
			l = new JLabel("<html>A hurricane is approaching. Please evacuate. Or not. I am a news story,<br />"
					+ "not your mother.</html>");
			new MessageDialog(rpg, "News", l, 425, 105);
			break;
		case 4:
			l = new JLabel("There is currently no news.");
			new MessageDialog(rpg, "News", l, 170, 90);
			break;
		case 5:
			l = new JLabel("Physicists will turn off gravity today.");
			new MessageDialog(rpg, "News", l, 220, 90);
			break;
		case 6:
			l = new JLabel("<html>Four hours ago, someone robbed the bank. If you are a witness, contact<br />"
					+ "the local police.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 7:
			l = new JLabel("<html>TORNADO WARNING<br />"
					+ "Stay in the basement.<br />"
					+ "(But a better choice would be to face the tornado and conquer it.)</html>");
			new MessageDialog(rpg, "News", l, 380, 120);
			break;
		case 8:
			l = new JLabel("A mass murderer has escaped. Beware.");
			new MessageDialog(rpg, "News", l, 245, 90);
			break;
		case 9:
			l = new JLabel("Our national debt recently skyrocketed to $9.5 octillion ($9.5 x 10^27).");
			new MessageDialog(rpg, "News", l, 410, 90);
			break;
		case 10:
			l = new JLabel("Everything is fine in our utopian community.");
			new MessageDialog(rpg, "News", l, 260, 90);
			break;
		case 11:
			l = new JLabel("<html>A new law was passed. Drivers on the highway were given permission<br />"
					+ "to ignore speed limit signs. The car accident rate has climbed 5,000%.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 12:
			l = new JLabel("Yesterday someone fell off the edge of the world. Be careful out there!");
			new MessageDialog(rpg, "News", l, 420, 90);
			break;
		case 13:
			l = new JLabel("<html>A hurricane is approaching. Please panic and stay outdoors for the rest<br />"
					+ "of the day.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 14:
			l = new JLabel("<html>A few days ago, an unidentified person robbed the bank. If you see him/<br />"
					+ "her, call 911.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 15:
			l = new JLabel("<html>POPULATION STATISTICS REPORT<br />"
					+ "Average Intelligence: 52<br />"
					+ "Average Health: 72.36%<br />"
					+ "Death Rate: 792 per second<br />"
					+ "Birth Rate: 851 per second<br /></html>");
			new MessageDialog(rpg, "News", l, 215, 150);
			break;
		case 16:
			l = new JLabel("<html>Scientists have recently discovered other worlds that have what they<br />"
					+ "call \"gravity.\" They say it is a good thing that our world does not have it.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 17:
			l = new JLabel("<html>The president, Mr. #%*!~@, is stupid. When signing documents, he saw a<br />"
					+ "sheet that said \"Do not write under this line,\" so he wrote \"Okay\" under<br />"
					+ "the line.</html>");
			new MessageDialog(rpg, "News", l, 430, 120);
			break;
		case 18:
			l = new JLabel("<html>SPECIAL NEWS<br />"
					+ "Children's stories are real! Peter Pan and Shrek got into a fight. Peter<br />"
					+ "Pan threw Shrek off a cliff. Beware of Peter and the Big Bad Wolf.</html>");
			new MessageDialog(rpg, "News", l, 410, 120);
			break;
		case 19:
			l = new JLabel("<html>A gang robbed Fat Foods and the bank yesterday. The members are<br />"
					+ "armed and dangerous. Arm yourself for protection and shoot anyone<br />"
					+ "who looks suspicious.</html>");
			new MessageDialog(rpg, "News", l, 410, 120);
			break;
		case 20:
			l = new JLabel("That's what she said.");
			new MessageDialog(rpg, "News", l, 130, 90);
			break;
		case 21:
			l = new JLabel("<html>A new law states that anyone who meets a police officer must bow and<br />"
					+ "present an apple.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 22:
			l = new JLabel("<html>A 16-year-old threatened to stab our bank teller with a leaf if he did not<br />"
					+ "give him $12 trillion. The brave bank teller refused and was stabbed. He<br />"
					+ "was transported to the hospital, where he died from his injuries.</html>");
			new MessageDialog(rpg, "News", l, 420, 120);
			break;
		case 23:
			l = new JLabel("<html>The president tried to negotiate with the bank regarding the loaning<br />"
					+ "rules. The bank refused to change the policy that after " + Person.MAX_LOANDAY + " days, the<br />"
					+ "loaner would be sentenced to death or life imprisonment.</html>");
			new MessageDialog(rpg, "News", l, 400, 120);
			break;
		case 24:
			l = new JLabel("<html>A reminder to all citizens: Sale of crack, heroin, or marijuana is illegal.<br />"
					+ "Police are keeping a careful lookout for suspicious activity.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 25:
			l = new JLabel("Welcome to News Channel 0. Keep watching for the latest headlines.");
			new MessageDialog(rpg, "News", l, 410, 90);
			break;
		case 26:
			l = new JLabel("We are currently experiencing technical difficulties.");
			new MessageDialog(rpg, "News", l, 315, 90);
			break;
		case 27:
			l = new JLabel("<html>ADVERTISEMENT<br />"
					+ "Have dry eyes? Come to WPH Pharmacy and get a new toenail today!</html>");
			new MessageDialog(rpg, "News", l, 410, 105);
			break;
		case 28:
			l = new JLabel("<html>ADVERTISEMENT<br />"
					+ "Are you constantly bothered by the fact that you have a chocolate brain?<br />"
					+ "Come to the Brain Exchange and buy a new one. We sell coal, water,<br />"
					+ "rubber, plastic, apple, cotton, and many more types of brains.</html>");
			new MessageDialog(rpg, "News", l, 425, 135);
			break;
		case 29:
			l = new JLabel("<html>ADVERTISEMENT<br />"
					+ "If you want money, come to the Coin Store. We sell pennies for $1,<br />"
					+ "nickels for $5, quarters for $40, half-dollars for $100, and dollar coins<br />"
					+ "for $350.</html>");
			new MessageDialog(rpg, "News", l, 420, 135);
			break;
		case 30:
			l = new JLabel("<html>ADVERTISEMENT<br />"
					+ "Come and buy something!</html>");
			new MessageDialog(rpg, "News", l, 170, 105);
			break;
		case 31:
			l = new JLabel("<html>ADVERTISEMENT<br />"
					+ "Our local casino has just gotten better! You can now lose all your money<br />"
					+ "in half the time as before, so come and go bankrupt!</html>");
			new MessageDialog(rpg, "News", l, 425, 120);
			break;
		case 32:
			l = new JLabel("<html>A body was found outside the mall. Reports indicate the individual was<br />"
					+ "drinking from Fat Foods's grease bin, which killed him instantly.</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 33:
			l = new JLabel("<html>Cars' and people's falling off the edge of the world is becoming a<br />"
					+ "serious problem. The mayor addressed the issue but does not know the<br />"
					+ "solution.</html>");
			new MessageDialog(rpg, "News", l, 420, 120);
			break;
		case 34:
			l = new JLabel("<html>New statistics show that teen pregnancy rates reduce significantly after<br />"
					+ "age 25.</html>");
			new MessageDialog(rpg, "News", l, 430, 105);
			break;
		case 35:
			l = new JLabel("<html>A woman was arrested for having pot in the engine compartment during<br />"
					+ "an oil change. When questioned, she said she did not know the mechanic<br />"
					+ "had to raise the hood to change the oil.</html>");
			new MessageDialog(rpg, "News", l, 440, 120);
			break;
		case 36:
			l = new JLabel("<html>A man tried to siphone gasoline from a motor home but accidentally<br />"
					+ "plugged the hose into the sewage tank. It was a smelly surprise.</html>");
			new MessageDialog(rpg, "News", l, 400, 105);
			break;
		case 37:
			l = new JLabel("<html>A man accused of robbing a jewelry store said that he could not have<br />"
					+ "been the culprit because he was breaking into a school at that time. He<br />"
					+ "was subsequently arrested for breaking into the school.</html>");
			new MessageDialog(rpg, "News", l, 420, 120);
			break;
		case 38:
			l = new JLabel("<html>A man trying to rob the bank handed the following note to the bank teller:<br />"
					+ "this iz a stikkup. Put all your muny in this bag.</html>");
			new MessageDialog(rpg, "News", l, 430, 105);
			break;
		case 39:
			l = new JLabel("<html>An auto mechanic received the note \"Check for clunking sound when<br />"
					+ "going around corners.\" He opened the trunk and returned the note<br />"
					+ "\"Removed bowling ball from trunk.\"</html>");
			new MessageDialog(rpg, "News", l, 415, 120);
			break;
		case 40:
			l = new JLabel("<html>A woman called 911 to report she had seen a mouse in her house.<br />"
					+ "</html>");
			new MessageDialog(rpg, "News", l, 400, 90);
			break;
		case 41:
			l = new JLabel("<html>LATEST HEADLINES<br />"
					+ "Iraqi Head Seeks Arms, Man Struck by Lightning Faces Charges,<br />"
					+ "Something Went Wrong in Jet Crash, Drunk Gets 9 Months in Violin<br />"
					+ "Case, Farmer Bill Dies in House, Prostitutes Appeal to Pope, Stud Tires<br />"
					+ "Out</html>");
			new MessageDialog(rpg, "News", l, 420, 150);
			break;
		case 42:
			l = new JLabel("<html>LATEST HEADLINES<br />"
					+ "Hospital Sued by 7-Foot Doctors, Miners Refuse to Work after Death,<br />"
					+ "Police Run Down Jaywalkers, Cold Wave Linked to Temperatures,<br />"
					+ "Typhoon Passes Cemetery; Hundreds Dead, Court Tries Shooting<br />"
					+ "Defendant</html>");
			new MessageDialog(rpg, "News", l, 410, 150);
			break;
		case 43:
			l = new JLabel("<html>LATEST HEADLINES<br />"
					+ "Vaccine Contains Rabies, Astronaut Blamed for Gas in Spacecraft,<br />"
					+ "Include Children When Baking Cookies, War Dims Hope for Peace, Eye<br />"
					+ "Drops off Shelf, Killer Sentenced to Die for Second Time in 10 Years<br /></html>");
			new MessageDialog(rpg, "News", l, 420, 135);
			break;
		case 44:
			l = new JLabel("<html>LATEST HEADLINES<br />"
					+ "Kids Make Nutritious Snacks, Arson Suspect Held in MA Fire, Residents<br />"
					+ "Drop off Trees, High School Dropouts Cut in Half, Deaf College Opens<br />"
					+ "Doors for Hearing, Lung Cancer in Women Mushrooms<br /></html>");
			new MessageDialog(rpg, "News", l, 420, 135);
			break;
		case 45:
			l = new JLabel("<html>LATEST HEADLINES<br />"
					+ "Two Convicts Evade Noose; Jury Hung, House Passes Gas Tax onto<br />"
					+ "Senate, Deaf Mute Gets Hearing in Killing, Two Sisters Reunited after 18<br />"
					+ "Years in Checkout Counter, Two Soviet Ships Collide; One Dies<br /></html>");
			new MessageDialog(rpg, "News", l, 420, 135);
			break;
		case 46:
			l = new JLabel("Federal agents raid a gun store and find weapons.");
			new MessageDialog(rpg, "News", l, 300, 90);
			break;
		case 47:
			l = new JLabel("<html>Babies named today: Al Bino, Anna Sasin, Barb E. Dahl, Bud Wieser, Sue<br />"
					+ "Yu, May Ann Naize, Will U. Marimy</html>");
			new MessageDialog(rpg, "News", l, 420, 105);
			break;
		case 48:
			l = new JLabel("<html>A deputy responded to a report of a vehicle stopping at mailboxes. It was<br />"
					+ "the mailman.</html>");
			new MessageDialog(rpg, "News", l, 430, 105);
			break;
		case 49:
			l = new JLabel("A one-armed man applauds the kindness of strangers.");
			new MessageDialog(rpg, "News", l, 325, 90);
			break;
		case 50:
			l = new JLabel("Psychics predict the world did not end yesterday.");
			new MessageDialog(rpg, "News", l, 300, 90);
			break;
		case 51:
			l = new JLabel("Students cook and serve grandparents.");
			new MessageDialog(rpg, "News", l, 240, 90);
			break;
		case 52:
			l = new JLabel("Our city is unsure why the sewer smells.");
			new MessageDialog(rpg, "News", l, 245, 90);
			break;
		case 53:
			l = new JLabel("<html>ADVERTISEMENT<br />"
					+ "Stay tuned to learn how to buy a $450,000 home for only $750,000.</html>");
			new MessageDialog(rpg, "News", l, 390, 105);
			break;
		case 54:
			l = new JLabel("Fish need water.");
			new MessageDialog(rpg, "News", l, 100, 90);
			break;
		case 55:
			l = new JLabel("The mayor answered, \"If I lose my life, I can live with that.\"");
			new MessageDialog(rpg, "News", l, 345, 90);
			break;
		case 56:
			l = new JLabel("The 15th annual pelling bee will be held tomorrow.");
			new MessageDialog(rpg, "News", l, 300, 90);
			break;
		case 57:
			l = new JLabel("Out of 40 women in the Senate, only 2 are female.");
			new MessageDialog(rpg, "News", l, 295, 90);
			break;
		case 58:
			l = new JLabel("Diana was still alive hours before she died.");
			new MessageDialog(rpg, "News", l, 260, 90);
			break;
		case 59:
			l = new JLabel("Work harder. Millions of people on welfare depend on you.");
			new MessageDialog(rpg, "News", l, 345, 90);
			break;
		case 60:
			l = new JLabel("Explaining alcoholism to children: Daddy drinks because you cry.");
			new MessageDialog(rpg, "News", l, 380, 90);
			break;
		case 61:
			l = new JLabel("<html>ADVERTISEMENT<br />"
					+ "Come to Fat Foods today! 0% off select items.</html>");
			new MessageDialog(rpg, "News", l, 270, 105);
			break;
		case 62:
			l = new JLabel("My boss told me to put something on this channel.");
			new MessageDialog(rpg, "News", l, 300, 90);
		}

		// update fields of p
		p.setHour(p.getHour() - 0.5);
		p.setSmart(p.getSmart() + Person.TV_SMART_INCREASE);
		if (n == 4) {
			l = new JLabel("You get extra intelligence from this news story.");
			new MessageDialog(rpg, "Bonus", l, 280, 90);
			p.setSmart(p.getSmart() + Person.TV_SMART_EXTRA_INCREASE);
		}
		p.setTvt(p.getTvt() + 1);
	}

	/** Watch cartoons if there is enough time. */
	private void watchCartoons(Person p) {
		// say there is not enough time if that is the case, and quit
		if (p.getHour() < 0.5) {
			JLabel l = new JLabel("Not enough time.");
			new MessageDialog(rpg, "Error", l, 100, 90);
			return;
		}

		// display the cartoon consisting of 1 random emoji and message saying intelligence lost
		JLabel l = new JLabel(new ImageIcon(getClass().getResource("/resources/emoji" + Person.randInt(1, 63) + ".png")));
		new MessageDialog(rpg, "Cartoon", l, 280, 325);
		l = new JLabel("Cartoons are bad for you. You lose " + commas(Person.CARTOON_LOSS) + " intelligence.");
		new MessageDialog(rpg, "Cartoon", l, 300, 90);

		// update fields
		p.setHour(p.getHour() - 0.5);
		p.setSmart(p.getSmart() - Person.CARTOON_LOSS);
		p.setTvt(p.getTvt() + 1);
	}

}
