/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.DienThoai;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import repository.IDienThoaiRepository;
import repository.impl.DienThoaiRepository;
import service.IDienThoaiService;
import viewmodel.QLDienThoai;

/**
 *
 * @author dungp
 */
public class DienThoaiService implements IDienThoaiService {
// Dũng Code
    IDienThoaiRepository dienThoaiRepository = new DienThoaiRepository();

    @Override
    public List<QLDienThoai> getAll() {
        List<QLDienThoai> listDT = new ArrayList<>();
        for (DienThoai dienThoai : dienThoaiRepository.getAll()) {
            QLDienThoai qlDienThoai = new QLDienThoai(dienThoai.getIdDienThoai(), dienThoai.getMaDienThoai(), dienThoai.getTenDienThoai(), dienThoai.getSoLuongTon(), dienThoai.getCPU(), dienThoai.getRAM(), dienThoai.getROM(), dienThoai.getManHinh(), dienThoai.getMauSac(), dienThoai.getPin(), dienThoai.getCamera(), dienThoai.getHeDieuHanh(), dienThoai.getAnh(), dienThoai.getGiaBan(), dienThoai.getThoiGianBaoHanh(), dienThoai.getMoTa(), dienThoai.getTrangThai());
            listDT.add(qlDienThoai);
        }
        return listDT;
    }

    @Override
    public String them(QLDienThoai QLDienThoai) {
        DienThoai dienThoai = new DienThoai(null, QLDienThoai.getMaDienThoai(), QLDienThoai.getTenDienThoai(), QLDienThoai.getSoLuongTon(), QLDienThoai.getCPU(), QLDienThoai.getRAM(), QLDienThoai.getROM(), QLDienThoai.getManHinh(), QLDienThoai.getMauSac(), QLDienThoai.getPin(), QLDienThoai.getCamera(), QLDienThoai.getHeDieuHanh(), QLDienThoai.getAnh(), QLDienThoai.getGiaBan(), QLDienThoai.getThoiGianBaoHanh(), QLDienThoai.getMota(), QLDienThoai.getTrangThai(), null, null);
        if (dienThoaiRepository.save(dienThoai)) {
            return "Them Thanh Cong";
        } else {
            return "Them That Bai";
        }
    }

    @Override
    public String sua(QLDienThoai QLDienThoai, UUID idDienThoai) {
        DienThoai dienThoai = new DienThoai(idDienThoai, QLDienThoai.getMaDienThoai(), QLDienThoai.getTenDienThoai(), QLDienThoai.getSoLuongTon(), QLDienThoai.getCPU(), QLDienThoai.getRAM(), QLDienThoai.getROM(), QLDienThoai.getManHinh(), QLDienThoai.getMauSac(), QLDienThoai.getPin(), QLDienThoai.getCamera(), QLDienThoai.getHeDieuHanh(), QLDienThoai.getAnh(), QLDienThoai.getGiaBan(), QLDienThoai.getThoiGianBaoHanh(), QLDienThoai.getMota(), QLDienThoai.getTrangThai(), null, null);
        if (dienThoaiRepository.update(dienThoai, idDienThoai)) {
            return "Sua Thanh Cong";
        } else {
            return "Sua That Bai";
        }
    }

    @Override
    public String xoa(UUID idDienThoai) {
        boolean xoa = dienThoaiRepository.delete(idDienThoai);
        if (xoa) {
            return "Xoa Thanh Cong";
        } else {
            return "Xoa That Bai";
        }
    }
    public DienThoai getOne(String ma){
        return dienThoaiRepository.getOne(ma);
    }

    @Override
    public List<DienThoai> timKiem(String tenDienThoai) {
        return dienThoaiRepository.timKiem(tenDienThoai);
    }
}
