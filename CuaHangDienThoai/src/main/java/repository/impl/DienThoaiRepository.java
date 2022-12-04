/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import repository.IDienThoaiRepository;
import domainmodel.DienThoai;
import org.hibernate.Transaction;
import util.HibernatUtil;

/**
 *
 * @author dungp
 */
public class DienThoaiRepository implements IDienThoaiRepository {

    @Override
    public List<DienThoai> getAll() {
        List<DienThoai> listDienThoai;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("From DienThoai");
            listDienThoai = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return listDienThoai;
    }

    public static void main(String[] args) {
        System.out.println(new DienThoaiRepository().getSP("dgfh"));
    }
    @Override
    public DienThoai getOne(String ma) {
        String hql = "FROM DienThoai WHERE maDienThoai =:ma";
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            Query q = session.createQuery(hql);
            q.setParameter("ma", ma);
            DienThoai dt = (DienThoai) q.getSingleResult();
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
// Code Dung

    @Override
    public boolean save(DienThoai dienThoai) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(dienThoai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean update(DienThoai dienThoai, UUID idDienThoai) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(String.valueOf(idDienThoai), dienThoai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(UUID idDienThoai) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            DienThoai dienThoai = session.get(DienThoai.class, idDienThoai);
            session.delete(dienThoai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            return false;
        }
    }

//    public static void main(String[] args) {
//        List<DienThoai> l = new DienThoaiRepository().getAll();
//        for (DienThoai dienThoai : l) {
//            System.out.println(dienThoai);
//        }
//    }


    @Override
    public List<DienThoai> timKiem(String tenDienThoai) {
        List<DienThoai> listTimKiem;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("FROM DienThoai WHERE MaDienThoai LIKE :t");
            String chuoi = "%" + tenDienThoai + "%";
            q.setParameter("t", chuoi);
            listTimKiem = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return listTimKiem;
    }

    @Override
    public List<DienThoai> getSP(String ten) {
       List<DienThoai> listDienThoai;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("SELECT dt From DienThoai dt where dt.maDienThoai=:ten");
            q.setParameter("ten", ten);
            listDienThoai = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return listDienThoai; }
}
