/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QLHoaDon {

    
    private UUID IdHD;
    private KhachHangViewMD IdKH;
    private QLNhanVien IdNhanVien;
    
    private String MaHD;
    private LocalDateTime NgayTao;
    private LocalDateTime NgayThanhToan;
    private int DiemTichLuy;
    private int trangThai;

    public QLHoaDon(UUID IdHD, String MaHD, LocalDateTime NgayThanhToan) {
        this.IdHD = IdHD;
        this.MaHD = MaHD;
        this.NgayThanhToan = NgayThanhToan;
    }

    public QLHoaDon(LocalDateTime NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }


     
 
    
}
