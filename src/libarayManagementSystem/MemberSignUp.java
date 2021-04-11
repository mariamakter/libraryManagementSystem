package libarayManagementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MemberSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField username;
	private JTextField password;
	private JTextField contact;
	private JTextField institution;
	private JTextField address;
	private JTextField searchTF;
	private JTextField searchData;
	private JTextField name1;
	private JTextField username1;
	private JTextField contact1;
	private JTextField institution1;
	private JTable table;
	private JTextField email1;
	private JTextField address1;
	JLabel clockLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberSignUp frame = new MemberSignUp();
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
	private JPasswordField passwordField;
	private JPasswordField enterPassword;
	private JPasswordField reEnterPassword;
	String na, em, us, pa, co, insti, add;

	// method

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
		String in = institution.getText();
		String ad = address.getText();
		String sql = "INSERT INTO member (m_name,m_email,m_username,m_password,m_contact,m_institute,m_adress) VALUES (?,?,?,?,?,?,?)";
		try {
		    ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, e);
			ps.setString(3, us);
			ps.setString(4, pass);
			ps.setString(5, cnn);
			ps.setString(6, in);
			ps.setString(7, ad);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Thanks for Regestation");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void searchData() {
		// String name = searchTF.getText();
		String sql = "SELECT * FROM member where m_username= '" + searchTF.getText() + "'";
		try {
			ps = cn.prepareStatement(sql);
			// ps.setString(1, name);
			rs = ps.executeQuery();
			// String data = rs.getString("m_username");
			while (rs.next()) {
				searchData.setText(rs.getString("m_username"));
			}

			// System.out.println(rs);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void update() {
		String name = searchTF.getText();
		String enter = enterPassword.getText();
		String enter1 = reEnterPassword.getText();
		String pass = null;
		String sql = "update member SET m_password = ? WHERE m_username = ?";
		try {
		    ps = cn.prepareStatement(sql);
			if (enter.equals(enter1)) {
				pass = enter;
			} else {
				JOptionPane.showMessageDialog(null, "PLease Enter Same password");
			}
			ps.setString(1, pass);
			ps.setString(2, name);
			ps.executeUpdate();
			//System.out.println("Data updated " + name + " " + pass);
            JOptionPane.showMessageDialog(null," Password Change Successfully!");
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public void dataShow() {
		try {
			String sql = "SELECT * FROM member";
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
			String sTable2 = (table.getModel().getValueAt(row, 3).toString());
			String sTable3 = (table.getModel().getValueAt(row, 4).toString());
			String sTable4 = (table.getModel().getValueAt(row, 5).toString());
			String sTable5 = (table.getModel().getValueAt(row, 6).toString());
			String sTable6 = (table.getModel().getValueAt(row, 7).toString());
			name1.setText(sTable);
			email1.setText(sTable1);
			username1.setText(sTable2);
			passwordField.setText(sTable3);
			contact1.setText(sTable4);
			institution1.setText(sTable5);
			address1.setText(sTable6);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void refresh() {
		name1.setText(null);
		email1.setText(null);
		username1.setText(null);
		passwordField.setText(null);
		institution1.setText(null);
		contact1.setText(null);
		address1.setText(null);
		dataShow();
	}


	public void newAdd() {
		String n = name1.getText();
		String e = email1.getText();
		String us = username1.getText();
		String pass = passwordField.getText();
		String cnn = contact1.getText();
		String in = institution1.getText();
		String ad = address1.getText();
		String sql = "INSERT INTO member (m_name,m_email,m_username,m_password,m_contact,m_institute,m_adress) VALUES (?,?,?,?,?,?,?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, e);
			ps.setString(3, us);
			ps.setString(4, pass);
			ps.setString(5, cnn);
			ps.setString(6, in);
			ps.setString(7, ad);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Thanks for Regestation");
		} catch (Exception ex) {
			System.out.println(ex);
		}

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
		String sql="DELETE from  member where m_ID=?";
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

	public MemberSignUp() {
		design();
		clock();
		cn = SQLConnection.ConnecrDb();
		dataShow();
	}

	public void design() {

		setTitle("Member Join");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 50, 760, 473);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Member Control", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Member Signup");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 367, 37);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 48, 166, 37);
		panel.add(lblNewLabel_1);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(10, 96, 166, 37);
		panel.add(lblEmail);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(10, 144, 166, 37);
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(10, 192, 166, 37);
		panel.add(lblPassword);

		JLabel lblContact = new JLabel("Contact:");
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContact.setBounds(10, 240, 166, 37);
		panel.add(lblContact);

		JLabel lblInstitution = new JLabel("Institution:");
		lblInstitution.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInstitution.setBounds(10, 288, 166, 37);
		panel.add(lblInstitution);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddress.setBounds(10, 333, 166, 37);
		panel.add(lblAddress);

		JButton btnNewButton = new JButton("Sign UP");
		btnNewButton.setIcon(new ImageIcon(MemberSignUp.class.getResource("/Icon/Signup-icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signUp();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(124, 381, 142, 41);
		panel.add(btnNewButton);

		name = new JTextField();
		name.setBounds(144, 48, 234, 37);
		panel.add(name);
		name.setColumns(10);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(144, 96, 234, 37);
		panel.add(email);

		username = new JTextField();
		username.setColumns(10);
		username.setBounds(144, 144, 234, 37);
		panel.add(username);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(144, 192, 234, 37);
		panel.add(password);

		contact = new JTextField();
		contact.setColumns(10);
		contact.setBounds(144, 240, 234, 37);
		panel.add(contact);

		institution = new JTextField();
		institution.setColumns(10);
		institution.setBounds(144, 288, 234, 37);
		panel.add(institution);

		address = new JTextField();
		address.setColumns(10);
		address.setBounds(144, 336, 234, 37);
		panel.add(address);

		JLabel lblPasswordChange = new JLabel("Password change");
		lblPasswordChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordChange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPasswordChange.setBounds(368, 0, 367, 37);
		panel.add(lblPasswordChange);

		searchTF = new JTextField();
		searchTF.setColumns(10);
		searchTF.setBounds(419, 81, 171, 37);
		panel.add(searchTF);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchData();
			}
		});
		btnSearch.setIcon(new ImageIcon(MemberSignUp.class.getResource("/Icon/Zoom-icon.png")));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.setBounds(590, 78, 145, 41);
		panel.add(btnSearch);

		searchData = new JTextField();
		searchData.setColumns(10);
		searchData.setBounds(446, 161, 234, 37);
		panel.add(searchData);

		JLabel lblEnterNewPassword = new JLabel("Enter New password");
		lblEnterNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterNewPassword.setBounds(419, 204, 287, 37);
		panel.add(lblEnterNewPassword);

		JLabel lblReenterPassword = new JLabel("Re-Enter Password");
		lblReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblReenterPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReenterPassword.setBounds(419, 288, 287, 37);
		panel.add(lblReenterPassword);

		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}

		});
		btnChange.setIcon(new ImageIcon(MemberSignUp.class.getResource("/Icon/Arrows-Up-2-icon.png")));
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChange.setBounds(524, 381, 142, 41);
		panel.add(btnChange);

		enterPassword = new JPasswordField();
		enterPassword.setBounds(429, 249, 277, 37);
		panel.add(enterPassword);

		reEnterPassword = new JPasswordField();
		reEnterPassword.setBounds(429, 333, 277, 37);
		panel.add(reEnterPassword);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Manage Member", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(74, 195, 166, 37);
		panel_1.add(label);

		JLabel label_1 = new JLabel("Username:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(74, 262, 166, 37);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("Institution:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(362, 322, 166, 37);
		panel_1.add(label_2);

		name1 = new JTextField();
		name1.setColumns(10);
		name1.setBounds(23, 231, 231, 37);
		panel_1.add(name1);

		username1 = new JTextField();
		username1.setColumns(10);
		username1.setBounds(23, 294, 231, 37);
		panel_1.add(username1);

		JLabel label_3 = new JLabel("Contact:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(74, 322, 166, 37);
		panel_1.add(label_3);

		contact1 = new JTextField();
		contact1.setColumns(10);
		contact1.setBounds(23, 354, 231, 37);
		panel_1.add(contact1);

		institution1 = new JTextField();
		institution1.setColumns(10);
		institution1.setBounds(344, 354, 231, 37);
		panel_1.add(institution1);

		JLabel label_4 = new JLabel("Email:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(362, 195, 166, 37);
		panel_1.add(label_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 711, 173);
		panel_1.add(scrollPane);
		
				table = new JTable();
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						tableDatashow();
					}
				});

		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(344, 231, 231, 37);
		panel_1.add(email1);

		JLabel label_5 = new JLabel("Password:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setBounds(362, 262, 166, 37);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("Address:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBounds(23, 397, 166, 37);
		panel_1.add(label_6);

		address1 = new JTextField();
		address1.setColumns(10);
		address1.setBounds(101, 397, 442, 37);
		panel_1.add(address1);

		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newAdd();
			}
		});
		button.setIcon(new ImageIcon(MemberSignUp.class.getResource("/Icon/Button-Add-icon.png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(604, 271, 127, 41);
		panel_1.add(button);

		JButton button_1 = new JButton("Refresh");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		button_1.setIcon(new ImageIcon(MemberSignUp.class.getResource("/Icon/refresh-icon.png")));
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(604, 375, 127, 41);
		panel_1.add(button_1);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		button_2.setIcon(new ImageIcon(MemberSignUp.class.getResource("/Icon/delete.png")));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_2.setBounds(604, 323, 127, 41);
		panel_1.add(button_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(344, 294, 231, 37);
		panel_1.add(passwordField);

		clockLabel = new JLabel("");
		clockLabel.setHorizontalAlignment(SwingConstants.LEFT);
		clockLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		clockLabel.setBounds(236, 11, 320, 42);
		contentPane.add(clockLabel);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LibarianPanel l= new LibarianPanel();
				l.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(517, 11, 100, 42);
		contentPane.add(btnNewButton_1);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FristPage f= new FristPage();
				f.setVisible(true);
				dispose();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogout.setBounds(628, 11, 100, 42);
		contentPane.add(btnLogout);

	}
}
