package com.teamvietdev.qlhv.model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhoaHoc {
    
    private int ma_khoa_hoc;
    private String ten_khoa_hoc;
    private String mo_ta;
    private Date ngay_bat_dau;
    private Date ngay_ket_thuc;
    private boolean tinh_trang;

    public int getMa_khoa_hoc() {
        return ma_khoa_hoc;
    }

    public void setMa_khoa_hoc(int ma_khoa_hoc) {
        this.ma_khoa_hoc = ma_khoa_hoc;
    }

    public String getTen_khoa_hoc() {
        return ten_khoa_hoc;
    }

    public void setTen_khoa_hoc(String ten_khoa_hoc) {
        this.ten_khoa_hoc = ten_khoa_hoc;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public Date getNgay_bat_dau() {
        return ngay_bat_dau;
    }

    public void setNgay_bat_dau(Date ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }

    public Date getNgay_ket_thuc() {
        return ngay_ket_thuc;
    }

    public void setNgay_ket_thuc(Date ngay_ket_thuc) {
        this.ngay_ket_thuc = ngay_ket_thuc;
    }

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
    
    
    
}
