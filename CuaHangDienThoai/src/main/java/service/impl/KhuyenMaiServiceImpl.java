/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainmodel.KhuyenMai;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import repository.IKhuyenMaiRepository;
import repository.impl.KhuyenMaiRepository;
import service.KhuyenMaiService;
import viewmodel.KhuyenMaiViewModel;

/**
 *
 * @author hoant
 */
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    private IKhuyenMaiRepository kmRep = new KhuyenMaiRepository();
    private List<KhuyenMaiViewModel> listQL = new ArrayList<>();

    @Override
    public List<KhuyenMaiViewModel> getAll() {
        List<KhuyenMai> list = kmRep.getAll();
        for (KhuyenMai x : list) {
            KhuyenMaiViewModel md = new KhuyenMaiViewModel(x.getId(), x.getMaKM(), x.getTenKM(), x.getSoTienGiam(), x.getChietKhau(), x.getNgayBatDau(), x.getNgayKT(), x.getTrangThai());
            listQL.add(md);
        }
        return listQL;
    }

    @Override
    public String add(KhuyenMaiViewModel x) {
        KhuyenMai km = new KhuyenMai(x.getMaKM(), x.getTenKM(), x.getSoTienGiam(), x.getChietKhau(), x.getNgayBatDau(), x.getNgayKT(), x.getTrangThai());
        boolean add = kmRep.add(km);
        if (add) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(KhuyenMaiViewModel x, UUID id) {
        KhuyenMai km = new KhuyenMai(x.getId(), x.getMaKM(), x.getTenKM(), x.getSoTienGiam(), x.getChietKhau(), x.getNgayBatDau(), x.getNgayKT(), x.getTrangThai(),null);

        boolean sua = kmRep.update(km, id);
        if (sua) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(UUID id) {
        boolean delete = kmRep.delete(id);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

}
