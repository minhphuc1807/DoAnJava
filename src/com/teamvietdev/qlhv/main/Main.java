package com.teamvietdev.qlhv.main;

import com.teamvietdev.qlhv.view.DangNhapJDialog;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        DangNhapJDialog dangNhapJDialog = new DangNhapJDialog(frame, true);
        dangNhapJDialog.setVisible(true);
    }
}
