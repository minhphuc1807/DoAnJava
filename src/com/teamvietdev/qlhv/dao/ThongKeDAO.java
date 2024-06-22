package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.bean.KhoaHocBean;
import com.teamvietdev.qlhv.bean.LopHocBean;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ThongKeDAO {
    
    public List<LopHocBean> getListByLopHoc();
    
    public List<KhoaHocBean> getListByKhoaHoc();
    
}
