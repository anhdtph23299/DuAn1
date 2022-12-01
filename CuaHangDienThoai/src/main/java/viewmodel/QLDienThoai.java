/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author dungp
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QLDienThoai {
// Dũng Code
    private UUID IdDienThoai;
    private String maDienThoai;
    private String tenDienThoai;
    private Integer soLuongTon;
    private String CPU;
    private String RAM;
    private String ROM;
    private String manHinh;
    private String mauSac;
    private String pin;
    private String camera;
    private String heDieuHanh;
    private byte[] anh;
    private BigDecimal giaBan;
    private int thoiGianBaoHanh;
    private String mota;
    private int trangThai;

    public QLDienThoai(String tenDienThoai, int soLuongTon, String RAM, String ROM, String manHinh, String mauSac, BigDecimal giaBan, int thoiGianBaoHanh, int trangThai) {
        this.tenDienThoai = tenDienThoai;
        this.soLuongTon = soLuongTon;
        this.RAM = RAM;
        this.ROM = ROM;
        this.manHinh = manHinh;
        this.mauSac = mauSac;
        this.giaBan = giaBan;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.trangThai = trangThai;
    }

    public QLDienThoai(UUID IdDienThoai, String maDienThoai) {
        this.IdDienThoai = IdDienThoai;
        this.maDienThoai = maDienThoai;
    }

    public QLDienThoai(UUID IdDienThoai) {
        this.IdDienThoai = IdDienThoai;
    }



 
}
