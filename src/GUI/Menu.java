package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel p_main;
	private JButton btn_removeBook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setTitle("e-Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 520);
		p_main = new JPanel();
		p_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p_main);
		p_main.setLayout(null);
		
		JButton btn_quit = new JButton("");
		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_quit.setIcon(new ImageIcon(Menu.class.getResource("/GUI/rsz_remove.png")));
		btn_quit.setBounds(472, 11, 32, 32);
		p_main.add(btn_quit);
		
		JLabel lbl_icon1 = new JLabel("");
		lbl_icon1.setIcon(new ImageIcon(Menu.class.getResource("/GUI/rsz_bookmark.png")));
		lbl_icon1.setBounds(20, 11, 64, 58);
		p_main.add(lbl_icon1);
		
		JButton btn_showLibrary = new JButton("MY LIBRARY");
		btn_showLibrary.setForeground(new Color(0, 0, 0));
		btn_showLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Library l;
					try {
						l = new Library();
						l.setVisible(true);
						dispose();
					} catch (SQLException e1) {}
			}
		});
		btn_showLibrary.setBackground(new Color(245, 255, 250));
		btn_showLibrary.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_showLibrary.setIcon(new ImageIcon(Menu.class.getResource("/GUI/bookshelf.png")));
		btn_showLibrary.setHorizontalTextPosition(JButton.LEFT);
		btn_showLibrary.setIconTextGap(20);
		btn_showLibrary.setBounds(74, 39, 265, 73);
		p_main.add(btn_showLibrary);
		
		JButton btn_addBook = new JButton("ADD BOOK");
		btn_addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook ab = new AddBook();
				ab.setVisible(true);
				dispose();
			}
		});
		btn_addBook.setForeground(new Color(0, 0, 0));
		btn_addBook.setIcon(new ImageIcon(Menu.class.getResource("/GUI/rsz_open-book.png")));
		btn_addBook.setIconTextGap(40);
		btn_addBook.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_addBook.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_addBook.setBackground(new Color(245, 255, 250));
		btn_addBook.setBounds(74, 123, 265, 73);
		p_main.add(btn_addBook);
		
		btn_removeBook = new JButton("REMOVE BOOK");
		btn_removeBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveBook rb = new RemoveBook();
				rb.setVisible(true);
				dispose();
			}
		});
		btn_removeBook.setForeground(new Color(0, 0, 0));
		btn_removeBook.setIconTextGap(10);
		btn_removeBook.setIcon(new ImageIcon(Menu.class.getResource("/GUI/rsz_delete.png")));
		btn_removeBook.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_removeBook.setFont(new Font("Tahoma", Font.BOLD, 21));
		btn_removeBook.setBackground(new Color(245, 255, 250));
		btn_removeBook.setBounds(74, 207, 265, 73);
		p_main.add(btn_removeBook);
		
		JButton btn_loanedOut = new JButton("LOANED OUT");
		btn_loanedOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanedOut lo = new LoanedOut();
				lo.setVisible(true);
				dispose();
			}
		});
		btn_loanedOut.setForeground(new Color(0, 0, 0));
		btn_loanedOut.setIcon(new ImageIcon(Menu.class.getResource("/GUI/sell.png")));
		btn_loanedOut.setIconTextGap(20);
		btn_loanedOut.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_loanedOut.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_loanedOut.setBackground(new Color(245, 255, 250));
		btn_loanedOut.setBounds(74, 291, 265, 73);
		p_main.add(btn_loanedOut);
		
		JLabel lbl_welcome = new JLabel("Hello Mert , here's your library !");
		lbl_welcome.setForeground(new Color(250, 235, 215));
		lbl_welcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		lbl_welcome.setBounds(60, 375, 310, 73);
		p_main.add(lbl_welcome);
		
		
		JLabel lbl_foreground = new JLabel("");
		lbl_foreground.setIcon(new ImageIcon(Menu.class.getResource("/GUI/rsz_foreground.png")));
		lbl_foreground.setBounds(30, 11, 370, 451);
		p_main.add(lbl_foreground);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(Menu.class.getResource("/GUI/rsz_img_0029.png")));
		lbl_background.setBounds(0, 0, 571, 561);
		p_main.add(lbl_background);
		setLocationRelativeTo(null);
	}
}
