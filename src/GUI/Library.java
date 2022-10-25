package GUI;

import java.awt.BorderLayout;
import dataBase.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Library extends JFrame {

	private UpdateData update = new UpdateData();
	private LoanedOutData transfer = new LoanedOutData();
	private ListData list = new ListData();
	private RemoveData remove = new RemoveData();
	private JPanel contentPane;
	private JTable t_myLibrary;
	private JTable t_loanedOut;
	private DefaultTableModel dtm;
	private DefaultTableModel dtm2;
	private Object[] column = {"ID" , "Book Name", "Author", "Page", "iR?"};
	private Object[] row = new Object[5];
	private JTextField txt_retakeID;
	private int i,y;
	private JTextField txt_read;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library frame = new Library();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Library() throws SQLException {
		setTitle("My Library");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 535);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//library modeli oluþturduk
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(column);
		
		//loanedout modeli oluþturduk
		dtm2 = new DefaultTableModel();
		dtm2.setColumnIdentifiers(column);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 43, 574, 349);
		tabbedPane.setFont(new Font("Ebrima", Font.PLAIN, 13));
		tabbedPane.setFocusable(false);
		contentPane.add(tabbedPane);
		
		JPanel p_myLibrary = new JPanel();
		p_myLibrary.setBackground(new Color(47, 79, 79));
		tabbedPane.addTab("My Library", null, p_myLibrary, null);
		p_myLibrary.setLayout(null);
		
		JScrollPane sp_myLibrary = new JScrollPane();
		sp_myLibrary.setBounds(10, 11, 549, 295);
		p_myLibrary.add(sp_myLibrary);
		
		t_myLibrary = new JTable();
		t_myLibrary.setForeground(new Color(0, 0, 0));
		t_myLibrary.setBackground(new Color(245, 222, 179));
		t_myLibrary.setEnabled(false);
		t_myLibrary.setFont(new Font("Spartan SemiBold", Font.PLAIN, 12));
		list.listData("elibrary", dtm, row); //dtm modelini kullanarak elibraryden verileri çek.
		t_myLibrary.setModel(dtm);
		setTableColumnBounds(t_myLibrary); //kolon geniþliðini ayarladýk.
		sp_myLibrary.setViewportView(t_myLibrary);
		
		JPanel p_loanedOut = new JPanel();
		p_loanedOut.setBackground(new Color(47, 79, 79));
		tabbedPane.addTab("Loaned Out", null, p_loanedOut, null);
		p_loanedOut.setLayout(null);
		
		JScrollPane sp_loanedOut = new JScrollPane();
		sp_loanedOut.setBounds(10, 11, 549, 295);
		p_loanedOut.add(sp_loanedOut);
		
		t_loanedOut = new JTable();
		t_loanedOut.setForeground(new Color(0, 0, 0));
		t_loanedOut.setBackground(new Color(210, 180, 140));
		t_loanedOut.setEnabled(false);
		t_loanedOut.setFont(new Font("Spartan SemiBold", Font.PLAIN, 12));
		list.listData("loanedout", dtm2, row); //dtm2 modelini kullanarak loanedout'tan verileri çek.
		t_loanedOut.setModel(dtm2);
		setTableColumnBounds(t_loanedOut); //kolon geniþliðini ayarladýk.
		sp_loanedOut.setViewportView(t_loanedOut);
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
		});
		btn_back.setBackground(new Color(47, 79, 79));
		btn_back.setIcon(new ImageIcon(Library.class.getResource("/GUI/back-arrow.png")));
		btn_back.setBounds(552, 9, 32, 32);
		contentPane.add(btn_back);
		
		JLabel lbl_title = new JLabel("Welcome to Library/Loaned Out section !");
		lbl_title.setIcon(new ImageIcon(Library.class.getResource("/GUI/rsz_open-book-tiny.png")));
		lbl_title.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 17));
		lbl_title.setForeground(new Color(255, 255, 255));
		lbl_title.setBounds(10, 9, 532, 32);
		contentPane.add(lbl_title);
		
		txt_retakeID = new JTextField();
		txt_retakeID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if(!(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
						i = Integer.parseInt(txt_retakeID.getText());
						if(!(i>0 && i<=99999)) {
							txt_retakeID.setText("");
							i=0;
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(contentPane, "Invalid Number!");
					txt_retakeID.setText("");
				}
			}
		});
		
		txt_retakeID.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_retakeID.setBounds(288, 412, 32, 32);
		contentPane.add(txt_retakeID);
		txt_retakeID.setColumns(10);
		
		JButton btn_retakeComplete = new JButton("");
		btn_retakeComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO elibrary (book_id,book_name,book_author,book_page,book_isRead) "
							+ "SELECT book_id,book_name,book_author,book_page,book_isRead "
							+ "FROM loanedout "
							+ "WHERE book_id = " + i;
					transfer.transferData(query);
					remove.removeBook("loanedout", i);
					txt_retakeID.setText("");
					updateTables(dtm, dtm2, row);
					JOptionPane.showMessageDialog(contentPane, "The book has been transferred!");
				} catch (SQLException e1) {}
			}
		});
		btn_retakeComplete.setBackground(new Color(105, 105, 105));
		btn_retakeComplete.setFont(new Font("Verdana", Font.BOLD, 12));
		btn_retakeComplete.setIcon(new ImageIcon(Library.class.getResource("/GUI/save.png")));
		btn_retakeComplete.setBounds(330, 412, 39, 32);
		btn_retakeComplete.setFocusable(false);
		btn_retakeComplete.setHorizontalTextPosition(JButton.LEFT);
		contentPane.add(btn_retakeComplete);
		
		JLabel lbl_retake = new JLabel("I got back book number");
		lbl_retake.setIcon(new ImageIcon(Library.class.getResource("/GUI/sell_tiny.png")));
		lbl_retake.setFont(new Font("Helvetica", Font.BOLD, 20));
		lbl_retake.setForeground(new Color(255, 255, 255));
		lbl_retake.setBounds(20, 412, 314, 32);
		contentPane.add(lbl_retake);
		
		txt_read = new JTextField();
		txt_read.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if(!(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
						y = Integer.parseInt(txt_read.getText());
						if(!(y>0 && y<=99999)) {
							txt_read.setText("");
							y=0;
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(contentPane, "Invalid Number!");
					txt_read.setText("");
				}
			}
		});
		
		txt_read.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_read.setColumns(10);
		txt_read.setBounds(288, 454, 32, 32);
		contentPane.add(txt_read);
		
		JButton btn_readComplete = new JButton("");
		btn_readComplete.setForeground(Color.BLACK);
		btn_readComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE elibrary SET book_isRead = 1 WHERE book_id=" + y;
					update.update_isRead(query);
					txt_read.setText("");
					updateTables(dtm, dtm2, row);
					JOptionPane.showMessageDialog(contentPane, "The book has been updated!");
				} catch (SQLException e1) {}
			
			}
		});
		btn_readComplete.setIcon(new ImageIcon(Library.class.getResource("/GUI/save.png")));
		btn_readComplete.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_readComplete.setFont(new Font("Verdana", Font.BOLD, 12));
		btn_readComplete.setFocusable(false);
		btn_readComplete.setBackground(SystemColor.controlDkShadow);
		btn_readComplete.setBounds(330, 454, 39, 32);
		contentPane.add(btn_readComplete);
		
		JLabel lbl_read = new JLabel("I read the book number");
		lbl_read.setIcon(new ImageIcon(Library.class.getResource("/GUI/book.png")));
		lbl_read.setForeground(Color.WHITE);
		lbl_read.setFont(new Font("Helvetica", Font.BOLD, 20));
		lbl_read.setBounds(20, 454, 314, 32);
		contentPane.add(lbl_read);
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbl_background.setForeground(new Color(0, 0, 0));
		lbl_background.setIcon(new ImageIcon(Library.class.getResource("/GUI/rsz_img_0029.png")));
		lbl_background.setBounds(0, 0, 609, 506);
		contentPane.add(lbl_background);
	}
	
	public void setTableColumnBounds(JTable table) {
		//yatay
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		
		//dikey
		table.setRowHeight(20);
	}
	
	public void updateTables(DefaultTableModel dtm, DefaultTableModel dtm2, Object[] row) throws SQLException {
		dtm.setRowCount(0);  
		dtm2.setRowCount(0);
		list.listData("elibrary", dtm, row);
		list.listData("loanedout", dtm2, row);
		//satýrlarý sýfýrlayýp listdata ile tekrardan listeledik.
	}
}
