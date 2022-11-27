/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author dungp
 */
@Entity
@Table(name = "DienThoai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DienThoai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdDienThoai")
    private UUID idDienThoai;

    @Column(name = "MaDienThoai")
    private String maDienThoai;

    @Column(name = "TenDienThoai")
    private String tenDienThoai;

    private int soLuong;

    @Column(name = "CPU")
    private String CPU;

    @Column(name = "RAM")
    private String RAM;

    @Column(name = "ROM")
    private String ROM;

    @Column(name = "ManHinh")
    private String manHinh;

    @Column(name = "MauSac")
    private String mauSac;
//Dung Code
    @Column(name = "Pin")
    private String pin;

    @Column(name = "Camera")
    private String camera;

    @Column(name = "HeDieuHanh")
    private String heDieuHanh;

    @Column(name = "Anh")
    private byte[] anh;
//
//    @Column(name = "QR")
//    private String qr;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "ThoiGianBaoHanh")
    private int thoiGianBaoHanh;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai")
    private int trangThai;

    @ManyToOne
    @JoinColumn(name = "IdHang")
    private Hang hang;

    @OneToMany(mappedBy = "dienThoai", fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> listHDCT;

//    @OneToMany(mappedBy = "dienThoai", fetch = FetchType.LAZY)
//    private List<ChiTietKhuyenMai> listCTKM;

    public BigDecimal getGia(int soLuong) {
        return giaBan.multiply(BigDecimal.valueOf(soLuong));
    }

    @Override
    public String toString() {
        return tenDienThoai;
    }

}
