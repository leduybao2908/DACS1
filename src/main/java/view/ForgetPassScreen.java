package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;

public class ForgetPassScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassScreen frame = new ForgetPassScreen();
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
	public ForgetPassScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel InstructLabel = new JLabel("Enter Your Email");
		InstructLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		InstructLabel.setHorizontalAlignment(SwingConstants.CENTER);
		InstructLabel.setBounds(165, 10, 121, 35);
		contentPane.add(InstructLabel);
		
		JLabel MailLabel = new JLabel("GMail:");
		MailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MailLabel.setBounds(52, 78, 58, 21);
		contentPane.add(MailLabel);
		
		textField = new JTextField();
		textField.setBounds(165, 77, 185, 28);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
