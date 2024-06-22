package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.bean.KhoaHocBean;
import com.teamvietdev.qlhv.bean.LopHocBean;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ThongKeService {
    
    public List<LopHocBean> getListByLopHoc();
    
    
    public List<KhoaHocBean> getListByKhoaHoc();
    
}
