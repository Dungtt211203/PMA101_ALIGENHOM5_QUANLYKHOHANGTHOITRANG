/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitories;


import DomainModel.ChatLieu;
import Util.DBContex2;
import ViewModels.ChatLieuViewModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Lvh9x
 */
public class ChatLieuRepository {

    final String INSERT = "INSERT INTO CHATLIEU(Ma,Ten) VALUES(?,?)";
    final String UPDATE = "UPDATE CHATLIEU SET Ma = ?, Ten = ? WHERE Id = ?";
    final String DELETE = "DELETE FROM CHATLIEU WHERE Id = ?";

    public ChatLieuRepository() {
    }

    public List<ChatLieuViewModel> GetAll() {
        String SELECT_CHATLIEU = "SELECT * FROM CHATLIEU";
        List<ChatLieuViewModel> _lstChatLieu = new ArrayList<>();
        try ( Connection conn = DBContex2.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_CHATLIEU);
            while (rs.next()) {
                _lstChatLieu.add(new ChatLieuViewModel(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("Không thể kết nối lại GetAll");
        }
        return _lstChatLieu;
    }

    //add
    public boolean Add(ChatLieu c) {
        try ( Connection conn = DBContex2.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, c.getMa());
            ps.setString(2, c.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi tại AddRepo");
            return false;
        }
    }

    public boolean Update(ChatLieu c) {
        try ( Connection conn = DBContex2.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, c.getMa());
            ps.setString(2, c.getTen());
            ps.setString(3, c.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi tại UpdateRepo");
            return false;
        }
    }

    public boolean Delete(ChatLieu c) {
        try ( Connection conn = DBContex2.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setString(1, c.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi tại DeleteRepo");
            return false;
        }
    }
    
    public static void main(String[] args) {
        ChatLieuRepository cl = new ChatLieuRepository();
        for (ChatLieuViewModel x : cl.GetAll()) {
            System.out.println(x.toString());
        }
    }
}
