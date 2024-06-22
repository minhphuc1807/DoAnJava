package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.model.HocVien;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface HocVienService {
    
    List<HocVien> getList();
    
    public int createOrUpdate(HocVien hocVien);

}
