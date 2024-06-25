package com.teamvietdev.qlhv.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.teamvietdev.qlhv.dao.TaiKhoanDAO;
import com.teamvietdev.qlhv.dao.TaiKhoanDAOImpl;
import com.teamvietdev.qlhv.model.TaiKhoan;
import com.teamvietdev.qlhv.view.DangKyJDialog;
import com.teamvietdev.qlhv.view.DangNhapJDialog;
import com.teamvietdev.qlhv.view.MainJFrame;

public class TaiKhoanController {

    private JDialog dialog;
    private JButton btnSubmit;
    private JButton btnRegister;
    private JTextField jtfTenDangNhap;
    private JPasswordField jtfMatKhau;
    private JLabel jlbMsg;
    private JComboBox<String> jcb;

    private TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanController(JDialog dialog, JButton btnSubmit, JButton btnRegister, JTextField jtfTenDangNhap,
            JPasswordField jtfMatKhau, JLabel jlbMsg, JComboBox<String> jcb) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.btnRegister = btnRegister;
        this.jtfTenDangNhap = jtfTenDangNhap;
        this.jtfMatKhau = jtfMatKhau;
        this.jlbMsg = jlbMsg;
        this.jcb = jcb;
        this.taiKhoanDAO = new TaiKhoanDAOImpl();
    }

    public TaiKhoanController(DangNhapJDialog dialog, JButton btnSubmit, JButton btnRegister,
            JTextField jtfTenDangNhap, JPasswordField jtfMatKhau, JLabel jlbMsg, JComboBox<String> jcb) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.btnRegister = btnRegister;
        this.jtfTenDangNhap = jtfTenDangNhap;
        this.jtfMatKhau = jtfMatKhau;
        this.jlbMsg = jlbMsg;
        this.jcb = jcb;
        this.taiKhoanDAO = new TaiKhoanDAOImpl();
    }

    public void setEvent() {
        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
                DangKyJDialog dangKyJDialog = new DangKyJDialog(new JFrame(), true);
                dangKyJDialog.setTitle("Đăng kí");
                dangKyJDialog.setSize(400, 400);
                dangKyJDialog.setVisible(true);
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

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedType = (String) jcb.getSelectedItem();
                String username = jtfTenDangNhap.getText();
                char[] passwordChars = jtfMatKhau.getPassword();

                // Kiểm tra dữ liệu nhập vào
                if (username.isEmpty() || passwordChars.length == 0) {
                    jlbMsg.setText("Vui lòng nhập đầy đủ thông tin đăng nhập!");
                    return;
                }

                // Chuyển đổi mật khẩu từ char[] sang String
                String password = new String(passwordChars);

                // Tạo chuỗi hash cho mật khẩu người dùng nhập vào
                String hashedPassword = TaiKhoanDAOImpl.hashPasswordSHA256(password);
                System.out.println("Hashed Password from input: " + hashedPassword);

                // Lấy mật khẩu đã băm từ cơ sở dữ liệu
                String storedHash = taiKhoanDAO.getPasswordHash(username);

                if (storedHash == null) {
                    jlbMsg.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
                    return;
                }

                // So sánh mật khẩu
                if (!isPasswordCorrect(hashedPassword, storedHash)) {
                    jlbMsg.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
                } else {
                    TaiKhoan taiKhoan = taiKhoanDAO.findByUsername(username);

                    if (taiKhoan != null && !taiKhoan.isTinh_trang()) {
                        jlbMsg.setText("Tài khoản đang bị tạm khóa!");
                        return;
                    }

                    // Đăng nhập thành công, xử lý loại tài khoản
                    if ("Admin".equals(selectedType)) {
                        dialog.dispose();
                        MainJFrame frame = new MainJFrame();
                        frame.setTitle("Quản Lý Học Viên");
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        frame.setVisible(true);
                    } else if ("Client".equals(selectedType)) {
                        jlbMsg.setText("Chưa cập nhật!!");
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
            }
        });
    }

    private boolean isPasswordCorrect(String inputPassword, String storedHash) {
        return inputPassword.equals(storedHash);
    }
}
