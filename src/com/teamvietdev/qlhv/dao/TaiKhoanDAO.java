    package com.teamvietdev.qlhv.dao;

    import com.teamvietdev.qlhv.model.TaiKhoan;

    public interface TaiKhoanDAO {

        TaiKhoan login(String tdn, String mk);

        boolean save(TaiKhoan taiKhoan);

        TaiKhoan findByUsername(String ten_dang_nhap);

    public boolean isPasswordCorrect(String inputPassword, String storedHash);

    public String getPasswordHash(String username);

    }

