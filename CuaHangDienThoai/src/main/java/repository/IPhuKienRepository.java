/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domainModel.PhuKien;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IPhuKienRepository {
    
    List<PhuKien> getAll();
    
}
