/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.HoaDonChiTiet;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernatUtil;

/**
 *
 * @author sktfk
 */
public class HoaDonChiTietRepository {
    public static List<HoaDonChiTiet> getAll(String maHD) {
        List<HoaDonChiTiet> list;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("From HoaDonChiTie");
//            q.setParameter("maHD", maHD);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return list;
    }
    public static void main(String[] args) {
        for (HoaDonChiTiet x : getAll("HD1")) {
            System.out.println(x);
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
}
