/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import domainmodel.DienThoai;
import domainmodel.HoaDon;
import domainmodel.HoaDonChiTiet;
import domainmodel.PhuKien;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import qr_code.Main;
import repository.impl.DienThoaiRepository;
import repository.impl.HoaDonChiTietRepository;
import repository.impl.HoaDonRepository;
import service.IDienThoaiService;
import service.INhanVienService;
import service.IPhuKienService;
import service.KhachHangService;
import service.KhuyenMaiService;
import service.impl.DienThoaiService;
import service.impl.KhachHangServiceImpl;
import service.impl.KhuyenMaiServiceImpl;
import util.ImageHelper;
import viewmodel.QLDienThoai;
import viewmodel.QLPhuKien;
import service.impl.PhuKienService;
import service.impl.NhanVienService;
import viewmodel.KhachHangViewMD;
import viewmodel.KhuyenMaiViewModel;
import viewmodel.QLNhanVien;

/**
 *
 * @author Admin
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    //Tuấn Anh code
    DienThoaiRepository dienThoaiRepo = new DienThoaiRepository();
    //Test source tree
    //Tuấn Anh test
    //Hoa thêm code
    //Hoá Đơn
    HoaDonRepository hoaDonRepo = new HoaDonRepository();
    DefaultTableModel modelHoaDon;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //Hoá đơn chi tiết
    static DefaultTableModel modelHDCT;
    HoaDonChiTietRepository hoaDonChiTietRepo;
//DienThoai Dũng Code
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultComboBoxModel dcbmTrangThai = new DefaultComboBoxModel();
    private IDienThoaiService dienThoaiService = new DienThoaiService();
    private List<QLDienThoai> listQLDienThoai = new ArrayList<>();
    private List<String> cbbDienThoai = new ArrayList<>();
    private byte[] personalImage;
    //Hết code của Dũng
    //Code của Vanh
    private DefaultTableModel dtmPhuKien = new DefaultTableModel();
    private List<QLPhuKien> listQLPhuKien = new ArrayList<>();
    private IPhuKienService iPhuKienService = new PhuKienService();
    private DefaultComboBoxModel dcbmTrangThaiPK = new DefaultComboBoxModel();
    private List<String> listCBBPK = new ArrayList<>();
    private DefaultTableModel dtmNhanVien = new DefaultTableModel();
    private List<QLNhanVien> listQLNhanVien = new ArrayList<>();
    private INhanVienService iNhanVienService = new NhanVienService();
    private DefaultComboBoxModel dcbmTrangThaiNV = new DefaultComboBoxModel();
    private List<String> listCBBNV = new ArrayList<>();
    //Hết code của Vanh
    //Code của Hoa
    private DefaultTableModel dtmKH = new DefaultTableModel();
    private DefaultTableModel dtmKM = new DefaultTableModel();
    private DefaultComboBoxModel dcmTT = new DefaultComboBoxModel();
    private List<KhachHangViewMD> listKH = new ArrayList<>();
    private List<KhuyenMaiViewModel> listKM = new ArrayList<>();
    private KhachHangService khService = new KhachHangServiceImpl();
    private KhuyenMaiService kmService = new KhuyenMaiServiceImpl();
//Hết code Hoa

    public Home() {
        initComponents();
        resetColor(new JPanel[]{btn_2, btn_3, btn_4}, new JPanel[]{ind_2, ind_3, ind_4});
        this.setExtendedState(MAXIMIZED_BOTH);
        modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        modelHDCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();

        
        // Code của Vanh
        String[] headersPK = {"Tên", "Số lượng", "Giá bán", "Thời gian bảo hành", "Mô tả", "Trạng thái"};
        dtmPhuKien.setColumnIdentifiers(headersPK);
        tblPhuKien.setModel(dtmPhuKien);
        listQLPhuKien = iPhuKienService.getAll();
        showDataTablePK(listQLPhuKien);
        cbbTrangThaiPK.setModel(dcbmTrangThaiPK);
        listCBBPK.add("Đang bán");
        listCBBPK.add("Ngừng bán");
        for (String item : listCBBPK) {
            dcbmTrangThaiPK.addElement(item);
        }
        String[] headersNV = {"Mã NV", "Họ tên", "Giới tính", "Năm Sinh", "Địa chỉ", "CCCD", "Email", "SĐT", "Trạng Thái"};
        dtmNhanVien.setColumnIdentifiers(headersNV);
        tblNhanVien.setModel(dtmNhanVien);
        listQLNhanVien = iNhanVienService.getAll();
        showDataTableNV(listQLNhanVien);
        cbbTrangThaiNV.setModel(dcbmTrangThaiNV);
        listCBBNV.add("Đang làm việc");
        listCBBNV.add("Đã nghỉ việc");
        for (String item : listCBBNV) {
            dcbmTrangThaiNV.addElement(item);
        }

        //code Tuấn Anh
        fillToHoaDon();
        addPanelDienThoai();
        addPanelPhuKien();
        //Code của Hoa
        tblListData.setModel(dtmKH);
        tblListDataKM.setModel(dtmKM);
        cbbTTKhuyenMai.setModel(dcmTT);
        String[] headerKH = {"Số CCCD", "Họ và tên", "Giới tính", "SĐT", "Email", "Địa chỉ", "Ngày mua", "Điểm tích lũy", "Ghi chú"};
        dtmKH.setColumnIdentifiers(headerKH);
        String[] headerKM = {"Mã KM", "Tên Khuyến Mại", "Số tiền giảm", "Chiết khấu", "Ngày Bắt đầu", "Ngày kết thúc", "Trạng thái"};
        dtmKM.setColumnIdentifiers(headerKM);
        dcmTT.addElement("Đang hoạt động");
        dcmTT.addElement("Hết hiệu lực");
        listKH = khService.getAll();
        listKM = kmService.getAll();
        showTableKH(listKH);
        showTableKM(listKM);
//Hết code Hoa
    }

    void codeDung(){
        //Dũng Code
        String[] header = {"Tên Điện Thoại", "Số Lượng", "RAM", "ROM", "Màn Hình", "Màu Sắc", "Giá Bán", "Thời Gian Bảo Hành", "Trạng Thái"};
        tblListDienThoai.setModel(dtm);
        dtm.setColumnIdentifiers(header);
        showData(listQLDienThoai = dienThoaiService.getAll());
        cbbTrangThaiDT.setModel(dcbmTrangThai);
        cbbDienThoai.add("Đang Bán");
        cbbDienThoai.add("Ngừng Bán");
        for (String a : cbbDienThoai) {
            dcbmTrangThai.addElement(a);
        }
        // Hết code Dũng
    }
//code Hoa

    private void showTableKH(List<KhachHangViewMD> lists) {
        dtmKH.setRowCount(0);
        for (KhachHangViewMD x : lists) {
            dtmKH.addRow(x.toDataRow());
        }
    }

    private void showTableKM(List<KhuyenMaiViewModel> lists) {
        dtmKM.setRowCount(0);
        for (KhuyenMaiViewModel x : lists) {
            dtmKM.addRow(x.toDataRow());
        }
    }
//FillData Hoa

    private void fillDataKH(int i) {
        KhachHangViewMD kh = listKH.get(i);
        txtCCCD.setText(kh.getCCCD());
        txtDiaChi.setText(kh.getDiaChi());
        txtDiemTichLuy.setText(kh.getDiemTichLuy() + "");
        txtEmail.setText(kh.getEmail());
        ngayMuaDP.setDate(kh.getNgayMua().toLocalDate());
        txtSDT.setText(kh.getSDT());
        txtGhiChu.setText(kh.getGhiChu());
        if (kh.getGioiTinh() == 1) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtNamSinh.setText(kh.getNamSinh() + "");
        txtHoVaTen.setText(kh.getHoTen());
    }

    private void fillDataKM(int i) {
        KhuyenMaiViewModel kh = listKM.get(i);
        //maKM, tenKM, soTienGiam, chietKhau, ngayBatDau, ngayKT
        txtKhuyenMai.setText(kh.getMaKM());
        txtTenKhuyenMai.setText(kh.getTenKM());
        txtSoTienGiam.setText(kh.getSoTienGiam() + "");
        txtChietKhau.setText(kh.getChietKhau() + "");
        datePickerNgayBD.setDate(kh.getNgayBatDau().toLocalDate());
        datePickerKT.setDate(kh.getNgayKT().toLocalDate());
        if (kh.getTrangThai() == 1) {
            cbbTTKhuyenMai.setSelectedIndex(1);
        } else {
            cbbTTKhuyenMai.setSelectedIndex(0);
        }
    }
//getData Hoa

    private KhachHangViewMD getDataKH() {
        KhachHangViewMD kh = new KhachHangViewMD();
        kh.setCCCD(txtCCCD.getText());
        kh.setHoTen(txtHoVaTen.getText());
        kh.setDiaChi(txtDiaChi.getText());
        kh.setDiemTichLuy(Integer.valueOf(txtDiemTichLuy.getText()));
        kh.setEmail(txtEmail.getText());
        kh.setNamSinh(Integer.valueOf(txtNamSinh.getText()));
        kh.setNgayMua(java.sql.Date.valueOf(ngayMuaDP.getDate()));
        kh.setSDT(txtSDT.getText());
        kh.setGhiChu(txtGhiChu.getText());
        if (rdoNam.isSelected()) {
            kh.setGioiTinh(1);
        } else {
            kh.setGioiTinh(0);
        }
        return kh;
    }

    private KhuyenMaiViewModel getDataKM() {
        KhuyenMaiViewModel kh = new KhuyenMaiViewModel();
        kh.setMaKM(txtKhuyenMai.getText());
        kh.setTenKM(txtTenKhuyenMai.getText());
        String tienGiam = txtSoTienGiam.getText();
        BigDecimal giam = new BigDecimal(tienGiam);
        kh.setSoTienGiam(giam);
        String ck = txtChietKhau.getText();
        BigDecimal ck1 = new BigDecimal(ck);
        kh.setChietKhau(ck1);
        kh.setNgayBatDau(java.sql.Date.valueOf(datePickerNgayBD.getDate()));
        kh.setNgayKT(java.sql.Date.valueOf(datePickerKT.getDate()));
        if (cbbTTKhuyenMai.getSelectedIndex() == 0) {
            kh.setTrangThai(0);
        } else {
            kh.setTrangThai(1);
        }
        return kh;
    }
//Hết fill Hoa
    //code Tuấn Anh

    void fillToHoaDon() {
        modelHoaDon.setRowCount(0);
        for (HoaDon x : hoaDonRepo.getAll()) {
            modelHoaDon.addRow(new Object[]{x.getMaHD(), "NV1", x.getNgayTao().format(formatter), x.getNgayThanhToan() == null ? "Chưa thanh toán" : x.getNgayThanhToan(), x.getTrangThaiStr()});
        }
    }

    void setButtonClick(JPanel panelClick) {
        JPanel panel[] = {btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10};
        for (JPanel x : panel) {
            x.setBackground(new Color(23, 35, 51));
        }
        panelClick.setBackground(new Color(40, 57, 80));
        panelClick.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        JOptionPane.showMessageDialog(this, "Hello");
    }
//    void setTable(){
//        tblThongKe.getTableHeader().setReorderingAllowed(false);
//        tblThongKe.setShowHorizontalLines(true);
//        tblThongKe.setOpaque(true);
//        tblThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
//    }

    void addPanelDienThoai() {
        FlowLayout flowLayout = new FlowLayout(10, 0, 0);
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setHgap(2);
        flowLayout.setVgap(2);
        flowLayout.setAlignOnBaseline(true);
        flowLayout.setAlignment(5);
        pnlSanPham.setLayout(flowLayout);
        List<DienThoai> list = dienThoaiRepo.getAll();
        for (DienThoai x : list) {
            clickProduct(x, null);
        }
    }

    void addPanelPhuKien() {
        FlowLayout flowLayout = new FlowLayout(10, 0, 0);
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setHgap(2);
        flowLayout.setVgap(2);
        flowLayout.setAlignOnBaseline(true);
        flowLayout.setAlignment(5);
        pnlSanPham.setLayout(flowLayout);
        for (PhuKien x : iPhuKienService.getAll1()) {
//            clickProduct(x);
            clickProduct(null, x);
        }
    }

    void clickProduct(DienThoai x, PhuKien y) {
        Image img = null;
        try {
            img = ImageHelper.createFromByteArray(x != null ? x.getAnh() : y.getAnh(), "png");
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setSize(120, 70);
//                panel.setBackground(Color.red);
        JLabel label = new JLabel(new ImageIcon(ImageHelper.resige(img, 100, 60)));
        panel.add(label);
        JLabel ten = new JLabel(x != null ? x.getTenDienThoai() : y.getTen());
        panel.add(ten);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblHoaDon.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn hoá đơn");
                    return;
                }
                if (!tblHoaDon.getValueAt(row, 4).toString().equals("Chờ thanh toán")) {
                    JOptionPane.showMessageDialog(null, "Chỉ có hoá đơn chờ thanh toán mới được sửa");
                    return;
                }
                HoaDon hoaDon = hoaDonRepo.getOne(tblHoaDon.getValueAt(row, 0).toString());
                if (x != null) {
                    ShowProduct.getValues(x, hoaDon);
                } else {
                    ShowPhuKien.getValues(y, hoaDon);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new java.awt.Color(100, 149, 237));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new java.awt.Color(255, 255, 255));

            }
        });
        pnlSanPham.add(panel);
    }

    public static void fillToHDCT(String maHD) {
        HoaDon hoaDon = HoaDonRepository.getOne(maHD);
        List<HoaDonChiTiet> list = hoaDon.getList();
        for (HoaDonChiTiet x : list) {
            modelHDCT.addRow(new Object[]{x.getDienThoai(), x.getSoLuong(), x.getDonGia(), x.getDienThoai().getGia(x.getSoLuong())});
        }
    }
    //Hết code Tuấn ANh

    //Code Dũng
    private void showData(List<QLDienThoai> showList) {
        dtm.setRowCount(0);
        for (QLDienThoai dienThoai : showList) {
            dtm.addRow(new Object[]{dienThoai.getTenDienThoai(), dienThoai.getSoLuongTon(), dienThoai.getRAM(), dienThoai.getROM(), dienThoai.getManHinh(), dienThoai.getMauSac(), dienThoai.getGiaBan(), dienThoai.getThoiGianBaoHanh(), dienThoai.getTrangThai() == 1 ? "Đang Bán" : "Ngừng Bán"});
        }
    }

    //Hết code Dũng
    //Code của Vanh
    private void showDataTablePK(List<QLPhuKien> lists) {
        dtmPhuKien.setRowCount(0);
        for (QLPhuKien pk : lists) {
            dtmPhuKien.addRow(pk.toDataRow());
        }
    }

    private void fillDataPK(int i) {
        QLPhuKien qLPhuKien = listQLPhuKien.get(i);
        txtTenPK.setText(qLPhuKien.getTen());
        txtGiaBanPK.setText(String.valueOf(qLPhuKien.getGiaBan()));
        txtSoLuongTonPK.setText(String.valueOf(qLPhuKien.getSoLuong()));
        txtMaPK.setText(qLPhuKien.getMa());
        txtMoTaPK.setText(qLPhuKien.getMoTa());
        txtThoiGianBaoHanhPK.setText(String.valueOf(qLPhuKien.getThoiGianBaoHanh()));
        if (qLPhuKien.getTrangThai() == 1) {
            cbbTrangThaiPK.setSelectedItem("Đang bán");
        } else {
            cbbTrangThaiPK.setSelectedItem("Ngừng bán");
        }
        if (qLPhuKien.getAnh() != null) {
            try {
                Image img = ImageHelper.createFromByteArray(qLPhuKien.getAnh(), "jpg");
                lblAnhPK.setIcon(new ImageIcon(ImageHelper.resige(img, lblAnhPK.getWidth(), lblAnhPK.getHeight())));
                personalImage = qLPhuKien.getAnh();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            lblAnhPK.setIcon(null);
        }
    }

    private void showDataTableNV(List<QLNhanVien> lists) {
        dtmNhanVien.setRowCount(0);
        for (QLNhanVien nv : lists) {
            dtmNhanVien.addRow(nv.toDataRow());
        }
    }

    private void fillDataNV(int i) {
        QLNhanVien qLNhanVien = listQLNhanVien.get(i);
        txtMaNV.setText(qLNhanVien.getMa());
        txtHovaTenNV.setText(qLNhanVien.getHoTen());
        if (qLNhanVien.getGioiTinh() == 1) {
            radioNamNV.setSelected(true);
        } else {
            radioNuNV.setSelected(true);
        }
        txtNamSinhNV.setText(String.valueOf(qLNhanVien.getNamSinh()));
        txtDiaChiNV.setText(qLNhanVien.getDiaChi());
        txtCccdNV.setText(qLNhanVien.getCccd());
        txtEmailNV.setText(qLNhanVien.getEmail());
        txtSoDienThoaiNV.setText(qLNhanVien.getSdt());
        if (qLNhanVien.getTrangThai() == 1) {
            cbbTrangThaiPK.setSelectedItem("Đang làm việc");
        } else {
            cbbTrangThaiPK.setSelectedItem("Đã nghỉ việc");
        }
        if (qLNhanVien.getAnh() != null) {
            try {
                Image img = ImageHelper.createFromByteArray(qLNhanVien.getAnh(), "jpg");
                lblAnhNV.setIcon(new ImageIcon(img));
                personalImage = qLNhanVien.getAnh();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            lblAnhNV.setIcon(null);
        }
    }
    //Hết code của Vanh

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        button1 = new java.awt.Button();
        buttonGroup1 = new javax.swing.ButtonGroup();
        side_pane = new javax.swing.JPanel();
        btn_1 = new javax.swing.JPanel();
        ind_1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_2 = new javax.swing.JPanel();
        ind_2 = new javax.swing.JPanel();
        ind_5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btn_3 = new javax.swing.JPanel();
        ind_3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btn_4 = new javax.swing.JPanel();
        ind_4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btn_5 = new javax.swing.JPanel();
        ind_6 = new javax.swing.JPanel();
        ind_7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btn_6 = new javax.swing.JPanel();
        ind_8 = new javax.swing.JPanel();
        ind_9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btn_7 = new javax.swing.JPanel();
        ind_10 = new javax.swing.JPanel();
        ind_11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btn_8 = new javax.swing.JPanel();
        ind_12 = new javax.swing.JPanel();
        ind_13 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        btn_9 = new javax.swing.JPanel();
        ind_14 = new javax.swing.JPanel();
        ind_15 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        btn_10 = new javax.swing.JPanel();
        ind_16 = new javax.swing.JPanel();
        ind_17 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btn_exit = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        pnlSearch = new javax.swing.JPanel();
        pnlTong = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlBanHang = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        labelCamera = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jPanel25 = new javax.swing.JPanel();
        jTextField100 = new javax.swing.JTextField();
        scpSanPhamBH = new javax.swing.JScrollPane();
        pnlSanPham = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        lblManV = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel143 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jButton36 = new javax.swing.JButton();
        jLabel144 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jTextField97 = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienTraKhach = new javax.swing.JTextField();
        jTextField98 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        pnlDienThoai = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblListDienThoai = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txtTenDienThoai = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        lbAnhDT = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtGiaNhapDienThoai = new javax.swing.JTextField();
        txtGiaBanDienThoai = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtMaDienThoai = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtMoTaDienThoai = new javax.swing.JTextArea();
        jLabel67 = new javax.swing.JLabel();
        txtSoLuongDienThoai = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        cbbTenHangDT = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        cbbTenNCCDT = new javax.swing.JComboBox<>();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtRomDT = new javax.swing.JTextField();
        txtRamDT = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txtCPUDT = new javax.swing.JTextField();
        txtHeDieuHanhDT = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtManHinhDT = new javax.swing.JTextField();
        txtCameraDT = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txtPinDT = new javax.swing.JTextField();
        txtMauSacDT = new javax.swing.JTextField();
        txtBaoHanhDT = new javax.swing.JTextField();
        cbbTrangThaiDT = new javax.swing.JComboBox<>();
        btnTrangTruoc = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnTrangSau = new javax.swing.JButton();
        btnLamMoiDT = new javax.swing.JButton();
        btnXoaDT = new javax.swing.JButton();
        btnSuaDT = new javax.swing.JButton();
        btnThemDT = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        txtTimKiemDienThoai = new javax.swing.JTextField();
        btnTimKiemDienThoai = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        btnTaoMa2 = new javax.swing.JButton();
        pnlPhuKien = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhuKien = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtTimKiemPK = new javax.swing.JTextField();
        btnSearchPK = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblAnhPK = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtTenPK = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtGiaBanPK = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtGiaNhapPK = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtMaPK = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTaPK = new javax.swing.JTextArea();
        jLabel40 = new javax.swing.JLabel();
        cbbTenHangPK = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        txtSoLuongTonPK = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtThoiGianBaoHanhPK = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        cbbTrangThaiPK = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        btnThemPK = new javax.swing.JButton();
        btnSuaPK = new javax.swing.JButton();
        btnXoaPK = new javax.swing.JButton();
        btnClearPK = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlHoaDon = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnTaoMa = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtTimKiemNV = new javax.swing.JTextField();
        txtSoDienThoaiNV = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        btnSuaNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        txtNamSinhNV = new javax.swing.JTextField();
        btnClearNV = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDiaChiNV = new javax.swing.JTextArea();
        btnSearchNV = new javax.swing.JButton();
        txtHovaTenNV = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblAnhNV = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtEmailNV = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtMatKhauNV = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtTenTKNV = new javax.swing.JTextField();
        radioNamNV = new javax.swing.JRadioButton();
        radioNuNV = new javax.swing.JRadioButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        cbbChucVuNV = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        txtCccdNV = new javax.swing.JTextField();
        cbbTrangThaiNV = new javax.swing.JComboBox<>();
        pnlThongKe = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel89 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        pnlKhachHang = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblListData = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        ngayMuaDP = new com.github.lgooddatepicker.components.DatePicker();
        jLabel107 = new javax.swing.JLabel();
        txtDiemTichLuy = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtNamSinh = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        pnlKhuyenMai = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel21 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        txtKhuyenMai = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        txtTenKhuyenMai = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        txtSoTienGiam = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        txtChietKhau = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        datePickerNgayBD = new com.github.lgooddatepicker.components.DatePicker();
        jLabel96 = new javax.swing.JLabel();
        datePickerKT = new com.github.lgooddatepicker.components.DatePicker();
        jLabel97 = new javax.swing.JLabel();
        cbbTTKhuyenMai = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblListDataKM = new javax.swing.JTable();
        btnThemKM = new javax.swing.JButton();
        btnSuaKM = new javax.swing.JButton();
        btnXoaKM = new javax.swing.JButton();
        btnClearKM = new javax.swing.JButton();

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        button1.setBackground(new java.awt.Color(71, 120, 197));
        button1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Book");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(813, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(375, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_pane.setBackground(new java.awt.Color(23, 35, 51));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_1.setBackground(new java.awt.Color(23, 35, 51));
        btn_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_1MouseExited(evt);
            }
        });

        ind_1.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_1.setOpaque(false);

        javax.swing.GroupLayout ind_1Layout = new javax.swing.GroupLayout(ind_1);
        ind_1.setLayout(ind_1Layout);
        ind_1Layout.setHorizontalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_1Layout.setVerticalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel8.setText("Trang chủ");
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_1Layout = new javax.swing.GroupLayout(btn_1);
        btn_1.setLayout(btn_1Layout);
        btn_1Layout.setHorizontalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_1Layout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        btn_1Layout.setVerticalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_1Layout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(btn_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 160, 50));

        btn_2.setBackground(new java.awt.Color(23, 35, 51));
        btn_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_2MouseExited(evt);
            }
        });

        ind_2.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_2.setOpaque(false);

        ind_5.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_5.setOpaque(false);

        javax.swing.GroupLayout ind_5Layout = new javax.swing.GroupLayout(ind_5);
        ind_5.setLayout(ind_5Layout);
        ind_5Layout.setHorizontalGroup(
            ind_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_5Layout.setVerticalGroup(
            ind_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel17.setText("Bán hàng");
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ind_2Layout = new javax.swing.GroupLayout(ind_2);
        ind_2.setLayout(ind_2Layout);
        ind_2Layout.setHorizontalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_2Layout.createSequentialGroup()
                .addComponent(ind_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel17)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ind_2Layout.setVerticalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_2Layout.createSequentialGroup()
                .addComponent(ind_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ind_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selling.png"))); // NOI18N
        jLabel9.setText(" Bán hàng");
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_2Layout = new javax.swing.GroupLayout(btn_2);
        btn_2.setLayout(btn_2Layout);
        btn_2Layout.setHorizontalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_2Layout.createSequentialGroup()
                .addComponent(ind_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        btn_2Layout.setVerticalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_2Layout.createSequentialGroup()
                .addComponent(ind_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(btn_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 160, -1));

        btn_3.setBackground(new java.awt.Color(23, 35, 51));
        btn_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_3MouseExited(evt);
            }
        });

        ind_3.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_3.setOpaque(false);

        javax.swing.GroupLayout ind_3Layout = new javax.swing.GroupLayout(ind_3);
        ind_3.setLayout(ind_3Layout);
        ind_3Layout.setHorizontalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_3Layout.setVerticalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smartphone-call.png"))); // NOI18N
        jLabel10.setText("Điện thoại");
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_3Layout = new javax.swing.GroupLayout(btn_3);
        btn_3.setLayout(btn_3Layout);
        btn_3Layout.setHorizontalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addComponent(ind_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel10)
                .addGap(0, 42, Short.MAX_VALUE))
        );
        btn_3Layout.setVerticalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addGroup(btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ind_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        side_pane.add(btn_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 160, -1));

        btn_4.setBackground(new java.awt.Color(23, 35, 51));
        btn_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_4MouseExited(evt);
            }
        });

        ind_4.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_4.setOpaque(false);

        javax.swing.GroupLayout ind_4Layout = new javax.swing.GroupLayout(ind_4);
        ind_4.setLayout(ind_4Layout);
        ind_4Layout.setHorizontalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_4Layout.setVerticalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/headphone.png"))); // NOI18N
        jLabel11.setText(" Phụ kiện");
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_4Layout = new javax.swing.GroupLayout(btn_4);
        btn_4.setLayout(btn_4Layout);
        btn_4Layout.setHorizontalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_4Layout.createSequentialGroup()
                .addComponent(ind_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addGap(0, 49, Short.MAX_VALUE))
        );
        btn_4Layout.setVerticalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_4Layout.createSequentialGroup()
                .addComponent(ind_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(btn_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 160, 40));

        btn_5.setBackground(new java.awt.Color(23, 35, 51));
        btn_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_5MouseExited(evt);
            }
        });

        ind_6.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_6.setOpaque(false);

        ind_7.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_7.setOpaque(false);

        javax.swing.GroupLayout ind_7Layout = new javax.swing.GroupLayout(ind_7);
        ind_7.setLayout(ind_7Layout);
        ind_7Layout.setHorizontalGroup(
            ind_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_7Layout.setVerticalGroup(
            ind_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel18.setText("Bán hàng");
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ind_6Layout = new javax.swing.GroupLayout(ind_6);
        ind_6.setLayout(ind_6Layout);
        ind_6Layout.setHorizontalGroup(
            ind_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_6Layout.createSequentialGroup()
                .addComponent(ind_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel18)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ind_6Layout.setVerticalGroup(
            ind_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_6Layout.createSequentialGroup()
                .addComponent(ind_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ind_6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bill.png"))); // NOI18N
        jLabel19.setText("Hóa đơn");
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_5Layout = new javax.swing.GroupLayout(btn_5);
        btn_5.setLayout(btn_5Layout);
        btn_5Layout.setHorizontalGroup(
            btn_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_5Layout.createSequentialGroup()
                .addComponent(ind_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel19)
                .addGap(0, 48, Short.MAX_VALUE))
        );
        btn_5Layout.setVerticalGroup(
            btn_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_5Layout.createSequentialGroup()
                .addComponent(ind_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(btn_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 160, -1));

        btn_6.setBackground(new java.awt.Color(23, 35, 51));
        btn_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_6MouseExited(evt);
            }
        });

        ind_8.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_8.setOpaque(false);

        ind_9.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_9.setOpaque(false);

        javax.swing.GroupLayout ind_9Layout = new javax.swing.GroupLayout(ind_9);
        ind_9.setLayout(ind_9Layout);
        ind_9Layout.setHorizontalGroup(
            ind_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_9Layout.setVerticalGroup(
            ind_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel20.setText("Bán hàng");
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ind_8Layout = new javax.swing.GroupLayout(ind_8);
        ind_8.setLayout(ind_8Layout);
        ind_8Layout.setHorizontalGroup(
            ind_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_8Layout.createSequentialGroup()
                .addComponent(ind_9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel20)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ind_8Layout.setVerticalGroup(
            ind_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_8Layout.createSequentialGroup()
                .addComponent(ind_9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ind_8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_user_20px_1.png"))); // NOI18N
        jLabel21.setText("Nhà cung câp");
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_6Layout = new javax.swing.GroupLayout(btn_6);
        btn_6.setLayout(btn_6Layout);
        btn_6Layout.setHorizontalGroup(
            btn_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_6Layout.createSequentialGroup()
                .addComponent(ind_8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel21)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        btn_6Layout.setVerticalGroup(
            btn_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_6Layout.createSequentialGroup()
                .addComponent(ind_8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(btn_6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 160, -1));

        btn_7.setBackground(new java.awt.Color(23, 35, 51));
        btn_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_7MouseExited(evt);
            }
        });

        ind_10.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_10.setOpaque(false);

        ind_11.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_11.setOpaque(false);

        javax.swing.GroupLayout ind_11Layout = new javax.swing.GroupLayout(ind_11);
        ind_11.setLayout(ind_11Layout);
        ind_11Layout.setHorizontalGroup(
            ind_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_11Layout.setVerticalGroup(
            ind_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel22.setText("Bán hàng");
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ind_10Layout = new javax.swing.GroupLayout(ind_10);
        ind_10.setLayout(ind_10Layout);
        ind_10Layout.setHorizontalGroup(
            ind_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_10Layout.createSequentialGroup()
                .addComponent(ind_11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel22)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ind_10Layout.setVerticalGroup(
            ind_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_10Layout.createSequentialGroup()
                .addComponent(ind_11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ind_10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/price-tag.png"))); // NOI18N
        jLabel23.setText("Khuyến mãi");
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_7Layout = new javax.swing.GroupLayout(btn_7);
        btn_7.setLayout(btn_7Layout);
        btn_7Layout.setHorizontalGroup(
            btn_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_7Layout.createSequentialGroup()
                .addComponent(ind_10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel23)
                .addGap(0, 37, Short.MAX_VALUE))
        );
        btn_7Layout.setVerticalGroup(
            btn_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_7Layout.createSequentialGroup()
                .addComponent(ind_10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(btn_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 160, -1));

        btn_8.setBackground(new java.awt.Color(23, 35, 51));
        btn_8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_8MouseExited(evt);
            }
        });

        ind_12.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_12.setOpaque(false);

        ind_13.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_13.setOpaque(false);

        javax.swing.GroupLayout ind_13Layout = new javax.swing.GroupLayout(ind_13);
        ind_13.setLayout(ind_13Layout);
        ind_13Layout.setHorizontalGroup(
            ind_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_13Layout.setVerticalGroup(
            ind_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel24.setText("Bán hàng");
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ind_12Layout = new javax.swing.GroupLayout(ind_12);
        ind_12.setLayout(ind_12Layout);
        ind_12Layout.setHorizontalGroup(
            ind_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_12Layout.createSequentialGroup()
                .addComponent(ind_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel24)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ind_12Layout.setVerticalGroup(
            ind_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_12Layout.createSequentialGroup()
                .addComponent(ind_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ind_12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/staff.png"))); // NOI18N
        jLabel25.setText("Nhân viên");
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_8Layout = new javax.swing.GroupLayout(btn_8);
        btn_8.setLayout(btn_8Layout);
        btn_8Layout.setHorizontalGroup(
            btn_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_8Layout.createSequentialGroup()
                .addComponent(ind_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel25)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        btn_8Layout.setVerticalGroup(
            btn_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_8Layout.createSequentialGroup()
                .addComponent(ind_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        side_pane.add(btn_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 160, -1));

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imge_1.jpg"))); // NOI18N
        side_pane.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 60, 60));

        btn_9.setBackground(new java.awt.Color(23, 35, 51));
        btn_9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_9MouseExited(evt);
            }
        });

        ind_14.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_14.setOpaque(false);

        ind_15.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_15.setOpaque(false);

        javax.swing.GroupLayout ind_15Layout = new javax.swing.GroupLayout(ind_15);
        ind_15.setLayout(ind_15Layout);
        ind_15Layout.setHorizontalGroup(
            ind_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_15Layout.setVerticalGroup(
            ind_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel26.setText("Bán hàng");
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ind_14Layout = new javax.swing.GroupLayout(ind_14);
        ind_14.setLayout(ind_14Layout);
        ind_14Layout.setHorizontalGroup(
            ind_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_14Layout.createSequentialGroup()
                .addComponent(ind_15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel26)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ind_14Layout.setVerticalGroup(
            ind_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_14Layout.createSequentialGroup()
                .addComponent(ind_15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ind_14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bar-chart.png"))); // NOI18N
        jLabel27.setText("Thống kê");
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_9Layout = new javax.swing.GroupLayout(btn_9);
        btn_9.setLayout(btn_9Layout);
        btn_9Layout.setHorizontalGroup(
            btn_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_9Layout.createSequentialGroup()
                .addComponent(ind_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        btn_9Layout.setVerticalGroup(
            btn_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_9Layout.createSequentialGroup()
                .addGroup(btn_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ind_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        side_pane.add(btn_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 160, -1));

        btn_10.setBackground(new java.awt.Color(23, 35, 51));
        btn_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_10MouseExited(evt);
            }
        });

        ind_16.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_16.setOpaque(false);

        ind_17.setPreferredSize(new java.awt.Dimension(3, 43));
        ind_17.setOpaque(false);

        javax.swing.GroupLayout ind_17Layout = new javax.swing.GroupLayout(ind_17);
        ind_17.setLayout(ind_17Layout);
        ind_17Layout.setHorizontalGroup(
            ind_17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_17Layout.setVerticalGroup(
            ind_17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jLabel28.setText("Bán hàng");
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ind_16Layout = new javax.swing.GroupLayout(ind_16);
        ind_16.setLayout(ind_16Layout);
        ind_16Layout.setHorizontalGroup(
            ind_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_16Layout.createSequentialGroup()
                .addComponent(ind_17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel28)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ind_16Layout.setVerticalGroup(
            ind_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ind_16Layout.createSequentialGroup()
                .addComponent(ind_17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ind_16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/people.png"))); // NOI18N
        jLabel29.setText("Khách hàng");
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout btn_10Layout = new javax.swing.GroupLayout(btn_10);
        btn_10.setLayout(btn_10Layout);
        btn_10Layout.setHorizontalGroup(
            btn_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_10Layout.createSequentialGroup()
                .addComponent(ind_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel29)
                .addGap(0, 38, Short.MAX_VALUE))
        );
        btn_10Layout.setVerticalGroup(
            btn_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_10Layout.createSequentialGroup()
                .addComponent(ind_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        side_pane.add(btn_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 160, -1));

        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_exitMousePressed(evt);
            }
        });
        side_pane.add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 640, -1, 46));

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Exit_25px.png"))); // NOI18N
        jLabel98.setText("Đăng xuất");
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        side_pane.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 86, 37));

        getContentPane().add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 840));

        pnlSearch.setBackground(new java.awt.Color(71, 120, 197));
        pnlSearch.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlSearchMouseDragged(evt);
            }
        });
        pnlSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlSearchMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1380, Short.MAX_VALUE)
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(pnlSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 1380, 50));

        pnlTong.setBackground(new java.awt.Color(255, 255, 255));
        pnlTong.setLayout(new java.awt.CardLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Main");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap(935, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(417, 417, 417))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel1)
                .addContainerGap(428, Short.MAX_VALUE))
        );

        pnlTong.add(pnlMain, "cardMain");

        pnlBanHang.setBackground(new java.awt.Color(255, 255, 255));

        jPanel22.setMinimumSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel23.setMinimumSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        labelCamera.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout labelCameraLayout = new javax.swing.GroupLayout(labelCamera);
        labelCamera.setLayout(labelCameraLayout);
        labelCameraLayout.setHorizontalGroup(
            labelCameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        labelCameraLayout.setVerticalGroup(
            labelCameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 13))); // NOI18N

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTaoHoaDon.setText("Tạo hoá đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jButton40.setText("Bỏ khỏi giỏ hàng");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setText("Làm Mới giỏ hàng");

        jButton42.setText("Quét mã ");
        jButton42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton42MouseClicked(evt);
            }
        });
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách Hóa đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa đơn", "Mã Nhân viên", "Ngày Tạo", "Ngày Thanh Toán", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tblHoaDon);

        jLabel5.setText("Loại Hóa đơn");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thanh toán", "Chờ thanh toán", "Đã Hủy" }));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        pnlSanPham.setPreferredSize(new java.awt.Dimension(700, 200));
        scpSanPhamBH.setViewportView(pnlSanPham);

        jLabel2.setText("Lọc Theo");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Điện thoại", "Phụ Kiện" }));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpSanPhamBH, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jTextField100, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(scpSanPhamBH, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel6.setText("Mã NV");

        jLabel99.setText("Ngày Tạo");

        jLabel100.setText("Mã Hóa đơn");

        lblManV.setText(".............");

        lblNgayTao.setText("................");

        lblMaHD.setText("..............");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel100)
                            .addComponent(jLabel99))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNgayTao)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblManV))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblManV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHD)
                    .addComponent(jLabel100))
                .addGap(27, 27, 27)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(lblNgayTao))
                .addGap(24, 24, 24))
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel137.setText("Tổng tiền");
        jLabel137.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel140.setText("Hình Thức Thanh Toán");
        jLabel140.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel141.setText("Tiền khách đưa");
        jLabel141.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel142.setText("Tiền trả khách");
        jLabel142.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jCheckBox1.setText("Chờ thanh toán");

        jCheckBox2.setText("Nhận hóa đơn");

        jLabel143.setText("Ghi chú");
        jLabel143.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setBorder(null);
        jScrollPane20.setViewportView(jTextArea5);

        jButton36.setText("Thanh Toán");
        jButton36.setBackground(new java.awt.Color(71, 120, 197));

        jLabel144.setText("Khuyến mãi");
        jLabel144.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTongTien.setEditable(false);
        txtTongTien.setText("200000");

        jTextField97.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyPressed(evt);
            }
        });

        txtTienTraKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jTextField98.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienKhachDua)
                                    .addComponent(txtTienTraKhach)))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel140)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField98))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel144))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField97)
                                    .addComponent(txtTongTien))))
                        .addGap(30, 30, 30))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(80, 80, 80))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel140)
                    .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel141))
                .addGap(26, 26, 26)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel142)
                    .addComponent(txtTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel143)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton35.setText("Huỷ hoá đơn");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addGap(853, 853, 853)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButton40)
                        .addGap(91, 91, 91)
                        .addComponent(jButton41)
                        .addGap(59, 59, 59)
                        .addComponent(jButton42))
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlBanHangLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton35)
                        .addGap(113, 113, 113)))
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addComponent(labelCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton35)
                    .addComponent(btnTaoHoaDon))
                .addGap(10, 10, 10)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton42)
                    .addComponent(jButton41)
                    .addComponent(jButton40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlTong.add(pnlBanHang, "cardBanHang");

        pnlDienThoai.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblListDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên Điện Thoại", "Số Lượng Tồn", "RAM", "ROM", "Pin", "Màu Sắc", "Giá bán", "Thời Gian Bảo Hành", "Trạng Thái"
            }
        ));
        tblListDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblListDienThoai.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblListDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListDienThoaiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblListDienThoai);

        jLabel46.setText("Tên Điện Thoại");
        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTenDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbAnhDT.setBorder(new javax.swing.border.MatteBorder(null));
        lbAnhDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhDTMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnhDT, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnhDT, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );

        jLabel51.setText("Giá Nhập");
        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel62.setText("Giá Bán");
        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtGiaNhapDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaNhapDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        txtGiaBanDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaBanDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        jLabel63.setText("VNĐ");
        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel64.setText("VNĐ");
        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel65.setText("Mã Điện Thoại");
        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMaDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        jLabel66.setText("Mô Tả");
        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMoTaDienThoai.setColumns(20);
        txtMoTaDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMoTaDienThoai.setRows(5);
        txtMoTaDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));
        jScrollPane7.setViewportView(txtMoTaDienThoai);

        jLabel67.setText("Số Lượng");
        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoLuongDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuongDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        jLabel68.setText("Tên Hãng");
        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbTenHangDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenHangDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));

        jLabel69.setText("Tên Nhà Cung Cấp");
        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbTenNCCDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTenNCCDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));

        jLabel70.setText("RAM");
        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel71.setText("ROM");
        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtRomDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRomDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        txtRamDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRamDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        jLabel72.setText("Hệ Điều Hành");
        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel73.setText("CPU");
        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCPUDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCPUDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        txtHeDieuHanhDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHeDieuHanhDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        jLabel74.setText("Màn Hình");
        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel75.setText("Camera");
        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtManHinhDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtManHinhDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        txtCameraDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCameraDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        jLabel76.setText("Pin");
        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel77.setText("Màu Sắc");
        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel78.setText("Bảo Hành");
        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel79.setText("Trạng Thái");
        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtPinDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPinDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        txtMauSacDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMauSacDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        txtBaoHanhDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBaoHanhDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 153)));

        cbbTrangThaiDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTrangThaiDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));

        btnTrangTruoc.setText("<|");
        btnTrangTruoc.setPreferredSize(new java.awt.Dimension(90, 30));

        btnPrev.setText("<<");
        btnPrev.setPreferredSize(new java.awt.Dimension(90, 30));

        btnNext.setText(">>");
        btnNext.setPreferredSize(new java.awt.Dimension(90, 30));

        btnTrangSau.setText("|>");
        btnTrangSau.setPreferredSize(new java.awt.Dimension(90, 30));

        btnLamMoiDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
        btnLamMoiDT.setBackground(new java.awt.Color(51, 51, 51));
        btnLamMoiDT.setBorder(null);
        btnLamMoiDT.setPreferredSize(new java.awt.Dimension(90, 30));
        btnLamMoiDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDTActionPerformed(evt);
            }
        });

        btnXoaDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/xoa.png"))); // NOI18N
        btnXoaDT.setBackground(new java.awt.Color(51, 51, 51));
        btnXoaDT.setBorder(null);
        btnXoaDT.setPreferredSize(new java.awt.Dimension(90, 30));
        btnXoaDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDTActionPerformed(evt);
            }
        });

        btnSuaDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sua.png"))); // NOI18N
        btnSuaDT.setBackground(new java.awt.Color(51, 51, 51));
        btnSuaDT.setBorder(null);
        btnSuaDT.setPreferredSize(new java.awt.Dimension(90, 30));
        btnSuaDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDTActionPerformed(evt);
            }
        });

        btnThemDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/them.png"))); // NOI18N
        btnThemDT.setBackground(new java.awt.Color(0, 0, 0));
        btnThemDT.setBorder(null);
        btnThemDT.setPreferredSize(new java.awt.Dimension(90, 30));
        btnThemDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDTActionPerformed(evt);
            }
        });

        txtTimKiemDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));

        btnTimKiemDienThoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_18px.png"))); // NOI18N
        btnTimKiemDienThoai.setBackground(new java.awt.Color(71, 120, 197));
        btnTimKiemDienThoai.setBorder(null);
        btnTimKiemDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemDienThoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(txtTimKiemDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiemDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel69)
                                    .addComponent(jLabel68)
                                    .addComponent(cbbTenNCCDT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTenHangDT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRomDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRamDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCPUDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel72)
                                    .addComponent(txtHeDieuHanhDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtManHinhDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel74)
                                    .addComponent(txtCameraDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel75))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMauSacDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPinDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel78)
                                    .addComponent(jLabel79)
                                    .addComponent(cbbTrangThaiDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBaoHanhDT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnTrangTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTrangSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThemDT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSuaDT, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaDT, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoiDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel46)
                            .addComponent(txtTenDienThoai)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addGap(96, 96, 96)
                                .addComponent(jLabel67))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtGiaNhapDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel63)))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaBanDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel64))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtMaDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(txtSoLuongDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtMaDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaNhapDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaBanDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel64)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtSoLuongDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSuaDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoiDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnTrangTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTrangSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnThemDT, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74)
                            .addComponent(jLabel76)
                            .addComponent(jLabel78))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbTenHangDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRamDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCPUDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtManHinhDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBaoHanhDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPinDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75)
                            .addComponent(jLabel77)
                            .addComponent(jLabel79))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTenNCCDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRomDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHeDieuHanhDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCameraDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMauSacDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTrangThaiDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Nhân Viên");

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(71, 120, 197));
        jLabel111.setText("Điện thoại");

        btnTaoMa2.setText("Tạo mã");
        btnTaoMa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMa2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator6)
                    .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaoMa2)
                .addGap(9, 9, 9))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel111)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(btnTaoMa2)
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout pnlDienThoaiLayout = new javax.swing.GroupLayout(pnlDienThoai);
        pnlDienThoai.setLayout(pnlDienThoaiLayout);
        pnlDienThoaiLayout.setHorizontalGroup(
            pnlDienThoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDienThoaiLayout.setVerticalGroup(
            pnlDienThoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDienThoaiLayout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pnlTong.add(pnlDienThoai, "cardDienThoai");

        pnlPhuKien.setBackground(new java.awt.Color(255, 255, 255));
        pnlPhuKien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPhuKienMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblPhuKien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên phụ kiện", "Số lượng tồn", "Giá bán", "Thời gian bảo hành", "Mô tả", "Trạng thái"
            }
        ));
        tblPhuKien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhuKienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhuKien);

        jLabel15.setText("Tìm kiếm");
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSearchPK.setText("Search");
        btnSearchPK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAnhPK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhPKMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhPK, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhPK, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel33.setText("Tên phụ kiện");
        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel34.setText("Giá nhập");
        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel35.setText("Giá bán");
        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel36.setText("VND");
        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel37.setText("VND");
        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel38.setText("Số lượng tồn");
        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel39.setText("Mô tả");
        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMoTaPK.setColumns(20);
        txtMoTaPK.setRows(5);
        jScrollPane3.setViewportView(txtMoTaPK);

        jLabel40.setText("Tên hãng");
        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbTenHangPK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel41.setText("Mã phụ kiện");
        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel42.setText("Thời gian bảo hành");
        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel43.setText("Trạng thái");
        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbTrangThaiPK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("+");

        btnThemPK.setText("Thêm");
        btnThemPK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPKActionPerformed(evt);
            }
        });

        btnSuaPK.setText("Sửa");
        btnSuaPK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaPKActionPerformed(evt);
            }
        });

        btnXoaPK.setText("Xóa");
        btnXoaPK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPKActionPerformed(evt);
            }
        });

        btnClearPK.setText("Clear");
        btnClearPK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearPKActionPerformed(evt);
            }
        });

        jButton7.setText("|<");

        jButton8.setText(">|");

        jButton9.setText(">>");

        jButton10.setText("<<");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemPK, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSearchPK))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40)
                            .addComponent(cbbTenHangPK, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(txtThoiGianBaoHanhPK, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(cbbTrangThaiPK, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addGap(173, 173, 173)
                            .addComponent(jLabel35))
                        .addComponent(txtTenPK)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtGiaNhapPK, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel37))
                                .addComponent(jLabel41))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel38)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtGiaBanPK, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel36))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtMaPK, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtSoLuongTonPK, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel33)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btnThemPK)
                            .addGap(67, 67, 67)
                            .addComponent(btnSuaPK)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaPK)
                            .addGap(73, 73, 73)
                            .addComponent(btnClearPK))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBanPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaNhapPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuongTonPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemPK)
                            .addComponent(btnSuaPK)
                            .addComponent(btnXoaPK)
                            .addComponent(btnClearPK))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTimKiemPK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchPK))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8)
                            .addComponent(jButton9)
                            .addComponent(jButton10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbTrangThaiPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtThoiGianBaoHanhPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTenHangPK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );

        jLabel4.setText("Phụ Kiện");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(71, 120, 197));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPhuKienLayout = new javax.swing.GroupLayout(pnlPhuKien);
        pnlPhuKien.setLayout(pnlPhuKienLayout);
        pnlPhuKienLayout.setHorizontalGroup(
            pnlPhuKienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhuKienLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );
        pnlPhuKienLayout.setVerticalGroup(
            pnlPhuKienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlTong.add(pnlPhuKien, "cardPhuKien");

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHoaDonMouseClicked(evt);
            }
        });

        jLabel12.setText("Diện Thoại");

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap(1096, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(412, Short.MAX_VALUE))
        );

        pnlTong.add(pnlHoaDon, "cardHoaDon");

        pnlNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pnlNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNhanVienMouseClicked(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Nhân Viên");
        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Nhân Viên");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(71, 120, 197));

        btnTaoMa.setText("Tạo mã");
        btnTaoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator3)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(94, 94, 94))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaoMa)
                        .addGap(24, 24, 24))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaoMa))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton17.setText(">|");

        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        jLabel48.setText("Mã NV");
        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel49.setText("Số điện thoại");
        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "CCCD", "Email", "Sdt", "Trạng thái", "Tên tài khoản", "Chức vụ"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNhanVien);

        jLabel54.setText("Năm sinh");
        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnClearNV.setText("Clear");
        btnClearNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNVActionPerformed(evt);
            }
        });

        txtDiaChiNV.setColumns(20);
        txtDiaChiNV.setRows(5);
        jScrollPane5.setViewportView(txtDiaChiNV);

        btnSearchNV.setText("Search");
        btnSearchNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel53.setText("Địa chỉ");
        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAnhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNVMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel47.setText("Họ và tên");
        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton19.setText("<<");

        jButton18.setText(">>");

        jButton16.setText("|<");

        jLabel52.setText("Giới tính");
        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel55.setText("Tìm kiếm");
        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel56.setText("Email");
        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel57.setText("Mật khẩu");
        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel58.setText("Tên tài khoản");
        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        buttonGroup1.add(radioNamNV);
        radioNamNV.setText("Nam");

        buttonGroup1.add(radioNuNV);
        radioNuNV.setText("Nữ");

        jLabel59.setText("Chức vụ");
        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel60.setText("Trạng thái");
        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbChucVuNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel61.setText("CCCD");
        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbTrangThaiNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSearchNV))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addGap(18, 18, 18)
                        .addComponent(jButton19)
                        .addGap(18, 18, 18)
                        .addComponent(jButton18)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel49)
                        .addGap(200, 200, 200))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel54)
                                        .addGap(181, 181, 181))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamSinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmailNV, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(radioNamNV)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioNuNV))))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel59)
                                                .addGap(193, 193, 193))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(cbbChucVuNV, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)))
                                        .addComponent(jLabel60))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSoDienThoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtHovaTenNV, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(btnThemNV)
                                                    .addGap(67, 67, 67)
                                                    .addComponent(btnSuaNV)
                                                    .addGap(77, 77, 77)
                                                    .addComponent(btnXoaNV)
                                                    .addGap(73, 73, 73)
                                                    .addComponent(btnClearNV))
                                                .addComponent(jLabel47)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addGap(3, 3, 3)
                                                            .addComponent(jLabel48))
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel57)
                                                                .addComponent(jLabel58)
                                                                .addComponent(jLabel61))))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtMatKhauNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                        .addComponent(txtTenTKNV, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtCccdNV)))
                                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(cbbTrangThaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenTKNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel57)
                                    .addComponent(txtMatKhauNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(txtCccdNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHovaTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel56))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoDienThoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailNV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(jLabel54))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamSinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioNamNV)
                            .addComponent(radioNuNV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbChucVuNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTrangThaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchNV)
                            .addComponent(jLabel55))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemNV)
                        .addComponent(jButton16)
                        .addComponent(jButton17)
                        .addComponent(jButton18)
                        .addComponent(jButton19))
                    .addComponent(btnSuaNV)
                    .addComponent(btnXoaNV)
                    .addComponent(btnClearNV))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTong.add(pnlNhanVien, "cardNhanVien");

        pnlThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThongKeMouseClicked(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Doanh Thu");
        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel80.setPreferredSize(new java.awt.Dimension(100, 32));

        jLabel31.setText("150.0000.000 Đồng");
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("Số Hoá Đơn");
        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel81.setPreferredSize(new java.awt.Dimension(100, 32));

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("12");
        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 204));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Tổng khách hàng");
        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel83.setPreferredSize(new java.awt.Dimension(100, 32));

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("15");
        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("Số hoá đơn huỷ");
        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel85.setPreferredSize(new java.awt.Dimension(100, 32));

        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("1");
        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel87.setText("Ngày bắt đầu");
        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel88.setText("Ngày kết thúc");
        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton29.setText("Lọc");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thời Gian", "Doanh Thu", "Hoá Đơn" }));

        jLabel89.setText("Sắp xếp theo");
        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Thời gian", "Doanh Thu", "Số hoá đơn thanh toán", "Số hoá đơn huỷ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblThongKe);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 37, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                    .addContainerGap(785, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(310, 310, 310)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(567, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel12);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1384, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Hoá Đơn", jPanel18);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1384, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel19);

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pnlTong.add(pnlThongKe, "cardThongKe");

        pnlKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlKhachHangMouseClicked(evt);
            }
        });

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane2.setInheritsPopupMenu(true);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        tblListData.setAutoCreateRowSorter(true);
        tblListData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListData.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblListData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListDataMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblListData);

        jLabel16.setText("Họ và tên : ");

        txtHoVaTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel101.setText("Giới tính :");

        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setText("Nữ");

        jLabel102.setText("Năm sinh");

        jLabel103.setText("Địa chỉ");

        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel104.setText("Số điện thoại :");

        jLabel105.setText("Email");

        jLabel106.setText("Ngày mua");

        jLabel107.setText("Điểm tích lũy");

        txtDiemTichLuy.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel108.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane11.setViewportView(txtGhiChu);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/them.png"))); // NOI18N
        btnThem.setBackground(new java.awt.Color(71, 120, 197));
        btnThem.setBorder(null);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemMouseExited(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sua.png"))); // NOI18N
        btnSua.setBackground(new java.awt.Color(71, 120, 197));
        btnSua.setBorder(null);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/xoa.png"))); // NOI18N
        btnXoa.setBackground(new java.awt.Color(71, 120, 197));
        btnXoa.setBorder(null);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
        btnClear.setBackground(new java.awt.Color(71, 120, 197));
        btnClear.setBorder(null);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel109.setText("Số CCCD:");

        txtCCCD.setBackground(new java.awt.Color(253, 253, 255));
        txtCCCD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(16, 71, 157)));
        txtCCCD.setCaretColor(new java.awt.Color(1, 59, 150));
        txtCCCD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtCCCD.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtCCCD.setSelectedTextColor(new java.awt.Color(102, 255, 204));
        txtCCCD.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCCCDerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel101)
                                .addGap(21, 21, 21)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoNu)
                                    .addComponent(rdoNam)))
                            .addComponent(jLabel109)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel102)
                            .addComponent(jLabel106)
                            .addComponent(jLabel103)
                            .addComponent(jLabel105)
                            .addComponent(txtDiaChi)
                            .addComponent(txtNamSinh)
                            .addComponent(ngayMuaDP, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtEmail))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel107)
                                .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel108))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(jLabel109)
                    .addComponent(jLabel107))
                .addGap(10, 10, 10)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiaChi)
                        .addComponent(txtDiemTichLuy)))
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel102)
                            .addComponent(jLabel16)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel108)))
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101)
                            .addComponent(rdoNam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNu)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addComponent(jLabel106)
                                .addGap(10, 10, 10)
                                .addComponent(ngayMuaDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel105)
                            .addComponent(jLabel104))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Danh sách Khách hàng", jPanel29);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jLabel32.setText("Khách hàng");
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(71, 120, 197));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel110.setText("Tên / CCCD / Địa chỉ");

        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 95, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel110)
                .addGap(18, 18, 18)
                .addComponent(txtSearch)
                .addGap(236, 236, 236))
        );

        jTabbedPane3.addTab("Tìm kiếm", jPanel30);

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 1117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(6, 6, 6)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        pnlTong.add(pnlKhachHang, "cardKhachHang");

        pnlKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        pnlKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlKhuyenMaiMouseClicked(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel90.setText("Khuyến mãi");
        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(71, 120, 197));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(66, 66, 66))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 261, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel91.setText("Mã Khuyến Mãi");

        jLabel92.setText("Tên khuyến mãi");

        jLabel93.setText("Số Tiền giảm");

        jLabel94.setText("Chiết khấu");

        jLabel95.setText("Ngày bắt đầu");

        jLabel96.setText("Ngày kết thúc");

        jLabel97.setText("Trạng thái");

        cbbTTKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblListDataKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListDataKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListDataKMMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblListDataKM);

        btnThemKM.setText("Thêm");
        btnThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKMActionPerformed(evt);
            }
        });

        btnSuaKM.setText("Sửa");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        btnXoaKM.setText("Xóa");
        btnXoaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKMActionPerformed(evt);
            }
        });

        btnClearKM.setText("Clear");
        btnClearKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKhuyenMaiLayout = new javax.swing.GroupLayout(pnlKhuyenMai);
        pnlKhuyenMai.setLayout(pnlKhuyenMaiLayout);
        pnlKhuyenMaiLayout.setHorizontalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbbTTKhuyenMai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKhuyenMai, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel93)
                                            .addComponent(txtSoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel94)
                                            .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(120, 120, 120)
                                        .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(datePickerKT, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel96)
                                            .addComponent(jLabel95)
                                            .addComponent(datePickerNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                                        .addGap(186, 186, 186)
                                        .addComponent(btnThemKM)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSuaKM)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnXoaKM)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnClearKM))))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1069, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlKhuyenMaiLayout.setVerticalGroup(
            pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhuyenMaiLayout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(jLabel93)
                    .addComponent(jLabel95))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datePickerNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(jLabel94)
                    .addComponent(jLabel96))
                .addGap(18, 18, 18)
                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datePickerKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel97)
                .addGap(18, 18, 18)
                .addGroup(pnlKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTTKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemKM)
                    .addComponent(btnSuaKM)
                    .addComponent(btnXoaKM)
                    .addComponent(btnClearKM))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pnlTong.add(pnlKhuyenMai, "cardKhuyenMai");

        getContentPane().add(pnlTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 1380, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int xx, xy;
    private void pnlSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSearchMousePressed
        // TODO add your handling code here:
        //drag this pane
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_pnlSearchMousePressed

    private void pnlSearchMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSearchMouseDragged
        // TODO add your handling code here:
        //source to drag
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlSearchMouseDragged

    private void btn_exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_exitMousePressed

    private void btn_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1MouseEntered
        // TODO add your handling code here:
        btn_1.setBackground(new Color(40, 57, 80));
        btn_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_1MouseEntered

    private void btn_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1MouseExited
        // TODO add your handling code here:
        btn_1.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_1MouseExited

    private void btn_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MouseEntered
        // TODO add your handling code here:
        btn_3.setBackground(new Color(40, 57, 80));
        btn_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_3MouseEntered

    private void btn_3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MouseExited
        // TODO add your handling code here:
        btn_3.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_3MouseExited

    private void btn_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_4MouseEntered
        // TODO add your handling code here:
        btn_4.setBackground(new Color(40, 57, 80));
        btn_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_4MouseEntered

    private void btn_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_4MouseExited
        // TODO add your handling code here:
        btn_4.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_4MouseExited

    private void btn_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MouseEntered
        // TODO add your handling code here:
        btn_2.setBackground(new Color(40, 57, 80));
        btn_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_2MouseEntered

    private void btn_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MouseExited
        // TODO add your handling code here:
        btn_2.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_2MouseExited

    private void btn_5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_5MouseEntered
        // TODO add your handling code here:
        btn_5.setBackground(new Color(40, 57, 80));
        btn_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_5MouseEntered

    private void btn_5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_5MouseExited
        // TODO add your handling code here:
        btn_5.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_5MouseExited

    private void btn_6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_6MouseEntered
        // TODO add your handling code here:
        btn_6.setBackground(new Color(40, 57, 80));
        btn_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_6MouseEntered

    private void btn_6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_6MouseExited
        // TODO add your handling code here:
        btn_6.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_6MouseExited

    private void btn_7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_7MouseEntered
        // TODO add your handling code here:
        btn_7.setBackground(new Color(40, 57, 80));
        btn_7.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_7MouseEntered

    private void btn_7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_7MouseExited
        // TODO add your handling code here:
        btn_7.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_7MouseExited

    private void btn_8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_8MouseEntered
        // TODO add your handling code here:
        btn_8.setBackground(new Color(40, 57, 80));
        btn_8.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_8MouseEntered

    private void btn_8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_8MouseExited
        // TODO add your handling code here:
        btn_8.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_8MouseExited

    private void btn_10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_10MouseEntered
        // TODO add your handling code here:
        btn_10.setBackground(new Color(40, 57, 80));
        btn_10.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_10MouseEntered

    private void btn_10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_10MouseExited
        // TODO add your handling code here:
        btn_10.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_10MouseExited

    private void btn_9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_9MouseEntered
        // TODO add your handling code here:
        btn_9.setBackground(new Color(40, 57, 80));
        btn_9.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_9MouseEntered

    private void btn_9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_9MouseExited
        // TODO add your handling code here:
        btn_9.setBackground(new Color(23, 35, 51));
    }//GEN-LAST:event_btn_9MouseExited

    private void btn_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardMain");
//        setButtonClick(btn_1);
    }//GEN-LAST:event_btn_1MouseClicked

    private void btn_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardDienThoai");;
    }//GEN-LAST:event_btn_3MouseClicked

    private void btn_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardBanHang");;
    }//GEN-LAST:event_btn_2MouseClicked

    private void pnlPhuKienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPhuKienMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_pnlPhuKienMouseClicked

    private void pnlHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHoaDonMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_pnlHoaDonMouseClicked

    private void pnlNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhanVienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlNhanVienMouseClicked

    private void pnlThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThongKeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlThongKeMouseClicked

    private void btn_9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_9MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardThongKe");;
    }//GEN-LAST:event_btn_9MouseClicked

    private void btn_10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_10MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardKhachHang");
    }//GEN-LAST:event_btn_10MouseClicked

    private void btn_8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_8MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardNhanVien");
    }//GEN-LAST:event_btn_8MouseClicked

    private void btn_7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_7MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardKhuyenMai");
    }//GEN-LAST:event_btn_7MouseClicked

    private void btn_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_6MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardBaoHanh");;
    }//GEN-LAST:event_btn_6MouseClicked

    private void btn_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_5MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardHoaDon");
    }//GEN-LAST:event_btn_5MouseClicked

    private void btn_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_4MouseClicked
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) pnlTong.getLayout();
        layout.show(pnlTong, "cardPhuKien");;
    }//GEN-LAST:event_btn_4MouseClicked

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton42MouseClicked
        // TODO add your handling code here:
//         new QuetMa().show();
    }//GEN-LAST:event_jButton42MouseClicked

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(this, valuesSL);
        new MaQR().setVisible(true);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        String maHD = "HD" + (tblHoaDon.getRowCount() + 1);
        String maNV = "F8196D15-FAC4-4945-BA20-EDC29534D46A";
        LocalDateTime dateTime = LocalDateTime.now();
        HoaDon hoaDon = new HoaDon(null, maHD, dateTime, null, 0, 0, null);
        hoaDonRepo.SaveOrUpdate(hoaDon);
        fillToHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:

        int row = tblHoaDon.getSelectedRow();
//        HoaDon hd = hoaDonRepo.getOne(maHD);
//        JOptionPane.showMessageDialog(this, hd+maHD);
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hoá đơn cần huỷ");
        }
        String trangThai = tblHoaDon.getValueAt(row, 4).toString();
        if (trangThai.equals("Chờ thanh toán")) {
            String maHD = tblHoaDon.getValueAt(row, 0).toString();
            HoaDon hd = hoaDonRepo.getOne(maHD);
            hd.setTrangThai(2);
            hoaDonRepo.SaveOrUpdate(hd);
            fillToHoaDon();
            JOptionPane.showMessageDialog(this, "Huỷ hoá đơn thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Hoá đơn chờ thanh toán mới được huỷ");
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        String maHD = tblHoaDon.getValueAt(row, 0).toString();
        HoaDon hd = hoaDonRepo.getOne(maHD);
        String maNV = tblHoaDon.getValueAt(row, 1).toString();
        String date = hd.getNgayTao().toString();
        lblMaHD.setText(maHD);
        lblManV.setText(maNV);
        System.out.println(date);
//        lblNgayTao.setText(date.substring(0, date.indexOf(" ")));
        fillToHDCT(hd.getMaHD());
    }//GEN-LAST:event_tblHoaDonMouseClicked
//Code Dũng
    private void btnThemDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDTActionPerformed
        // TODO add your handling code here:
        String ma = txtMaDienThoai.getText();
        String ten = txtTenDienThoai.getText();
        String ram = txtRamDT.getText();
        String rom = txtRomDT.getText();
        String cpu = txtCPUDT.getText();
        String hdh = txtHeDieuHanhDT.getText();
        String manHinh = txtManHinhDT.getText();
        String camera = txtCameraDT.getText();
        String pin = txtPinDT.getText();
        String mauSac = txtMauSacDT.getText();
        Integer baoHanh = Integer.valueOf(txtBaoHanhDT.getText());
        Integer trangThai;
        if (cbbTrangThaiDT.getSelectedItem().equals("Dang Ban")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        String giaBan1 = txtGiaBanDienThoai.getText();
        BigDecimal giaBan = new BigDecimal(giaBan1);
        String moTa = txtMoTaDienThoai.getText();
        QLDienThoai qlDienThoai = new QLDienThoai();
        qlDienThoai.setMaDienThoai(ma);
        qlDienThoai.setTenDienThoai(ten);
        qlDienThoai.setSoLuongTon(0);
        qlDienThoai.setRAM(ram);
        qlDienThoai.setROM(rom);
        qlDienThoai.setCPU(cpu);
        qlDienThoai.setHeDieuHanh(hdh);
        qlDienThoai.setManHinh(manHinh);
        qlDienThoai.setCamera(camera);
        qlDienThoai.setPin(pin);
        qlDienThoai.setMauSac(mauSac);
        qlDienThoai.setGiaBan(giaBan);
        qlDienThoai.setThoiGianBaoHanh(baoHanh);
        qlDienThoai.setTrangThai(trangThai);
        qlDienThoai.setAnh(personalImage);
        String add = dienThoaiService.them(qlDienThoai);
        JOptionPane.showMessageDialog(this, add);
        showData(listQLDienThoai = dienThoaiService.getAll());
    }//GEN-LAST:event_btnThemDTActionPerformed

    private void btnSuaDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDTActionPerformed
        // TODO add your handling code here:
        int row = tblListDienThoai.getSelectedRow();
        UUID idDienThoai = listQLDienThoai.get(row).getIdDienThoai();
        String ma = txtMaDienThoai.getText();
        String ten = txtTenDienThoai.getText();
        String ram = txtRamDT.getText();
        String rom = txtRomDT.getText();
        String cpu = txtCPUDT.getText();
        String hdh = txtHeDieuHanhDT.getText();
        String manHinh = txtManHinhDT.getText();
        String camera = txtCameraDT.getText();
        String pin = txtPinDT.getText();
        String mauSac = txtMauSacDT.getText();
        Integer baoHanh = Integer.valueOf(txtBaoHanhDT.getText());
        Integer trangThai;
        if (cbbTrangThaiDT.getSelectedItem().equals("Đang Bán")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        String giaBan1 = txtGiaBanDienThoai.getText();
        BigDecimal giaBan = new BigDecimal(giaBan1);
        String moTa = txtMoTaDienThoai.getText();
        QLDienThoai QLDienThoai = listQLDienThoai.get(row);
        QLDienThoai.setMaDienThoai(ma);
        QLDienThoai.setTenDienThoai(ten);
        QLDienThoai.setSoLuongTon(0);
        QLDienThoai.setRAM(ram);
        QLDienThoai.setROM(rom);
        QLDienThoai.setCPU(cpu);
        QLDienThoai.setHeDieuHanh(hdh);
        QLDienThoai.setManHinh(manHinh);
        QLDienThoai.setCamera(camera);
        QLDienThoai.setPin(pin);
        QLDienThoai.setMauSac(mauSac);
        QLDienThoai.setGiaBan(giaBan);
        QLDienThoai.setThoiGianBaoHanh(baoHanh);
        QLDienThoai.setTrangThai(trangThai);
        QLDienThoai.setAnh(personalImage);
        QLDienThoai.setMota(moTa);
        String add = dienThoaiService.sua(QLDienThoai, idDienThoai);
        JOptionPane.showMessageDialog(this, add);
        showData(listQLDienThoai = dienThoaiService.getAll());
    }//GEN-LAST:event_btnSuaDTActionPerformed

    private void btnXoaDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDTActionPerformed
        // TODO add your handling code here:
        int row = tblListDienThoai.getSelectedRow();
        UUID idDienThoai = listQLDienThoai.get(row).getIdDienThoai();
        JOptionPane.showMessageDialog(this, dienThoaiService.xoa(idDienThoai));
        showData(listQLDienThoai = dienThoaiService.getAll());
    }//GEN-LAST:event_btnXoaDTActionPerformed

    private void btnLamMoiDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDTActionPerformed
        // TODO add your handling code here:
        txtTimKiemDienThoai.setText("");
        txtBaoHanhDT.setText("");
        txtCPUDT.setText("");
        txtCameraDT.setText("");
        txtGiaBanDienThoai.setText("");
        txtGiaNhapDienThoai.setText("");
        txtHeDieuHanhDT.setText("");
        txtMaDienThoai.setText("");
        txtManHinhDT.setText("");
        txtMauSacDT.setText("");
        txtMoTaDienThoai.setText("");
        txtPinDT.setText("");
        txtRamDT.setText("");
        txtRomDT.setText("");
        txtSoLuongDienThoai.setText("");
        txtTenDienThoai.setText("");
        txtTimKiemDienThoai.setText("");
        cbbTenHangDT.setSelectedIndex(0);
        cbbTenNCCDT.setSelectedIndex(0);
        cbbTrangThaiDT.setSelectedIndex(0);
        lbAnhDT.setIcon(null);
    }//GEN-LAST:event_btnLamMoiDTActionPerformed

    private void btnTimKiemDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemDienThoaiActionPerformed

    private void tblListDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListDienThoaiMouseClicked
        // TODO add your handling code here:
        int row = tblListDienThoai.getSelectedRow();
        QLDienThoai qlDienThoai = listQLDienThoai.get(row);
        txtMaDienThoai.setText(qlDienThoai.getMaDienThoai());
        txtTenDienThoai.setText(qlDienThoai.getTenDienThoai());
        txtGiaBanDienThoai.setText(String.valueOf(qlDienThoai.getGiaBan()));
        txtMoTaDienThoai.setText(qlDienThoai.getMota());
        txtRamDT.setText(qlDienThoai.getRAM());
        txtRomDT.setText(qlDienThoai.getROM());
        txtCPUDT.setText(qlDienThoai.getCPU());
        txtHeDieuHanhDT.setText(qlDienThoai.getHeDieuHanh());
        txtMauSacDT.setText(qlDienThoai.getMauSac());
        txtManHinhDT.setText(qlDienThoai.getManHinh());
        txtCameraDT.setText(qlDienThoai.getCamera());
        txtPinDT.setText(qlDienThoai.getPin());
        txtBaoHanhDT.setText(String.valueOf(qlDienThoai.getThoiGianBaoHanh()));
        cbbTrangThaiDT.setSelectedItem(qlDienThoai.getTrangThai());
        if (qlDienThoai.getTrangThai() == 1) {
            cbbTrangThaiDT.setSelectedItem("Đang Bán");
        } else {
            cbbTrangThaiDT.setSelectedItem("Ngừng Bán");
        }
        if (qlDienThoai.getAnh() != null) {
            try {
                Image img = ImageHelper.createFromByteArray(qlDienThoai.getAnh(), "jpg");
                lbAnhDT.setIcon(new ImageIcon(ImageHelper.resige(img, lbAnhDT.getWidth(), lbAnhDT.getHeight())));
                personalImage = qlDienThoai.getAnh();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        } else {
            lbAnhDT.setIcon(null);
        }
    }//GEN-LAST:event_tblListDienThoaiMouseClicked

    private void lbAnhDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhDTMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(pnlDienThoai) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        try {
            File file = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getPath());
            Image image = ImageHelper.resige(icon.getImage(), 200, 200);
            ImageIcon resizedIcon = new ImageIcon(image);
            lbAnhDT.setIcon(resizedIcon);
            personalImage = ImageHelper.toByteArray(image, "jpg");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }//GEN-LAST:event_lbAnhDTMouseClicked
    //Hết code của Dũng

    //Code của Vanh
    private void tblPhuKienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhuKienMouseClicked
        // TODO add your handling code here:
        int row = tblPhuKien.getSelectedRow();
        fillDataPK(row);
    }//GEN-LAST:event_tblPhuKienMouseClicked

    private void btnThemPKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPKActionPerformed
        // TODO add your handling code here:
        String ma = txtMaPK.getText();
        String ten = txtTenPK.getText();
        String giaBan = txtGiaBanPK.getText();
        BigDecimal giaBan1 = new BigDecimal(giaBan);
        int thoiGianBH = Integer.parseInt(txtThoiGianBaoHanhPK.getText());
        String moTa = txtMoTaPK.getText();
        int trangThai;
        if (cbbTrangThaiPK.getSelectedItem().equals("Đang bán")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        QLPhuKien qLPhuKien = new QLPhuKien();
        qLPhuKien.setMa(ma);
        qLPhuKien.setTen(ten);
        qLPhuKien.setSoLuong(0);
        qLPhuKien.setGiaBan(giaBan1);
        qLPhuKien.setThoiGianBaoHanh(thoiGianBH);
        qLPhuKien.setMoTa(moTa);
        qLPhuKien.setTrangThai(1);
        qLPhuKien.setAnh(personalImage);
        String add = iPhuKienService.add(qLPhuKien);
        JOptionPane.showMessageDialog(this, add);
        listQLPhuKien = iPhuKienService.getAll();
        showDataTablePK(listQLPhuKien);
    }//GEN-LAST:event_btnThemPKActionPerformed

    private void btnSuaPKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaPKActionPerformed
        // TODO add your handling code here:
        int row = tblPhuKien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Xin chọn phụ kiến muốn update");
            return;
        }
        UUID id = listQLPhuKien.get(row).getId();
        String ma = txtMaPK.getText();
        String ten = txtTenPK.getText();
        String giaBan = txtGiaBanPK.getText();
        BigDecimal giaBan1 = new BigDecimal(giaBan);
        int thoiGianBH = Integer.parseInt(txtThoiGianBaoHanhPK.getText());
        String moTa = txtMoTaPK.getText();
        int trangThai;
        if (cbbTrangThaiPK.getSelectedItem().equals("Đang bán")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        QLPhuKien qLPhuKien = listQLPhuKien.get(row);
        qLPhuKien.setMa(ma);
        qLPhuKien.setTen(ten);
        qLPhuKien.setSoLuong(0);
        qLPhuKien.setGiaBan(giaBan1);
        qLPhuKien.setThoiGianBaoHanh(thoiGianBH);
        qLPhuKien.setMoTa(moTa);
        qLPhuKien.setTrangThai(trangThai);
        qLPhuKien.setAnh(personalImage);
        String update = iPhuKienService.update(qLPhuKien, id);
        JOptionPane.showMessageDialog(this, update);
        listQLPhuKien = iPhuKienService.getAll();
        showDataTablePK(listQLPhuKien);
    }//GEN-LAST:event_btnSuaPKActionPerformed

    private void btnXoaPKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPKActionPerformed
        // TODO add your handling code here:
        int row = tblPhuKien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Xin chọn phụ kiến muốn delete");
            return;
        }
        UUID id = listQLPhuKien.get(row).getId();
        JOptionPane.showMessageDialog(this, iPhuKienService.delete(id));
        listQLPhuKien = iPhuKienService.getAll();
        showDataTablePK(listQLPhuKien);
    }//GEN-LAST:event_btnXoaPKActionPerformed

    private void btnClearPKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearPKActionPerformed
        // TODO add your handling code here:
        txtTenPK.setText("");
        txtGiaBanPK.setText("");
        txtSoLuongTonPK.setText("");
        txtMaPK.setText("");
        txtMoTaPK.setText("");
        txtThoiGianBaoHanhPK.setText("");
        cbbTrangThaiPK.setSelectedIndex(0);
        txtGiaNhapPK.setText("");
        cbbTenHangPK.setSelectedIndex(0);
        lblAnhPK.setIcon(null);
    }//GEN-LAST:event_btnClearPKActionPerformed

    private void lblAnhPKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhPKMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(pnlPhuKien) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        try {
            File file = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = ImageHelper.resige(icon.getImage(), 200, 200);
            ImageIcon resizedIcon = new ImageIcon(img);
            lblAnhPK.setIcon(resizedIcon);
            personalImage = ImageHelper.toByteArray(img, "jpg");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lblAnhPKMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        fillDataNV(row);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void lblAnhNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNVMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(pnlNhanVien) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        try {
            File file = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = ImageHelper.resige(icon.getImage(), 140, 160);
            ImageIcon resizedIcon = new ImageIcon(img);
            lblAnhNV.setIcon(resizedIcon);
            personalImage = ImageHelper.toByteArray(img, "jpg");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lblAnhNVMouseClicked

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        String ma = txtMaNV.getText();
        String hoTen = txtHovaTenNV.getText();
        int gioiTinh;
        if (radioNamNV.isSelected()) {
            gioiTinh = 1;
        } else {
            gioiTinh = 0;
        }
        int namSinh = Integer.parseInt(txtNamSinhNV.getText());
        String diaChi = txtDiaChiNV.getText();
        String cccd = txtCccdNV.getText();
        String email = txtEmailNV.getText();
        String sdt = txtSoDienThoaiNV.getText();
        int trangThai;
        if (cbbTrangThaiPK.getSelectedItem().equals("Đang làm việc")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        QLNhanVien qLNhanVien = new QLNhanVien();
        qLNhanVien.setMa(ma);
        qLNhanVien.setHoTen(hoTen);
        qLNhanVien.setGioiTinh(gioiTinh);
        qLNhanVien.setNamSinh(namSinh);
        qLNhanVien.setDiaChi(diaChi);
        qLNhanVien.setCccd(cccd);
        qLNhanVien.setEmail(email);
        qLNhanVien.setSdt(sdt);
        qLNhanVien.setTrangThai(trangThai);
        qLNhanVien.setAnh(personalImage);
        String add = iNhanVienService.add(qLNhanVien);
        JOptionPane.showMessageDialog(this, add);
        listQLNhanVien = iNhanVienService.getAll();
        showDataTableNV(listQLNhanVien);
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Xin chọn nhân viên muốn update");
            return;
        }
        UUID id = listQLNhanVien.get(row).getId();
        String ma = txtMaNV.getText();
        String hoTen = txtHovaTenNV.getText();
        int gioiTinh;
        if (radioNamNV.isSelected()) {
            gioiTinh = 1;
        } else {
            gioiTinh = 0;
        }
        int namSinh = Integer.parseInt(txtNamSinhNV.getText());
        String diaChi = txtDiaChiNV.getText();
        String cccd = txtCccdNV.getText();
        String email = txtEmailNV.getText();
        String sdt = txtSoDienThoaiNV.getText();
        int trangThai;
        if (cbbTrangThaiPK.getSelectedItem().equals("Đang làm việc")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        QLNhanVien qLNhanVien = listQLNhanVien.get(row);
        qLNhanVien.setMa(ma);
        qLNhanVien.setHoTen(hoTen);
        qLNhanVien.setGioiTinh(gioiTinh);
        qLNhanVien.setNamSinh(namSinh);
        qLNhanVien.setDiaChi(diaChi);
        qLNhanVien.setCccd(cccd);
        qLNhanVien.setEmail(email);
        qLNhanVien.setSdt(sdt);
        qLNhanVien.setTrangThai(trangThai);
        qLNhanVien.setAnh(personalImage);
        String update = iNhanVienService.update(qLNhanVien, id);
        JOptionPane.showMessageDialog(this, update);
        listQLNhanVien = iNhanVienService.getAll();
        showDataTableNV(listQLNhanVien);
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Xin chọn nhân viên muốn delete");
            return;
        }
        UUID id = listQLNhanVien.get(row).getId();
        String delete = iNhanVienService.delete(id);
        JOptionPane.showMessageDialog(this, delete);
        listQLNhanVien = iNhanVienService.getAll();
        showDataTableNV(listQLNhanVien);
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnClearNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNVActionPerformed
        // TODO add your handling code here:
        txtMaNV.setText("");
        txtHovaTenNV.setText("");
        buttonGroup1.clearSelection();
        txtNamSinhNV.setText("");
        txtDiaChiNV.setText("");
        txtCccdNV.setText("");
        txtEmailNV.setText("");
        txtSoDienThoaiNV.setText("");
        cbbChucVuNV.setSelectedIndex(0);
        cbbTrangThaiNV.setSelectedIndex(0);
        lblAnhNV.setIcon(null);
    }//GEN-LAST:event_btnClearNVActionPerformed

    private void btnTaoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMaActionPerformed
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên cần tạo mã");
            return;
        }
        QLNhanVien nv = listQLNhanVien.get(row);
        new Main(nv);
    }//GEN-LAST:event_btnTaoMaActionPerformed

    private void tblListDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListDataMouseClicked
        // TODO add your handling code here:
        int row = tblListData.getSelectedRow();
        fillDataKH(row);
    }//GEN-LAST:event_tblListDataMouseClicked

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        // TODO add your handling code here:
        btnThem.setBackground(new Color(111, 140, 186));
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        // TODO add your handling code here:
        btnThem.setBackground(new Color(71, 120, 197));
    }//GEN-LAST:event_btnThemMouseExited

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, khService.add(getDataKH()));
        listKH.clear();
        listKH = khService.getAll();
        showTableKH(listKH);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblListData.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để sửa");
        } else {
            UUID id = listKH.get(row).getId();
            JOptionPane.showMessageDialog(this, khService.update(getDataKH(), id));
            listKH.clear();
            listKH = khService.getAll();
            showTableKH(listKH);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblListData.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để sửa");
        } else {
            UUID id = listKH.get(row).getId();
            JOptionPane.showMessageDialog(this, khService.delete(id));
            listKH.remove(row);
            listKH.clear();
            listKH = khService.getAll();
            showTableKH(listKH);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtHoVaTen.setText("");
        txtCCCD.setText("");
        txtHoVaTen.setText("");
        txtDiaChi.setText("");
        txtDiemTichLuy.setText("");
        txtEmail.setText("");
        ngayMuaDP.setDate(new Date(System.currentTimeMillis()).toLocalDate());
        txtSDT.setText("");
        txtGhiChu.setText("");
        txtNamSinh.setText("");
        txtHoVaTen.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtCCCDerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCCCDerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCCCDerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:
        String search = txtSearch.getText();
        listKH.clear();
        listKH = khService.search(search);
        showTableKH(listKH);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void pnlKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlKhachHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlKhachHangMouseClicked

    private void tblListDataKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListDataKMMouseClicked
        // TODO add your handling code here:
        int row = tblListDataKM.getSelectedRow();
        fillDataKM(row);
    }//GEN-LAST:event_tblListDataKMMouseClicked

    private void btnThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKMActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, kmService.add(getDataKM()));
        listKM.clear();
        listKM = kmService.getAll();
        showTableKM(listKM);
    }//GEN-LAST:event_btnThemKMActionPerformed

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed
        // TODO add your handling code here:
        UUID id = listKM.get(tblListDataKM.getSelectedRow()).getId();
        JOptionPane.showMessageDialog(this, kmService.update(getDataKM(), id));
        listKM.clear();
        listKM = kmService.getAll();
        showTableKM(listKM);
    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void btnXoaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKMActionPerformed
        // TODO add your handling code here:
        UUID id = listKM.get(tblListDataKM.getSelectedRow()).getId();
        JOptionPane.showMessageDialog(this, kmService.delete(id));
        listKM.remove(tblListDataKM.getSelectedRow());
        listKM.clear();
        listKM = kmService.getAll();
        showTableKM(listKM);
    }//GEN-LAST:event_btnXoaKMActionPerformed

    private void btnClearKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearKMActionPerformed
        // TODO add your handling code here:
        txtKhuyenMai.setText("");
        txtTenKhuyenMai.setText("");
        txtSoTienGiam.setText("");
        txtChietKhau.setText("");
        datePickerNgayBD.setDate(new Date(System.currentTimeMillis()).toLocalDate());
        datePickerKT.setDate(new Date(System.currentTimeMillis()).toLocalDate());
        cbbTTKhuyenMai.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearKMActionPerformed

    private void pnlKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlKhuyenMaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlKhuyenMaiMouseClicked

    private void txtTienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyPressed
        // TODO add your handling code here:
        String tienKhachDuaStr = null;
        if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            try {
                tienKhachDuaStr = txtTienKhachDua.getText().trim().substring(0, txtTienKhachDua.getText().length() - 1);
            } catch (Exception e) {
                System.out.println("hi");
            }
            if (tienKhachDuaStr.isEmpty()) {
                txtTienTraKhach.setText("");
                return;
            }
        } else {
            tienKhachDuaStr = txtTienKhachDua.getText() + evt.getKeyChar();
        }
        String tongTienStr = txtTongTien.getText();
        if (tongTienStr.isEmpty()) {
            tongTienStr = "0";
        }
        Integer tongTien = Integer.parseInt(tongTienStr);
        Integer tienKhachDua = Integer.parseInt(tienKhachDuaStr);
        Integer tienTra = tienKhachDua - tongTien;
        txtTienTraKhach.setText(String.valueOf(tienTra));
    }//GEN-LAST:event_txtTienKhachDuaKeyPressed

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnTaoMa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaoMa2ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        int rowHDCT = tblHoaDonChiTiet.getSelectedRow();
        if (rowHDCT == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm cần bỏ khỏi giỏ hàng");
            return;
        }
        int rowHD = tblHoaDon.getSelectedRow();
        String maHD = tblHoaDon.getValueAt(rowHD, 0).toString();
        HoaDon hoaDon = hoaDonRepo.getOne(maHD);
        if (hoaDon.getTrangThai() != 0) {
            JOptionPane.showMessageDialog(this, "Chỉ có hoá đơn chưa thanh toán mới được xoá sản phẩm");
            return;
        }
        Object o = tblHoaDonChiTiet.getValueAt(rowHDCT, 0);
        if (o instanceof DienThoai) {
            DienThoai dt = (DienThoai) o;
        } else {
            PhuKien phuKien = (PhuKien) o;
        }
    }//GEN-LAST:event_jButton40ActionPerformed
    //Hết Code của Vanh

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    private void setColor(JPanel pane) {
        pane.setBackground(new Color(41, 57, 80));
    }

    private void resetColor(JPanel[] pane, JPanel[] indicators) {
        for (int i = 0; i < pane.length; i++) {
            pane[i].setBackground(new Color(23, 35, 51));

        }
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setOpaque(false);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearKM;
    private javax.swing.JButton btnClearNV;
    private javax.swing.JButton btnClearPK;
    private javax.swing.JButton btnLamMoiDT;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSearchNV;
    private javax.swing.JButton btnSearchPK;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaDT;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnSuaPK;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTaoMa;
    private javax.swing.JButton btnTaoMa2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemDT;
    private javax.swing.JButton btnThemKM;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThemPK;
    private javax.swing.JButton btnTimKiemDienThoai;
    private javax.swing.JButton btnTrangSau;
    private javax.swing.JButton btnTrangTruoc;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaDT;
    private javax.swing.JButton btnXoaKM;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnXoaPK;
    private javax.swing.JPanel btn_1;
    private javax.swing.JPanel btn_10;
    private javax.swing.JPanel btn_2;
    private javax.swing.JPanel btn_3;
    private javax.swing.JPanel btn_4;
    private javax.swing.JPanel btn_5;
    private javax.swing.JPanel btn_6;
    private javax.swing.JPanel btn_7;
    private javax.swing.JPanel btn_8;
    private javax.swing.JPanel btn_9;
    private javax.swing.JLabel btn_exit;
    private java.awt.Button button1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChucVuNV;
    private javax.swing.JComboBox<String> cbbTTKhuyenMai;
    private javax.swing.JComboBox<String> cbbTenHangDT;
    private javax.swing.JComboBox<String> cbbTenHangPK;
    private javax.swing.JComboBox<String> cbbTenNCCDT;
    private javax.swing.JComboBox<String> cbbTrangThaiDT;
    private javax.swing.JComboBox<String> cbbTrangThaiNV;
    private javax.swing.JComboBox<String> cbbTrangThaiPK;
    private com.github.lgooddatepicker.components.DatePicker datePickerKT;
    private com.github.lgooddatepicker.components.DatePicker datePickerNgayBD;
    private javax.swing.JPanel ind_1;
    private javax.swing.JPanel ind_10;
    private javax.swing.JPanel ind_11;
    private javax.swing.JPanel ind_12;
    private javax.swing.JPanel ind_13;
    private javax.swing.JPanel ind_14;
    private javax.swing.JPanel ind_15;
    private javax.swing.JPanel ind_16;
    private javax.swing.JPanel ind_17;
    private javax.swing.JPanel ind_2;
    private javax.swing.JPanel ind_3;
    private javax.swing.JPanel ind_4;
    private javax.swing.JPanel ind_5;
    private javax.swing.JPanel ind_6;
    private javax.swing.JPanel ind_7;
    private javax.swing.JPanel ind_8;
    private javax.swing.JPanel ind_9;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField100;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField97;
    private javax.swing.JTextField jTextField98;
    private javax.swing.JPanel labelCamera;
    private javax.swing.JLabel lbAnhDT;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JLabel lblAnhPK;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblManV;
    private javax.swing.JLabel lblNgayTao;
    private com.github.lgooddatepicker.components.DatePicker ngayMuaDP;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlDienThoai;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKhuyenMai;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlPhuKien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton radioNamNV;
    private javax.swing.JRadioButton radioNuNV;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JScrollPane scpSanPhamBH;
    private javax.swing.JPanel side_pane;
    private static javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblListData;
    private javax.swing.JTable tblListDataKM;
    private javax.swing.JTable tblListDienThoai;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblPhuKien;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTextField txtBaoHanhDT;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtCPUDT;
    private javax.swing.JTextField txtCameraDT;
    private javax.swing.JTextField txtCccdNV;
    private javax.swing.JTextField txtChietKhau;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextArea txtDiaChiNV;
    private javax.swing.JTextField txtDiemTichLuy;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailNV;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaBanDienThoai;
    private javax.swing.JTextField txtGiaBanPK;
    private javax.swing.JTextField txtGiaNhapDienThoai;
    private javax.swing.JTextField txtGiaNhapPK;
    private javax.swing.JTextField txtHeDieuHanhDT;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtHovaTenNV;
    private javax.swing.JTextField txtKhuyenMai;
    private javax.swing.JTextField txtMaDienThoai;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPK;
    private javax.swing.JTextField txtManHinhDT;
    private javax.swing.JTextField txtMatKhauNV;
    private javax.swing.JTextField txtMauSacDT;
    private javax.swing.JTextArea txtMoTaDienThoai;
    private javax.swing.JTextArea txtMoTaPK;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtNamSinhNV;
    private javax.swing.JTextField txtPinDT;
    private javax.swing.JTextField txtRamDT;
    private javax.swing.JTextField txtRomDT;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoDienThoaiNV;
    private javax.swing.JTextField txtSoLuongDienThoai;
    private javax.swing.JTextField txtSoLuongTonPK;
    private javax.swing.JTextField txtSoTienGiam;
    private javax.swing.JTextField txtTenDienThoai;
    private javax.swing.JTextField txtTenKhuyenMai;
    private javax.swing.JTextField txtTenPK;
    private javax.swing.JTextField txtTenTKNV;
    private javax.swing.JTextField txtThoiGianBaoHanhPK;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienTraKhach;
    private javax.swing.JTextField txtTimKiemDienThoai;
    private javax.swing.JTextField txtTimKiemNV;
    private javax.swing.JTextField txtTimKiemPK;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
