package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.model.TaiKhoan;
import com.teamvietdev.qlhv.service.TaiKhoanService;
import com.teamvietdev.qlhv.service.TaiKhoanServiceImpl;
import com.teamvietdev.qlhv.view.MainJFrame;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class TaiKhoanController {
    
    private JDialog dialog;
    private JButton btnSubmit;
    private JTextField jtfTenDangNhap;
    private JPasswordField jtfMatKhau;
    private JLabel jlbMsg;
    
    private TaiKhoanService taiKhoanService = null;

    public TaiKhoanController(JDialog dialog, JButton btnSubmit, JTextField jtfTenDangNhap, JPasswordField jtfMatKhau, JLabel jlbMsg) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.jtfTenDangNhap = jtfTenDangNhap;
        this.jtfMatKhau = jtfMatKhau;
        this.jlbMsg = jlbMsg;
        
        taiKhoanService = new TaiKhoanServiceImpl();
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfTenDangNhap.getText().length() == 0 || jtfMatKhau.getText().length() == 0) {
                    jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                } else {
                    TaiKhoan taiKhoan = taiKhoanService.login(jtfTenDangNhap.getText(), jtfMatKhau.getText());
                    if(taiKhoan == null){
                        jlbMsg.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
                    }else{
                        if(!taiKhoan.isTinh_trang()){
                            jlbMsg.setText("Tài khoản đang bị tạm khóa!");
                        }else{
                            dialog.dispose();
                            MainJFrame frame = new MainJFrame();
                            frame.setTitle("Quản Lý Học Viên");
                            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            frame.setVisible(true);
                        }
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
    
}
