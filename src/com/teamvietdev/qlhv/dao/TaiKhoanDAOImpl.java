package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.model.TaiKhoan;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

public class TaiKhoanDAOImpl implements TaiKhoanDAO {

    @Override
    public boolean save(TaiKhoan taiKhoan) {
        Connection cons = DBConnect.getConnection();
        String sql = "INSERT INTO tai_khoan (ten_dang_nhap, mat_khau, tinh_trang) VALUES (?, ?, ?)";

        try {
            String hashedPassword = hashPasswordSHA256(taiKhoan.getMat_khau()); // Hash the password

            // Validate password length here if needed (example limit is 255 characters)
            if (hashedPassword.length() > 255) {
                throw new IllegalArgumentException("Password exceeds maximum allowed length.");
            }

            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTen_dang_nhap());
            ps.setString(2, hashedPassword); // Set the hashed password
            ps.setBoolean(3, true); // Set tinh_trang = true or 1

            int rowsAffected = ps.executeUpdate();
            ps.close();

            return rowsAffected > 0; // Return true if insertion was successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if there was an exception or if no rows were affected
        } finally {
            try {
                if (cons != null) {
                    cons.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TaiKhoan findByUsername(String tenDangNhap) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap = ?";
        TaiKhoan taiKhoan = null;

        try (PreparedStatement ps = cons.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    taiKhoan = new TaiKhoan();
                    taiKhoan.setMa_tai_khoan(rs.getInt("ma_tai_khoan"));
                    taiKhoan.setTen_dang_nhap(rs.getString("ten_dang_nhap"));
                    taiKhoan.setMat_khau(rs.getString("mat_khau")); // You may choose to return hashed or unhashed password here
                    taiKhoan.setTinh_trang(rs.getBoolean("tinh_trang"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cons != null) {
                    cons.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return taiKhoan;
    }

    @Override
    public boolean isPasswordCorrect(String inputPassword, String storedHash) {
        // Lấy muối từ chuỗi hash đã lưu
        String salt = "afajkbjkasbdsdacugxuiagjzhzxmnxcbmv";

        // Tạo chuỗi hash từ mật khẩu nhập vào và muối
        String hashedInputPassword = hashPasswordSHA256(inputPassword + salt);

        // So sánh chuỗi đã băm và mã hóa với chuỗi đã lưu trong cơ sở dữ liệu
        return hashedInputPassword.equals(storedHash);
    }

    @Override
    public String getPasswordHash(String username) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT mat_khau FROM tai_khoan WHERE ten_dang_nhap = ?";
        String hashedPassword = null;

        try (PreparedStatement ps = cons.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hashedPassword = rs.getString("mat_khau");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cons != null) {
                    cons.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return hashedPassword;
    }

    public static String hashPasswordSHA256(String str) {
        // Thêm muối (salt) để tăng độ phức tạp cho mật khẩu
        String salt = "afajkbjkasbdsdacugxuiagjzhzxmnxcbmv";
        String result = null;

        // Nối muối vào chuỗi mật khẩu
        str += salt;

        try {
            // Chuyển chuỗi thành mảng byte sử dụng mã hóa UTF-8
            byte[] dataByte = str.getBytes(StandardCharsets.UTF_8);

            // Lấy đối tượng MessageDigest sử dụng thuật toán SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Mã hóa dữ liệu và nhận mảng byte kết quả
            byte[] digest = md.digest(dataByte);

            // Chuyển đổi mảng byte kết quả thành chuỗi Base64
            result = Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Trả về kết quả mã hóa
        return result;
    }

    @Override
    public TaiKhoan login(String tdn, String mk) {
        TaiKhoan taiKhoan = findByUsername(tdn);

        if (taiKhoan != null) {
            String storedHash = taiKhoan.getMat_khau();
            if (isPasswordCorrect(mk, storedHash)) {
                return taiKhoan;
            }
        }

        return null;
    }
}
