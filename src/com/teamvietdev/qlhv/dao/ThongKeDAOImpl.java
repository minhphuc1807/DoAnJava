package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.bean.KhoaHocBean;
import com.teamvietdev.qlhv.bean.LopHocBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAOImpl implements ThongKeDAO {

    @Override
    public List<LopHocBean> getListByLopHoc() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT ngay_dang_ky, COUNT(*) as so_luong FROM lop_hoc GROUP BY ngay_dang_ky;";
        List<LopHocBean> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocBean lopHocBean = new LopHocBean();
                lopHocBean.setNgay_dang_ky(rs.getString("ngay_dang_ky"));
                lopHocBean.setSo_luong_hoc_vien(rs.getInt("so_luong"));
                list.add(lopHocBean);
            }
            ps.close();
            cons.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KhoaHocBean> getListByKhoaHoc() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT ten_khoa_hoc, ngay_bat_dau, ngay_ket_thuc FROM khoa_hoc WHERE tinh_trang = TRUE ORDER BY ngay_bat_dau ASC;";
        List<KhoaHocBean> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhoaHocBean khoaHocBean = new KhoaHocBean();
                khoaHocBean.setTen_khoa_hoc(rs.getString("ten_khoa_hoc"));
                khoaHocBean.setNgay_bat_dau(rs.getDate("ngay_bat_dau"));
                khoaHocBean.setNgay_ket_thuc(rs.getDate("ngay_ket_thuc"));
                list.add(khoaHocBean);
            }
            ps.close();
            cons.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}