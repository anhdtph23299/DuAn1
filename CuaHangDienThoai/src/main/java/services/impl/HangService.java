/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModel.Hang;
import java.util.ArrayList;
import java.util.List;
import repository.IHangRepository;
import repository.impl.HangRepository;
import services.IHangService;
import viewModel.QLHang;

/**
 *
 * @author Admin
 */
public class HangService implements IHangService{
    
    private IHangRepository hangRe = new HangRepository();
    
    @Override
    public List<QLHang> getAll() {
        List<QLHang> listHang = new ArrayList<>();
        for (Hang x : hangRe.getAll()) {
            QLHang hang = new QLHang(x.getId(), x.getMa(), x.getTen());
            listHang.add(hang);
        }
        return listHang;
    }
    
}
