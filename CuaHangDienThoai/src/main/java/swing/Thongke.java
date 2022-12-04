/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import service.IPhuKienService;
import service.impl.FHoaDonService;
import service.impl.PhuKienService;
import viewmodel.QLHoaDon;
import viewmodel.QLPhuKien;
import viewmodel.QLHoaDonChiTiet;
import service.impl.FHoaDonChiTietService;
import service.IFHoaDonService;
import service.IFHoaDonChiTietService;

/**
 *
 * @author Admin
 */
public class Thongke extends javax.swing.JFrame {
//  List<QLPhuKien> listQLPhuKien = new ArrayList<>();
//     IPhuKienService iPhuKienService;
//
//     List<QLHoaDon> lstQLHD = new ArrayList<>();
//     IHoaDonService iHoaDonService;
////     

    List<QLHoaDonChiTiet> lsthdct = new ArrayList<>();
    IFHoaDonChiTietService ihoadonchitiet;

    /**
     * Creates new form Thongke
     */
    public Thongke() {
//        this.iPhuKienService = new PhuKienService();
//        this.iHoaDonService = new HoaDonService();
        this.ihoadonchitiet = new FHoaDonChiTietService();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
//        showPieChart();
//        showBarChart();
//        showHistogram();
//        showLineChart();
        thongKeCot();
//Thongke();
//in();
    }

//    public void setDataToChart1(panelbarchar jpnItem) {
//        List<LopHocBean> listItem = thongKeService.getListByLopHoc();
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        if (listItem != null) {
//            for (LopHocBean item : listItem) {
//                dataset.addValue(item.getSo_luong_hoc_vien(), "Học viên", item.getNgay_dang_ky());
//            }
//        }
//
//        JFreeChart barChart = ChartFactory.createBarChart(
//                "Biểu đồ thống kê số lượng học viên đăng ký".toUpperCase(),
//                "Thời gian", "Số lượng",
//                dataset, PlotOrientation.VERTICAL, false, true, false);
//
//        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));
//
//        jpnItem.removeAll();
//        jpnItem.setLayout(new CardLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.repaint();
//    }
//     private  IHoaDonService IHoadonservice  ;
//List<QLHoaDon> lsthd = IHoadonservice.getAll();
    
    public void thongKeCot() {
//    List<QLHoaDon> lstpk = iHoaDonService.getAll();
        List<QLHoaDonChiTiet> lstpk = ihoadonchitiet.getAll();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (lstpk != null) {
            for (QLHoaDonChiTiet hdct : lstpk) {
                dataset.setValue(hdct.getTongTien(hdct.getSoluong()), "Tổng Doanh Thu", hdct.getIdHD().getNgayThanhToan() );


            }
        }
        
         
        
        JFreeChart linechart = ChartFactory.createLineChart("Thống kê doanh thu", "Năm", "Tổng Doanh Thu",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        ChartPanel sd = new ChartPanel(linechart);
        panelLineChart.removeAll();
        panelLineChart.add(sd, BorderLayout.CENTER);
        panelLineChart.validate();



        




    }
//      public void Thongke(){
//     List<QLPhuKien> lstpk = iPhuKienService.getAll();
//
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        if (lstpk != null) {
//            for (QLPhuKien item : lstpk) {
//                dataset.addValue(item.get, "Học viên", item.getNgayThanhToan());
//            }
//        }
//
//        JFreeChart barChart = ChartFactory.createBarChart(
//                "Biểu đồ thống kê số lượng học viên đăng ký".toUpperCase(),
//                "Thời gian", "Số lượng",
//                dataset, PlotOrientation.VERTICAL, false, true, false);
//
//        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(new Dimension(jPanel2.getWidth(), 321));
//
//        jPanel2.removeAll();
//        jPanel2.setLayout(new CardLayout());
//        jPanel2.add(chartPanel);
//        jPanel2.validate();
//        jPanel2.repaint();
//      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLineChart.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 410, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Thongke().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelLineChart;
    // End of variables declaration//GEN-END:variables
//public void showPieChart(){
//        
//        //create dataset
//      DefaultPieDataset barDataset = new DefaultPieDataset( );
//      
//      
//      barDataset.setValue( "IPhone 5s" , new Double( 20 ) );  
//      barDataset.setValue( "SamSung Grand" , new Double( 20 ) );   
//      barDataset.setValue( "MotoG" , new Double( 40 ) );    
//      barDataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
//
//      //create chart
//       JFreeChart piechart = ChartFactory.createPieChart("mobile sales",barDataset, false,true,false);//explain
//      
//        PiePlot piePlot =(PiePlot) piechart.getPlot();
//      
//       //changing pie chart blocks colors
//       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
//        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
//        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
//        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
//      
//       
//        piePlot.setBackgroundPaint(Color.white);
//        
//        //create chartPanel to display chart(graph)
//        ChartPanel barChartPanel = new ChartPanel(piechart);
//        panelchart.removeAll();
//        panelchart.add(barChartPanel, BorderLayout.CENTER);
//        panelchart.validate();
//    }
//
//    /*=============================================================================*/
//    
//    public void showLineChart(){
//        //create dataset for the graph
//         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.setValue(200, "Amount", "january");
//        dataset.setValue(150, "Amount", "february");
//        dataset.setValue(18, "Amount", "march");
//        dataset.setValue(100, "Amount", "april");
//        dataset.setValue(80, "Amount", "may");
//        dataset.setValue(250, "Amount", "june");
//        
//        //create chart
//        JFreeChart linechart = ChartFactory.createLineChart("contribution","monthly","amount", 
//                dataset, PlotOrientation.VERTICAL, false,true,false);
//        
//        //create plot object
//         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
//       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
//        lineCategoryPlot.setBackgroundPaint(Color.white);
//        
//        //create render object to change the moficy the line properties like color
//        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
//        Color lineChartColor = new Color(204,0,51);
//        lineRenderer.setSeriesPaint(0, lineChartColor);
//        
//         //create chartPanel to display chart(graph)
//        ChartPanel lineChartPanel = new ChartPanel(linechart);
//        panelLineChart.removeAll();
//        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
//        panelLineChart.validate();
//    }
//
//    /*========================================================================================*/
//    
//    public void showHistogram(){
//        
//         double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
//                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
//                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
//                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
//                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
//                          };
// 
// 
//        HistogramDataset dataset = new HistogramDataset();
//        dataset.addSeries("key", values, 20);
//        
//         JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram","Data", "Frequency", dataset,PlotOrientation.VERTICAL, false,true,false);
//            XYPlot plot= chart.getXYPlot();
//        plot.setBackgroundPaint(Color.WHITE);
//
//        
//        
//        ChartPanel barpChartPanel2 = new ChartPanel(chart);
//        panelcuoi.removeAll();
//        panelcuoi.add(barpChartPanel2, BorderLayout.CENTER);
//        panelcuoi.validate();
//    }
//
//    /*========================================================================================*/
//    
//    public void showBarChart(){
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.setValue(200, "Amount", "january");
//        dataset.setValue(150, "Amount", "february");
//        dataset.setValue(18, "Amount", "march");
//        dataset.setValue(100, "Amount", "april");
//        dataset.setValue(80, "Amount", "may");
//        dataset.setValue(250, "Amount", "june");
//        
//
//        JFreeChart chart = ChartFactory.createBarChart("contribution","monthly","amount", 
//                dataset, PlotOrientation.VERTICAL, false,true,false);
//        
//        CategoryPlot categoryPlot = chart.getCategoryPlot();
//        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
//        categoryPlot.setBackgroundPaint(Color.WHITE);
//        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
//        Color clr3 = new Color(204,0,51);
//        renderer.setSeriesPaint(0, clr3);
//        
//        ChartPanel barpChartPanel = new ChartPanel(chart);
//        panelbarchar.removeAll();
//        panelbarchar.add(barpChartPanel, BorderLayout.CENTER);
//        panelbarchar.validate();
//        
//        
//    }

}
