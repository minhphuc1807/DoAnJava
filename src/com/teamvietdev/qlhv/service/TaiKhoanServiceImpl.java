package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.dao.TaiKhoanDAO;
import com.teamvietdev.qlhv.dao.TaiKhoanDAOImpl;
import static com.teamvietdev.qlhv.dao.TaiKhoanDAOImpl.hashPasswordSHA256;
import com.teamvietdev.qlhv.model.TaiKhoan;

public class TaiKhoanServiceImpl implements TaiKhoanService {

    private TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanServiceImpl() {
        this.taiKhoanDAO = new TaiKhoanDAOImpl(); // Khởi tạo DAO
    }

    @Override
    public boolean save(TaiKhoan taiKhoan) {
        // Bạn có thể thêm logic xử lý trước khi lưu vào DAO nếu cần
        return taiKhoanDAO.save(taiKhoan);
    }

    @Override
    public TaiKhoan findByUsername(String tenDangNhap) {
        // Bạn có thể thêm logic xử lý trước khi truy vấn từ DAO nếu cần
        return taiKhoanDAO.findByUsername(tenDangNhap);
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
}
