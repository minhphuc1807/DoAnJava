package com.teamvietdev.qlhv.utility;

import com.teamvietdev.qlhv.model.HocVien;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ClassTableModel {
    
    public DefaultTableModel setTableHocVien(List<HocVien> listItem, String[] listColumn){
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }   

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
            
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0){
            for(int i = 0; i < rows; i++){
                HocVien hocVien = listItem.get(i);
                obj = new Object[columns];
                obj[0] = hocVien.getMa_hoc_vien();
                obj[1] = (i + 1);
                obj[2] = hocVien.getHo_ten();
                obj[3] = hocVien.getNgay_sinh();
                obj[4] = hocVien.isGioi_tinh() == true ? "Nam" : "Nữ";
                obj[5] = hocVien.getSo_dien_thoai();
                obj[6] = hocVien.getDia_chi();
                obj[7] = hocVien.isTinh_trang();
                dtm.addRow(obj);
            }
        }        
        return dtm;                
    }
    
}
