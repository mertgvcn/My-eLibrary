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
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.SystemColor;

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_author;
	private JTextField txt_page;
	private short s;
	private int i;
	public AddData add = new AddData();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() {
		setTitle("Add Book");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_authorname_1 = new JLabel("Page ");
		lbl_authorname_1.setForeground(Color.WHITE);
		lbl_authorname_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_authorname_1.setBackground(Color.WHITE);
		lbl_authorname_1.setBounds(53, 201, 112, 32);
		contentPane.add(lbl_authorname_1);

		JLabel lbl_authorname = new JLabel("Author");
		lbl_authorname.setForeground(Color.WHITE);
		lbl_authorname.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_authorname.setBackground(Color.WHITE);
		lbl_authorname.setBounds(53, 140, 112, 32);
		contentPane.add(lbl_authorname);

		JLabel lbl_bookname = new JLabel("Book Name ");
		lbl_bookname.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_bookname.setForeground(Color.WHITE);
		lbl_bookname.setBackground(Color.WHITE);
		lbl_bookname.setBounds(53, 71, 111, 32);
		contentPane.add(lbl_bookname);

		JCheckBox chckbx_isRead = new JCheckBox("I have already read this book before ...");
		chckbx_isRead.setBackground(new Color(255, 255, 255));
		chckbx_isRead.setForeground(new Color(0, 0, 0));
		chckbx_isRead.setFont(new Font("Helvetica Rounded", Font.ITALIC, 15));
		chckbx_isRead.setBounds(53, 310, 323, 23);
		chckbx_isRead.setFocusable(false);
		chckbx_isRead.setOpaque(false);
		contentPane.add(chckbx_isRead);
		
		txt_page = new JTextField();
		txt_page.addKeyListener(new KeyAdapter() {
			
			// sayfa için sadece sayý girme
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if(!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)) {
						i = Integer.parseInt(txt_page.getText());
						if (! (i > 0 && i <= 99999) ) {
							txt_page.setText("");
							i = 0;
						}
					}
				}catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(contentPane, "Invalid Number!");
					txt_page.setText("");
				}
				
			//Sýrasýyla eðer basýlan tuþ space veya enter deðilse, girilen sayý 0 ile 99999 arasýnda deðilse
			//geçersiz sayý hatasý ver ve text area'yý temizle.
			}
		});


		txt_page.setForeground(Color.BLACK);
		txt_page.setFont(new Font("Helvetica", Font.PLAIN, 14));
		txt_page.setColumns(10);
		txt_page.setBackground(Color.WHITE);
		txt_page.setBounds(53, 227, 75, 32);
		contentPane.add(txt_page);

		txt_author = new JTextField();
		txt_author.setForeground(Color.BLACK);
		txt_author.setFont(new Font("Helvetica", Font.PLAIN, 14));
		txt_author.setColumns(10);
		txt_author.setBackground(Color.WHITE);
		txt_author.setBounds(53, 166, 323, 32);
		contentPane.add(txt_author);

		txt_name = new JTextField();
		txt_name.setBackground(new Color(255, 255, 255));
		txt_name.setForeground(new Color(0, 0, 0));
		txt_name.setFont(new Font("Helvetica", Font.PLAIN, 14));
		txt_name.setBounds(53, 97, 323, 32);
		contentPane.add(txt_name);
		txt_name.setColumns(10);

		JButton btn_clear = new JButton("CLEAR");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_name.setText("");
				txt_author.setText("");
				txt_page.setText("");
				chckbx_isRead.setSelected(false);

			}
		});
		btn_clear.setForeground(new Color(0, 0, 0));
		btn_clear.setIcon(new ImageIcon(AddBook.class.getResource("/GUI/clear.png")));
		btn_clear.setBackground(new Color(204, 204, 204));
		btn_clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_clear.setBounds(277, 236, 99, 23);
		btn_clear.setHorizontalTextPosition(JButton.LEFT);
		btn_clear.setFocusable(false);
		contentPane.add(btn_clear);
		
		JButton btn_save = new JButton("SAVE");
		btn_save.setBackground(new Color(152, 251, 152));
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbx_isRead.isSelected()) {
					s = 1;
				}else {
					s= 0;
				}
				
				if(txt_name.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "The name part cannot be empty!");
				}else {
					try {
						add.addData("elibrary" , txt_name.getText(), txt_author.getText(), i, s);
						JOptionPane.showMessageDialog(contentPane, "The book has been saved successfully!");
					} catch(SQLException e1){
						System.out.println("hata");
					}
				}
			}
		});
		btn_save.setIcon(new ImageIcon(AddBook.class.getResource("/GUI/save.png")));
		btn_save.setForeground(Color.BLACK);
		btn_save.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_save.setBounds(129, 360, 151, 63);
		btn_save.setHorizontalTextPosition(JButton.LEFT);
		btn_save.setFocusable(false);
		contentPane.add(btn_save);

		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
		});
		btn_back.setBackground(new Color(0, 51, 51));
		btn_back.setIcon(new ImageIcon(AddBook.class.getResource("/GUI/back-arrow.png")));
		btn_back.setBounds(470, 11, 32, 32);
		contentPane.add(btn_back);

		JLabel lbl_icon1 = new JLabel("");
		lbl_icon1.setIcon(new ImageIcon(AddBook.class.getResource("/GUI/rsz_bookmark.png")));
		lbl_icon1.setBounds(10, 11, 64, 58);
		contentPane.add(lbl_icon1);

		JLabel lbl_foreground = new JLabel("");
		lbl_foreground.setIcon(new ImageIcon(AddBook.class.getResource("/GUI/rsz_foreground.png")));
		lbl_foreground.setBounds(20, 11, 370, 451);
		contentPane.add(lbl_foreground);

		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(AddBook.class.getResource("/GUI/rsz_img_0029.png")));
		lbl_background.setBounds(0, 0, 514, 481);
		contentPane.add(lbl_background);
		setLocationRelativeTo(null);

	}

	public short getS() {
		return s;
	}

	public void setS(short s) {
		this.s = s;
	}
	
	
}
