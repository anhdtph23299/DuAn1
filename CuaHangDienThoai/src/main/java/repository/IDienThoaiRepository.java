/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import java.util.UUID;
import domainmodel.DienThoai;

/**
 *
 * @author dungp
 */
public interface IDienThoaiRepository {

    List<DienThoai> getAll();

    DienThoai getOne(UUID IdDienThoai);

    boolean save(DienThoai dienThoai);

    boolean update(UUID IdDienThoai, DienThoai dienThoai);

    boolean delete(UUID IdDienThoai);
    
}
