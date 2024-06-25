package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.model.HocVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAOImpl implements HocVienDAO {

    @Override
    public List<HocVien> getList() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM hoc_vien";
        List<HocVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocVien hocVien = new HocVien();
                hocVien.setMa_hoc_vien(rs.getInt("ma_hoc_vien"));
                hocVien.setHo_ten(rs.getString("ho_ten"));
                hocVien.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                hocVien.setDia_chi(rs.getString("dia_chi"));
                hocVien.setNgay_sinh(rs.getDate("ngay_sinh"));
                hocVien.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                hocVien.setTinh_trang(rs.getBoolean("tinh_trang"));
                list.add(hocVien);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public int createOrUpdate(HocVien hocVien) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO hoc_vien(ma_hoc_vien, ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, tinh_trang) VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ho_ten = VALUES(ho_ten), ngay_sinh = VALUES(ngay_sinh), gioi_tinh = VALUES(gioi_tinh), so_dien_thoai = VALUES(so_dien_thoai), dia_chi = VALUES(dia_chi), tinh_trang = VALUES(tinh_trang);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, hocVien.getMa_hoc_vien());
            ps.setString(2, hocVien.getHo_ten());
            ps.setDate(3, (Date) hocVien.getNgay_sinh());
            ps.setBoolean(4, hocVien.isGioi_tinh());
            ps.setString(5, hocVien.getSo_dien_thoai());
            ps.setString(6, hocVien.getDia_chi());
            ps.setBoolean(7, hocVien.isTinh_trang());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}