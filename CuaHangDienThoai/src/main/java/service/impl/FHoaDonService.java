/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import viewmodel.QLHoaDon;
import repository.impl.HoaDonRepository;
import viewmodel.QLHoaDon;
import domainmodel.HoaDon;
import java.time.LocalDateTime;
import viewmodel.KhachHangViewMD;
import viewmodel.QLNhanVien;
import repository.IFHoaDonRespository;
/**
 *
 * @author sktfk
 */
public class FHoaDonService implements service.IFHoaDonService{
private IFHoaDonRespository  hd;
private List<QLHoaDon> lsthd;
    public FHoaDonService() {
        hd = new repository.impl.FHoaDonRespository();
        lsthd= new ArrayList<>();
    }

    @Override
    public List<QLHoaDon> getAll() {
        lsthd.clear();
        lsthd = new ArrayList<>();
        for (HoaDon hoadon : hd.getAll()) {
      KhachHangViewMD  IdKH = new KhachHangViewMD(hoadon.getKhachhang().getId(),hoadon.getKhachhang().getHoTenKH());

QLNhanVien IdNhanVien = new QLNhanVien(hoadon.getNhanvien().getId(),hoadon.getNhanvien().getMa());
      QLHoaDon qlhd = new QLHoaDon(hoadon.getIdHD(), IdKH, IdNhanVien,hoadon.getMaHD(),hoadon.getNgayTao(),hoadon.getNgayThanhToan(),hoadon.getDiemTichLuy(),hoadon.getTrangThai());
      lsthd.add(qlhd);
        }
        
        return lsthd;

//List<QLHoaDon>  lsthd =  new ArrayList<>();
//        for (HoaDon hoadon : hd.getAll()) {
//            QLHoaDon qlhd = new  QLHoaDon(hoadon.getIdHD(), IdKH, IdNhanVien, MaHD,hoadon.getNgayTao(),hoadon.getNgayThanhToan(),hoadon.getDiemTichLuy(),hoadon.getTrangThai());
//      lsthd.add(qlhd);
//        }
//        return lsthd;
    }

}
