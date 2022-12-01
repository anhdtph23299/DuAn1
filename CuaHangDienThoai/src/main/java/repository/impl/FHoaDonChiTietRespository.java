/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;
import domainmodel.HoaDonChiTiet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernatUtil;
import repository.IFHoaDonChiTietRespository;
/**
 *
 * @author Admin
 */
public class FHoaDonChiTietRespository implements repository.IFHoaDonChiTietRespository{

    @Override
    public List<HoaDonChiTiet> getAll() {
 String hql = "from HoaDonChiTiet order by DonGia  DESC";
        try ( Session session = HibernatUtil.getFACTORY().openSession()){
           Query q = session.createQuery(hql);
           return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<HoaDonChiTiet> search(String TongTien) {
try (Session sess = HibernatUtil.getFACTORY().openSession()) {
            Query qr = sess.createQuery("SELECT hdct FROM HoaDonChiTiet hdct WHERE hdct.SoLuong LIKE CONCAT('%',:SoLuong,'%') between hdct.CCCD LIKE CONCAT('%',:SoLuong,'%') ");
            qr.setParameter("CCCD", TongTien);
            List<HoaDonChiTiet> list = qr.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
  
    
    
    }
    
    }
    

