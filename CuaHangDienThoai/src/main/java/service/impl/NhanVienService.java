/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import repository.INhanVienRepository;
import repository.impl.NhanVienRepository;
import service.INhanVienService;
import viewmodel.QLNhanVien;

/**
 *
 * @author lethi
 */
public class NhanVienService implements INhanVienService {

    private INhanVienRepository nhanVienRepository = new NhanVienRepository();

    @Override
    public List<QLNhanVien> getAll() {
        List<QLNhanVien> list = new ArrayList<>();
        for (NhanVien x : nhanVienRepository.getAll()) {
            QLNhanVien nhanVien = new QLNhanVien(x.getId(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getNamSinh(), x.getDiaChi(), x.getCccd(), x.getEmail(), x.getSdt(), x.getTrangThai(), x.getAnh(), null);
            list.add(nhanVien);
        }
        return list;
    }

    public String add(QLNhanVien qlnv) {
        NhanVien nhanVien = new NhanVien(null, qlnv.getMa(), qlnv.getHoTen(), qlnv.getGioiTinh(), qlnv.getNamSinh(), qlnv.getDiaChi(), qlnv.getCccd(), qlnv.getEmail(), qlnv.getSdt(), qlnv.getTrangThai(), qlnv.getAnh(), null);
        boolean add = nhanVienRepository.add(nhanVien);
        if (add) {
            return "Add thành công";
        } else {
            return "Add không thành công";
        }
    }

    @Override
    public String update(QLNhanVien qlnv, UUID id) {
        NhanVien nhanVien = new NhanVien(qlnv.getId(), qlnv.getMa(), qlnv.getHoTen(), qlnv.getGioiTinh(), qlnv.getNamSinh(), qlnv.getDiaChi(), qlnv.getCccd(), qlnv.getEmail(), qlnv.getSdt(), qlnv.getTrangThai(), qlnv.getAnh(), null);
        boolean update = nhanVienRepository.update(nhanVien, id);
        if (update) {
            return "Update thành công";
        } else {
            return "Update không thành công";
        }
    }

    @Override
    public String delete(UUID id) {
        boolean delete = nhanVienRepository.delete(id);
        if (delete) {
            return "Delete thành công";
        } else {
            return "Delete không thành công";
        }
    }
    
    @Override
    public List<QLNhanVien> search(String CCCD) {
        List<QLNhanVien> list = new ArrayList<>();
        for (NhanVien x : nhanVienRepository.search(CCCD)) {
            QLNhanVien nhanVien = new QLNhanVien(x.getId(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getNamSinh(), x.getDiaChi(), x.getCccd(), x.getEmail(), x.getSdt(), x.getTrangThai(), x.getAnh(), null);
            list.add(nhanVien);
        }
        return list;
    }
    
}
