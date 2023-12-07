package repo;


import DomainModel.SanPham;
import util.DBContex2;

import ViewModel.SanPhamViewmodel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER NQC 112021
 */
public class SanPhamRepo {
     public void insert(SanPham sp) {
        try {
            Connection conn = DBContex2.getConnection();
            String sql = "INSERT INTO SanPham" + "(Ma,Ten)" + "VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMa());
            ps.setString(2, sp.getTen());
            ps.execute();
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void update(SanPham sp, String id) {
        try {
            Connection conn = DBContex2.getConnection();
            String sql = "UPDATE SanPham SET " + "Ma=?,Ten=? WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMa());
            ps.setString(2, sp.getTen());
            ps.setString(3, id);
            ps.execute();
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = DBContex2.getConnection();
            String sql = "DELETE FROM SanPham WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ArrayList<SanPhamViewmodel> getAll(){
        ArrayList<SanPhamViewmodel> listMS=new ArrayList<>();
        try {
            Connection conn=DBContex2.getConnection();
            String sql="SELECT * FROM SanPham";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs=ps.getResultSet();
            while (rs.next()) {                
                listMS.add(new SanPhamViewmodel(rs.getString("Id"),rs.getString("Ma"),rs.getString("Ten")));
            }
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
        }
        return listMS;
        
    }
    
    public static void main(String[] args) {
        SanPhamRepo ctsp = new SanPhamRepo();
        for (SanPhamViewmodel x : ctsp.getAll()) {
            System.out.println(x.toString());
        }
    }
}
