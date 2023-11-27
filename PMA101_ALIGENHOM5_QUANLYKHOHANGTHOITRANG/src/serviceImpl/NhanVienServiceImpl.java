/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import DomainModel.NhanVien;
import ViewModel.NhanVienViewModel;


import java.util.List;
import repo.NhanVienReponsitory;
import service.NhanVienService;

/**
 *
 * @author NamNguyenTien
 */
public class NhanVienServiceImpl implements NhanVienService{

    private NhanVienReponsitory nhanVienReponsitory = new NhanVienReponsitory();
  
    @Override
    public String Add(NhanVien nv) {
        if (nhanVienReponsitory.Add(nv)) {
            
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String Update(NhanVien nv) {
        if (nhanVienReponsitory.Update(nv)) {
            return "Update thành công";
        }
        return "Update thất bại";
    }

    @Override
    public String Delete(NhanVien nv) {
        if (nhanVienReponsitory.Delete(nv)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }

    
    
    
    @Override
    public List<NhanVienViewModel> GetAll() {
        return nhanVienReponsitory.GetAll();
    }
    
    public NhanVienViewModel GetNVByID(String id){
        for (NhanVienViewModel x : nhanVienReponsitory.GetAll()) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        return null;
    }
}
