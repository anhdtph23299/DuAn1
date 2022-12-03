/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.io.Serializable;
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
public class KhachHangViewMD implements Serializable {

    private UUID id;
    private String CCCD;
    private String hoTen;
    private String ghiChu;
    private String SDT;
    private String email;
    private String diaChi;
    private Integer diemTichLuy;
    private Integer gioiTinh;
    private Date ngayMua;
    private Integer namSinh;

    public Object[] toDataRow() {
        return new Object[]{CCCD, hoTen, gioiTinh == 1 ? "Nam" : "Nữ", SDT, email, diaChi,  diemTichLuy, ghiChu};
    }

}
