package com.example.liaotian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class SimpleChatClient2 {
    private JFrame frame;
    private JTextArea incomingTextArea;
    private JTextField outgoingTextField;
    private PrintWriter writer;
    private Socket socket;

    public void createGUI() {
        frame = new JFrame("简单聊天客户端");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        incomingTextArea = new JTextArea(15, 50);
        incomingTextArea.setLineWrap(true);
        incomingTextArea.setWrapStyleWord(true);
        incomingTextArea.setEditable(false);

        JScrollPane scroller = new JScrollPane(incomingTextArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        outgoingTextField = new JTextField(20);

        JButton sendButton = new JButton("发送");
        sendButton.addActionListener(new SendButtonListener());

        JPanel panel = new JPanel();
        panel.add(scroller);
        panel.add(outgoingTextField);
        panel.add(sendButton);

        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void setUpNetworking() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("网络连接已建立");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(outgoingTextField.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoingTextField.setText("");
            outgoingTextField.requestFocus();
        }
    }

    public class IncomingReader implements Runnable {
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("接收：" + message);
                    incomingTextArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.createGUI();
    }
}
