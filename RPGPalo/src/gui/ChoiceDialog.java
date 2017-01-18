package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/** A class for a dialog where the user must choose between two options. */ 
public class ChoiceDialog extends JDialog {
	public static final Color TEXT_COLOR = Color.BLACK; // text color
	public static final Color BUTTON_COLOR = Menu.BUTTON_BACKGROUND_COLOR;
	public static final LineBorder BUTTON_BORDER = new LineBorder(Color.BLACK, 1);
	public static final Color BUTTON_MOUSEOVER_COLOR = Menu.BUTTON_MOUSEOVER_COLOR;
	public static final LineBorder BUTTON_FOCUS_BORDER = new LineBorder(Color.BLUE, 1);
	public static final Color BACKGROUND_COLOR = new Color(107, 171, 211);

	private boolean choice; // the choice the user makes, true if option 1, false if option 2

	/** Constructor: A dialog where the user must choose between two options.
	 * The dialog has parent frame rpg. It contains title t, text l, option text
	 * o1, option text o2, and dimensions (width, height). */
	public ChoiceDialog(RPG rpg, String t, JLabel l, String o1, String o2, int width, int height) {
		super(rpg, t, true);
		setLayout(new FlowLayout());
		getContentPane().setBackground(BACKGROUND_COLOR);
		l.setForeground(TEXT_COLOR);
		add(l);

		JButton option1 = new JButton(o1);
		option1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = true;
				dispose();
			}
		});
		option1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					choice = true;
					dispose();
				}
			}
		});
		option1.addMouseListener(new MouseAdapter() {
			/** Lighten the color upon mouse over. */
			public void mouseEntered(MouseEvent e) {
				option1.setBackground(BUTTON_MOUSEOVER_COLOR);
			}
			/** Return old color upon mouse exit. */
			public void mouseExited(MouseEvent e) {
				option1.setBackground(BUTTON_COLOR);
			}
		});
		option1.addFocusListener(new FocusAdapter() {
			/** Change border color when button is in focus. */
			public void focusGained(FocusEvent e) {
				option1.setBorder(BUTTON_FOCUS_BORDER);
			}
			/** Return border color when button is out of focus. */
			public void focusLost(FocusEvent e) {
				option1.setBorder(BUTTON_BORDER);
			}
		});
		option1.setBackground(BUTTON_COLOR);
		option1.setForeground(TEXT_COLOR);
		option1.setFocusPainted(false);
		Dimension preferredSize = option1.getPreferredSize();
		option1.setBorder(BUTTON_BORDER);
		option1.setPreferredSize(preferredSize);
		add(option1);

		JButton option2 = new JButton(o2);
		option2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = false;
				dispose();
			}
		});
		option2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					choice = false;
					dispose();
				}
			}
		});
		option2.addMouseListener(new MouseAdapter() {
			/** Lighten the color upon mouse over. */
			public void mouseEntered(MouseEvent e) {
				option2.setBackground(BUTTON_MOUSEOVER_COLOR);
			}
			/** Return old color upon mouse exit. */
			public void mouseExited(MouseEvent e) {
				option2.setBackground(BUTTON_COLOR);
			}
		});
		option2.addFocusListener(new FocusAdapter() {
			/** Change border color when button is in focus. */
			public void focusGained(FocusEvent e) {
				option2.setBorder(BUTTON_FOCUS_BORDER);
			}
			/** Return border color when button is out of focus. */
			public void focusLost(FocusEvent e) {
				option2.setBorder(BUTTON_BORDER);
			}
		});
		option2.setBackground(BUTTON_COLOR);
		option2.setForeground(TEXT_COLOR);
		option2.setFocusPainted(false);
		preferredSize = option2.getPreferredSize();
		option2.setBorder(BUTTON_BORDER);
		option2.setPreferredSize(preferredSize);
		add(option2);

		setSize(new Dimension(width, height));
		setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	/** Getter for the choice the user makes: true if option 1, false if option 2. */
	public boolean getChoice() {
		return choice;
	}

}
