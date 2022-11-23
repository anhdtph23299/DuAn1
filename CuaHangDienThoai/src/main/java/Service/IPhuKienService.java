/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import java.util.UUID;
import viewmodel.QLPhuKien;

/**
 *
 * @author Admin
 */
public interface IPhuKienService {
    
    List<QLPhuKien> getAll();
    String add(QLPhuKien qLPhuKien);
    String update(QLPhuKien qLPhuKien, UUID id);
    String delete(UUID id);
    
}
