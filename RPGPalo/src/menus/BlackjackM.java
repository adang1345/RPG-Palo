package menus;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import gui.Menu;
import gui.MessageDialog;
import gui.RPG;
import user.Person;

public class BlackjackM extends Menu {
	private ArrayList<String> deck; // the deck of remaining cards
	private ArrayList<String> playerCards; // the player's cards
	private ArrayList<String> dealerCards; // the dealer's cards
	private long betAmount; // the amount the player bet

	/** Constructor: the Blackjack menu */
	public BlackjackM(RPG rpg, long betAmount, ArrayList<String> deck,
			ArrayList<String> playerCards, ArrayList<String> dealerCards) {
		super(rpg);
		Person p = rpg.getPerson();
		this.betAmount = betAmount;
		this.deck = deck;
		this.playerCards = playerCards;
		this.dealerCards = dealerCards;

		buttons[0] = new JButton("Hit Me!");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hitMe(p);
			}
		});

		buttons[1] = new JButton("Stand");
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stand(p);
			}
		});

		formatButtons();
		updateButtons();
	}

	/** Hit.
	 * Precondition: This person has not busted. */
	private void hitMe(Person p) {
		playerCards.add(randCard());
		rpg.setDisplay(new displays.BlackjackD(p, playerCards, dealerCards));
		if (value(playerCards) > 21) {
			JLabel l = new JLabel("Bust! You have gone over 21.");
			new MessageDialog(rpg, "Blackjack", l, 190, 90);
			p.setMoney(p.getMoney() - betAmount);
			rpg.setMenu(new menus.VegasCasinoM(rpg));
			rpg.setDisplay(new displays.VegasCasinoD(p));
		}
	}

	/** Stand.
	 * Precondition: Neither player has busted. */
	private void stand(Person p) {
		do {
			dealerCards.add(randCard());
			rpg.setDisplay(new displays.BlackjackD(p, playerCards, dealerCards));
		} while (value(dealerCards) < 17);
		int opponentValue = value(dealerCards);
		int personValue = value(playerCards);
		if (opponentValue > 21) {
			JLabel l = new JLabel("Dealer bust! You win.");
			new MessageDialog(rpg, "Blackjack", l, 110, 90);
			p.setMoney(p.getMoney() + betAmount);
		} else if (opponentValue > personValue) {
			JLabel l = new JLabel("The dealer wins.");
			new MessageDialog(rpg, "Blackjack", l, 100, 90);
			p.setMoney(p.getMoney() - betAmount);
		} else if (opponentValue == personValue) { //TODO //TODO //TODO
			JLabel l = new JLabel("Push. The game is a draw.");
			new MessageDialog(rpg, "Blackjack", l, 170, 90);
		} else {
			JLabel l = new JLabel("<html>&nbsp;&nbsp;You win.&nbsp;&nbsp;</html>");
			new MessageDialog(rpg, "Blackjack", l, 100, 90);
			p.setMoney(p.getMoney() + betAmount);
		}
		rpg.setMenu(new menus.VegasCasinoM(rpg));
		rpg.setDisplay(new displays.VegasCasinoD(p));
	}

	/** Return the value of a hand of cards. */
	private int value(ArrayList<String> cards) {
		int v = 0;
		boolean hasAce = false;
		for (String s : cards) {
			switch(s) {
			case "J": case "Q": case "K":
				v += 10;
				break;
			case "A":
				hasAce = true;
				v += 1;
				break;
			default:
				v += Integer.parseInt(s);
			}
		}
		if (hasAce && v <= 11) v += 10;
		return v;
	}

	/** Return and remove a random card from deck. */
	private String randCard() {
		String newCard = deck.get(Person.randInt(0, deck.size()-1));
		deck.remove(newCard);
		return newCard;
	}

}
