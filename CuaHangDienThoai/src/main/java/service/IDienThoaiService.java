/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodel.DienThoai;
import java.util.List;
import java.util.UUID;
import viewmodel.QLDienThoai;

/**
 *
 * @author dungp
 */
public interface IDienThoaiService {

    List<QLDienThoai> getAll();

    String them(QLDienThoai QLDienThoai);

    String sua(QLDienThoai QLDienThoai, UUID idDienThoai);

    String xoa(UUID idDienThoai);

    List<DienThoai> timKiem(String tenDienThoai);
}
