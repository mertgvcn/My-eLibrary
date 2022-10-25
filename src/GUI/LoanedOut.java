package GUI;

import dataBase.*;
import java.awt.BorderLayout;
import dataBase.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class LoanedOut extends JFrame {

	private LoanedOutData transfer = new LoanedOutData();
	private RemoveData remove = new RemoveData();
	private JPanel contentPane;
	private JTextField txt_id;
	private int i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanedOut frame = new LoanedOut();
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
	public LoanedOut() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txt_id = new JTextField();
		txt_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if(!(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
						i = Integer.parseInt(txt_id.getText());
						if(!(i > 0 && i<= 99999)) {
							txt_id.setText("");
							i = 0;
						}
					}
				}catch(Exception e1) {
					  JOptionPane.showMessageDialog(contentPane, "Invalid Number!");
					  txt_id.setText("");
				}
				
			}
		});
		
		txt_id.setFont(new Font("Helvetica", Font.PLAIN, 14));
		txt_id.setColumns(10);
		txt_id.setBounds(265, 138, 64, 32);
		contentPane.add(txt_id);
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
		});
		btn_back.setIcon(new ImageIcon(LoanedOut.class.getResource("/GUI/back-arrow.png")));
		btn_back.setBackground(new Color(0, 51, 51));
		btn_back.setBounds(335, 11, 32, 32);
		contentPane.add(btn_back);
		
		JButton btn_lend = new JButton("LEND");
		btn_lend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//elibrary tablosundan id'si i olan id,name,author,page,isRead verilerini loanedout tablosuna aktarma iþlemi!
					String query = "INSERT INTO loanedout (book_id,book_name,book_author,book_page,book_isRead) "
							+ "SELECT book_id,book_name,book_author,book_page,book_isRead "
							+ "FROM elibrary "
							+ "WHERE book_id = " + i ;
					transfer.transferData(query);
					remove.removeBook("elibrary", i); //elibrary de kalýyo onu geri silmek için
					txt_id.setText("");
					JOptionPane.showMessageDialog(contentPane, "The book has been transferred!");
				} catch (SQLException e1) {}
				
			}
		});
		btn_lend.setIcon(new ImageIcon(LoanedOut.class.getResource("/GUI/sell.png")));
		btn_lend.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_lend.setForeground(Color.BLACK);
		btn_lend.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_lend.setFocusable(false);
		btn_lend.setBackground(new Color(255, 218, 185));
		btn_lend.setBounds(110, 221, 159, 63);
		contentPane.add(btn_lend);
		
		JLabel lbl_title = new JLabel("Lend Book");
		lbl_title.setForeground(Color.WHITE);
		lbl_title.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 20));
		lbl_title.setBackground(Color.WHITE);
		lbl_title.setBounds(136, 87, 97, 32);
		contentPane.add(lbl_title);
		
		JLabel lbl_removeid = new JLabel("Enter the \"id\" of the book :");
		lbl_removeid.setForeground(Color.WHITE);
		lbl_removeid.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_removeid.setBackground(Color.WHITE);
		lbl_removeid.setBounds(43, 136, 218, 32);
		contentPane.add(lbl_removeid);
		
		JLabel lbl_icon = new JLabel("");
		lbl_icon.setIcon(new ImageIcon(LoanedOut.class.getResource("/GUI/rsz_bookmark.png")));
		lbl_icon.setBounds(23, 54, 64, 58);
		contentPane.add(lbl_icon);
		
		JLabel lbl_foreground = new JLabel("");
		lbl_foreground.setIcon(new ImageIcon(LoanedOut.class.getResource("/GUI/rsz_foreground.png")));
		lbl_foreground.setBounds(33, 54, 308, 156);
		contentPane.add(lbl_foreground);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(LoanedOut.class.getResource("/GUI/rsz_img_0029.png")));
		lbl_background.setBounds(0, 0, 373, 316);
		contentPane.add(lbl_background);
	}

}
