/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DomainModel.NhanVien;
import ViewModel.NhanVienViewModel;
import java.util.List;

/**
 *
 * @author NamNguyenTien
 */
public interface NhanVienService {
    public String Add(NhanVien nv);

    public String Update(NhanVien nv);

    public String Delete(NhanVien nv);

    public List<NhanVienViewModel> GetAll();
}
