package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.dao.HocVienDAO;
import com.teamvietdev.qlhv.dao.HocVienDAOImpl;
import com.teamvietdev.qlhv.model.HocVien;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class HocVienServiceImpl implements HocVienService{
    
    private HocVienDAO hocVienDAO = null;

    public HocVienServiceImpl() {
        hocVienDAO = new HocVienDAOImpl();
    }
    
    

    @Override
    public List<HocVien> getList() {
        return hocVienDAO.getList();
    }

    @Override
    public int createOrUpdate(HocVien hocVien) {
        return hocVienDAO.createOrUpdate(hocVien);
    }
    
}
