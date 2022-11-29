/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.NhanVien;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernatUtil;
import repository.INhanVienRepository;

/**
 *
 * @author lethi
 */
public class NhanVienRepository implements INhanVienRepository {

    @Override
    public List<NhanVien> getAll() {
        List<NhanVien> list;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("FROM NhanVien");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<NhanVien> l = new NhanVienRepository().search("Nguyen Thi D");
        System.out.println(l);
    }

    @Override
    public boolean add(NhanVien nhanVien) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            session.save(nhanVien);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    @Override
    public boolean update(NhanVien nhanVien, UUID id) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(String.valueOf(id), nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(UUID id) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            NhanVien nhanVien = session.get(NhanVien.class, id);
            transaction = session.beginTransaction();
            session.delete(nhanVien);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;

    }

    @Override
    public List<NhanVien> search(String CCCD) {
        try ( Session sess = HibernatUtil.getFACTORY().openSession()) {
            Query qr = sess.createQuery("SELECT n FROM NhanVien n WHERE n.cccd LIKE CONCAT('%',:CCCD,'%') "
                    + "OR n.hoTen LIKE CONCAT('%',:ten,'%') "
                    + "OR n.diaChi LIKE CONCAT('%',:diaChi,'%') "
                    + "OR n.email LIKE CONCAT('%',:email,'%') "
                    + "OR n.ma LIKE CONCAT('%',:ma,'%') "
                    + "OR n.namSinh LIKE CONCAT('%',:namSinh,'%') "
                    + "OR n.sdt LIKE CONCAT('%',:sdt,'%')");
            qr.setParameter("CCCD", CCCD);
            qr.setParameter("ten", CCCD);
            qr.setParameter("diaChi", CCCD);
            qr.setParameter("email", CCCD);
            qr.setParameter("ma", CCCD);
            qr.setParameter("namSinh", CCCD);
            qr.setParameter("sdt", CCCD);
            List<NhanVien> list = qr.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
