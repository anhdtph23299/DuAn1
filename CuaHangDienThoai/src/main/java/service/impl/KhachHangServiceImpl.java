/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import service.KhachHangService;
import viewmodel.KhachHangViewMD;
import repository.IKhachHangRepository;
import repository.impl.KhachHangRepository;

/**
 *
 * @author hoant
 */
public class KhachHangServiceImpl implements KhachHangService{

    KhachHangRepository khRep = new KhachHangRepository();
    List<KhachHangViewMD> listQL = new ArrayList<>();

    @Override
    public List<KhachHangViewMD> getAll() {
        List<KhachHang> list = khRep.getAll();
        for (KhachHang x : list) {
            KhachHangViewMD md = new KhachHangViewMD();
            md.setCCCD(x.getCCCD());
            md.setDiaChi(x.getDiaChi());
            md.setDiemTichLuy(x.getDiemTichLuy());
            md.setEmail(x.getEmail());
            md.setGioiTinh(x.getGioiTinh());
            md.setId(x.getId());
            md.setGhiChu(x.getGhiChu());
            md.setHoTen(x.getHoTenKH());
            md.setSDT(x.getSDT());
            md.setGhiChu(x.getGhiChu());
            md.setNgayMua(x.getNgayMua());
            md.setNamSinh(x.getNamSinh());
            listQL.add(md);
        }
        return listQL;
    }

    @Override
    public String add(KhachHangViewMD x) {
        KhachHang md = new KhachHang();
        md.setCCCD(x.getCCCD());
        md.setDiaChi(x.getDiaChi());
        md.setDiemTichLuy(x.getDiemTichLuy());
        md.setEmail(x.getEmail());
        md.setGioiTinh(x.getGioiTinh());
        md.setId(x.getId());
        md.setNamSinh(x.getNamSinh());
        md.setHoTenKH(x.getHoTen());
        md.setSDT(x.getSDT());
        md.setGhiChu(x.getGhiChu());
        md.setNgayMua(x.getNgayMua());
        boolean add = khRep.add(md);
        if (add) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(KhachHangViewMD x, UUID id) {
        KhachHang md = new KhachHang();
        md.setId(x.getId());
        md.setCCCD(x.getCCCD());
        md.setDiaChi(x.getDiaChi());
        md.setDiemTichLuy(x.getDiemTichLuy());
        md.setEmail(x.getEmail());
        md.setGioiTinh(x.getGioiTinh());
        md.setNamSinh(x.getNamSinh());
        md.setHoTenKH(x.getHoTen());
        md.setSDT(x.getSDT());
        md.setGhiChu(x.getGhiChu());
        md.setNgayMua(x.getNgayMua());
        boolean update = khRep.update(md, id);
        if (update) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(UUID id) {
        boolean delete = khRep.delete(id);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

//    public static void main(String[] args) {
//        System.out.println(new KhachHangServiceImpl().update()));
//    }
    @Override
    public List<KhachHangViewMD> search(String CCCD) {
        List<KhachHang> list = khRep.search(CCCD);
        for (KhachHang x : list) {
            KhachHangViewMD md = new KhachHangViewMD();
            md.setCCCD(x.getCCCD());
            md.setDiaChi(x.getDiaChi());
            md.setDiemTichLuy(x.getDiemTichLuy());
            md.setEmail(x.getEmail());
            md.setGioiTinh(x.getGioiTinh());
            md.setId(x.getId());
            md.setGhiChu(x.getGhiChu());
            md.setHoTen(x.getHoTenKH());
            md.setSDT(x.getSDT());
            md.setGhiChu(x.getGhiChu());
            md.setNgayMua(x.getNgayMua());
            md.setNamSinh(x.getNamSinh());
            listQL.add(md);
        }
        return listQL;
    }
}
