/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModel.PhuKien;
import java.util.ArrayList;
import java.util.List;
import repository.IPhuKienRepository;
import repository.impl.PhuKienRepository;
import services.IPhuKienService;
import viewModel.QLPhuKien;

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
                    x.getGiaBan(), null, x.getThoiGianBaoHanh(), x.getMoTa(), x.getTrangThai(), x.getHang());
            list.add(phuKien);
        }
        return list;
    }

}
