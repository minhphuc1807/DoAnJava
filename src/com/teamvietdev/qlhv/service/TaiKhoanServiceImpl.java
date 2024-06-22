package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.dao.TaiKhoanDAO;
import com.teamvietdev.qlhv.dao.TaiKhoanDAOImpl;
import com.teamvietdev.qlhv.model.TaiKhoan;

/**
 *
 * @author ASUS
 */
public class TaiKhoanServiceImpl implements TaiKhoanService{
    
    private TaiKhoanDAO taiKhoanDAO = null;

    public TaiKhoanServiceImpl() {
        taiKhoanDAO = new TaiKhoanDAOImpl();
    }
    
    

    @Override
    public TaiKhoan login(String tdn, String mk) {
        return taiKhoanDAO.login(tdn, mk);
    }
    
}
