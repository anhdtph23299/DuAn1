/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.HibernatUtil;

/**
 *
 * @author sktfk
 */
public class HoaDonChiTietRepository {

    public static List<HoaDonChiTiet> getAll(String maHD) {
        List<HoaDonChiTiet> list;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("From HoaDonChiTiet c where c.hoaDon.MaHD =: mahd");
            q.setParameter("mahd", maHD);
//            q.setParameter("madt", maDT);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return list;
    }

    public HoaDonChiTiet getAllDT(String maHD, String maDT) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("From HoaDonChiTiet c where c.hoaDon.MaHD =:mahd and c.dienThoai.maDienThoai =:madt");
            q.setParameter("mahd", maHD);
            q.setParameter("madt", maDT);
            List<HoaDonChiTiet> list = q.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public HoaDonChiTiet getAllPK(String maHD, String maPK) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("From HoaDonChiTiet c where c.hoaDon.MaHD =:mahd and c.phuKien.ma =:mapk");
            q.setParameter("mahd", maHD);
            q.setParameter("mapk", maPK);
            List<HoaDonChiTiet> list = q.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public void SaveOrUpdate(HoaDonChiTiet hd) {
        Transaction transaction = null;
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hd);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
        }
    }

    public void delete(HoaDonChiTiet hd) {
        Transaction transaction = null;
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(hd);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
        }
    }

    public static void deleteAll(String maHD) {
        Transaction transaction = null;
        String hql = "EXEC XoaSP :mahd";
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query q = session.createNativeQuery(hql);
            q.setParameter("mahd", maHD);
            q.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
        }
    }

    public static void main(String[] args) {
        deleteAll("HD04");
    }

    public BigDecimal sumMoney(String maHD) {
        Session session = HibernatUtil.getFACTORY().openSession();
        Query q = session.createQuery("select SUM(h.soLuong * h.donGia) from HoaDonChiTiet h where h.hoaDon.MaHD =:mahd",
                 BigDecimal.class);
        q.setParameter("mahd", maHD);
        BigDecimal getSum = (BigDecimal) q.getSingleResult();
        return getSum;
    }
}
