package view;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.barcodelib.barcode.Linear;
import dao.AdminItemDAO;
import dao.PaymentItemDAO;
import database.JDBCUtil;
import model.ProductCart;
import model.billModel;
import net.glxn.qrgen.image.ImageType;
import supportview.RoundJTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Desktop;

public class Paymentscreen extends JFrame {

    public static final long serialVersionUID = 1L;
    public JPanel contentPane;
    public JTextField textFieldChange;
    public JTextField textFieldCustomerMoney;
    public AdminScreen AdminScreen;
    private DefaultTableModel tableModelBill;
    public JTable TableBillList;
    private JTextField txtFieldCustomerName;
    private JTextField textFieldCustomerPhone;
    Image img = null;

    public Paymentscreen(AdminScreen AdminScreen) {
        this.AdminScreen = AdminScreen;
    }

    public Paymentscreen() {
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });
        setBounds(100, 100, 1100, 750);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) {
                    // Thay đổi kích thước ảnh ở đây, ví dụ: 300x300
                    int imgWidth = 300;
                    int imgHeight = 300;
                    g.drawImage(img, 50, 50, imgWidth, imgHeight, this);
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Add all other components first
        tableModelBill = new DefaultTableModel(new Object[][]{}, new String[]{"ID PRODUCT", "NAME PRODUCT", "PRICE", "QUANTITY", "IDCATEGORY"});
        TableBillList = new JTable(tableModelBill);
        TableBillList.setFont(new Font("Tahoma", Font.PLAIN, 16));
        TableBillList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            }
        });
        SaleTable();

        JScrollPane scrollPaneTablebILLList = new JScrollPane(TableBillList);
        scrollPaneTablebILLList.setBounds(52, 409, 975, 193);
        scrollPaneTablebILLList.setToolTipText("");
        contentPane.add(scrollPaneTablebILLList);

        JLabel LabelMain = new JLabel("ENTER THE AMOUNT OF MONEY GIVEN BY THE CUSTOMER");
        LabelMain.setBackground(new Color(255, 255, 255));
        LabelMain.setFont(new Font("Tahoma", Font.BOLD, 18));
        LabelMain.setBounds(271, 39, 557, 40);
        LabelMain.setForeground(Color.WHITE);
        contentPane.add(LabelMain);

        JButton ButtonCalculateMoney = new supportview.ButtonGradient();
        ButtonCalculateMoney.setText("Cash");
        ButtonCalculateMoney.setFont(new Font("Tahoma", Font.BOLD, 16));
        ButtonCalculateMoney.setBounds(730, 106, 140, 43);
        ButtonCalculateMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime Time = LocalDateTime.now();
                String timebill = Time.toString();
                int BillId = PaymentItemDAO.getInstanitemDAO().getBillCount() + 1;
                String IdofBill = Integer.toString(BillId);
                String NameFile = IdofBill + ".txt";
                String namecustomer = txtFieldCustomerName.getText();
                String phonecustomer = textFieldCustomerPhone.getText();
                float totalprice = PaymentItemDAO.getInstanitemDAO().getTotalCostFromTable();
                float cost = Float.parseFloat(textFieldCustomerMoney.getText());
                float costchange = cost - totalprice;
                textFieldChange.setText(String.valueOf(costchange));
                billModel bill = new billModel(BillId, timebill, totalprice, namecustomer, phonecustomer);
                PaymentItemDAO.getInstanitemDAO().insert(bill);
                PaymentItemDAO.getInstanitemDAO().deleteProductcart();
                String FileLink = "C:\\Users\\LE DUY BAO\\Documents\\DACS-HOADON\\HOADON-";
                String filepath =  FileLink + NameFile;
                PaymentItemDAO.getInstanitemDAO().writeToFile(filepath, timebill, BillId, namecustomer, phonecustomer, cost, totalprice, costchange);
                PaymentItemDAO.getInstanitemDAO().openFileExplorer(filepath);
                PaymentItemDAO.getInstanitemDAO().writeTableToFile(tableModelBill, filepath);
                try { 
                    File f = new File(filepath);
                    List<String> allText = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                    String totaldata = String.join("", allText);
                    String imagePath = "D:\\22222.png";
                    ByteArrayOutputStream qrcode = net.glxn.qrgen.QRCode.from(totaldata).to(ImageType.PNG).stream();
                    FileOutputStream fout = new FileOutputStream(new File(imagePath));
                    fout.write(qrcode.toByteArray());
                    fout.flush();
                    fout.close();

                    Toolkit t = Toolkit.getDefaultToolkit();
                    Image i = t.getImage(imagePath);
                    img = i;
                    contentPane.repaint(); // Trigger repaint of the contentPane to show the image
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                SaleTable();
                clearFields();
            }
        });
        contentPane.add(ButtonCalculateMoney);

        JButton ButtonGetQr = new supportview.ButtonGradient();
        ButtonGetQr.setText("Get QR");
        ButtonGetQr.setFont(new Font("Tahoma", Font.BOLD, 16));
        ButtonGetQr.setBounds(730, 182, 140, 43);
        ButtonGetQr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int BillId = PaymentItemDAO.getInstanitemDAO().getBillCount();
                    String billIdStr = String.valueOf(BillId);
                    String filepath = "C:\\Users\\LE DUY BAO\\Documents\\DACS-HOADON\\HOADON-" + billIdStr + ".txt";
                    File f = new File(filepath);
                    List<String> allText = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                    String totaldata = String.join("", allText);
                    ByteArrayOutputStream qrcode = net.glxn.qrgen.QRCode.from(totaldata).to(ImageType.PNG).stream();
                    try (FileOutputStream fout = new FileOutputStream(new File("D:\\22222.png"))) {
                        fout.write(qrcode.toByteArray());
                    }
                    Desktop.getDesktop().open(new File("D:\\22222.png")); // Open the QR code image
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        contentPane.add(ButtonGetQr);
        
        JButton ButtonSendBill = new supportview.ButtonGradient();
        ButtonSendBill.setText("Send bill to Email");
        ButtonSendBill.setFont(new Font("Tahoma", Font.BOLD, 16));
        ButtonSendBill.setBounds(714, 259, 181, 43);
        ButtonSendBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        contentPane.add(ButtonSendBill);

        textFieldChange = new JTextField();
        textFieldChange.setBounds(425, 632, 255, 40);
        contentPane.add(textFieldChange);
        textFieldChange.setColumns(10);

        textFieldCustomerMoney = new JTextField();
        textFieldCustomerMoney.setColumns(10);
        textFieldCustomerMoney.setBounds(425, 110, 255, 40);
        contentPane.add(textFieldCustomerMoney);

        JLabel LabelCustomerMoney = new JLabel("MONEY CUSTOMER GIVES");
        LabelCustomerMoney.setFont(new Font("Tahoma", Font.BOLD, 16));
        LabelCustomerMoney.setBounds(158, 107, 257, 40);
        LabelCustomerMoney.setForeground(Color.WHITE);
        contentPane.add(LabelCustomerMoney);

        JLabel LabelChange = new JLabel("CHANGE:");
        LabelChange.setFont(new Font("Tahoma", Font.BOLD, 16));
        LabelChange.setBounds(316, 629, 94, 40);
        LabelChange.setForeground(Color.WHITE);
        contentPane.add(LabelChange);

        txtFieldCustomerName = new JTextField();
        txtFieldCustomerName.setColumns(10);
        txtFieldCustomerName.setBounds(425, 186, 255, 40);
        contentPane.add(txtFieldCustomerName);

        JLabel LblCustomerName = new JLabel("CUSTOMER NAME");
        LblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 16));
        LblCustomerName.setBounds(158, 183, 209, 40);
        LblCustomerName.setForeground(Color.WHITE);
        contentPane.add(LblCustomerName);

        JLabel lblCustomerPhonenumber = new JLabel("CUSTOMER PHONENUMBER");
        lblCustomerPhonenumber.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCustomerPhonenumber.setBounds(158, 260, 257, 40);
        lblCustomerPhonenumber.setForeground(Color.WHITE);
        contentPane.add(lblCustomerPhonenumber);

        textFieldCustomerPhone = new JTextField();
        textFieldCustomerPhone.setColumns(10);
        textFieldCustomerPhone.setBounds(425, 263, 255, 40);
        contentPane.add(textFieldCustomerPhone);

        // Add the background label last
        JLabel lblNewLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(Paymentscreen.class.getResource("bgg.jpg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1086, 713, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblNewLabel.setIcon(scaledIcon);
        lblNewLabel.setBounds(0, 0, 1086, 713);
        contentPane.add(lblNewLabel);

        // Set the background label to the lowest Z order
        contentPane.setComponentZOrder(lblNewLabel, contentPane.getComponentCount() - 1);
    }

    public void SaleTable() {
        tableModelBill = (DefaultTableModel) TableBillList.getModel();
        ArrayList<ProductCart> items = new PaymentItemDAO().getInstanitemDAO().selectProductCart();
        tableModelBill.getDataVector().removeAllElements();
        tableModelBill.setRowCount(0); // Clear existing data
        for (int i = 0; i < items.size(); i++) {
            tableModelBill.addRow(items.get(i).toObject());
        }
    }

    public void handleClosing() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    public void clearFields() {
        textFieldCustomerMoney.setText("");
        textFieldCustomerPhone.setText("");
        txtFieldCustomerName.setText("");
    }

    public void openNewScreen() {
        AdminScreen NewScreen = new AdminScreen();
        NewScreen.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //	AdminScreen frame = new AdminScreen();
                    // Now create the ChangeScreen instance
                    Paymentscreen newScreenInstance = new Paymentscreen();
                    newScreenInstance.setLocationRelativeTo(null);
                    newScreenInstance.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}