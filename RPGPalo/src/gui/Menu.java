package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import gui.Menu;

/** A class describing a menu of buttons. */
public abstract class Menu extends JPanel {
	public static final int BUTTON_HEIGHT = 50; // height of a button
	public static final int BUTTON_WIDTH = 100; // width of a button
	public static final int BUTTON_COUNT = 8; // max number of non-null buttons in menu
	public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 12); // button text font
	public static final Color BUTTON_BACKGROUND_COLOR = new Color(110, 220, 80);
	public static final LineBorder BUTTON_BORDER = new LineBorder(Color.BLACK, 1);
	public static final Color BUTTON_MOUSEOVER_COLOR = new Color(139, 227, 115);
	public static final Color BUTTON_TEXT_COLOR = Color.BLACK; // button text color
	public static final int MENU_HEIGHT = (int) (BUTTON_HEIGHT*1.5); // height of menu

	protected JButton[] buttons;
	// the buttons in the menu
	// invariant: if buttons[n] is null, then buttons[n..] are all null
	protected RPG rpg; // the RPG game that contains this menu

	/** Constructor: a menu */
	public Menu(RPG rpg) {
		super(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(RPG.WINDOW_WIDTH, MENU_HEIGHT));
		setBackground(RPG.BACKGROUND_COLOR);
		this.rpg = rpg;
		buttons = new JButton[BUTTON_COUNT];
	}

	/** Format the non-null buttons. */
	protected void formatButtons() {
		for (JButton b : buttons) {
			if (b == null) break;
			b.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
			b.setFont(BUTTON_FONT);
			b.setForeground(BUTTON_TEXT_COLOR);
			b.setBackground(BUTTON_BACKGROUND_COLOR);
			b.addMouseListener(new MouseAdapter() {
				/** Lighten the color upon mouse over. */
				public void mouseEntered(MouseEvent e) {
					b.setBackground(BUTTON_MOUSEOVER_COLOR);
				}
				/** Return old color upon mouse exit. */
				public void mouseExited(MouseEvent e) {
					b.setBackground(BUTTON_BACKGROUND_COLOR);
				}
				/** Return old color upon mouse click. */
				public void mousePressed(MouseEvent e) {
					b.setBackground(BUTTON_BACKGROUND_COLOR);
				}
			});
			b.setBorder(BUTTON_BORDER);
			b.setMargin(new Insets(0,0,0,0));
			b.setFocusable(false);
		}
	}

	/** Update all non-null buttons in this menu. */
	protected void updateButtons() {
		repaint();
		for (JButton b : buttons) {
			if (b == null) break;
			add(b);
		}
	}

	/** Insert commas every 3 digits in an int or long. */
	public static String commas(long i) {
		return Display.commas(i);
	}

	/** Insert commas every 3 digits in a double.
	 * Precondition: d contains exactly 1 decimal digit, and 0 < d < 9999999.9. */
	public static String commas(double d) {
		return Display.commas(d);
	}

}
