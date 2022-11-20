/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domainModel.Hang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHangRepository {
    
    List<Hang> getAll();
    
    boolean save(Hang hang);
    
    boolean update (Hang hang);
    
    boolean delete (Hang hang);
    
}
