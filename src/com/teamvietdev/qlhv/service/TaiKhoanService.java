package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.model.TaiKhoan;
import java.security.MessageDigest;
import java.util.Base64;

public interface TaiKhoanService {

    boolean save(TaiKhoan taiKhoan);

    TaiKhoan findByUsername(String tenDangNhap);

    boolean isPasswordCorrect(String inputPassword, String storedHash);

    static String hashPasswordSHA256(String str) {
        // Thêm muối (salt) để tăng độ phức tạp cho mật khẩu
        String salt = "afajkbjkasbdsdacugxuiagjzhzxmnxcbmv";
        String result = null;

        // Nối muối vào chuỗi mật khẩu
        str += salt;

        try {
            // Chuyển chuỗi thành mảng byte sử dụng mã hóa UTF-8
            byte[] dataByte = str.getBytes("UTF-8");

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
