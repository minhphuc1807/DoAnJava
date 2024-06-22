package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.bean.KhoaHocBean;
import com.teamvietdev.qlhv.bean.LopHocBean;
import com.teamvietdev.qlhv.service.ThongKeService;
import com.teamvietdev.qlhv.service.ThongKeServiceImpl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class QuanLyThongKeController {

    private ThongKeService thongKeService = null;

    public QuanLyThongKeController() {
        this.thongKeService = new ThongKeServiceImpl();
    }

    public void setDataToChart1(JPanel jpnItem) {
        List<LopHocBean> listItem = thongKeService.getListByLopHoc();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (LopHocBean item : listItem) {
                dataset.addValue(item.getSo_luong_hoc_vien(), "Học viên", item.getNgay_dang_ky());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng học viên đăng ký".toUpperCase(),
                "Thời gian", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    public void setDataToChart2(JPanel jpnItem) {
        List<KhoaHocBean> listItem = thongKeService.getListByKhoaHoc();

        TaskSeriesCollection ds = new TaskSeriesCollection();
        JFreeChart ganttChart = ChartFactory.createGanttChart(
                "BIỂU ĐỒ THEO DÕI TÌNH TRẠNG KHÓA HỌC",
                "Khóa học", "Thời gian", ds, true, false, false
        );

        TaskSeries taskSeries;
        Task task;

        if (listItem != null) {
            for (KhoaHocBean item : listItem) {
                taskSeries = new TaskSeries(item.getTen_khoa_hoc());
                task = new Task(item.getTen_khoa_hoc(), new SimpleTimePeriod(item.getNgay_bat_dau(), item.getNgay_ket_thuc()));
                taskSeries.add(task);
                ds.add(taskSeries);
            }
        }

        ChartPanel chartPanel = new ChartPanel(ganttChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

}