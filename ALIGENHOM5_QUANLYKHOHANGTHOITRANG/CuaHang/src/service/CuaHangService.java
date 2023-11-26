package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.CuaHang;

public class CuaHangService {

    List<CuaHang> listSV;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<CuaHang> getAll() {
        listSV = new ArrayList();
        //lấy toàn bộ bảng dl từ bảng sang list
        sql = "select * from CuaHang";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); //thực thi câu lệnh sql lấy kết quả cảu select trả về rs
            while (rs.next()) {
                CuaHang sv = new CuaHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                listSV.add(sv);
            }
            return listSV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insertCH(CuaHang ch) {
        //trả về số dòng được thêm mới
        sql = "insert into CuaHang (Ma,\n"
                + "Ten,DiaChi,ThanhPho,QuocGia) values\n"
                + "(?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ch.getMa());
            ps.setString(2, ch.getTen());
            ps.setString(3, ch.getDiaChi());
            ps.setString(4, ch.getThanhPho());
            ps.setString(5, ch.getQuocGia());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteCH(String ma) {
        sql = "delete from CuaHang where Ma like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(String ma, CuaHang ch) {
        sql = "update CuaHang set Ten=?,DiaChi=?,ThanhPho=?,QuocGia=?\n"
                + " where Ma like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ch.getTen());
            ps.setString(2, ch.getDiaChi());
            ps.setString(3, ch.getThanhPho());
            ps.setString(4, ch.getQuocGia());
            ps.setString(5, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
