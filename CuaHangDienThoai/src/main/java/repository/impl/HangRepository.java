/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainModel.Hang;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.HibernatUtil;
import repository.IHangRepository;

/**
 *
 * @author Admin
 */
public class HangRepository implements IHangRepository {

    @Override
    public List<Hang> getAll() {
        List<Hang> list;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("FROM Hang");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return list;
    }

    public List<Hang> timKiem(String chuoi) {
        List<Hang> list;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query q = session.createQuery("FROM Hang WHERE ma like :t");
            String chuois = "%" + chuoi + "%";
            q.setParameter("t", chuois);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<Hang> list = new HangRepository().timKiem("H");
        for (Hang x : list) {
            System.out.println(x);
        }
//        Hang hang = new HangRepository().getOne("H005");
//        System.out.println(hang);
    }

    @Override
    public boolean save(Hang hang) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            return false;            
        }
    }

    @Override
    public boolean update(Hang hang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Hang hang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
