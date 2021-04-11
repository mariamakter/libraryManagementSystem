package libarayManagementSystem;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ButtonGroup;


@SuppressWarnings("serial")
public class FristPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FristPage frame = new FristPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void login() {
		if(admin.isSelected()) {
			adminLogin();
		}
		else if(libarian.isSelected()) {
			lbiraianLogin();
		}
		else if(member.isSelected()) {
			memberLogin();
		}else {
			JOptionPane.showMessageDialog(null,"Please Select!!!!!");
		}
	}
	
	public void memberLogin() {
		 String name = userName.getText();
	        String pass= password.getText();
	        try{
	        String sql="SELECT * FROM member where m_username=? and m_password =?";
	            PreparedStatement ps=cn.prepareStatement(sql);
	            ps.setString(1, name);
	            ps.setString(2, pass);
	            ResultSet rs=ps.executeQuery();
	        if(rs.next()){
	        JOptionPane.showMessageDialog(null, "Log in Successful");
	        MemberPanel m=new MemberPanel();
	        m.setVisible(true);
	        dispose();
	        }
	        else{
	            JOptionPane.showMessageDialog(null, "Log In UnSuccessful");
	        }
	        }
	        catch(Exception ex){
	           JOptionPane.showMessageDialog(null, ex);
	        }
	}
	
	public void lbiraianLogin() {

		 String name = userName.getText();
	        String pass= password.getText();
	        try{
	        String sql="SELECT * FROM librarian  where l_username=? and l_password =?";
	            PreparedStatement ps=cn.prepareStatement(sql);
	            ps.setString(1, name);
	            ps.setString(2, pass);
	            ResultSet rs=ps.executeQuery();
	        if(rs.next()){
	        JOptionPane.showMessageDialog(null, "Log in Successful");
	        LibarianPanel l=new LibarianPanel();
	        l.setVisible(true);
	        dispose();
	        }
	        else{
	            JOptionPane.showMessageDialog(null, "Log In UnSuccessful");
	        }
	        }
	        catch(Exception ex){
	           JOptionPane.showMessageDialog(null, ex);
	        }
	
	}
	
	public void adminLogin() {
		 String name = userName.getText();
	        String pass= password.getText();
	        try{
	        String sql="SELECT * FROM admin where a_username=? and a_password =?";
	            PreparedStatement ps=cn.prepareStatement(sql);
	            ps.setString(1, name);
	            ps.setString(2, pass);
	            ResultSet rs=ps.executeQuery();
	        if(rs.next()){
	        JOptionPane.showMessageDialog(null, "Log in Successful");
	        Admin a =new Admin();
	        a.setVisible(true);
	        dispose();
	        }
	        else{
	            JOptionPane.showMessageDialog(null, "Log In UnSuccessful");
	        }
	        }
	        catch(Exception ex){
	           JOptionPane.showMessageDialog(null, ex);
	        }
	}
	

	/**
	 * Create the frame.
	 */
	
	Connection cn = null;
	private JTextField userName;
	private JPasswordField password;
	JRadioButton admin,libarian,member;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public FristPage() {
		design();
		cn = SQLConnection.ConnecrDb();
	}
	public void design() {

		setTitle("Login ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 412);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FristPage.class.getResource("/Icon/username.png")));
		lblNewLabel.setBounds(419, 93, 32, 32);
		contentPane.add(lblNewLabel);
		
		userName = new JTextField();
		userName.setBounds(448, 93, 164, 32);
		contentPane.add(userName);
		userName.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(448, 146, 164, 32);
		contentPane.add(password);
		
	    admin = new JRadioButton("Admin");
		buttonGroup.add(admin);
		admin.setBounds(518, 201, 83, 32);
		contentPane.add(admin);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FristPage.class.getResource("/Icon/admin.png")));
		lblNewLabel_2.setBounds(489, 198, 32, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(FristPage.class.getResource("/Icon/member.png")));
		lblNewLabel_3.setBounds(489, 240, 32, 32);
		contentPane.add(lblNewLabel_3);
		
		libarian = new JRadioButton("Libarian");
		buttonGroup.add(libarian);
		libarian.setBounds(518, 240, 83, 32);
		contentPane.add(libarian);
		
		member = new JRadioButton("Member");
		buttonGroup.add(member);
		member.setBounds(518, 283, 83, 32);
		contentPane.add(member);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FristPage.class.getResource("/Icon/memeber.png")));
		label.setBounds(489, 283, 32, 32);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FristPage.class.getResource("/Icon/change-password-icon.png")));
		lblNewLabel_1.setBounds(419, 146, 32, 32);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setIcon(new ImageIcon(FristPage.class.getResource("/Icon/login.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnNewButton.setBounds(567, 322, 127, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(FristPage.class.getResource("/Icon/background2.jpg")));
		lblNewLabel_4.setBounds(0, 0, 732, 383);
		contentPane.add(lblNewLabel_4);
		
	
	}
}
