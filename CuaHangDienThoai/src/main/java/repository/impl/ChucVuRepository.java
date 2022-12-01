/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.ChucVu;
import repository.IChucVuRepository;
import util.HibernatUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ongbi
 */
public class ChucVuRepository implements IChucVuRepository {

    @Override
    public List<ChucVu> getAll() {
        String hql = "From ChucVu ";
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            Query q = session.createQuery(hql);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ChucVu getOne(String ten) {
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChucVu where ten like :ten");
            query.setParameter("ten", ten);
            ChucVu cv = (ChucVu) query.getSingleResult();
            return cv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean SaveOrUpdate(ChucVu cv) {
        boolean check;
        Transaction transaction = null;
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(cv);
            check = true;
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            check = false;
            transaction.rollback();
        }
        return check;
    }

    @Override
    public boolean delete(ChucVu cv) {
        boolean check;
        Transaction transaction = null;
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cv);
            check = true;
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            check = false;
            transaction.rollback();
        }
        return check;
    }

    public static void main(String[] args) {

        System.out.println(new ChucVuRepository().getAll().size());
    }

    @Override
    public boolean khoiPhuc(ChucVu cv) {
        boolean check;
        String hql = "Update TrangThai Form NhaCungCap Where IdNCC=?";
        Transaction transaction = null;
        try ( Session session = new HibernatUtil().getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(cv);
            check = true;
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            check = false;
            transaction.rollback();
        }
        return check;
    }
}
