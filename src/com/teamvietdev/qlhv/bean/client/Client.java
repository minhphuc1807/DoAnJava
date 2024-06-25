package com.teamvietdev.qlhv.bean.client;

import com.teamvietdev.qlhv.view.DangNhapJDialog;
import java.awt.Frame;

import java.net.Socket;
import javax.swing.JFrame;

public class Client {
    private static final String URL = "localhost";
    private static final int PORT = 5000;

    private void startClient(){
        try {
            JFrame frame = new JFrame();
//            Socket frame = new Socket(URL, PORT);
            Socket modal = new Socket(URL, PORT);
            System.out.println("Connected to Server");
            new DangNhapJDialog(frame,  true).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }
}
