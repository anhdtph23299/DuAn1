/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.DienThoai;
import domainmodel.TaiKhoan;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import repository.impl.TaiKhoanRepository;
import viewmodel.QLTaiKhoan;
import repository.ITaiKhoanRepository;
import service.ITaiKhoanService;

/**
 *
 * @author dungp
 */
public class TaiKhoanService implements ITaiKhoanService {

    ITaiKhoanRepository taiKhoanRepository = new TaiKhoanRepository();

    @Override
    public TaiKhoan getOne(String taiKhoan, String matKhau) {
        return taiKhoanRepository.getOne(taiKhoan, matKhau);
    }

    @Override
    public List<QLTaiKhoan> getAll() {
        List<QLTaiKhoan> listQLTK = new ArrayList<>();
        for (TaiKhoan taiKhoan : taiKhoanRepository.getAll()) {
            QLTaiKhoan qlTaiKhoan = new QLTaiKhoan(taiKhoan.getId(), taiKhoan.getTenTaiKhoan(), taiKhoan.getMatKhau());
            listQLTK.add(qlTaiKhoan);
        }
        return listQLTK;
    }

    @Override
    public List<QLTaiKhoan> timKiem(String tenTK) {
        List<QLTaiKhoan> listQLTK = new ArrayList<>();
        for (TaiKhoan tk : taiKhoanRepository.timKiem(tenTK)) {
            QLTaiKhoan qlTaiKhoan = new QLTaiKhoan(tk.getId(), tk.getTenTaiKhoan(), tk.getMatKhau());
            listQLTK.add(qlTaiKhoan);
        }
        return listQLTK;
    }

    @Override
    public String add(QLTaiKhoan qlTaiKhoan) {
        TaiKhoan tk = new TaiKhoan(qlTaiKhoan.getIdTaiKhoan(), qlTaiKhoan.getTenTaiKhoan(), qlTaiKhoan.getMatKhau());
        if (taiKhoanRepository.add(tk)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }

    @Override
    public String update(QLTaiKhoan qlTaiKhoan) {
        TaiKhoan tk = new TaiKhoan(qlTaiKhoan.getIdTaiKhoan(), qlTaiKhoan.getTenTaiKhoan(), qlTaiKhoan.getMatKhau());
        if (taiKhoanRepository.update(tk)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }

    @Override
    public String delete(UUID idTaiKhoan) {
        if (taiKhoanRepository.delete(idTaiKhoan)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }

}
