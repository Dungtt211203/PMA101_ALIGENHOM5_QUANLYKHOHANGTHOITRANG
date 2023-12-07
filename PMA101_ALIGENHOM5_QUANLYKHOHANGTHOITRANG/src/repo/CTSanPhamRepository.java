/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import DomainModel.CTSanPham;
import util.DBContex2;
import util.DBContext;
import ViewModel.CTSanPhamViewModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Lvh9x
 */
public class CTSanPhamRepository {

//    final String INSERT_SQL = "INSERT INTO CHITIETSP(IdSP,IdSize,IdMauSac,IDNhaCungCap,IdChatLieu,MoTa,SoLuongTon,GiaBan,GiaNhap) VALUES(?,?,?,?,?,?,?,?,?)";
//
//    final String DELETE_SQL = "DELETE FROM CHITIETSP WHERE ID = ?";// câu này nó cần mỗi cái id để xóa 
    public CTSanPhamRepository() {
    }

    public ArrayList<CTSanPhamViewModel> getAll() {
        ArrayList<CTSanPhamViewModel> listCTSP = new ArrayList<>();
        try {
            Connection conn = DBContex2.getConnection();
            String sql = "SELECT * FROM ChiTietSP";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("Id");
                String namBH = rs.getString("NamBH");
                String moTa = rs.getString("MoTa");
                String soLuong = rs.getString("SoLuongTon");
                String giaNhap = rs.getString("GiaNhap");
                String giaBan = rs.getString("GiaBan");
                CTSanPhamViewModel ctsp = new CTSanPhamViewModel(id, namBH, moTa, soLuong, giaNhap, giaBan);
                listCTSP.add(ctsp);
            }
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
        return listCTSP;

    }

    public void insert(CTSanPham ctsp) {
        try {
            Connection conn = DBContex2.getConnection();
            String sql = "INSERT INTO ChiTietSP" + "(NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan)" + "VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ctsp.getNamBH());
            ps.setString(2, ctsp.getMoTa());
            ps.setString(3, ctsp.getSoLuongTon());
            ps.setString(4, ctsp.getGiaNhap());
            ps.setString(5, ctsp.getGiaBan());
            ps.execute();
            System.out.println("Thêm thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void update(CTSanPham ctsp, String id) {
        try {
            Connection conn = DBContex2.getConnection();
            String sql = "UPDATE ChiTietSP SET " + "NamBH=?,MoTa=?,SoLuongTon=?,GiaNhap=?,GiaBan=? WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ctsp.getNamBH());
            ps.setString(2, ctsp.getMoTa());
            ps.setString(3, ctsp.getSoLuongTon());
            ps.setString(4, ctsp.getGiaNhap());
            ps.setString(5, ctsp.getGiaBan());
            ps.setString(6, id);
            ps.execute();
            System.out.println("Sửa thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = DBContext.getConnection();
            String sql = "DELETE FROM ChiTietSP WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            System.out.println("Xóa thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
