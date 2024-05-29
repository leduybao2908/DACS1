package view;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AdminItemDAO;
import model.Admin;
import model.Employee;
import supportview.ButtonCustom;
import supportview.PasswordFields;
import supportview.RadioButtonCustom;
import supportview.TextFields;

//import com.mysql.cj.jdbc.Driver;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
//import org.jdesktop.swingx.border.DropShadowBorder;
import java.awt.TextField;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private PasswordFields textFieldConfirmpass;
	private TextFields textFieldUsername;
	private PasswordFields textFieldPassword;
	private TextFields textFieldGmail;
	private JLabel lblNewLabel;
	private RadioButtonCustom rdbtnCashier;
	private RadioButtonCustom rdbtnAdmin;
	private TextFields textFieldPhoneNumber;
	private PasswordFields passwordField;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup buttonGroup = new ButtonGroup();

        rdbtnCashier =  new RadioButtonCustom();
		rdbtnCashier.setText("Cashier");
        rdbtnCashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnCashier.setBounds(64, 381, 104, 33);
        contentPane.add(rdbtnCashier);
        buttonGroup.add(rdbtnCashier);

        rdbtnAdmin =  new RadioButtonCustom();
		rdbtnAdmin.setText("Admin");
        rdbtnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnAdmin.setBounds(287, 381, 104, 33);
        contentPane.add(rdbtnAdmin);
        buttonGroup.add(rdbtnAdmin);
		
		JLabel LabelUsername = new JLabel("Username");
		LabelUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelUsername.setBounds(10, 67, 90, 55);
		//contentPane.add(LabelUsername);
		
		textFieldConfirmpass = new PasswordFields();
		textFieldConfirmpass.setLabelText("Confirm password");
		textFieldConfirmpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldConfirmpass.setBounds(100, 244, 300, 55);
		contentPane.add(textFieldConfirmpass);
		
		textFieldUsername = new TextFields();
		textFieldUsername.setLabelText("User name");
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldUsername.setBounds(100, 67, 300, 55);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		ButtonCustom ButtonLogin = new ButtonCustom();
		ButtonLogin.setText("LOGIN");
//		ButtonLogin.setBackground(SystemColor.activeCaption);
		ButtonLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	            	openNewScreen();
	            }
	        });
		
		ButtonLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonLogin.setBounds(150, 443, 145, 53);
		contentPane.add(ButtonLogin);
		
		textFieldPassword = new PasswordFields();
		textFieldPassword.setLabelText("Password");
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPassword.setBounds(100, 186, 300, 55);
		contentPane.add(textFieldPassword);
		
		textFieldGmail = new TextFields();
		textFieldGmail.setLabelText("Gmail");
		textFieldGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldGmail.setBounds(100, 124, 300, 55);
		contentPane.add(textFieldGmail);
		
		JLabel LabelGmail = new JLabel("Gmail");
		LabelGmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelGmail.setBounds(10, 124, 90, 55);
		//contentPane.add(LabelGmail);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelPassword.setBounds(10, 100, 90, 55);
		//contentPane.add(LabelPassword);
		
		JLabel LabelConfirmPass = new JLabel("Confirm Password");
		LabelConfirmPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelConfirmPass.setBounds(10, 244, 158, 55);
		//contentPane.add(LabelConfirmPass);
		
		ButtonCustom ButtonRegister = new ButtonCustom();
			ButtonRegister.setText("REGISTER");

		ButtonRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonRegister.setBounds(150, 506, 145, 53);
		ButtonRegister.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int ID = AdminItemDAO.getEmployeeCount() + 1;
	                String NAME = textFieldUsername.getText();
	                String GMAIL = textFieldGmail.getText();
	                String PASSWORD = textFieldPassword.getText();
	                String CONFIRM = textFieldConfirmpass.getText();
	                float WORKHOUR = 0;
	                String PHONENUMBER = textFieldPhoneNumber.getText();

	                // Modify the username based on selection
	                if (rdbtnCashier.isSelected() && PASSWORD.equals(CONFIRM)) {
	                	 Employee user = new Employee(ID, NAME, GMAIL, PASSWORD , PHONENUMBER, WORKHOUR);
	 	                AdminItemDAO.insertloginCashier(user);
	 	               clearFields();
	 	                } else if (rdbtnAdmin.isSelected() && PASSWORD.equals(CONFIRM)) {
	 	                	 Admin admin = new Admin(NAME, GMAIL, PASSWORD);
	 		                AdminItemDAO.insertloginAdmin(admin);
	 		               clearFields();
	 		                }else {
	 		                	JOptionPane.showMessageDialog(null, "Mật khẩu không khớp!");
	 		                }        
	            }
	        });
		
	/*	DropShadowBorder shadow = new DropShadowBorder();
		shadow.setShadowSize(5); // Đặt kích thước bóng
		shadow.setShowLeftShadow(true); // Hiển thị bóng bên trái
		shadow.setShowRightShadow(true); // Hiển thị bóng bên phải
		shadow.setShowBottomShadow(true); // Hiển thị bóng phía dưới
		ButtonRegister.setBorder(shadow); // Đặt hiệu ứng bóng cho nút */
	        contentPane.add(ButtonRegister);
		
		JLabel LabelRegister = new JLabel(" BADMINTON STORE ");
		LabelRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		LabelRegister.setBounds(103, 6, 330, 51);
		contentPane.add(LabelRegister);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	//	lblNewLabel.setIcon(new ImageIcon(LoginScreenn.class.getResource("badminton3.png")));
		lblNewLabel.setBounds(0, 0, 459, 640);
		contentPane.add(lblNewLabel);	
		
		textFieldPhoneNumber = new TextFields();
		textFieldPhoneNumber.setLabelText("Phone number");
		textFieldPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPhoneNumber.setBounds(100, 302, 300, 55);
		contentPane.add(textFieldPhoneNumber);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhonenumber.setBounds(10, 302, 158, 55);
	//	contentPane.add(lblPhonenumber);
		

	JLabel lblNewLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(Paymentscreen.class.getResource("bgregist.jpg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(473, 630, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblNewLabel.setIcon(scaledIcon);
        lblNewLabel.setBounds(0, 0, 473, 630);
        contentPane.add(lblNewLabel);

        // Set the background label to the lowest Z order
        contentPane.setComponentZOrder(lblNewLabel, contentPane.getComponentCount() - 1);
		setLocationRelativeTo(null);
		setLocationRelativeTo(null);
		
	}
	
	public void openNewScreen() {
      LoginScreen NewScreen = new LoginScreen();      
        NewScreen.setVisible(true);
        dispose();
    }
	
	private void clearFields() {
        textFieldUsername.setText("");
        textFieldGmail.setText("");
        textFieldPassword.setText("");
        textFieldConfirmpass.setText("");
        textFieldPhoneNumber.setText("");

    }
		}
		