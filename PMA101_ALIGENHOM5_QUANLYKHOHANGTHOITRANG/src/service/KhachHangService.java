/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DomainModel.KhachHang;
import ViewModel.KhachHangVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface KhachHangService {

    ArrayList<KhachHangVM> getall();

    void insert(KhachHang kh);

    void update(KhachHang kh, String id);

    void delete(String id);
    
    KhachHang getListSDT(String sdt);
}
