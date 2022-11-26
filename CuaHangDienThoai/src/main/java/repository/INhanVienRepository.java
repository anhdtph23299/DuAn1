/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domainmodel.NhanVien;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author lethi
 */
public interface INhanVienRepository {
    List<NhanVien> getAll();
    boolean add(NhanVien nhanVien);
    boolean update(NhanVien nhanVien, UUID id);
    boolean delete(UUID id);
    List<NhanVien> search(String CCCD);
}
