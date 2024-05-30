package view;

import java.awt.EventQueue;  
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AdminItemDAO;
import supportview.ButtonCustom;
import supportview.PasswordFields;
import supportview.RadioButtonCustom;
import supportview.TextFields;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static TextFields textUsername;
	private PasswordFields textFieldPassword;
	private JLabel lblNewLabel;
	private ButtonCustom btnRegist;
	private RadioButtonCustom rdbtnCashier;
	private RadioButtonCustom rdbtnAdmin;
	private JLabel lblLogin;
	private JLabel lblNewLabel_1;
	private ButtonCustom btnForgetPass;
	
	
	public LoginScreen() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });

		setBounds(100, 100, 473, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsername = new TextFields();
		textUsername.setLabelText("username");
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textUsername.setBounds(83, 172, 300, 55);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();

        rdbtnCashier = new RadioButtonCustom();
		rdbtnCashier.setText("Cashier");
        rdbtnCashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnCashier.setBounds(100, 311, 104, 33);
        contentPane.add(rdbtnCashier);
        buttonGroup.add(rdbtnCashier);

        rdbtnAdmin = new RadioButtonCustom();
		rdbtnAdmin.setText("Admin");
        rdbtnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnAdmin.setBounds(279, 311, 104, 33);
        contentPane.add(rdbtnAdmin);
        buttonGroup.add(rdbtnAdmin);
        
		ButtonCustom ButtonLogin = new ButtonCustom();
		ButtonLogin.setText("Login");
		ButtonLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ButtonLogin.setBounds(120, 384, 208, 45);
		ButtonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String enterUsername = textUsername.getText();
				String enterPassword = textFieldPassword.getText();
								
				           
				   if (rdbtnCashier.isSelected()) {
					   if ( AdminItemDAO.checkLogin(enterUsername, enterPassword))
						  { 
							 OpenCashierScreen(enterUsername);
							 
						  } else {
			                     JOptionPane.showMessageDialog(contentPane, "Incorrect username, password or ability!",
			                             "Login Failed", JOptionPane.ERROR_MESSAGE);
			                 }				
	                } else if (rdbtnAdmin.isSelected()) {
						   if ( AdminItemDAO.checkLoginAdmin(enterUsername, enterPassword)) {
			                	OpenAdminScreen();

	                }else {
	                     JOptionPane.showMessageDialog(contentPane, "Incorrect username, password or ability!",
	                             "Login Failed", JOptionPane.ERROR_MESSAGE);
	                 }	
	                } 			     	
	                            
			}
        });

		
		btnRegist = new ButtonCustom();
		btnRegist.setText("Register");
		btnRegist.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegist.setBounds(120, 439, 208, 45);
		contentPane.add(btnRegist);
		btnRegist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				OpenRegisterScreen();
			}
        });

		contentPane.add(ButtonLogin);
		
		btnForgetPass = new ButtonCustom();
		btnForgetPass.setText("Forget Password");
		btnForgetPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnForgetPass.setBounds(120, 494, 208, 45);
		contentPane.add(btnRegist);
		btnForgetPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				ForgotPasswordScreen  forgotPasswordScreen= new ForgotPasswordScreen();
				forgotPasswordScreen.setVisible(true);
				dispose();
			}
        });

		contentPane.add(btnForgetPass);
		
		textFieldPassword = new PasswordFields();
		textFieldPassword.setLabelText("password");
		textFieldPassword.setShowAndHide(true);
		textFieldPassword.setBounds(83, 237, 300, 55);
		contentPane.add(textFieldPassword);
		
		
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(204, 87, 78, 52);
		contentPane.add(lblLogin);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginScreen.class.getResource("account.png")));
		lblNewLabel_1.setBounds(194, 10, 78, 97);
		contentPane.add(lblNewLabel_1);
		

		JLabel lblNewLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(LoginScreen.class.getResource("bglogin.jpg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(473, 630, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblNewLabel.setIcon(scaledIcon);
        lblNewLabel.setBounds(0, 0, 473, 630);
        contentPane.add(lblNewLabel);

        // Set the background label to the lowest Z order
        contentPane.setComponentZOrder(lblNewLabel, contentPane.getComponentCount() - 1);
		setLocationRelativeTo(null);
		
	}
	public void handleClosing() {
	    int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
	            "Confirmation", JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION) {	       
	        dispose();
	    }
	}
	
	public void OpenRegisterScreen() {
		RegisterScreen registerscreen = new RegisterScreen();
		registerscreen.setVisible(true);
		dispose();
	}
	
	 public void OpenCashierScreen(String userName) {
		 CashierScreen cashscreen = new CashierScreen(userName);
		 cashscreen.setVisible(true);
		 dispose();
	 }
	 
	 public void OpenAdminScreen() {
		 AdminScreen adminscreen = new AdminScreen();
		 adminscreen.setVisible(true);
		 dispose();
	 }
    
    public static String getUsername() {
		return textUsername.getText();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
