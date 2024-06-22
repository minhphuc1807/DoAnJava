package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.model.HocVien;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface HocVienDAO {
    
    public List<HocVien> getList();
    
    public int createOrUpdate(HocVien hocVien);
    
}