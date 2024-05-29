package supportview;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FogetPasswordScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textFieldOTP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FogetPasswordScreen frame = new FogetPasswordScreen();
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
	public FogetPasswordScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel InstructLabel = new JLabel("Enter Your Email");
		InstructLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		InstructLabel.setHorizontalAlignment(SwingConstants.CENTER);
		InstructLabel.setBounds(137, 25, 156, 32);
		contentPane.add(InstructLabel);
		
		textField = new JTextField();
		textField.setBounds(147, 67, 220, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel GmailLabel = new JLabel("Gmail:");
		GmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GmailLabel.setBounds(46, 67, 74, 32);
		contentPane.add(GmailLabel);
		
		JButton GetOTPLabel = new JButton("Get OTP");
		GetOTPLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GetOTPLabel.setBounds(174, 109, 98, 32);
		contentPane.add(GetOTPLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 230, 220, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(147, 295, 220, 32);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Update Password");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(147, 366, 156, 32);
		contentPane.add(btnNewButton);
		
		JLabel NewPassWordLabel = new JLabel("New PassWord");
		NewPassWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NewPassWordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		NewPassWordLabel.setBounds(0, 227, 117, 32);
		contentPane.add(NewPassWordLabel);
		
		JLabel lblConfirmPassword = new JLabel("Confirm PassWord");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(0, 292, 139, 32);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblNewLabel = new JLabel("Enter OTP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(0, 167, 86, 32);
		contentPane.add(lblNewLabel);
		
		textFieldOTP = new JTextField();
		textFieldOTP.setBounds(147, 170, 220, 32);
		contentPane.add(textFieldOTP);
		textFieldOTP.setColumns(10);
	}

}
