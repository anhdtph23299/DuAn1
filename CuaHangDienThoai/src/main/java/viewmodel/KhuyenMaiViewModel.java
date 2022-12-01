/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hoant
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhuyenMaiViewModel {

    private UUID id;
    private String maKM;
    private String tenKM;
    private BigDecimal soTienGiam;
    private BigDecimal chietKhau;
    private Date ngayBatDau;
    private Date ngayKT;
    private Integer trangThai;

    public KhuyenMaiViewModel(String maKM, String tenKM, BigDecimal soTienGiam, BigDecimal chietKhau, Date ngayBatDau, Date ngayKT, Integer trangThai) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.soTienGiam = soTienGiam;
        this.chietKhau = chietKhau;
        this.ngayBatDau = ngayBatDau;
        this.ngayKT = ngayKT;
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{maKM, tenKM, soTienGiam, chietKhau, ngayBatDau, ngayKT,trangThai==1?"Hết hiệu lực":"Đang hoạt động"};
    }

    public KhuyenMaiViewModel(UUID id) {
        this.id = id;
    }
    
}
