/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reponsitories;

import DomainModel.MauSac;
import Util.DBContext1;
import ViewModels.MauSacViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public class MauSacReponsitory {

    public void insert(MauSac m) {
        try {
            Connection conn = DBContext1.getConnection();
            String sql = "INSERT INTO MauSac" + "(Ma,Ten)" + "VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTen());
            ps.execute();
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void update(MauSac m, String id) {
        try {
            Connection conn = DBContext1.getConnection();
            String sql = "UPDATE MauSac SET " + "Ma=?,Ten=? WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTen());
            ps.setString(3, id);
            ps.execute();
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = DBContext1.getConnection();
            String sql = "DELETE FROM MauSac WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ArrayList<MauSacViewModel> getAll() {
        ArrayList<MauSacViewModel> listMS = new ArrayList<>();
        try {
            Connection conn = DBContext1.getConnection();
            String sql = "SELECT * FROM MauSac";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                listMS.add(new MauSacViewModel(rs.getString("Id"), rs.getString("Ma"), rs.getString("Ten")));
            }
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
        }
        return listMS;

    }

    public static void main(String[] args) {
        MauSacReponsitory ctsp = new MauSacReponsitory();
        for (MauSacViewModel x : ctsp.getAll()) {
            System.out.println(x.toString());
        }
    }
}
