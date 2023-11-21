/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import DomainModel.MauSac;
import ViewModel.MauSacViewModel;
import java.util.ArrayList;
import repo.MauSacRepo;

/**
 *
 * @author Admin
 */
public class MauSacServiceImpl {
     private MauSacRepo mRepo;
    public MauSacServiceImpl(){
        this.mRepo=new MauSacRepo();
    }
    public void insert(MauSac m){
        this.mRepo.insert(m);
    }
    public void update(MauSac m,String id){
        this.mRepo.update(m, id);
    }
    public void delete(String id){
        this.mRepo.delete(id);
    }
    public ArrayList<MauSacViewModel> getAll(){
        return this.mRepo.getAll();
    }
}
