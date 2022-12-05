/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
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
public class QLPhuKienNCC {

  private UUID id;
    private UUID inhaCungCap;
    private UUID iphuKien;
    private String manhaCungCap;
    private String maphuKien;
    private BigDecimal giaNhap;
    private int soLuongNhap;
    private Date ngayNhap;

    public BigDecimal tongTien(BigDecimal gia, int soLuong) {
        BigDecimal convertSo = new BigDecimal(soLuong);
        BigDecimal tongTien = gia.multiply(convertSo);
        return tongTien;
     
    }


}
