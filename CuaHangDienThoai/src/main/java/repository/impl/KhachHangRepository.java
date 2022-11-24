/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.KhachHang;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.IKhachHangRepository;
import util.HibernatUtil;

/**
 *
 * @author hoant
 */
public class KhachHangRepository implements IKhachHangRepository {

    @Override
    public List<KhachHang> getAll() {
        try (Session session = HibernatUtil.getFACTORY().openSession()) {
            Query qr = session.createQuery("FROM KhachHang kh");
            return qr.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public Boolean add(KhachHang kh) {
        Transaction tran = null;
        try (Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(kh);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public Boolean update(KhachHang kh, UUID id) {
        Transaction tran = null;
        try (Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            KhachHang kh1 = session.get(KhachHang.class, id);
            kh1.setCCCD(kh.getCCCD());
            kh1.setDiaChi(kh.getDiaChi());
            kh1.setDiemTichLuy(kh.getDiemTichLuy());
            kh1.setEmail(kh.getEmail());
            kh1.setGhiChu(kh.getGhiChu());
            kh1.setGioiTinh(kh.getGioiTinh());
            kh1.setHoTenKH(kh.getHoTenKH());
            kh1.setNamSinh(kh.getNamSinh());
            kh1.setNgayMua(kh.getNgayMua());
            kh1.setSDT(kh.getSDT());
            session.update(kh1);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public Boolean delete(UUID id) {
        Transaction tran = null;
        try (Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            KhachHang kh = session.find(KhachHang.class, id);
            session.delete(kh);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new KhachHangRepository().search("0987"));
    }

    @Override
    public List<KhachHang> search(String CCCD) {
        try (Session sess = HibernatUtil.getFACTORY().openSession()) {
            Query qr = sess.createQuery("SELECT k FROM KhachHang k WHERE k.CCCD LIKE CONCAT('%',:CCCD,'%') OR k.hoTenKH LIKE CONCAT('%',:ten,'%') OR k.diaChi LIKE CONCAT('%',:diaChi,'%')");
            qr.setParameter("CCCD", CCCD);
            qr.setParameter("ten", CCCD);
            qr.setParameter("diaChi", CCCD);
            List<KhachHang> list = qr.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
