/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.time.LocalDateTime;
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
 * @author sktfk
 */
@Entity
@Table(name = "HoaDon")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDHD")
    private UUID idHD;

    @Column(name = "MaHD")
    private String MaHD;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "NgayThanhToan")
    private LocalDateTime NgayThanhToan;

    @Column(name = "DiemTichLuy")
    private int DiemTichLuy;

    @Column(name = "TrangThai")
    private int trangThai;
    
    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
    private List<HoaDonChiTiet> list;
     @ManyToOne
    @JoinColumn(name = "IdKH", nullable = false)
    private KhachHang khachhang ;
 @ManyToOne
    @JoinColumn(name = "IdNhanVien", nullable = false)
    private NhanVien nhanvien ;
//    public String getTrangThai() {
//        return trangThai == 0 ? "Chờ thanh toán" : trangThai==1?"Đã thanh toán":"Huỷ";
//    }

    @Override
    public String toString() {
        return "HoaDon{" + "idHD=" + idHD + ", MaHD=" + MaHD + ", NgayTao=" + ngayTao + ", NgayThanhToan=" + NgayThanhToan + ", DiemTichLuy=" + DiemTichLuy + ", TrangThai=" + trangThai + '}';
    }

}
