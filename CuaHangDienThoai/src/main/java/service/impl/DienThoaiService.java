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
        return dienThoaiRepository.getAll();
    }

    @Override
    public String them(QLDienThoai QLDienThoai) {
        DienThoai dienThoai = new DienThoai(null, QLDienThoai.getMaDienThoai(), QLDienThoai.getTenDienThoai(), QLDienThoai.getSoLuongTon(), QLDienThoai.getCPU(), QLDienThoai.getRAM(), QLDienThoai.getROM(), QLDienThoai.getManHinh(), QLDienThoai.getMauSac(), QLDienThoai.getPin(), QLDienThoai.getCamera(), QLDienThoai.getHeDieuHanh(), QLDienThoai.getAnh(), QLDienThoai.getGiaBan(), QLDienThoai.getThoiGianBaoHanh(), QLDienThoai.getMota(), QLDienThoai.getTrangThai(), QLDienThoai.getHang(),null,null);
        if (dienThoaiRepository.save(dienThoai)) {
            return "Thêm Thành Công Điện Thoại Có Mã Là: "+QLDienThoai.getMaDienThoai();
        } else {
            return "Thêm Điện Thoại Thất Bại.";
        }
    }

    @Override
    public String sua(QLDienThoai QLDienThoai, UUID idDienThoai) {
        DienThoai dienThoai = new DienThoai(idDienThoai, QLDienThoai.getMaDienThoai(), QLDienThoai.getTenDienThoai(), QLDienThoai.getSoLuongTon(), QLDienThoai.getCPU(), QLDienThoai.getRAM(), QLDienThoai.getROM(), QLDienThoai.getManHinh(), QLDienThoai.getMauSac(), QLDienThoai.getPin(), QLDienThoai.getCamera(), QLDienThoai.getHeDieuHanh(), QLDienThoai.getAnh(), QLDienThoai.getGiaBan(), QLDienThoai.getThoiGianBaoHanh(), QLDienThoai.getMota(), QLDienThoai.getTrangThai(), QLDienThoai.getHang(),null,null);
        if (dienThoaiRepository.update(dienThoai, idDienThoai)) {
            return "Sửa Thành Công Điện Thoại Có Mã Là: "+QLDienThoai.getMaDienThoai();
        } else {
            return "Sửa Thất Bại Điện Thoại có Mã Là: "+QLDienThoai.getMaDienThoai();
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

    public DienThoai getOne(String ma) {
        return dienThoaiRepository.getOne(ma);
    }

    @Override
    public List<QLDienThoai> timKiem(String maDienThoai, String tenDienThoai) {
        return dienThoaiRepository.timKiem(maDienThoai, tenDienThoai);
    }

    @Override
    public List<QLDienThoai> dtDangBan() {
        return dienThoaiRepository.dtDangBan();
    }

    @Override
    public List<QLDienThoai> dtNgungBan() {
        return dienThoaiRepository.dtNgungBan();
    }

    @Override
    public List<QLDienThoai> getSP(String ten) {
        List<QLDienThoai> listDT1 = new ArrayList<>();
        for (DienThoai dienThoai : dienThoaiRepository.getSP(ten)) {
            QLDienThoai qlDienThoai = new QLDienThoai(dienThoai.getIdDienThoai(), dienThoai.getMaDienThoai(), dienThoai.getTenDienThoai(), dienThoai.getSoLuongTon(), dienThoai.getCPU(), dienThoai.getRAM(), dienThoai.getROM(), dienThoai.getManHinh(), dienThoai.getMauSac(), dienThoai.getPin(), dienThoai.getCamera(), dienThoai.getHeDieuHanh(), dienThoai.getAnh(), dienThoai.getGiaBan(), dienThoai.getThoiGianBaoHanh(), dienThoai.getMoTa(), dienThoai.getTrangThai(), dienThoai.getHang());
            listDT1.add(qlDienThoai);
        }
        return listDT1;
    }
}
