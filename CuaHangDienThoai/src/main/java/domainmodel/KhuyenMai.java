/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author hoant
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "KhuyenMai")
public class KhuyenMai implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;
    @Column(name = "Ma", unique = true)
    private String maKM;
    @Column(name = "Ten", nullable = false)
    private String tenKM;
    @Column(name = "SoTienGiam")
    private BigDecimal soTienGiam;
    @Column(name = "ChietKhau")
    private BigDecimal chietKhau;
    @Column(name = "NgayBatDau", nullable = false)
    private Date ngayBatDau;
    @Column(name = "NgayKetThuc", nullable = false)
    private Date ngayKT;
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;

    public KhuyenMai(String maKM, String tenKM, BigDecimal soTienGiam, BigDecimal chietKhau, Date ngayBatDau, Date ngayKT, Integer trangThai) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.soTienGiam = soTienGiam;
        this.chietKhau = chietKhau;
        this.ngayBatDau = ngayBatDau;
        this.ngayKT = ngayKT;
        this.trangThai = trangThai;
    }

}
