package libarayManagementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField name;
	private JTextField email;
	private JTextField username;
	private JTextField password;
	private JTextField contact;
	private JTextField address;
	private JTextField searchTF;
	private JTextField searchData;
	private JTextField name1;
	private JTextField username1;
	private JTextField contact1;
	private JTextField email1;
	private JTextField address1;
	private JTable table;
	JLabel clockLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection cn = null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	private JPasswordField enterPassword;
	private JPasswordField reEnterPassword;
	private JPasswordField passwordField;

	
	//method
	
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
	
	public void signUp() {
		String n = name.getText();
		String e = email.getText();
		String us = username.getText();
		String pass = password.getText();
		String cnn = contact.getText();
		String ad = address.getText();
		String sql = "INSERT INTO librarian (l_name,l_username,l_password,l_email,l_contact,l_address ) VALUES (?,?,?,?,?,?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, e);
			ps.setString(3, us);
			ps.setString(4, pass);
			ps.setString(5, cnn);
			ps.setString(6, ad);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Thanks for Regestation");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	public void searchData() {
		try {
			String name=searchTF.getText();
			String sql = "SELECT * FROM librarian where l_username=?";
		    ps = cn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			searchData.setText(rs.getString("l_username"));
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
	
	public void update() {
		String name=searchTF.getText();
		String enter=enterPassword.getText();
		String enter1=reEnterPassword.getText();
		String pass=null;
		String sql="update  librarian SET l_password = ? WHERE l_username = ?"; 
        try{
             ps=cn.prepareStatement(sql); 
             if(enter.equals(enter1)) {
     			pass=enter;
     		}else {
     			JOptionPane.showMessageDialog(null,"PLease Enter Same password");
     		}
             ps.setString(1, pass);
             ps.setString(2, name);
             ps.executeUpdate();
             System.out.println("Data updated " + name + " " + pass);
          
        }catch(Exception ex)
        {
           System.out.println(ex);
        }

	}
	
	public void dataShow() {
		try {
			String sql = "SELECT *  FROM  librarian";
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void tableDatashow() {
		try {
			int row = table.getSelectedRow();
			 String sTable = (table.getModel().getValueAt(row, 1).toString());
			 String sTable1 = (table.getModel().getValueAt(row, 2).toString());
			 String sTable2 = (table.getModel().getValueAt(row, 4).toString());
			 String sTable3 = (table.getModel().getValueAt(row, 3).toString());
			 String sTable4= (table.getModel().getValueAt(row, 5).toString());
			 String sTable5 = (table.getModel().getValueAt(row, 6).toString());
			 name1.setText(sTable);
			 email1.setText(sTable1);
			 username1.setText(sTable2);
			 passwordField.setText(sTable3);
			 contact1.setText(sTable4);
			 address1.setText(sTable5);
		} catch (Exception e) {
			System.out.println(e);
		}

	}


	public void refresh() {
		name1.setText(null);
		email1.setText(null);
		username1.setText(null);
		passwordField.setText(null);
		contact1.setText(null);
		address1.setText(null);
		dataShow();
	}
	
	public int getLibraianID(String a) {
		int id = 0;
		try {
			int row = table.getSelectedRow();
			String sTable = (table.getModel().getValueAt(row, 0).toString());
		    id= Integer.parseInt(sTable);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	
	}
	
	public void delete() {
		String n = name1.getText();
		int one=getLibraianID(n);
		/*String e = email1.getText();
		String us = username1.getText();
		String pass = passwordField.getText();
		String cnn = contact1.getText();
		String ad = address1.getText();*/
		String sql="DELETE from  librarian where l_ID=?";
        try{
                ps=cn.prepareStatement(sql);
                ps.setInt(1, one);
				/*ps.setString(2, e);
				ps.setString(3, us);
				ps.setString(4, pass);
				ps.setString(5, cnn);
				ps.setString(6, ad);*/
             ps.execute();
            JOptionPane.showMessageDialog(null, "Deleted!");
        }catch(Exception ex)
        {
           System.out.println(ex);
        }

	}
	
	public void lbAdd() {
		String n = name1.getText();
		String e = email1.getText();
		String us = username1.getText();
		String pass = passwordField.getText();
		String cnn = contact1.getText();
		String ad = address1.getText();
		String sql = "INSERT INTO  librarian (l_name,l_username,l_password,l_email,l_contact,l_address) VALUES (?,?,?,?,?,?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, e);
			ps.setString(3, us);
			ps.setString(4, pass);
			ps.setString(5, cnn);
			ps.setString(6, ad);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Thanks for Regestation");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}


	public Admin() {
		design();
		clock() ;
		cn = SQLConnection.ConnecrDb();
		dataShow();
	}
	
	public void design() {

		setTitle("Admin Panel");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 775, 438);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main Panel", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNumberOfMember = new JLabel("Number Of Member:");
		lblNumberOfMember.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumberOfMember.setBounds(10, 118, 166, 37);
		panel.add(lblNumberOfMember);
		
		JLabel lblNumberOfBooks = new JLabel("Number of Books:");
		lblNumberOfBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumberOfBooks.setBounds(10, 172, 166, 37);
		panel.add(lblNumberOfBooks);
		
		JLabel lblNumberOfIssues = new JLabel("Number of Issues:");
		lblNumberOfIssues.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumberOfIssues.setBounds(10, 229, 166, 37);
		panel.add(lblNumberOfIssues);
		
		JLabel lblNumberOfRequests = new JLabel("Number of Requests:");
		lblNumberOfRequests.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumberOfRequests.setBounds(10, 277, 166, 37);
		panel.add(lblNumberOfRequests);
		
		JLabel lblChangeAdminPassword = new JLabel("Change Admin Password");
		lblChangeAdminPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChangeAdminPassword.setBounds(447, 118, 166, 37);
		panel.add(lblChangeAdminPassword);
		
		JLabel lblEnterNewPassword = new JLabel("Enter New password");
		lblEnterNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterNewPassword.setBounds(374, 166, 297, 37);
		panel.add(lblEnterNewPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-Enter Password");
		lblReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblReenterPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReenterPassword.setBounds(374, 239, 297, 37);
		panel.add(lblReenterPassword);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(371, 198, 319, 37);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(374, 277, 319, 37);
		panel.add(textField_1);
		
		JButton btnMemberControl = new JButton("Member Control");
		btnMemberControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MemberSignUp m=new MemberSignUp();
				m.setVisible(true);
				dispose();
			}
		});
		btnMemberControl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMemberControl.setBounds(408, 35, 143, 41);
		panel.add(btnMemberControl);
		
		JButton btnBookIssue = new JButton("Book Issue");
		btnBookIssue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookIssue.setBounds(575, 35, 131, 41);
		panel.add(btnBookIssue);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Librarian Control", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(23, 60, 166, 37);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Email:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(23, 108, 166, 37);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Username:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(23, 156, 166, 37);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Password:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(23, 204, 166, 37);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Contact:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(23, 252, 166, 37);
		panel_1.add(label_4);
		
		JLabel label_6 = new JLabel("Address:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBounds(23, 300, 166, 37);
		panel_1.add(label_6);
		
		JButton button = new JButton("Sign UP");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signUp();
			}
			
		});
		button.setIcon(new ImageIcon(Admin.class.getResource("/Icon/Signup-icon.png")));
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(167, 348, 142, 41);
		panel_1.add(button);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(157, 61, 234, 37);
		panel_1.add(name);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(157, 109, 234, 37);
		panel_1.add(email);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(157, 157, 234, 37);
		panel_1.add(username);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(157, 204, 234, 37);
		panel_1.add(password);
		
		contact = new JTextField();
		contact.setColumns(10);
		contact.setBounds(157, 252, 234, 37);
		panel_1.add(contact);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(157, 301, 234, 37);
		panel_1.add(address);
		
		searchTF = new JTextField();
		searchTF.setColumns(10);
		searchTF.setBounds(432, 72, 171, 37);
		panel_1.add(searchTF);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchData();
			}
		});
		button_1.setIcon(new ImageIcon(Admin.class.getResource("/Icon/Zoom-icon.png")));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_1.setBounds(613, 69, 123, 41);
		panel_1.add(button_1);
		
		searchData = new JTextField();
		searchData.setColumns(10);
		searchData.setBounds(459, 142, 234, 37);
		panel_1.add(searchData);
		
		JLabel label_7 = new JLabel("Enter New password");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBounds(432, 185, 287, 37);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("Re-Enter Password");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_8.setBounds(432, 252, 287, 37);
		panel_1.add(label_8);
		
		JButton button_2 = new JButton("Change");
		button_2.setIcon(new ImageIcon(Admin.class.getResource("/Icon/Arrows-Up-2-icon.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
			
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_2.setBounds(534, 332, 142, 41);
		panel_1.add(button_2);
		
		JLabel lblLibrarian = new JLabel("Librarian Signup");
		lblLibrarian.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibrarian.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLibrarian.setBounds(10, 12, 367, 37);
		panel_1.add(lblLibrarian);
		
		JLabel label_5 = new JLabel("Password change");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(369, 13, 367, 37);
		panel_1.add(label_5);
		
		enterPassword = new JPasswordField();
		enterPassword.setBounds(442, 213, 277, 37);
		panel_1.add(enterPassword);
		
		reEnterPassword = new JPasswordField();
		reEnterPassword.setBounds(442, 284, 277, 37);
		panel_1.add(reEnterPassword);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Manage Librarian", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_9 = new JLabel("Name:");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_9.setBounds(75, 160, 166, 37);
		panel_2.add(label_9);
		
		JLabel label_10 = new JLabel("Username:");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_10.setBounds(75, 227, 166, 37);
		panel_2.add(label_10);
		
		name1 = new JTextField();
		name1.setColumns(10);
		name1.setBounds(24, 196, 231, 37);
		panel_2.add(name1);
		
		username1 = new JTextField();
		username1.setColumns(10);
		username1.setBounds(24, 259, 231, 37);
		panel_2.add(username1);
		
		JLabel label_12 = new JLabel("Contact:");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_12.setBounds(75, 287, 166, 37);
		panel_2.add(label_12);
		
		contact1 = new JTextField();
		contact1.setColumns(10);
		contact1.setBounds(24, 319, 231, 37);
		panel_2.add(contact1);
		
		JLabel label_13 = new JLabel("Email:");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_13.setBounds(345, 180, 166, 37);
		panel_2.add(label_13);
		
		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(345, 228, 231, 37);
		panel_2.add(email1);
		
		JLabel label_14 = new JLabel("Password:");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_14.setBounds(345, 258, 166, 37);
		panel_2.add(label_14);
		
		JLabel label_15 = new JLabel("Address:");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_15.setBounds(24, 362, 166, 37);
		panel_2.add(label_15);
		
		address1 = new JTextField();
		address1.setColumns(10);
		address1.setBounds(102, 362, 442, 37);
		panel_2.add(address1);
		
		JButton button_3 = new JButton("Add");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbAdd();
			}
		});
		button_3.setIcon(new ImageIcon(Admin.class.getResource("/Icon/Button-Add-icon.png")));
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_3.setBounds(606, 244, 129, 41);
		panel_2.add(button_3);
		
		JButton button_4 = new JButton("Refresh");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		button_4.setIcon(new ImageIcon(Admin.class.getResource("/Icon/refresh-icon.png")));
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_4.setBounds(606, 345, 129, 41);
		panel_2.add(button_4);
		
		JButton button_5 = new JButton("Delete");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		button_5.setIcon(new ImageIcon(Admin.class.getResource("/Icon/delete.png")));
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_5.setBounds(606, 296, 129, 41);
		panel_2.add(button_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 725, 159);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tableDatashow();
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setBounds(345, 296, 231, 37);
		panel_2.add(passwordField);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Log", null, panel_3, null);
		panel_3.setLayout(null);
		
		clockLabel = new JLabel("");
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		clockLabel.setBounds(10, 0, 775, 47);
		contentPane.add(clockLabel);
		
		JButton button_6 = new JButton("Logout");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FristPage f= new FristPage();
				f.setVisible(true);
				dispose();
			}
		});
		button_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_6.setBounds(656, 22, 100, 42);
		contentPane.add(button_6);
	
	}
}
