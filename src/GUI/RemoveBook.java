package GUI;

import java.awt.BorderLayout;
import dataBase.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class RemoveBook extends JFrame {

	RemoveData remove = new RemoveData();
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
					RemoveBook frame = new RemoveBook();
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
	public RemoveBook() {
		setTitle("Remove Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_id = new JTextField();
		txt_id.addKeyListener(new KeyAdapter() {

			// id için sadece sayý girme
			@Override
			public void keyReleased(KeyEvent e) {

				try {
					if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)) {
						i = Integer.parseInt(txt_id.getText());
						if (!(i > 0 && i <= 99999)) {
							txt_id.setText("");
							i = 0;
						}
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPane, "Invalid Number!");
					txt_id.setText("");
				}
			}
		});

		txt_id.setFont(new Font("Helvetica", Font.PLAIN, 14));
		txt_id.setBounds(265, 138, 64, 32);
		txt_id.setColumns(10);
		contentPane.add(txt_id);
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
		});
		btn_back.setIcon(new ImageIcon(RemoveBook.class.getResource("/GUI/back-arrow.png")));
		btn_back.setBackground(new Color(0, 51, 51));
		btn_back.setBounds(335, 11, 32, 32);
		contentPane.add(btn_back);

		JButton btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_id.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "ID part cannot be empty!");
				}else {
					  try {
					   	remove.removeBook("elibrary", i);
					   	JOptionPane.showMessageDialog(contentPane, "The book has been removed!");
					  } catch (SQLException e1) {}
					  
				}
			}
		});
		btn_delete.setIcon(new ImageIcon(RemoveBook.class.getResource("/GUI/rsz_delete_lite.png")));
		btn_delete.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_delete.setForeground(Color.BLACK);
		btn_delete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_delete.setFocusable(false);
		btn_delete.setBackground(new Color(255, 204, 204));
		btn_delete.setBounds(110, 221, 151, 63);
		contentPane.add(btn_delete);

		JLabel lbl_title = new JLabel("Remove Book\r\n");
		lbl_title.setForeground(Color.WHITE);
		lbl_title.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 20));
		lbl_title.setBackground(Color.WHITE);
		lbl_title.setBounds(118, 85, 133, 32);
		contentPane.add(lbl_title);

		JLabel lbl_removeid = new JLabel("Enter the \"id\" of the book :");
		lbl_removeid.setForeground(Color.WHITE);
		lbl_removeid.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_removeid.setBackground(Color.WHITE);
		lbl_removeid.setBounds(43, 136, 218, 32);
		contentPane.add(lbl_removeid);

		JLabel lbl_icon = new JLabel("");
		lbl_icon.setIcon(new ImageIcon(RemoveBook.class.getResource("/GUI/rsz_bookmark.png")));
		lbl_icon.setBounds(23, 54, 64, 58);
		contentPane.add(lbl_icon);

		JLabel lbl_foreground = new JLabel("New label");
		lbl_foreground.setIcon(new ImageIcon(RemoveBook.class.getResource("/GUI/rsz_foreground.png")));
		lbl_foreground.setBounds(33, 54, 308, 156);
		contentPane.add(lbl_foreground);

		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(RemoveBook.class.getResource("/GUI/rsz_img_0029.png")));
		lbl_background.setBounds(0, 0, 379, 316);
		contentPane.add(lbl_background);
		setLocationRelativeTo(null);
	}
}
