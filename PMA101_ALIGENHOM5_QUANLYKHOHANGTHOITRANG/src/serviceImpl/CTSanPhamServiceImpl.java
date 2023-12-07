/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviceImpl;

import DomainModel.CTSanPham;
import repo.CTSanPhamRepository;
import repo.ChatLieuRepository;
import service.CTSanPhamService;
import ViewModel.CTSanPhamViewModel;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Lvh9x
 */
public class CTSanPhamServiceImpl implements CTSanPhamService {

    private CTSanPhamRepository repoCTSP = new CTSanPhamRepository();

    @Override
    public ArrayList<CTSanPhamViewModel> getall() {
       return  this.repoCTSP.getAll();
    }

    @Override
    public void insert(CTSanPham ctsp) {
        this.repoCTSP.insert(ctsp);
    }

    @Override
    public void update(CTSanPham ctsp, String id) {
        this.repoCTSP.update(ctsp, id);
    }

    @Override
    public void delete(String id) {
        this.repoCTSP.delete(id);
    }




}




