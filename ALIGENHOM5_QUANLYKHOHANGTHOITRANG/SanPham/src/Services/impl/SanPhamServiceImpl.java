package Services.impl;


import DomainModel.SanPham;
import Reponsitories.SanPhamRepo;
import Services.SanPhamService;
import ViewModels.SanPhamViewmodel;
import java.util.ArrayList;


public class SanPhamServiceImpl implements SanPhamService{
     private SanPhamRepo mRepo;
    public SanPhamServiceImpl(){
        this.mRepo=new SanPhamRepo();
    }
    public void insert(SanPham m){
        this.mRepo.insert(m);
    }
    public void update(SanPham m,String id){
        this.mRepo.update(m, id);
    }
    public void delete(String id){
        this.mRepo.delete(id);
    }
    public ArrayList<SanPhamViewmodel> getAll(){
        return this.mRepo.getAll();
    }
}
