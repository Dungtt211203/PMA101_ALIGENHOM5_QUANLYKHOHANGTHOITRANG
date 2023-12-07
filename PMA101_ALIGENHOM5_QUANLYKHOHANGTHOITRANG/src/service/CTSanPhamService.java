/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import DomainModel.CTSanPham;
import ViewModel.CTSanPhamViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lvh9x
 */
public interface CTSanPhamService {

    ArrayList<CTSanPhamViewModel> getall();

    void insert(CTSanPham ctsp);

    void update(CTSanPham ctsp, String id);

    void delete(String id);
    
//    CTSanPham getListSDT(String sdt);


}
