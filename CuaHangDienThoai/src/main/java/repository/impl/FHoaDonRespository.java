/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainmodel.HoaDon;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernatUtil;

/**
 *
 * @author Admin
 */
public class FHoaDonRespository implements repository.IFHoaDonRespository{

    /**
     *
     * @return
     */
    @Override
    public List<HoaDon> getAll() {
  String hql = "From HoaDon Order by ngayThanhToan ASC";
        try ( Session session = HibernatUtil.getFACTORY().openSession()){
           Query q = session.createQuery(hql);
           return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    
}
