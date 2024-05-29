package supportview;

import javax.swing.*;

import supportview.ButtonGradient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientFrame extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField msgTxt;
    private static JTextArea msgArea;
    private static Socket s;
    private static DataInputStream din;
    private static DataOutputStream dout;
    private static final String PREFIX_SERVER = "Server: ";
    private static final String PREFIX_CLIENT = "Client: ";
    private boolean isServer; // Đánh dấu vai trò của client
   
    public ClientFrame(final boolean isServer,final String name) {
        this.isServer = isServer;
        setLayout(new BorderLayout());

        msgArea = new JTextArea();
        msgArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(msgArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        msgTxt = new JTextField();
        bottomPanel.add(msgTxt, BorderLayout.CENTER);

        ButtonGradient btnSend = new supportview.ButtonGradient();
        btnSend.setText("Send");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String msgout = msgTxt.getText().trim();
                    if (isServer) {
                        // Nếu là server, thêm tiền tố "Server: "
                        dout.writeUTF(PREFIX_SERVER + msgout);
                    } else {
                        dout.writeUTF(name+": " + msgout);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        bottomPanel.add(btnSend, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Tự động kết nối với server socket khi tạo ClientFrame
        connectToServer();
    }

    public void connectToServer() {
        Thread clientThread = new Thread(new Runnable() {
			@Override
			public void run() {
			    try {
			        s = new Socket("127.0.0.1", 1201);
			        din = new DataInputStream(s.getInputStream());
			        dout = new DataOutputStream(s.getOutputStream());
			        String msgin = "";
			        while (!msgin.equals("exit")) {
			            msgin = din.readUTF();
			            msgArea.append(msgin + "\n"); 
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		});
        clientThread.start();
    }
}
