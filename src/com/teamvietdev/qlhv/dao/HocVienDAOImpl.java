package com.teamvietdev.qlhv.dao;

import java.sql.Connection;
import com.teamvietdev.qlhv.model.HocVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class HocVienDAOImpl implements HocVienDAO{

    @Override
    public List<HocVien> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM db_qlhv.hoc_vien";
            List<HocVien> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocVien hocVien = new HocVien();
                hocVien.setMa_hoc_vien(rs.getInt("ma_hoc_vien"));
                hocVien.setHo_ten(rs.getString("ho_ten"));
                hocVien.setNgay_sinh(rs.getDate("ngay_sinh"));
                hocVien.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                hocVien.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                hocVien.setDia_chi(rs.getString("dia_chi"));
                hocVien.setTinh_trang(rs.getBoolean("tinh_trang"));                
                list.add(hocVien);
            }
            ps.close();
            rs.close();
            cons.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public int createOrUpdate(HocVien hocVien) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO hoc_vien(ma_hoc_vien, ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, tinh_trang) VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ho_ten = VALUES(ho_ten), ngay_sinh = VALUES(ngay_sinh), gioi_tinh = VALUES(gioi_tinh), so_dien_thoai = VALUES(so_dien_thoai), dia_chi = VALUES(dia_chi), tinh_trang = VALUES(tinh_trang);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, hocVien.getMa_hoc_vien());
            ps.setString(2, hocVien.getHo_ten());
            ps.setDate(3, (java.sql.Date) new Date(hocVien.getNgay_sinh().getTime()));
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
    
    public static void main(String[] args) {
        HocVienDAO hocVienDAO = new HocVienDAOImpl();
        System.out.println(hocVienDAO.getList());
    }
    
}
