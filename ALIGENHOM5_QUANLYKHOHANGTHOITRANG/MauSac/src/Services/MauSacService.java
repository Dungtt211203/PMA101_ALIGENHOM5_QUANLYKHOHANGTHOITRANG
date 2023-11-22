/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DomainModel.MauSac;
import ViewModels.MauSacViewModel;
import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public interface MauSacService {
    ArrayList<MauSacViewModel> getAll();
    
    void insert(MauSac m);
    void update(MauSac m, String id);
    void delete(String id);
}
