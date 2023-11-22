/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.impl;

import DomainModel.MauSac;
import Reponsitories.MauSacReponsitory;
import Services.MauSacService;
import ViewModels.MauSacViewModel;
import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public class MauSacServiceImpl implements MauSacService {

    private MauSacReponsitory mRepo;

    public MauSacServiceImpl() {
        this.mRepo = new MauSacReponsitory();
    }

    public void insert(MauSac m) {
        this.mRepo.insert(m);
    }

    public void update(MauSac m, String id) {
        this.mRepo.update(m, id);
    }

    public void delete(String id) {
        this.mRepo.delete(id);
    }

    public ArrayList<MauSacViewModel> getAll() {
        return this.mRepo.getAll();
    }
}
