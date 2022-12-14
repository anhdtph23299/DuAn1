/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Imei")
public class Imei {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private UUID id;
    
    @Column(name = "MaImei")
    private String ma;
    
    @Column(name = "TrangThai")
    private int trangThai;
    
    @ManyToOne
    @JoinColumn(name = "IdDTNCC")
    private DienThoaiNCC dtNcc;
    
    @ManyToOne
    @JoinColumn(name = "IdPKNCC")
    private PhuKienNCC pkNcc;
    
}
