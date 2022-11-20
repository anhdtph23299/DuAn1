/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainModel.PhuKien;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import repository.HibernatUtil;
import repository.IPhuKienRepository;

/**
 *
 * @author Admin
 */
public class PhuKienRepository implements IPhuKienRepository{

    @Override
    public List<PhuKien> getAll() {
        List<PhuKien> list;
        try(Session session = HibernatUtil.getFACTORY().openSession()){
            Query q = session.createQuery("SELECT pk FROM PhuKien pk");
            list = q.getResultList();
        }catch(Exception e){
            e.printStackTrace(System.out);
            return null;
        }
        return list;
    }
    
}
