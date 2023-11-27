/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModel.NhanVien;
import ViewModels.NhanVienViewModel;
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
