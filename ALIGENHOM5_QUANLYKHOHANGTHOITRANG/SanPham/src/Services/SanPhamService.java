package Services;


import DomainModel.SanPham;
import ViewModels.SanPhamViewmodel;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER NQC 112021
 */
public interface SanPhamService {
     ArrayList<SanPhamViewmodel> getAll();
    
    void insert(SanPham sp);
    void update(SanPham sp, String id);
    void delete(String id);
}
