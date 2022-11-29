/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.PhuKien;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernatUtil;
import repository.IPhuKienRepository;

/**
 *
 * @author Admin
 */
public class PhuKienRepository implements IPhuKienRepository {

    @Override
    public List<PhuKien> getAll() {
        List<PhuKien> list;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("FROM PhuKien");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return list;
    }

    @Override
    public boolean add(PhuKien phuKien) {
        boolean check = false;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            session.save(phuKien);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }

    @Override
    public boolean update(PhuKien phuKien, UUID id) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(String.valueOf(id), phuKien);
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
            PhuKien phuKien = session.get(PhuKien.class, id);
            transaction = session.beginTransaction();
            session.delete(phuKien);
            transaction.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
    
    @Override
    public List<PhuKien> search(String ten) {
        try ( Session sess = HibernatUtil.getFACTORY().openSession()) {
            Query qr = sess.createQuery("SELECT p FROM PhuKien p WHERE p.giaBan LIKE CONCAT('%',:giaBan,'%') "
                    + "OR p.hang LIKE CONCAT('%',:hang,'%') "
                    + "OR p.ma LIKE CONCAT('%',:ma,'%') "
                    + "OR p.ten LIKE CONCAT('%',:ten,'%') "
                    + "OR p.thoiGianBaoHanh LIKE CONCAT('%',:thoiGianBaoHanh,'%')");
            qr.setParameter("giaBan", ten);
            qr.setParameter("hang", ten);
            qr.setParameter("ma", ten);
            qr.setParameter("ten", ten);
            qr.setParameter("thoiGianBaoHanh", ten);
            List<PhuKien> list = qr.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
