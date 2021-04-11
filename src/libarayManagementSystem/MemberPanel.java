package libarayManagementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;

public class MemberPanel extends JFrame {

	private JPanel contentPane;
	private JTable libraryRulesTable;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable bookTable;
	private JTextField b_titleTF;
	private JTextField bookTitle;
	private JTextField isbn;
	private JTextField bookEdition;
	JLabel bookImageLevel;
	JLabel clockLabel;
	String fileName=null;
	int s=0;
	byte[] book_iamge=null;
	ImageIcon formate=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberPanel frame = new MemberPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection cn = null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	private JTextField rule;
	private JTable bookRequestTable;
	private JTextField replyTF;
	private JTextField bookName;
	private JTextField bookDetalis;

	private void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar cal = new GregorianCalendar();
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
						Date date = cal.getTime();
						String timeString = formatter.format(date);
						clockLabel.setText(timeString);
						sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		clock.start();
	}
	
	
	public void bookDataShow() {
		try {
			String sql = "SELECT b_title,b_isbn,b_edition FROM book";
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			bookTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	public void bookTableDataShow() {
		try {
			int row = bookTable.getSelectedRow();
			 String sTable = (bookTable.getModel().getValueAt(row, 0).toString());
			 String sTable1 = (bookTable.getModel().getValueAt(row, 1).toString());
			 String sTable2 = (bookTable.getModel().getValueAt(row, 2).toString());
			 bookTitle.setText(sTable);
			 isbn.setText(sTable1);
			 bookEdition.setText(sTable2);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void libraryrulesDataShow() {
		try {
			String sql = "SELECT rule FROM libraryrules";
			 ps = cn.prepareStatement(sql);
			 rs = ps.executeQuery();
			 libraryRulesTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void libraryrulesTableDataShow() {

		try {
			int row = libraryRulesTable.getSelectedRow();
			 String sTable1 = (libraryRulesTable.getModel().getValueAt(row, 0).toString());
             rule.setText(sTable1);
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	
	public void bookDataShow2() {
		String one= b_titleTF.getText();
		String sql = "SELECT b_title,b_isbn,b_edition FROM book WHERE b_title=?";
		try {
			 ps = cn.prepareStatement(sql);
			 ps.setString(1, one);
			 rs = ps.executeQuery();
			 bookTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void refresh() {
		bookTitle.setText(null);
		bookEdition.setText(null);
		isbn.setText(null);
		bookDataShow();
	}
	
	public void choosPicture() {
		
		JFileChooser chooser= new JFileChooser();
		chooser.showOpenDialog(null);
		File f=chooser.getSelectedFile();
		fileName=f.getAbsolutePath();
		
		try {
			File  image = new File (fileName);
			FileInputStream fis= new FileInputStream(image);
			ByteArrayOutputStream bos =new ByteArrayOutputStream();
			byte[] buf= new byte[1024];
			for (int readNum ; (readNum =fis.read(buf))!=-1;) {
				bos.write(buf,0,readNum);
			}
			
		 book_iamge =bos.toByteArray();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void bookInSert() {

		String one = bookName.getText();
		String two= bookDetalis.getText();
		String sql = "INSERT INTO bookrequest (r_bookName,r_bookDetalis,r_bookPicture) VALUES (?,?,?)";
		try {
		    ps = cn.prepareStatement(sql);
			ps.setString(1, one);
			ps.setString(2, two);
			ps.setBytes(3, book_iamge);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Thanks for Regestation");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	public void bookRequestDataShow() {
		try {
			String sql = "SELECT  r_serial,r_memberID_FK,r_bookName,r_bookDetalis FROM bookrequest";
		    ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			bookRequestTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	String sTable=null;
	public void bookRequestTableDataShow() {
		try {
			int row = bookRequestTable.getSelectedRow();
			 sTable = (bookRequestTable.getModel().getValueAt(row, 0).toString());
			 String sTable1 = (bookRequestTable.getModel().getValueAt(row, 2).toString());
			 String sTable2 = (bookRequestTable.getModel().getValueAt(row, 3).toString());
			 //String sTable3 = (bookRequestTable.getModel().getValueAt(row, 4).toString());
			 bookName.setText(sTable1);
			 bookDetalis.setText(sTable2);
			 String a=showReply(sTable);
			 if(a!=null) {
				 replyTF.setText(a); 
			 }else {
				 JOptionPane.showMessageDialog(null, "Reply Not available!");
			 }
			 showingPicture(sTable);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public String showReply(String a) {
		String sql = "select * from bookrequest where  r_serial=?";
		String data = null;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, a);
			rs = ps.executeQuery();
			while (rs.next()) {
				   data=rs.getString("r_librarian_reply");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return data;
		
	}
	
	public void showingPicture(String a) {
		try {
			String sql="SELECT r_bookPicture from bookrequest WHERE r_serial=?";
					ps=cn.prepareStatement(sql);
					ps.setString(1, a);
					rs=ps.executeQuery();
					if(rs.next()) {
						byte[] imageData=rs.getBytes("r_bookPicture");
						formate=new ImageIcon(imageData);
						bookImageLevel.setIcon(formate);
					}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//Constructor
	public MemberPanel() {
		design();
		clock() ;
		cn = SQLConnection.ConnecrDb();
		bookDataShow();
		libraryrulesDataShow();
		bookRequestDataShow();
	}
	
	/**
	 * Create the frame.
	 */
	
	public void design() {

		setTitle("Member panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 531);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(135, 206, 235));
		tabbedPane.setBounds(10, 50, 864, 431);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		tabbedPane.addTab("Rule", null, panel, null);
		panel.setLayout(null);
		
		libraryRulesTable = new JTable();
		libraryRulesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				libraryrulesTableDataShow();
			}
		});
		libraryRulesTable.setBounds(10, 11, 839, 294);
		panel.add(libraryRulesTable);
		
		rule = new JTextField();
		rule.setFont(new Font("Tahoma", Font.BOLD, 13));
		rule.setBounds(10, 326, 839, 53);
		panel.add(rule);
		rule.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 128, 144));
		tabbedPane.addTab("Borrowed Book", null, panel_1, null);
		panel_1.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(10, 11, 450, 366);
		panel_1.add(table_1);
		
		JLabel lblBookTitle = new JLabel("Book Title\r\n :");
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBookTitle.setBounds(470, 24, 142, 32);
		panel_1.add(lblBookTitle);
		
		JLabel lblBorrowedDate = new JLabel("Borrowed Date:");
		lblBorrowedDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBorrowedDate.setBounds(470, 89, 142, 32);
		panel_1.add(lblBorrowedDate);
		
		JLabel lblReturnedDate = new JLabel("Returned Date :\r\n");
		lblReturnedDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturnedDate.setBounds(470, 150, 142, 32);
		panel_1.add(lblReturnedDate);
		
		JLabel lblFine = new JLabel("Fine :");
		lblFine.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFine.setBounds(470, 216, 142, 32);
		panel_1.add(lblFine);
		
		JLabel lblWarning = new JLabel("Warning :");
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWarning.setBounds(470, 288, 142, 32);
		panel_1.add(lblWarning);
		
		textField = new JTextField();
		textField.setBounds(614, 24, 221, 32);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(614, 89, 221, 32);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(614, 150, 221, 32);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(614, 216, 167, 32);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(614, 270, 235, 96);
		panel_1.add(textField_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(112, 128, 144));
		tabbedPane.addTab("Book Check", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 492, 370);
		panel_2.add(scrollPane);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bookTableDataShow();
			}
		});
		scrollPane.setViewportView(bookTable);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"b_Title"}));
		comboBox.setBounds(526, 21, 131, 28);
		panel_2.add(comboBox);
		
		b_titleTF = new JTextField();
		b_titleTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				bookDataShow2();
			}
		});
		b_titleTF.setBounds(699, 25, 139, 28);
		panel_2.add(b_titleTF);
		b_titleTF.setColumns(10);
		
		bookTitle = new JTextField();
		bookTitle.setColumns(10);
		bookTitle.setBounds(556, 136, 231, 28);
		panel_2.add(bookTitle);
		
		isbn = new JTextField();
		isbn.setColumns(10);
		isbn.setBounds(556, 218, 231, 28);
		panel_2.add(isbn);
		
		bookEdition = new JTextField();
		bookEdition.setColumns(10);
		bookEdition.setBounds(556, 294, 231, 28);
		panel_2.add(bookEdition);
		
		JLabel label = new JLabel("Book Title\r\n :");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(628, 89, 142, 32);
		panel_2.add(label);
		
		JLabel lblIsbnNo = new JLabel("ISBN No.");
		lblIsbnNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIsbnNo.setBounds(628, 175, 142, 32);
		panel_2.add(lblIsbnNo);
		
		JLabel lblBookEdition = new JLabel("Book Edition");
		lblBookEdition.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBookEdition.setBounds(628, 251, 142, 32);
		panel_2.add(lblBookEdition);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setIcon(new ImageIcon(MemberPanel.class.getResource("/Icon/refresh-icon.png")));
		btnNewButton.setBounds(616, 340, 139, 41);
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Book Request", null, panel_3, null);
		panel_3.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(244, 24, 231, 368);
		panel_3.add(desktopPane);
		
		bookImageLevel = new JLabel("");
		bookImageLevel.setBounds(10, 11, 211, 346);
		desktopPane.add(bookImageLevel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(485, 24, 364, 239);
		panel_3.add(scrollPane_1);
		
		bookRequestTable = new JTable();
		bookRequestTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bookRequestTableDataShow();
			}
		});
		scrollPane_1.setViewportView(bookRequestTable);
		
		replyTF = new JTextField();
		replyTF.setBounds(499, 292, 292, 45);
		panel_3.add(replyTF);
		replyTF.setColumns(10);
		
		bookName = new JTextField();
		bookName.setColumns(10);
		bookName.setBounds(10, 51, 211, 45);
		panel_3.add(bookName);
		
		bookDetalis = new JTextField();
		bookDetalis.setColumns(10);
		bookDetalis.setBounds(10, 154, 211, 82);
		panel_3.add(bookDetalis);
		
		JButton btnNewButton_1 = new JButton("Choose Image");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choosPicture();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(48, 262, 142, 45);
		panel_3.add(btnNewButton_1);
		
		JButton btnSendRequest = new JButton("Send Request");
		btnSendRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookInSert();
			}
		});
		btnSendRequest.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSendRequest.setBounds(48, 329, 142, 45);
		panel_3.add(btnSendRequest);
		
		JLabel lblNewLabel = new JLabel("Book Detalis");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(20, 107, 201, 36);
		panel_3.add(lblNewLabel);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBookName.setBounds(10, 11, 201, 36);
		panel_3.add(lblBookName);
		
		clockLabel = new JLabel("");
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setForeground(Color.WHITE);
		clockLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		clockLabel.setBounds(10, 0, 766, 51);
		contentPane.add(clockLabel);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FristPage f= new FristPage();
				f.setVisible(true);
				dispose();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(748, 9, 100, 42);
		contentPane.add(button);
	
	}
}
