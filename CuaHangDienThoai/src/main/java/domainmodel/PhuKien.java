/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "PhuKien")
public class PhuKien {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDPhuKien", length = 36)
    private UUID id;
    
    @Column(name = "MaPhuKien")
    private String ma;
    
    @Column(name = "TenPhuKien")
    private String ten;
    
    @Column(name = "SoLuongTon")
    private int soLuong;
    
    @Column(name = "GiaBan")
    private BigDecimal giaBan;
    
    @Column(name = "Anh", nullable = true)
    private byte[] anh;
    
    @Column(name = "ThoiGianBaoHanh")
    private int thoiGianBaoHanh;
    
    @Column(name = "MoTa")
    private String moTa;
    
    @Column(name = "TrangThai")
    private int trangThai;

//    public PhuKien(String ma, String ten, int soLuong, BigDecimal giaBan, int thoiGianBaoHanh, String moTa, int trangThai) {
//        this.ma = ma;
//        this.ten = ten;
//        this.soLuong = soLuong;
//        this.giaBan = giaBan;
//        this.thoiGianBaoHanh = thoiGianBaoHanh;
//        this.moTa = moTa;
//        this.trangThai = trangThai;
//    }
    
}