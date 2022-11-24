/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domainmodel.KhuyenMai;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author hoant
 */
public interface IKhuyenMaiRepository {

    List<KhuyenMai> getAll();

    Boolean add(KhuyenMai km);

    Boolean update(KhuyenMai km, UUID id);

    Boolean delete(UUID id);
}
