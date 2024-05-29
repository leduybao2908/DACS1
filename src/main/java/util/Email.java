package util;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    static final String from = "ble37588@gmail.com";
    static final String password = "srrphqinahautkmo";

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

    public static void main(String[] args) {
        String noiDung = generateEmailContent();
        Email.sendEmail("chinhlq.23it@vku.udn.vn", "Invoice Details", noiDung);
    }

    public static String generateEmailContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time: 2024-05-25T10:47:45.196886400<br>");
        sb.append("Bill ID: 15<br>");
        sb.append("Customer Name: <br>");
        sb.append("Customer PhoneNumber: <br>");
        sb.append("Money Customer Gives: 200.0<br>");
        sb.append("Total Money: 179.95<br>");
        sb.append("Change Money: 20.05<br>");
        sb.append("-----------<br>");
        sb.append("<table border='1' style='border-collapse: collapse;'>");
        sb.append("<tr><th>ID</th><th>Item</th><th>Price</th><th>Quantity</th><th>Total</th></tr>");
        sb.append("<tr><td>2</td><td>Shuttlecocks</td><td>19.99</td><td>3</td><td>59.97</td></tr>");
        sb.append("<tr><td>3</td><td>Badminton Shoes</td><td>59.99</td><td>2</td><td>119.98</td></tr>");
        sb.append("</table>");
        sb.append("-----------<br>");
        return sb.toString();
    }
}