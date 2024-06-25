package com.teamvietdev.qlhv.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;



/**
 * Đại diện cho một tài khoản trong hệ thống
 */
public class TaiKhoan {

    private int ma_tai_khoan;
    private String ten_dang_nhap;
    private String mat_khau; // Sẽ không lưu mật khẩu dưới dạng plain text
    private boolean tinh_trang;

    public TaiKhoan(int ma_tai_khoan, String ten_dang_nhap, String mat_khau, boolean tinh_trang) {
        this.ma_tai_khoan = ma_tai_khoan;
        this.ten_dang_nhap = ten_dang_nhap;
        this.mat_khau = mat_khau;
        this.tinh_trang = tinh_trang;
    }

    public TaiKhoan() {
    this.ma_tai_khoan = 0; // Hoặc giá trị mặc định phù hợp
    this.ten_dang_nhap = "";
    this.mat_khau = "";
    this.tinh_trang = false;
}


    
    
    
    public int getMa_tai_khoan() {
        return ma_tai_khoan;
    }

    public void setMa_tai_khoan(int ma_tai_khoan) {
        this.ma_tai_khoan = ma_tai_khoan;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        String salt = "afajkbjkasbdsdacugxuiagjzhzxmnxcbmv";
        // Băm mật khẩu trước khi lưu
        this.mat_khau = hashPasswordSHA256(mat_khau + salt);
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

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.ma_tai_khoan;
        hash = 29 * hash + Objects.hashCode(this.ten_dang_nhap);
        hash = 29 * hash + Objects.hashCode(this.mat_khau);
        hash = 29 * hash + (this.tinh_trang ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaiKhoan other = (TaiKhoan) obj;
        if (this.ma_tai_khoan != other.ma_tai_khoan) {
            return false;
        }
        if (this.tinh_trang != other.tinh_trang) {
            return false;
        }
        if (!Objects.equals(this.ten_dang_nhap, other.ten_dang_nhap)) {
            return false;
        }
        return Objects.equals(this.mat_khau, other.mat_khau);
    }

    
    
}
