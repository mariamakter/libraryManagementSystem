package libarayManagementSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;

public class LibarianPanel extends JFrame {

	private JPanel contentPane;
	private JTextField m_IDTF;
	private JTextField b_titleTF;
	private JTable memberTable;
	private JTable bookTable1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table_2;
	private JTable table_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField bookSearchTF;
	private JTable bookTable;
	private JTextField bookTitle;
	private JTextField isbnNO;
	private JTextField bookEdition;
	private JTextField langauge;
	private JTextField location;
	private JTextField bookQuantity;
	private JTextField price;
	private JTextField categoryTextFeild;
	private JTextField autherText;
	private JTextField publicationText;
	private JTextField suplierText;
	private JTable supplierTable;
	private JTextField s_nameTF;
	private JTextField sName;
	private JTextField sContact;
	private JTextField saddress;
	private JTextField sDescription;
	private JTable libraryrulesTable;
	private JTextField rule;
	JLabel clockLabel;
	JLabel memberNameLevel,MemberIDLevel,memberContactLevel,memberEmailLevel,bookID,bookTitleLabel,ISBNLevel,LocationLevel,bookImageLevel;
	
	JComboBox Supplier,auther,publication,bookcategory;
	JDateChooser dateChooser,dateChooser_5,dateChooser_4;
	ImageIcon formate=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibarianPanel frame = new LibarianPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection cn = null;
	PreparedStatement ps=null;
	ResultSet rs =null;
	private JTable bookcategoryTable;
	private JTextField category;
	private JTextField catDetalis;
	private JTable autherTable;
	private JTextField name1;
	private JTextField Details;
	private JTable publicationTable;
	private JTextField name;
	private JTextField address;
	private JTextField detalis1;
	private JTable bookRequestTable;
	private JTextField bookNameTF;
	private JTextField bookDetalisTF;
	private JTextField replyTF;
	/**
	 * Create the frame.
	 */
	
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
	
	public int publicationSearchData() {
		String five = publication.getSelectedItem().toString();
		String sql = "select p_ID from publication where p_name =?";
		int data = 0;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
			rs = ps.executeQuery();
			while (rs.next()) {
				 data=rs.getInt("p_ID");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return data;
	}
	
	public int AuthorSearchData() {
		String five = auther.getSelectedItem().toString();
		String sql = "select * from auther where at_name =?";
		int data = 0;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
		    rs = ps.executeQuery();
			while (rs.next()) {
				 data=rs.getInt("at_serial");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return data;
	}
	
    public void bookSearchData() {
		
		String five =bookSearchTF.getText();
		String sql = "select b_title,b_categoryID_FK,b_language,b_quantity from book where b_Title=?";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
			rs = ps.executeQuery();
			bookTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	
	public void supplierSearchData1(){
		
		String five = s_nameTF.getText();
		String sql = "select * from supplier where s_name =?";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
			rs = ps.executeQuery();
			supplierTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public int supplierSearchData() {
		String five = Supplier.getSelectedItem().toString();
		String sql = "select * from supplier where s_name =?";
		int data = 0;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
			rs = ps.executeQuery();
			while (rs.next()) {
				data=rs.getInt("s_ID");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return data;
	}
	
	public int bookcategorySearchData() {
		String five =bookcategory.getSelectedItem().toString();
		String sql = "select * from bookcategory where category =?";
		int data = 0;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
			rs = ps.executeQuery();
			while (rs.next()) {
				   data=rs.getInt("c_ID");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return data;
	}
	
	public void bookAdd() {
		String isbn = isbnNO.getText();
		int one=Integer.parseInt(isbn);
		String two = bookTitle.getText();
		int three = bookcategorySearchData();
		int four = AuthorSearchData();
		int five = publicationSearchData();
		//date six = dateChooser.getDateFormatString();
		String seven = bookEdition.getText();
		String eight = langauge.getText();
		String nine = location.getText();
		String q = bookQuantity.getText();
		int ten=Integer.parseInt(q);
		String p = price.getText();
		Double eleven=Double.parseDouble(p);
		int ad =supplierSearchData();
		//System.out.println(select p_ID from publication where p_name = 'five');
		String sql = "INSERT INTO book (b_isbn,b_title,b_categoryID_FK,b_authorID_FK,b_publicationID_FK,b_publication_date,b_edition,b_language,b_location,b_quantity,b_price,b_supplierld_FK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    ps = cn.prepareStatement(sql);
			ps.setInt(1, one);
			ps.setString(2, two);
			ps.setInt(3, three);
			ps.setInt(4, four);
			ps.setInt(5, five);
			ps.setString(6, ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText());
			ps.setString(7, seven);
			ps.setString(8, eight);
			ps.setString(9, nine);
			ps.setInt(10, ten);
			ps.setDouble(11, eleven);
			ps.setInt(12, ad);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Book Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public int getMemberId() {
		int id = 0;
		try {
			int row = memberTable.getSelectedRow();
			String sTable = (memberTable.getModel().getValueAt(row, 0).toString());
		    id= Integer.parseInt(sTable);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
	
	public int getBookId() {
		int id = 0;
		try {
			int row = bookTable1.getSelectedRow();
			String sTable = (bookTable1.getModel().getValueAt(row, 0).toString());
		    id= Integer.parseInt(sTable);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
	
	public void issueBook() {
		int one=getMemberId();
		int two =getBookId();
		String sql = "INSERT INTO bookissue (i_memberID_FK,i_bookID_FK,i_date,i_return,i_returned,i_fineID_FK,i_librarianID_FK ) VALUES (?,?,?,?,?,?,?)";
		try {
		    ps = cn.prepareStatement(sql);
			ps.setInt(1, one);
			ps.setInt(2, two);
			ps.setString(3, ((JTextField) dateChooser_4.getDateEditor().getUiComponent()).getText());
			ps.setString(4, ((JTextField) dateChooser_5.getDateEditor().getUiComponent()).getText());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Book Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	public void addAu() {
		String n = name1.getText();
		String e = Details.getText();
		String sql = "INSERT INTO auther (at_name,at_details) VALUES (?,?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, e);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	public void addPub() {
		String n = name.getText();
		String e = detalis1.getText();
		String ad= address.getText();
		String sql = "INSERT INTO publication (p_name ,p_address ,p_details 	) VALUES (?,?,?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, ad);
			ps.setString(3, e);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	public void addS() {
		String n = sName.getText();
		String des = sDescription.getText();
		String ad= saddress.getText();
		String con= sContact.getText();
		String sql = "INSERT INTO supplier (s_name,s_contact ,s_address ,s_desripiton ) VALUES (?,?,?,?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, con);
			ps.setString(3, ad);
			ps.setString(4, des);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	public void catAdd() {
		String n = category.getText();
		String des = catDetalis.getText();
		String sql = "INSERT INTO bookcategory (category,c_detalis) VALUES (?,?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, des);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	public void addRule() {
		String n = rule.getText();
		String sql = "INSERT INTO libraryrules (rule) VALUES (?)";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, n);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Rule Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	//ComboBox FILL
	public void supplierfillcombobox() {
		String sql = "SELECT * from supplier";
		try {
			ps = cn.prepareStatement(sql);
		    rs=ps.executeQuery();
			while(rs.next()) {
				Supplier.addItem(rs.getString("s_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void autherfillcombobox() {
		String sql = "SELECT * from auther";
		try {
			ps = cn.prepareStatement(sql);
		    rs=ps.executeQuery();
			while(rs.next()) {
				auther.addItem(rs.getString("at_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void publicationfillcombobox() {
		String sql = "SELECT * from publication";
		try {
			ps = cn.prepareStatement(sql);
		    rs=ps.executeQuery();
			while(rs.next()) {
				publication.addItem(rs.getString("p_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void bookcategoryfillcombobox() {
		String sql = "SELECT * from bookcategory";
		try {
			ps = cn.prepareStatement(sql);
		    rs=ps.executeQuery();
			while(rs.next()) {
				bookcategory.addItem(rs.getString("category"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	//Data Show & Table Data Show
	public void memberDataShow1() {
		String five = m_IDTF.getText();
		String sql = "SELECT m_ID,m_name,m_username,m_contact FROM member WHERE m_ID=?";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, five);
			rs = ps.executeQuery();
			memberTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void memberDataShow() {
		try {
			String sql = "SELECT m_ID,m_name,m_username,m_contact FROM member";
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			memberTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void bookDataShow2() {
		String one= b_titleTF.getText();
		String sql = "SELECT b_serial,b_isbn,b_title,b_language FROM book WHERE b_title=?";
		try {
			 ps = cn.prepareStatement(sql);
			 ps.setString(1, one);
			 rs = ps.executeQuery();
			 bookTable1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void bookcategoryDataShow() {
		try {
			String sql = "SELECT *FROM bookcategory";
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			bookcategoryTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	public void autherDataShow() {
		try {
			String sql = "SELECT *FROM auther";
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			autherTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	
	public void publicationDataShow() {
		try {
			String sql = "SELECT *FROM publication";
			 ps = cn.prepareStatement(sql);
			 rs = ps.executeQuery();
			publicationTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	
	public void supplierDataShow() {
		try {
			String sql = "SELECT *FROM supplier";
		    ps = cn.prepareStatement(sql);
			rs = ps.executeQuery();
			supplierTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	public void bookDataShow() {
		try {
			String sql = "SELECT b_title,b_categoryID_FK,b_language,b_quantity FROM book";
			 ps = cn.prepareStatement(sql);
			 rs = ps.executeQuery();
             bookTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void libraryrulesDataShow() {
		try {
			String sql = "SELECT l_ID_FK,rule  FROM libraryrules";
			 ps = cn.prepareStatement(sql);
			 rs = ps.executeQuery();
			 libraryrulesTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void libraryrulesTableDataShow() {

		try {
			int row = libraryrulesTable.getSelectedRow();
			 String sTable1 = (libraryrulesTable.getModel().getValueAt(row, 1).toString());
             rule.setText(sTable1);
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	
	public void bookDataShow1() {
		try {
			String sql = "SELECT b_serial,b_isbn,b_title,b_language FROM book";
			 ps = cn.prepareStatement(sql);
			 rs = ps.executeQuery();
			 bookTable1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public String getBookLocation(String a) {
		String sql = "select * from book where b_serial =?";
		String data = null;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, a);
			rs = ps.executeQuery();
			while (rs.next()) {
				   data=rs.getString("b_location");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return data;
	}
	
	
	public void bookTable1Datashow() {
		try {
			int row = bookTable1.getSelectedRow();
			 String sTable = (bookTable1.getModel().getValueAt(row, 0).toString());
			 String sTable1 = (bookTable1.getModel().getValueAt(row, 1).toString());
			 String sTable2 = (bookTable1.getModel().getValueAt(row, 2).toString());
			 String sTable3 = getBookLocation(sTable);
			 bookID.setText(sTable);
			 ISBNLevel.setText(sTable1);
			 bookTitleLabel.setText(sTable2);
			 LocationLevel.setText(sTable3);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	public String getMemberEmail(String a) {
		String sql = "select * from member where m_ID =?";
		String data = null;
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, a);
			rs = ps.executeQuery();
			while (rs.next()) {
				   data=rs.getString("m_email");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return data;
	}
	
	 
	
	
	public void memberTableDatashow() {
		try {
			int row = memberTable.getSelectedRow();
			 String sTable = (memberTable.getModel().getValueAt(row, 0).toString());
			 String sTable1 = (memberTable.getModel().getValueAt(row, 1).toString());
			 String sTable2 = (memberTable.getModel().getValueAt(row, 3).toString());
			 String sTable3 = getMemberEmail(sTable);
			 MemberIDLevel.setText(sTable);
			 memberNameLevel.setText(sTable1);
			 memberContactLevel.setText(sTable2);
			 memberEmailLevel.setText(sTable3);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void supplierTableDatashow() {
		try {
			int row = supplierTable.getSelectedRow();
			 String sTable = (supplierTable.getModel().getValueAt(row, 1).toString());
			 String sTable1 = (supplierTable.getModel().getValueAt(row, 2).toString());
			 String sTable2 = (supplierTable.getModel().getValueAt(row, 3).toString());
			 String sTable3 = (supplierTable.getModel().getValueAt(row, 4).toString());
			 sName.setText(sTable);
			 saddress.setText(sTable2);
			 sDescription.setText(sTable3);
			 sContact.setText(sTable1);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	public void autherTableDatashow() {
		try {
			int row = autherTable.getSelectedRow();
			 String sTable = (autherTable.getModel().getValueAt(row, 1).toString());
			 String sTable1 = (autherTable.getModel().getValueAt(row, 2).toString());
			 name1.setText(sTable);
			 Details.setText(sTable1);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void publicationTableDatashow() {
		try {
			int row = publicationTable.getSelectedRow();
			 String sTable = (publicationTable.getModel().getValueAt(row, 1).toString());
			 String sTable1 = (publicationTable.getModel().getValueAt(row, 2).toString());
			 String sTable2 = (publicationTable.getModel().getValueAt(row, 3).toString());
			 name.setText(sTable);
			 address.setText(sTable1);
			 detalis1.setText(sTable2);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void bookcategoryTableDatashow() {
		try {
			int row = bookcategoryTable.getSelectedRow();
			 String sTable = (bookcategoryTable.getModel().getValueAt(row, 1).toString());
			 String sTable1 = (bookcategoryTable.getModel().getValueAt(row, 2).toString());
			 category.setText(sTable);
			 catDetalis.setText(sTable1);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void bookTableDataShow() {
		try {
			int row = supplierTable.getSelectedRow();
			 String sTable = (supplierTable.getModel().getValueAt(row, 1).toString());
			 String sTable1 = (supplierTable.getModel().getValueAt(row, 2).toString());
			 String sTable2 = (supplierTable.getModel().getValueAt(row, 3).toString());
			 String sTable3 = (supplierTable.getModel().getValueAt(row, 4).toString());
			 sName.setText(sTable);
			 saddress.setText(sTable2);
			 sDescription.setText(sTable3);
			 sContact.setText(sTable1);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	

	public void bookRequestDataShow() {
		try {
			String sql = "SELECT  r_serial,r_memberID_FK,r_bookName,r_bookDetalis,r_bookPicture FROM bookrequest";
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
			 bookNameTF.setText(sTable1);
			 bookDetalisTF.setText(sTable2);
			 showingPicture(sTable);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	//Refresh method
	public void suplierRefresh() {
		sName.setText(null);
		saddress.setText(null);
		sContact.setText(null);
		sDescription.setText(null);
		supplierDataShow();
	}
	
	public void catRefresh() {
		category.setText(null);
		catDetalis.setText(null);
		bookcategoryDataShow();
	}
	
	public void publishRefresh() {
		name.setText(null);
		address.setText(null);
		detalis1.setText(null);
		publicationDataShow();
	}
	
	public void autherRefresh() {
		name1.setText(null);
		Details.setText(null);
		autherDataShow();
	}
	
	public void bookRefresh() {
		isbnNO.setText(null);
		bookTitle.setText(null);
		bookEdition.setText(null);
		bookQuantity.setText(null);
		langauge.setText(null);
		location.setText(null);
		dateChooser.setDate(null);
		price.setText(null);
		Supplier.setSelectedIndex(0);
		publication.setSelectedIndex(0);
		auther.setSelectedIndex(0);
		bookcategory.setSelectedIndex(0);
	}
	
	public void reload() {
		 MemberIDLevel.setText(null);
		 memberNameLevel.setText(null);
		 memberContactLevel.setText(null);
		 memberEmailLevel.setText(null);
		 bookID.setText(null);
		 ISBNLevel.setText(null);
		 bookTitleLabel.setText(null);
		 LocationLevel.setText(null);
		 memberDataShow();
		 bookDataShow1();
	}
	
	
	//Picture Mathod
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
	
	
	public void reply() {
		String one = replyTF.getText();
		String sql = "UPDATE bookrequest SET r_librarian_reply=? WHERE r_serial =?";
		try {
			ps = cn.prepareStatement(sql);
			ps.setString(1, one);
			ps.setString(2, sTable);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	
	
	//Constructor
	public LibarianPanel() {
		design();
		clock();
		cn = SQLConnection.ConnecrDb();
		bookcategoryfillcombobox();
		publicationfillcombobox();
		autherfillcombobox() ;
		supplierfillcombobox();
		bookcategoryDataShow();
		autherDataShow();
		publicationDataShow();
		supplierDataShow();
		bookDataShow();
		bookDataShow1();
		memberDataShow();
		libraryrulesDataShow();
		bookRequestDataShow();
	}
	

	
	//Design
   public void design() {

		setTitle("Book");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1056, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 62, 1030, 598);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		tabbedPane.addTab("Book Issue", null, panel, null);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"m_ID"}));
		comboBox.setBounds(10, 11, 115, 35);
		panel.add(comboBox);
		
		m_IDTF = new JTextField();
		m_IDTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				memberDataShow1();
			}
		});
		m_IDTF.setBounds(135, 13, 174, 33);
		panel.add(m_IDTF);
		m_IDTF.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"b_tilte"}));
		comboBox_1.setBounds(551, 11, 115, 35);
		panel.add(comboBox_1);
		
		b_titleTF = new JTextField();
		b_titleTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				bookDataShow2();
			}
		});
		b_titleTF.setColumns(10);
		b_titleTF.setBounds(681, 12, 160, 33);
		panel.add(b_titleTF);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 57, 472, 216);
		panel.add(scrollPane_4);
		
		memberTable = new JTable();
		scrollPane_4.setViewportView(memberTable);
		memberTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			 memberTableDatashow();
			}
			
		});
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(512, 57, 477, 216);
		panel.add(scrollPane_5);
		
		bookTable1 = new JTable();
		scrollPane_5.setViewportView(bookTable1);
		bookTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bookTable1Datashow();
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("Member ID:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 284, 183, 35);
		panel.add(lblNewLabel_4);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName_1.setBounds(10, 330, 183, 35);
		panel.add(lblName_1);
		
		JLabel lblContact_1 = new JLabel("Contact:");
		lblContact_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContact_1.setBounds(10, 376, 183, 35);
		panel.add(lblContact_1);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(10, 422, 183, 35);
		panel.add(lblEmail);
		
	    MemberIDLevel = new JLabel("");
	    MemberIDLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    MemberIDLevel.setBounds(244, 284, 183, 35);
	    panel.add(MemberIDLevel);
	    
	    memberNameLevel = new JLabel("");
	    memberNameLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    memberNameLevel.setBounds(244, 330, 183, 35);
	    panel.add(memberNameLevel);
	    
	    memberContactLevel = new JLabel("");
	    memberContactLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    memberContactLevel.setBounds(244, 376, 183, 35);
	    panel.add(memberContactLevel);
	    
	    memberEmailLevel = new JLabel("");
	    memberEmailLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    memberEmailLevel.setBounds(244, 422, 183, 35);
	    panel.add(memberEmailLevel);
	    
	    JLabel lblBookId = new JLabel("Book ID:");
	    lblBookId.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblBookId.setBounds(512, 284, 183, 35);
	    panel.add(lblBookId);
	    
	    JLabel lblTitle = new JLabel("Title:");
	    lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblTitle.setBounds(512, 330, 183, 35);
	    panel.add(lblTitle);
	    
	    JLabel lblIsbn = new JLabel("ISBN:");
	    lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblIsbn.setBounds(512, 376, 183, 35);
	    panel.add(lblIsbn);
	    
	    JLabel lblLocation_1 = new JLabel("Location:");
	    lblLocation_1.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblLocation_1.setBounds(512, 424, 183, 35);
	    panel.add(lblLocation_1);
	    
	    bookID = new JLabel("");
	    bookID.setFont(new Font("Tahoma", Font.BOLD, 13));
	    bookID.setBounds(740, 284, 183, 35);
	    panel.add(bookID);
	    
	    bookTitleLabel = new JLabel("");
	    bookTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    bookTitleLabel.setBounds(740, 330, 183, 35);
	    panel.add(bookTitleLabel);
	    
	    ISBNLevel = new JLabel("");
	    ISBNLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    ISBNLevel.setBounds(740, 376, 183, 35);
	    panel.add(ISBNLevel);
	    
	    LocationLevel = new JLabel("");
	    LocationLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
	    LocationLevel.setBounds(740, 424, 183, 35);
	    panel.add(LocationLevel);
	    
	    JLabel lblIssueDate_1 = new JLabel("Issue Date:");
	    lblIssueDate_1.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblIssueDate_1.setBounds(10, 468, 183, 35);
	    panel.add(lblIssueDate_1);
	    
	    JLabel lblReturnDate_1 = new JLabel("Return Date:");
	    lblReturnDate_1.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblReturnDate_1.setBounds(10, 514, 183, 35);
	    panel.add(lblReturnDate_1);
	    
	    JButton btnIssueBook = new JButton("Issue Book");
	    btnIssueBook.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		issueBook();
	    	}
	    });
	    btnIssueBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnIssueBook.setBounds(376, 508, 127, 41);
	    panel.add(btnIssueBook);
	    
	    JButton btnReload = new JButton("Reload");
	    btnReload.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		reload();
	    	}
	    });
	    btnReload.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnReload.setBounds(551, 508, 127, 41);
	    panel.add(btnReload);
	    
	    JButton btnPrintRecipet = new JButton("Print Recipet");
	    btnPrintRecipet.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnPrintRecipet.setBounds(720, 510, 127, 41);
	    panel.add(btnPrintRecipet);
	    
	    JButton btnSavePdf = new JButton("Save PDF");
	    btnSavePdf.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnSavePdf.setBounds(888, 508, 127, 41);
	    panel.add(btnSavePdf);
	    
	    dateChooser_4 = new JDateChooser();
	    dateChooser_4.setBounds(118, 476, 174, 27);
	    dateChooser_4.setDateFormatString("yyyy-MM-dd");
	    panel.add(dateChooser_4);
	    
	    dateChooser_5 = new JDateChooser();
	    dateChooser_5.setBounds(118, 522, 174, 27);
	    dateChooser_5.setDateFormatString("yyyy-MM-dd");
	    panel.add(dateChooser_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 128, 144));
		tabbedPane.addTab("Return Book", null, panel_1, null);
		panel_1.setLayout(null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 11, 108, 33);
		panel_1.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(600, 12, 108, 30);
		panel_1.add(comboBox_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(128, 11, 174, 33);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(711, 11, 174, 33);
		panel_1.add(textField_3);
		
		table_2 = new JTable();
		table_2.setBounds(10, 55, 473, 175);
		panel_1.add(table_2);
		
		table_3 = new JTable();
		table_3.setBounds(538, 55, 457, 175);
		panel_1.add(table_3);
		
		JLabel lblNewLabel = new JLabel("Member Name");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 234, 174, 47);
		panel_1.add(lblNewLabel);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setForeground(new Color(0, 0, 0));
		lblBookName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBookName.setBounds(244, 234, 174, 47);
		panel_1.add(lblBookName);
		
		JLabel lblFineAmount = new JLabel("Fine Amount");
		lblFineAmount.setForeground(new Color(0, 0, 0));
		lblFineAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFineAmount.setBounds(520, 247, 174, 47);
		panel_1.add(lblFineAmount);
		
		JLabel lblWarning = new JLabel("Warning");
		lblWarning.setForeground(new Color(0, 0, 0));
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWarning.setBounds(520, 320, 174, 47);
		panel_1.add(lblWarning);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 277, 197, 47);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(244, 277, 220, 47);
		panel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(627, 249, 197, 47);
		panel_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(627, 335, 368, 108);
		panel_1.add(textField_7);
		
		JLabel lblIssueDate = new JLabel("Issue Date");
		lblIssueDate.setForeground(new Color(0, 0, 0));
		lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIssueDate.setBounds(10, 352, 174, 47);
		panel_1.add(lblIssueDate);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setForeground(new Color(0, 0, 0));
		lblReturnDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturnDate.setBounds(156, 352, 174, 47);
		panel_1.add(lblReturnDate);
		
		JLabel lblReturnnigAt = new JLabel("Returnnig at");
		lblReturnnigAt.setForeground(new Color(0, 0, 0));
		lblReturnnigAt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturnnigAt.setBounds(336, 352, 174, 47);
		panel_1.add(lblReturnnigAt);
		
		JButton btnNewButton = new JButton("Return ");
		btnNewButton.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/return.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(27, 462, 122, 41);
		panel_1.add(btnNewButton);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/refresh-icon.png")));
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRefresh.setBounds(174, 462, 122, 41);
		panel_1.add(btnRefresh);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/delete.png")));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(306, 462, 122, 41);
		panel_1.add(btnDelete);
		
		JButton btnSetfine = new JButton("Set Fine");
		btnSetfine.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/setFIne.png")));
		btnSetfine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSetfine.setBounds(638, 479, 147, 41);
		panel_1.add(btnSetfine);
		
		JButton btnRemoveFine = new JButton("Remove Fine");
		btnRemoveFine.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/removeFIne.png")));
		btnRemoveFine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemoveFine.setBounds(816, 479, 161, 41);
		panel_1.add(btnRemoveFine);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(10, 398, 108, 30);
		panel_1.add(dateChooser_1);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(156, 398, 108, 30);
		panel_1.add(dateChooser_2);
		
		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(336, 398, 108, 30);
		panel_1.add(dateChooser_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(169, 169, 169));
		tabbedPane.addTab("New book", null, panel_2, null);
		panel_2.setLayout(null);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"b_Title"}));
		comboBox_4.setBounds(10, 11, 101, 26);
		panel_2.add(comboBox_4);
		
		bookSearchTF = new JTextField();
		bookSearchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				bookSearchData();
			}
		});
		bookSearchTF.setBounds(121, 14, 152, 32);
		panel_2.add(bookSearchTF);
		bookSearchTF.setColumns(10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 69, 516, 333);
		panel_2.add(scrollPane_3);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bookTableDataShow();
			}
		});
		scrollPane_3.setViewportView(bookTable);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookAdd();
			}
		});
		btnAdd.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/Button-Add-icon.png")));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(57, 426, 127, 41);
		panel_2.add(btnAdd);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookRefresh();
				bookDataShow();
				supplierfillcombobox();
				bookcategoryfillcombobox();
				autherfillcombobox();
				publicationfillcombobox();
			}
		});
		btnRefresh_1.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/refresh-icon.png")));
		btnRefresh_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRefresh_1.setBounds(226, 426, 127, 41);
		panel_2.add(btnRefresh_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/update.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(57, 484, 127, 41);
		panel_2.add(btnUpdate);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/delete.png")));
		btnDelete_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete_1.setBounds(226, 484, 127, 41);
		panel_2.add(btnDelete_1);
		
		JLabel lblNewLabel_1 = new JLabel("Book Title");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(536, 23, 127, 32);
		panel_2.add(lblNewLabel_1);
		
		bookTitle = new JTextField();
		bookTitle.setBounds(536, 66, 211, 47);
		panel_2.add(bookTitle);
		bookTitle.setColumns(10);
		
		JLabel lblIsbnNo = new JLabel("ISBN No.\r\n\r\n");
		lblIsbnNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIsbnNo.setBounds(536, 124, 127, 32);
		panel_2.add(lblIsbnNo);
		
	    bookcategory = new JComboBox();
		bookcategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bookcategory.setModel(new DefaultComboBoxModel(new String[] {"Category"}));
		bookcategory.setBounds(536, 232, 127, 42);
		panel_2.add(bookcategory);
		
		auther = new JComboBox();
		auther.setFont(new Font("Tahoma", Font.PLAIN, 13));
		auther.setModel(new DefaultComboBoxModel(new String[] {"Author"}));
		auther.setBounds(536, 296, 127, 39);
		panel_2.add(auther);
		
	    publication = new JComboBox();
		publication.setFont(new Font("Tahoma", Font.PLAIN, 13));
		publication.setModel(new DefaultComboBoxModel(new String[] {"Publication"}));
		publication.setBounds(536, 363, 127, 39);
		panel_2.add(publication);
		
		JLabel lblPublications = new JLabel("Publication Date");
		lblPublications.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPublications.setBounds(536, 413, 127, 32);
		panel_2.add(lblPublications);
		
		JLabel lblBookEdition = new JLabel("Book Edition");
		lblBookEdition.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBookEdition.setBounds(804, 25, 127, 32);
		panel_2.add(lblBookEdition);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLanguage.setBounds(804, 126, 127, 32);
		panel_2.add(lblLanguage);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLocation.setBounds(804, 196, 127, 32);
		panel_2.add(lblLocation);
		
		JLabel lblBookQuantity = new JLabel("Book Quantity");
		lblBookQuantity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBookQuantity.setBounds(804, 279, 127, 32);
		panel_2.add(lblBookQuantity);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrice.setBounds(804, 365, 127, 32);
		panel_2.add(lblPrice);
		
		Supplier = new JComboBox();
		Supplier.setModel(new DefaultComboBoxModel(new String[] {"Supplier"}));
		Supplier.setBounds(804, 471, 127, 32);
		panel_2.add(Supplier);
		
		isbnNO = new JTextField();
		isbnNO.setColumns(10);
		isbnNO.setBounds(536, 167, 211, 47);
		panel_2.add(isbnNO);
		
		bookEdition = new JTextField();
		bookEdition.setColumns(10);
		bookEdition.setBounds(804, 66, 211, 47);
		panel_2.add(bookEdition);
		
		langauge = new JTextField();
		langauge.setColumns(10);
		langauge.setBounds(804, 156, 211, 47);
		panel_2.add(langauge);
		
		location = new JTextField();
		location.setColumns(10);
		location.setBounds(804, 232, 211, 47);
		panel_2.add(location);
		
		bookQuantity = new JTextField();
		bookQuantity.setColumns(10);
		bookQuantity.setBounds(804, 322, 211, 47);
		panel_2.add(bookQuantity);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(804, 408, 211, 47);
		panel_2.add(price);
		
		categoryTextFeild = new JTextField();
		categoryTextFeild.setBounds(678, 232, 69, 38);
		panel_2.add(categoryTextFeild);
		categoryTextFeild.setColumns(10);
		
		autherText = new JTextField();
		autherText.setColumns(10);
		autherText.setBounds(678, 297, 69, 38);
		panel_2.add(autherText);
		
		publicationText = new JTextField();
		publicationText.setColumns(10);
		publicationText.setBounds(678, 363, 69, 38);
		panel_2.add(publicationText);
		
		suplierText = new JTextField();
		suplierText.setColumns(10);
		suplierText.setBounds(941, 469, 69, 38);
		panel_2.add(suplierText);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(546, 456, 152, 32);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		panel_2.add(dateChooser);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Author", null, panel_4, null);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			 autherTableDatashow();
			}
		});
		scrollPane_1.setBounds(10, 11, 560, 548);
		panel_4.add(scrollPane_1);
		
		autherTable = new JTable();
		autherTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				autherTableDatashow();
			}
		});
		scrollPane_1.setViewportView(autherTable);
		
		JLabel lblAutherName = new JLabel("Auther Name");
		lblAutherName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutherName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAutherName.setBounds(608, 105, 389, 49);
		panel_4.add(lblAutherName);
		
		name1 = new JTextField();
		name1.setColumns(10);
		name1.setBounds(642, 166, 340, 61);
		panel_4.add(name1);
		
		JLabel label_1 = new JLabel("Detalis");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(608, 253, 389, 49);
		panel_4.add(label_1);
		
		Details = new JTextField();
		Details.setColumns(10);
		Details.setBounds(642, 313, 340, 109);
		panel_4.add(Details);
		
		JButton button_3 = new JButton("Add");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAu();
			}
		});
		button_3.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/Button-Add-icon.png")));
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_3.setBounds(642, 433, 143, 49);
		panel_4.add(button_3);
		
		JButton button_5 = new JButton("Refresh");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autherRefresh();
			}
		});
		button_5.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/refresh-icon.png")));
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_5.setBounds(811, 433, 143, 49);
		panel_4.add(button_5);
		
		JButton button_11 = new JButton("Update\r\n");
		button_11.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/update.png")));
		button_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_11.setBounds(642, 493, 143, 41);
		panel_4.add(button_11);
		
		JButton button_12 = new JButton("Delete");
		button_12.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/delete.png")));
		button_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_12.setBounds(811, 493, 143, 41);
		panel_4.add(button_12);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(119, 136, 153));
		tabbedPane.addTab("Supplier", null, panel_6, null);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				supplierTableDatashow() ;
			}
		});
		scrollPane_2.setBounds(10, 11, 488, 522);
		panel_6.add(scrollPane_2);
		
		supplierTable = new JTable();
		supplierTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				supplierTableDatashow();
			}
		});
		scrollPane_2.setViewportView(supplierTable);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"s_name"}));
		comboBox_9.setBounds(535, 11, 135, 32);
		panel_6.add(comboBox_9);
		
		JLabel lblNewLabel_2 = new JLabel("Search");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(710, 11, 78, 32);
		panel_6.add(lblNewLabel_2);
		
		s_nameTF = new JTextField();
		s_nameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				supplierSearchData1();
			}
		});
		s_nameTF.setBounds(776, 11, 189, 32);
		panel_6.add(s_nameTF);
		s_nameTF.setColumns(10);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setForeground(new Color(0, 0, 0));
		lblSupplierName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSupplierName.setBounds(559, 83, 142, 32);
		panel_6.add(lblSupplierName);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setForeground(new Color(0, 0, 0));
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContact.setBounds(559, 152, 135, 32);
		panel_6.add(lblContact);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(0, 0, 0));
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress.setBounds(559, 214, 135, 32);
		panel_6.add(lblAddress);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(new Color(0, 0, 0));
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDescription.setBounds(559, 280, 142, 32);
		panel_6.add(lblDescription);
		
		JButton button = new JButton("Delete");
		button.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/delete.png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(801, 460, 129, 41);
		panel_6.add(button);
		
		JButton btnUpdate_1 = new JButton("Update\r\n");
		btnUpdate_1.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/update.png")));
		btnUpdate_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate_1.setBounds(648, 460, 129, 41);
		panel_6.add(btnUpdate_1);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addS();
			}
		});
		btnAdd_1.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/Button-Add-icon.png")));
		btnAdd_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd_1.setBounds(648, 392, 129, 41);
		panel_6.add(btnAdd_1);
		
		JButton btnRefresh_2 = new JButton("Refresh");
		btnRefresh_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				suplierRefresh();
			}
		});
		btnRefresh_2.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/refresh-icon.png")));
		btnRefresh_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRefresh_2.setBounds(801, 392, 129, 41);
		panel_6.add(btnRefresh_2);
		
		sName = new JTextField();
		sName.setColumns(10);
		sName.setBounds(733, 85, 244, 32);
		panel_6.add(sName);
		
		sContact = new JTextField();
		sContact.setColumns(10);
		sContact.setBounds(733, 154, 244, 32);
		panel_6.add(sContact);
		
		saddress = new JTextField();
		saddress.setColumns(10);
		saddress.setBounds(733, 216, 244, 32);
		panel_6.add(saddress);
		
		sDescription = new JTextField();
		sDescription.setColumns(10);
		sDescription.setBounds(733, 282, 244, 69);
		panel_6.add(sDescription);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Publication", null, panel_5, null);
		panel_5.setLayout(null);
		
		publicationTable = new JTable();
		publicationTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				publicationTableDatashow();
			}
		});
		publicationTable.setBounds(10, 11, 530, 535);
		panel_5.add(publicationTable);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(597, 44, 389, 49);
		panel_5.add(lblName);
		
		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddress_1.setBounds(610, 131, 389, 49);
		panel_5.add(lblAddress_1);
		
		JLabel lblDetalis_1 = new JLabel("Detalis");
		lblDetalis_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalis_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDetalis_1.setBounds(610, 264, 389, 49);
		panel_5.add(lblDetalis_1);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(646, 85, 340, 61);
		panel_5.add(name);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(646, 192, 340, 61);
		panel_5.add(address);
		
		detalis1 = new JTextField();
		detalis1.setColumns(10);
		detalis1.setBounds(646, 308, 340, 102);
		panel_5.add(detalis1);
		
		JButton btnAdd_2 = new JButton("Add");
		btnAdd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPub();
			}
		});
		btnAdd_2.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/Button-Add-icon.png")));
		btnAdd_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd_2.setBounds(646, 434, 140, 45);
		panel_5.add(btnAdd_2);
		
		JButton button_4 = new JButton("Refresh");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				publishRefresh();
			}
		});
		button_4.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/refresh-icon.png")));
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_4.setBounds(802, 436, 140, 41);
		panel_5.add(button_4);
		
		JButton button_7 = new JButton("Update\r\n");
		button_7.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/update.png")));
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_7.setBounds(646, 494, 139, 41);
		panel_5.add(button_7);
		
		JButton button_10 = new JButton("Delete");
		button_10.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/delete.png")));
		button_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_10.setBounds(802, 494, 140, 41);
		panel_5.add(button_10);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Category", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 509, 548);
		panel_3.add(scrollPane);
		
		bookcategoryTable = new JTable();
		bookcategoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bookcategoryTableDatashow();
			}
		});
		scrollPane.setViewportView(bookcategoryTable);
		
		JLabel lblNewLabel_3 = new JLabel("Category");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(604, 97, 389, 49);
		panel_3.add(lblNewLabel_3);
		
		category = new JTextField();
		category.setBounds(632, 157, 340, 61);
		panel_3.add(category);
		category.setColumns(10);
		
		JLabel lblDetalis = new JLabel("Detalis");
		lblDetalis.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalis.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDetalis.setBounds(604, 241, 389, 49);
		panel_3.add(lblDetalis);
		
		catDetalis = new JTextField();
		catDetalis.setColumns(10);
		catDetalis.setBounds(632, 295, 340, 115);
		panel_3.add(catDetalis);
		
		JButton button_2 = new JButton("Add");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				catAdd();
			}
		});
		button_2.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/Button-Add-icon.png")));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_2.setBounds(632, 421, 138, 41);
		panel_3.add(button_2);
		
		JButton button_6 = new JButton("Refresh");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				catRefresh();
			}
		});
		button_6.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/refresh-icon.png")));
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_6.setBounds(800, 421, 129, 41);
		panel_3.add(button_6);
		
		JButton button_8 = new JButton("Update\r\n");
		button_8.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/update.png")));
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_8.setBounds(632, 481, 138, 41);
		panel_3.add(button_8);
		
		JButton button_9 = new JButton("Delete");
		button_9.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/delete.png")));
		button_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_9.setBounds(800, 481, 129, 41);
		panel_3.add(button_9);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(169, 169, 169));
		tabbedPane.addTab("Rules", null, panel_8, null);
		panel_8.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 11, 981, 290);
		panel_8.add(scrollPane_6);
		
		libraryrulesTable = new JTable();
		libraryrulesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				libraryrulesTableDataShow();
			}
		});
		scrollPane_6.setViewportView(libraryrulesTable);
		
		rule = new JTextField();
		rule.setBounds(10, 372, 981, 70);
		panel_8.add(rule);
		rule.setColumns(10);
		
		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRule();
			}
		});
		button_1.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/Button-Add-icon.png")));
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(171, 490, 131, 41);
		panel_8.add(button_1);
		
		JButton btnChange = new JButton("Change");
		btnChange.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/update.png")));
		btnChange.setHorizontalAlignment(SwingConstants.LEFT);
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChange.setBounds(368, 490, 131, 41);
		panel_8.add(btnChange);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setIcon(new ImageIcon(LibarianPanel.class.getResource("/Icon/remove.png")));
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove.setBounds(563, 490, 131, 41);
		panel_8.add(btnRemove);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Book Request", null, panel_7, null);
		panel_7.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.DARK_GRAY);
		desktopPane.setBounds(21, 28, 372, 531);
		panel_7.add(desktopPane);
		
		bookImageLevel = new JLabel("");
		bookImageLevel.setForeground(Color.BLACK);
		bookImageLevel.setBounds(10, 11, 352, 509);
		desktopPane.add(bookImageLevel);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(403, 28, 612, 294);
		panel_7.add(scrollPane_7);
		
		bookRequestTable = new JTable();
		bookRequestTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bookRequestTableDataShow();
			}
		});
		scrollPane_7.setViewportView(bookRequestTable);
		
		JLabel lblNewLabel_5 = new JLabel("Book Name:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(553, 347, 230, 35);
		panel_7.add(lblNewLabel_5);
		
		JLabel lblDetails = new JLabel("Details:");
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetails.setBounds(553, 393, 230, 35);
		panel_7.add(lblDetails);
		
		JLabel lblDefaultReply = new JLabel("Default Reply:");
		lblDefaultReply.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDefaultReply.setBounds(461, 456, 230, 35);
		panel_7.add(lblDefaultReply);
		
		bookNameTF = new JTextField();
		bookNameTF.setBounds(716, 348, 281, 35);
		panel_7.add(bookNameTF);
		bookNameTF.setColumns(10);
		
		bookDetalisTF = new JTextField();
		bookDetalisTF.setColumns(10);
		bookDetalisTF.setBounds(716, 394, 281, 52);
		panel_7.add(bookDetalisTF);
		
		replyTF = new JTextField();
		replyTF.setFont(new Font("Tahoma", Font.BOLD, 12));
		replyTF.setText("Book is Not Avaiable Right now!");
		replyTF.setColumns(10);
		replyTF.setBounds(588, 457, 347, 47);
		panel_7.add(replyTF);
		
		JButton btnNewButton_1 = new JButton("Reply");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reply();
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(838, 512, 126, 47);
		panel_7.add(btnNewButton_1);
		
		clockLabel = new JLabel("");
		clockLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setBounds(21, 15, 782, 36);
		contentPane.add(clockLabel);
		
		JButton btnNewButton_2 = new JButton("Control");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MemberSignUp m=new MemberSignUp();
				m.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(754, 15, 99, 40);
		contentPane.add(btnNewButton_2);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FristPage f= new FristPage();
				f.setVisible(true);
				dispose();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogout.setBounds(890, 15, 99, 40);
		contentPane.add(btnLogout);
	
   }
}
