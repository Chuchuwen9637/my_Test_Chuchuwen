package com.example.liaotian;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleChatServer {
    private ArrayList<PrintWriter> clientWriters;

    public void startServer() {
        clientWriters = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("服务器已启动，等待连接...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("连接建立: " + clientSocket);

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientWriters.add(writer);

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            try {
                writer.println(message);
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleChatServer server = new SimpleChatServer();
        server.startServer();
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader reader;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("接收: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
