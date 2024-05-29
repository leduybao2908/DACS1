package supportview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiClientServer extends JPanel {
    private static final long serialVersionUID = 1L;
    private static List<ClientHandler> clientHandlers = new ArrayList<>();
    static JTextArea msgArea;
    private static JTextField msgTxt;
    private static final String PREFIX_SERVER = "Server: ";
    private static final String PREFIX_CLIENT = "Client: ";

    public MultiClientServer() {
        setLayout(new BorderLayout());

        msgArea = new JTextArea();
        msgArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(msgArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        msgTxt = new JTextField();
        bottomPanel.add(msgTxt, BorderLayout.CENTER);

        JButton btnSend = new supportview.ButtonGradient();
        btnSend.setText("Send");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String msgout = msgTxt.getText().trim();
                msgArea.append(PREFIX_SERVER + msgout + "\n"); // Thêm tiền tố "Server: "
                sendMessageToAllClients(PREFIX_SERVER + msgout);
                msgTxt.setText("");
            }
        });
        bottomPanel.add(btnSend, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Tự động khởi chạy server socket khi tạo MultiClientServer
        startServer();
    }

    public void startServer() {
        Thread serverThread = new Thread(new Runnable() {
			@Override
			public void run() {
			    try {
			        ServerSocket ss = new ServerSocket(1201);
			        while (true) {
			            Socket s = ss.accept();
			            ClientHandler clientHandler = new ClientHandler(s);
			            clientHandlers.add(clientHandler);
			            Thread t = new Thread(clientHandler);
			            t.start();
			        }
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
		});
        serverThread.start();
    }

    public static void sendMessageToAllClients(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.dout.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Chat", new MultiClientServer());

        frame.getContentPane().add(tabbedPane);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private DataInputStream din;
    DataOutputStream dout;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            din = new DataInputStream(clientSocket.getInputStream());
            dout = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String msgin = "";
            while (!msgin.equals("exit")) {
                msgin = din.readUTF();
                MultiClientServer.msgArea.append(msgin + "\n");
                MultiClientServer.sendMessageToAllClients(msgin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
