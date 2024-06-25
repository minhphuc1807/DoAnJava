package com.teamvietdev.qlhv.server;

/**
 *
 * @author ASUS
 */
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;

    private void startServer(){
        try {
//            websocket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port: " + PORT);
//            Client connects to Server
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

//                ClientHandler clientHandler = new ClientHandler(clientSocket,System.currentTimeMillis()+"");

//                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}

