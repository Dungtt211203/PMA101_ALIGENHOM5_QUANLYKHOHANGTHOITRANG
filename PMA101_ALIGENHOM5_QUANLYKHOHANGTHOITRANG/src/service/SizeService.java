/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DomainModel.Size;
import ViewModel.SizeVM;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface SizeService {

    ArrayList<SizeVM> getListSize();

    void insert(Size s);

    void update(Size s, String id);

    void delete(String id);
}
