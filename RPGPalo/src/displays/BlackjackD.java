package displays;

import java.util.ArrayList;
import javax.swing.JLabel;

import gui.Display;
import user.Person;

/** A class for the Blackjack display. */
public class BlackjackD extends Display {

	/** Constructor: the Blackjack display */
	public BlackjackD(Person p, ArrayList<String> personCards, ArrayList<String> opponentCards) {
		super(p);
		JLabel l = new JLabel("<html><p style=\"text-decoration: underline\">Your Cards</p>" +
				printCards(personCards) +
				"<br /><br /><p style=\"text-decoration: underline\">Opponent's Cards</p>" +
				printCards(opponentCards));
		l.setFont(DISPLAY_FONT);
		l.setForeground(DISPLAY_COLOR);
		add(l);
	}

	/** Return a string representation of an array of cards.
	 * Precondition: cards is not null */
	private String printCards(ArrayList<String> cards) {
		String s = "";
		for (String x : cards) {
			s += x + "&nbsp;&nbsp;";
		}
		return s.substring(0, s.length()-12);
	}

}
