package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import gui.Menu;

public class InputDialog extends JDialog {
	public static final Color TEXT_COLOR = Color.BLACK; // text color
	public static final Color BUTTON_COLOR = Menu.BUTTON_BACKGROUND_COLOR;
	public static final LineBorder BUTTON_BORDER = new LineBorder(Color.BLACK, 1);
	public static final Color BUTTON_MOUSEOVER_COLOR = Menu.BUTTON_MOUSEOVER_COLOR;
	public static final LineBorder BUTTON_FOCUS_BORDER = new LineBorder(Color.BLUE, 1);
	public static final Color BACKGROUND_COLOR = new Color(107, 171, 211);

	private String input; // the user-inputted string, null if no input was given

	/** Constructor: a frame that allows typed user input. It has parent frame
	 * rpg, title t, text l, and dimensions (width, height) with a text field of
	 * size textSize. */
	public InputDialog(RPG rpg, String t, JLabel l, int width, int height, int textSize) {
		super(rpg, t, true);
		setLayout(new FlowLayout());
		getContentPane().setBackground(BACKGROUND_COLOR);
		l.setForeground(TEXT_COLOR);
		add(l);

		JTextField text = new JTextField(textSize);
		text.addKeyListener(new KeyAdapter() {
			/** Close dialog if Esc is pressed. Store user input and close
			 * dialog if Enter is pressed. */
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ESCAPE) dispose();
				else if (keyCode == KeyEvent.VK_ENTER) {
					input = text.getText();
					dispose();
				}
			}
		});
		text.setForeground(Color.BLACK);
		add(text);

		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			/** Store user input and close dialog. */
			public void actionPerformed(ActionEvent e) {
				input = text.getText();
				dispose();
			}
		});
		ok.addKeyListener(new KeyAdapter() {
			/** Close dialog if Esc is pressed. Store user input and close
			 * dialog if Enter is pressed. */
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ESCAPE) dispose();
				else if (keyCode == KeyEvent.VK_ENTER) {
					input = text.getText();
					dispose();
				}
			}
		});
		ok.addMouseListener(new MouseAdapter() {
			/** Lighten the color upon mouse over. */
			public void mouseEntered(MouseEvent e) {
				ok.setBackground(BUTTON_MOUSEOVER_COLOR);
			}
			/** Return old color upon mouse exit. */
			public void mouseExited(MouseEvent e) {
				ok.setBackground(BUTTON_COLOR);
			}
		});
		ok.addFocusListener(new FocusAdapter() {
			/** Change border color when button is in focus. */
			public void focusGained(FocusEvent e) {
				ok.setBorder(BUTTON_FOCUS_BORDER);
			}
			/** Return border color when button is out of focus. */
			public void focusLost(FocusEvent e) {
				ok.setBorder(BUTTON_BORDER);
			}
		});
		ok.setBackground(BUTTON_COLOR);
		ok.setForeground(TEXT_COLOR);
		ok.setFocusPainted(false);
		Dimension preferredSize = ok.getPreferredSize();
		ok.setBorder(BUTTON_BORDER);
		ok.setPreferredSize(preferredSize);
		add(ok);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			/** Close dialog without storing user input. */
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.addKeyListener(new KeyAdapter() {
			/** Close dialog if Esc or Enter is pressed. */
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode==KeyEvent.VK_ESCAPE || keyCode==KeyEvent.VK_ENTER) dispose();
			}
		});
		cancel.addMouseListener(new MouseAdapter() {
			/** Lighten the color upon mouse over. */
			public void mouseEntered(MouseEvent e) {
				cancel.setBackground(BUTTON_MOUSEOVER_COLOR);
			}
			/** Return old color upon mouse exit. */
			public void mouseExited(MouseEvent e) {
				cancel.setBackground(BUTTON_COLOR);
			}
		});
		cancel.addFocusListener(new FocusAdapter() {
			/** Change border color when button is in focus. */
			public void focusGained(FocusEvent e) {
				cancel.setBorder(BUTTON_FOCUS_BORDER);
			}
			/** Return border color when button is out of focus. */
			public void focusLost(FocusEvent e) {
				cancel.setBorder(BUTTON_BORDER);
			}
		});
		cancel.setBackground(BUTTON_COLOR);
		cancel.setForeground(TEXT_COLOR);
		cancel.setFocusPainted(false);
		preferredSize = cancel.getPreferredSize();
		cancel.setBorder(BUTTON_BORDER);
		cancel.setPreferredSize(preferredSize);
		add(cancel);

		setSize(new Dimension(width, height));
		setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/** Getter for user-inputted string, null if nothing was inputted. */
	public String getInput() {
		return input;
	}

}
