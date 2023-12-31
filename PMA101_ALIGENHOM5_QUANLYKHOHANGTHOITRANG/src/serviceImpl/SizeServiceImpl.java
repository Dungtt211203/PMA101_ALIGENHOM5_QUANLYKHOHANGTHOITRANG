/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import DomainModel.Size;
import ViewModel.SizeVM;
import java.util.ArrayList;
import repo.SizeReponsitory;
import service.SizeService;

/**
 *
 * @author Admin
 */
public class SizeServiceImpl implements SizeService {

    private SizeReponsitory sRepo;

    public SizeServiceImpl() {
        this.sRepo = new SizeReponsitory();
    }

    public void insert(Size s) {
        this.sRepo.insert(s);
    }

    public void update(Size s, String id) {
        this.sRepo.update(s, id);
    }

    public void delete(String id) {
        this.sRepo.delete(id);
    }

    public ArrayList<SizeVM> getListSize() {
        return this.sRepo.all();
    }
}
