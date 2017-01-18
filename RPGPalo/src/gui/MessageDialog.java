package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import gui.Menu;

/** A class describing a popup message with a close button. */
public class MessageDialog extends JDialog {
	public static final Color TEXT_COLOR = Color.BLACK; // text color
	public static final Color BUTTON_COLOR = Menu.BUTTON_BACKGROUND_COLOR;
	public static final LineBorder BUTTON_BORDER = new LineBorder(Color.BLACK, 1);
	public static final Color BUTTON_MOUSEOVER_COLOR = Menu.BUTTON_MOUSEOVER_COLOR;
	public static final LineBorder BUTTON_FOCUS_BORDER = new LineBorder(Color.BLUE, 1);
	public static final Color BACKGROUND_COLOR = new Color(107, 171, 211);

	/** Constructor: a popup message with parent frame rpg, title t, text l, and
	 * dimensions (width, height). */
	public MessageDialog(RPG rpg, String t, JLabel l, int width, int height) {
		super(rpg, t, true);
		setLayout(new FlowLayout());
		getContentPane().setBackground(BACKGROUND_COLOR);
		l.setForeground(TEXT_COLOR);
		add(l);

		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			/** Close the dialog. */
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.addKeyListener(new KeyAdapter() {
			/** Close dialog if Esc or Enter is pressed. */
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode==KeyEvent.VK_ESCAPE || keyCode==KeyEvent.VK_ENTER) dispose();
			}
		});
		close.addMouseListener(new MouseAdapter() {
			/** Lighten the color upon mouse over. */
			public void mouseEntered(MouseEvent e) {
				close.setBackground(BUTTON_MOUSEOVER_COLOR);
			}
			/** Return old color upon mouse exit. */
			public void mouseExited(MouseEvent e) {
				close.setBackground(BUTTON_COLOR);
			}
		});
		close.addFocusListener(new FocusAdapter() {
			/** Change border color when button is in focus. */
			public void focusGained(FocusEvent e) {
				close.setBorder(BUTTON_FOCUS_BORDER);
			}
			/** Return border color when button is out of focus. */
			public void focusLost(FocusEvent e) {
				close.setBorder(BUTTON_BORDER);
			}
		});
		close.setBackground(BUTTON_COLOR);
		close.setForeground(TEXT_COLOR);
		close.setFocusPainted(false);
		Dimension preferredSize = close.getPreferredSize();
		close.setBorder(BUTTON_BORDER);
		close.setPreferredSize(preferredSize);
		close.setFocusPainted(false);
		add(close);

		setSize(new Dimension(width, height));
		setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
