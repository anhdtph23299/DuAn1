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
import repository.HibernatUtil;

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

    @Override
    public boolean save(DienThoai dienThoai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(UUID IdDienThoai, DienThoai dienThoai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(UUID IdDienThoai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        List<DienThoai> l = new DienThoaiRepository().getAll();
        for (DienThoai dienThoai : l) {
            System.out.println(dienThoai);
        }
    }

    @Override
    public DienThoai getOne(UUID IdDienThoai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
