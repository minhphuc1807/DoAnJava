package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.model.TaiKhoan;
import com.teamvietdev.qlhv.service.TaiKhoanService;
import com.teamvietdev.qlhv.service.TaiKhoanServiceImpl;
import com.teamvietdev.qlhv.view.DangKyJDialog;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DangKyController {
    private JDialog dialog;
    private JButton btnRegister;
    private JTextField jtfNewUsername;
    private JPasswordField jtfNewPassword;
    private JLabel jlbRegisterMsg;

    private TaiKhoanService taiKhoanService = null;

    public DangKyController(JDialog dialog, JButton btnRegister, JTextField jtfNewUsername, JPasswordField jtfNewPassword, JLabel jlbRegisterMsg) {
        this.dialog = dialog;
        this.btnRegister = btnRegister;
        this.jtfNewUsername = jtfNewUsername;
        this.jtfNewPassword = jtfNewPassword;
        this.jlbRegisterMsg = jlbRegisterMsg;

        taiKhoanService = new TaiKhoanServiceImpl();
    }

    public DangKyController(DangKyJDialog dialog, JButton btnRegister, JTextField jtfNewUsername, JPasswordField jtfNewPassword, JLabel jlbRegisterMsg) {
        this.dialog = dialog;
        this.btnRegister = btnRegister;
        this.jtfNewUsername = jtfNewUsername;
        this.jtfNewPassword = jtfNewPassword;
        this.jlbRegisterMsg = jlbRegisterMsg;

        taiKhoanService = new TaiKhoanServiceImpl();
    }

    public void setEvent() {
        btnRegister.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        String newUsername = jtfNewUsername.getText();
        String newPassword = new String(jtfNewPassword.getPassword());

        // Kiểm tra tính hợp lệ của thông tin đăng ký
        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            jlbRegisterMsg.setText("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        if (taiKhoanService.findByUsername(newUsername) != null) {
            jlbRegisterMsg.setText("Tên đăng nhập đã tồn tại.");
            return;
        }

        if (newPassword.length() < 8) {
            jlbRegisterMsg.setText("Mật khẩu phải có ít nhất 8 ký tự.");
            return;
        }

        // Mã hóa mật khẩu bằng SHA-256 và chuyển đổi sang Base64
        String hashedPassword = hashPasswordSHA256(newPassword);

        // Tạo tài khoản mới
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTen_dang_nhap(newUsername);
        taiKhoan.setMat_khau(hashedPassword);

        // Lưu tài khoản mới vào cơ sở dữ liệu (trong một luồng xử lý riêng biệt)
        new Thread(() -> {
            boolean isRegistered = taiKhoanService.save(taiKhoan);

            // Hiển thị kết quả đăng ký trên giao diện chính
            dialog.dispose(); // Đóng dialog đăng ký nếu cần thiết
            if (isRegistered) {
                jlbRegisterMsg.setText("Đăng ký thành công!");
            } else {
                jlbRegisterMsg.setText("Đăng ký thất bại, vui lòng thử lại.");
            }
        }).start();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        btnRegister.setBackground(new Color(0, 200, 83));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        btnRegister.setBackground(new Color(100, 221, 23));
    }
});
    }

    public static String hashPasswordSHA256(String str) {
        // Thêm muối (salt) để tăng độ phức tạp cho mật khẩu
        String salt = "afajkbjkasbdsdacugxuiagjzhzxmnxcbmv";
        String result = null;

        // Nối muối vào chuỗi mật khẩu
        str += salt;

        try {
            // Chuyển chuỗi thành mảng byte sử dụng mã hóa UTF-8
            byte[] dataByte = str.getBytes("UTF-8");
            // Do các thuật toán băm tất cả đều làm việc với byte nên ta phải chuyển chuỗi thành byte

            // Lấy đối tượng MessageDigest sử dụng thuật toán SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Mã hóa dữ liệu và nhận mảng byte kết quả
            byte[] digest = md.digest(dataByte);

            // Chuyển đổi mảng byte kết quả thành chuỗi Base64
            result = Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            e.printStackTrace();
        }

        // Trả về kết quả mã hóa
        return result;
    }   
}
