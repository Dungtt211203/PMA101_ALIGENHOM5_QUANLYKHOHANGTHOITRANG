/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhacungcap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ctuye
 */
public class NhaCungCapRepo {

    public void insert(NhaCungCapVM ncc) {
        try {
            Connection conn = (Connection) DBContext11.getConnection();
            String sql = "INSERT INTO NhaCungCap" + "(MaNCC,TenNCC)" + "VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.execute();
            System.out.println("Thêm thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void update(NhaCungCapVM ncc, String id) {
        try {
            Connection conn = (Connection) DBContext11.getConnection();
            String sql = "UPDATE NhaCungCap SET " + "MaNCC=?,TenNCC=? WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, id);
            ps.execute();
            System.out.println("Sửa thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = (Connection) DBContext11.getConnection();
            String sql = "DELETE FROM NhaCungCap WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            System.out.println("Xóa thành công");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ArrayList<NhaCungCapVM> getAll() {
        ArrayList<NhaCungCapVM> listNCC = new ArrayList<>();
        try {
            Connection conn = (Connection) DBContext11.getConnection();
            String sql = "SELECT * FROM NhaCungCap";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                listNCC.add(new NhaCungCapVM(rs.getString("Id"), rs.getString("MaNCC"), rs.getString("TenNCC")));
            }
            System.out.println("Truy vấn thành công");
        } catch (Exception e) {
            e.getMessage();
        }
        return listNCC;

    }

    public static void main(String[] args) {
        NhaCungCapRepo ctsp = new NhaCungCapRepo();
        for (NhaCungCapVM x : ctsp.getAll()) {
            System.out.println(x.toString());
        }
    }
}
