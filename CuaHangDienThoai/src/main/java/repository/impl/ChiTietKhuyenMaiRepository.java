/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.ChiTietKhuyenMai;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import repository.IChiTietKhuyenMaiRepository;
import util.HibernatUtil;
import viewmodel.ChiTietKMCustom;

/**
 *
 * @author hoant
 */
public class ChiTietKhuyenMaiRepository implements IChiTietKhuyenMaiRepository {

    @Override
    public List<ChiTietKMCustom> getAll() {
        try (Session sess = HibernatUtil.getFACTORY().openSession()) {//String maKM, String tenKM, BigDecimal mucKhuyenMai, String hinhThucKhuyenMai, Date ngayBatDau, Date ngayKT, Integer trangThai, String moTa, String sanPham
            Query qr = sess.createQuery("SELECT new viewmodel.ChiTietKMCustom(ctkm.id,ctkm.khuyenMai.maKM, ctkm.khuyenMai.tenKM,ctkm.khuyenMai.ngayBatDau,ctkm.khuyenMai.ngayKT,ctkm.khuyenMai.loaiKhuyenMai,dt.maDienThoai)  From ChiTietKhuyenMai ctkm LEFT JOIN ctkm.dienThoai dt");
            return qr.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(new ChiTietKhuyenMaiRepository().getAll());
    }
}
