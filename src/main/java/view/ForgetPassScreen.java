package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.AdminItemDAO;
import supportview.*;
import util.Email;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionEvent;

public class ForgotPasswordScreen extends JFrame {
    static final String from = "ble37588@gmail.com";
    static final String password = "srrphqinahautkmo";
    private String mail = null;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private TextFields enterGmailtf;
    private PasswordFields EnterNewPasswordtf;
    private PasswordFields confirmPasswordtf;
    private TextFields textFieldOTP;
    private String noiDung = generateOTP();
    private ButtonCustom Verifybutton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ForgotPasswordScreen frame = new ForgotPasswordScreen();
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
    public ForgotPasswordScreen() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 478, 444);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JLabel InstructLabel = new JLabel("Enter Your Email");
        InstructLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        InstructLabel.setHorizontalAlignment(SwingConstants.CENTER);
        InstructLabel.setBounds(183, 10, 156, 32);
        contentPane.add(InstructLabel);

        enterGmailtf = new TextFields();
        enterGmailtf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        enterGmailtf.setText("");
        enterGmailtf.setBounds(159, 63, 220, 51);
        contentPane.add(enterGmailtf);
        enterGmailtf.setColumns(10);

        final JLabel GmailLabel = new JLabel("Gmail:");
        GmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GmailLabel.setBounds(10, 77, 74, 32);
        contentPane.add(GmailLabel);

        final ButtonCustom GetOTPButton = new ButtonCustom();
        GetOTPButton.setText("Get OTP");
        GetOTPButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mail = enterGmailtf.getText();
               
                AdminItemDAO adminDao = new AdminItemDAO();

                if (adminDao.checkEmailExistence(mail)) {
                	Verifybutton.setEnabled(true);
                	
                    InstructLabel.setText("Enter your OTP");
                    GmailLabel.setText("Enter OTP");
                    GetOTPButton.setVisible(false);
                    enterGmailtf.setVisible(false);
                    Verifybutton.setEnabled(true); // Enable the Verifybutton after email is verified
                    textFieldOTP.setVisible(true);
                    Verifybutton.setVisible(true);
                    String emnail = enterGmailtf.getText(); 

                    Email.sendEmail(emnail, "Invoice Details", noiDung);
                    JOptionPane.showConfirmDialog(ForgotPasswordScreen.this, "Verify Email Success!", "Verify", JOptionPane.DEFAULT_OPTION);
                    
                }

            }

        });
        GetOTPButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GetOTPButton.setBounds(216, 124, 98, 32);
        contentPane.add(GetOTPButton);

        EnterNewPasswordtf = new PasswordFields();
        EnterNewPasswordtf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        EnterNewPasswordtf.setShowAndHide(true);
        EnterNewPasswordtf.setBounds(159, 186, 220, 51);
        contentPane.add(EnterNewPasswordtf);
        EnterNewPasswordtf.setColumns(10);
        EnterNewPasswordtf.setVisible(false);

        confirmPasswordtf = new PasswordFields();
        confirmPasswordtf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        confirmPasswordtf.setShowAndHide(true);
        confirmPasswordtf.setColumns(10);
        confirmPasswordtf.setBounds(159, 261, 220, 51);
        confirmPasswordtf.setVisible(false);
        contentPane.add(confirmPasswordtf);

        final ButtonCustom btnNewButton = new ButtonCustom();
        btnNewButton.setText("Update Password");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(EnterNewPasswordtf.getText().equals(confirmPasswordtf.getText())) {
        			System.out.println(mail);
        			AdminItemDAO adminItemDAO = new AdminItemDAO();
        			adminItemDAO.updatePasswordByEmail(mail, confirmPasswordtf.getText());
                    JOptionPane.showMessageDialog(ForgotPasswordScreen.this, "Change Password Success!", "Change Password", JOptionPane.DEFAULT_OPTION);
                    LoginScreen loginScreen = new LoginScreen();
                    loginScreen.setVisible(true);
                    dispose();

        		}
        		else {
        			JOptionPane.showMessageDialog(ForgotPasswordScreen.this, "Password did not match, Please try again", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBounds(124, 343, 260, 32);
        btnNewButton.setVisible(false);
        contentPane.add(btnNewButton);

        final JLabel NewPassWordLabel = new JLabel("New Password");
        NewPassWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NewPassWordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        NewPassWordLabel.setBounds(10, 200, 117, 32);
        NewPassWordLabel.setVisible(false);
        contentPane.add(NewPassWordLabel);

        final JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblConfirmPassword.setVisible(false);
        lblConfirmPassword.setBounds(10, 275, 139, 32);
        contentPane.add(lblConfirmPassword);

        textFieldOTP = new TextFields();
        textFieldOTP.setVisible(false);
        textFieldOTP.setBounds(159, 65, 220, 51);
        contentPane.add(textFieldOTP);
        textFieldOTP.setColumns(10);

        Verifybutton = new ButtonCustom();
        Verifybutton.setEnabled(false);
        Verifybutton.setText("Verify OTP");
        Verifybutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(textFieldOTP.getText());
                System.out.println(noiDung);

                if (textFieldOTP.getText().equals(noiDung)) {
                	lblConfirmPassword.setVisible(true);
                	confirmPasswordtf.setVisible(true);
                	EnterNewPasswordtf.setVisible(true);
                	NewPassWordLabel.setVisible(true);
                	btnNewButton.setVisible(true);
                	
                    JOptionPane.showMessageDialog(ForgotPasswordScreen.this, "Verify Success!", "Verify", JOptionPane.DEFAULT_OPTION);
                   
                } else {
                    JOptionPane.showMessageDialog(ForgotPasswordScreen.this, "Invalid OTP!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JLabel lblNewLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(Paymentscreen.class.getResource("bglogin.jpg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(473, 630, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblNewLabel.setIcon(scaledIcon);
        lblNewLabel.setBounds(0, -65, 697, 630);
        contentPane.add(lblNewLabel);
        Verifybutton.setBounds(216, 124, 98, 32);
        contentPane.add(Verifybutton);
        // Set the background label to the lowest Z order
        contentPane.setComponentZOrder(lblNewLabel, contentPane.getComponentCount() - 1);
        setLocationRelativeTo(null);
        
    }

    public static String generateOTP() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000)); // Generate a 6-digit OTP
    }

    public static boolean sendEmail(String to, String tieuDe, String noiDung) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Force TLS version if needed
        props.put("mail.debug", "true"); // Enable debug output

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        session.setDebug(true); // Enable session debugging

        MimeMessage msg = new MimeMessage(session);

        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(tieuDe);
            msg.setSentDate(new Date());
            msg.setContent(noiDung, "text/HTML; charset=UTF-8");

            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }
}
