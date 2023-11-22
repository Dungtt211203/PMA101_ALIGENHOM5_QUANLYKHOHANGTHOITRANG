/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.impl;

import DomainModel.KhachHang;
import Reponsitories.KhachHangRepo;
import Services.KhachHangService;
import ViewModels.KhachHangVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepo khRepo;

    public KhachHangServiceImpl() {
        this.khRepo = new KhachHangRepo();
    }

    public void insert(KhachHang kh) {
        this.khRepo.insert(kh);
    }

    public void update(KhachHang kh, String id) {
        this.khRepo.update(kh, id);
    }

    public void delete(String id) {
        this.khRepo.delete(id);
    }

    public ArrayList<KhachHangVM> getall() {
        return this.khRepo.getall();
    }
    
    public KhachHang getListSDT(String sdt){
        return this.khRepo.getListSDT(sdt);
    }
}
