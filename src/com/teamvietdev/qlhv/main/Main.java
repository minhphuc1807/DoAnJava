package com.teamvietdev.qlhv.main;

import com.teamvietdev.qlhv.view.DangNhapJDialog;
import com.teamvietdev.qlhv.view.MainJFrame;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
//        new MainJFrame().setVisible(true);
        DangNhapJDialog dialog = new DangNhapJDialog(null, true);
        dialog.setTitle("Đăng Nhập Hệ Thống");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
}
