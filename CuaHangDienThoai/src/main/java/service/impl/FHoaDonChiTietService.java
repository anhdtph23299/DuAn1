/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import viewmodel.QLHoaDonChiTiet;
import viewmodel.QLHoaDon;
import viewmodel.QLDienThoai;
import viewmodel.QLPhuKien;
import viewmodel.ChiTietKhuyenMaiViewModel;
import domainmodel.HoaDonChiTiet;
import java.math.BigDecimal;
import repository.IFHoaDonChiTietRespository;

/**
 *
 * @author Admin
 */
public class FHoaDonChiTietService implements service.IFHoaDonChiTietService {

    private IFHoaDonChiTietRespository hdct;
    private List<QLHoaDonChiTiet> lsthdct;

    public FHoaDonChiTietService() {
        hdct = new repository.impl.FHoaDonChiTietRespository();
        lsthdct = new ArrayList<>();
    }

    @Override
    public List<QLHoaDonChiTiet> getAll() {
        lsthdct.clear();
        lsthdct = new ArrayList<>();
        for (HoaDonChiTiet hoadonchitiet : hdct.getAll()) {
            QLHoaDon IdHD = new QLHoaDon(hoadonchitiet.getHoaDon().getIdHD(), hoadonchitiet.getHoaDon().getMaHD(), hoadonchitiet.getHoaDon().getNgayThanhToan());

            QLPhuKien IdPK = new QLPhuKien(hoadonchitiet.getPhuKien().getId(), hoadonchitiet.getPhuKien().getMa());
            ChiTietKhuyenMaiViewModel IdCTKM = new ChiTietKhuyenMaiViewModel(hoadonchitiet.getCtkm().getId());
            QLDienThoai IdDienThoai = new QLDienThoai(hoadonchitiet.getDienThoai().getIdDienThoai(), hoadonchitiet.getDienThoai().getMaDienThoai());
            QLHoaDonChiTiet qlhdct = new QLHoaDonChiTiet(hoadonchitiet.getId(), IdHD, IdPK, IdCTKM, IdDienThoai, hoadonchitiet.getSoLuong(), hoadonchitiet.getDonGia());
            lsthdct.add(qlhdct);
        }

        return lsthdct;
    }

}
