/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.PhuKien;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import repository.IPhuKienRepository;
import repository.impl.PhuKienRepository;
import service.IPhuKienService;
import viewmodel.QLPhuKien;

/**
 *
 * @author Admin
 */
public class PhuKienService implements IPhuKienService {

    private IPhuKienRepository phuKienRe = new PhuKienRepository();

    @Override
    public List<QLPhuKien> getAll() {
        List<QLPhuKien> list = new ArrayList<>();
        for (PhuKien x : phuKienRe.getAll()) {
            QLPhuKien phuKien = new QLPhuKien(x.getId(), x.getMa(), x.getTen(), x.getSoLuong(),
                    x.getGiaBan(), x.getAnh(), x.getThoiGianBaoHanh(), x.getMoTa(), x.getTrangThai());
            list.add(phuKien);
        }
        return list;
    }
    public List<PhuKien> getAll1() {
        return phuKienRe.getAll();
    }

    public String add(QLPhuKien qLPhuKien) {
        PhuKien phuKien = new PhuKien(null, qLPhuKien.getMa(), qLPhuKien.getTen(), qLPhuKien.getSoLuong(), qLPhuKien.getGiaBan(), qLPhuKien.getAnh(), qLPhuKien.getThoiGianBaoHanh(), qLPhuKien.getMoTa(), qLPhuKien.getTrangThai(), null, null);
        boolean add = phuKienRe.add(phuKien);
        if (add) {
            return "Add thành công";
        } else {
            return "Add không thành công";
        }
    }

    @Override
    public String update(QLPhuKien qLPhuKien, UUID id) {
        PhuKien phuKien = new PhuKien(qLPhuKien.getId(), qLPhuKien.getMa(), qLPhuKien.getTen(), qLPhuKien.getSoLuong(), qLPhuKien.getGiaBan(), qLPhuKien.getAnh(), qLPhuKien.getThoiGianBaoHanh(), qLPhuKien.getMoTa(), qLPhuKien.getTrangThai(), null, null);
        boolean update = phuKienRe.update(phuKien, id);
        if (update) {
            return "Update thành công";
        } else {
            return "Update không thành công";
        }
    }

    @Override
    public String delete(UUID id) {
        boolean delete = phuKienRe.delete(id);
        if (delete) {
            return "Delete thành công";
        } else {
            return "Delete không thành công";
        }
    }

}
