/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author Lvh9x
 */
public class ChatLieuViewModel {
    String id;
    String maCL;
    String tenCL;

    public ChatLieuViewModel() {
    }

    public ChatLieuViewModel(String id, String ma, String ten) {
        this.id = id;
        this.maCL = ma;
        this.tenCL = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return maCL;
    }

    public void setMa(String ma) {
        this.maCL = ma;
    }

    public String getTen() {
        return tenCL;
    }

    public void setTen(String ten) {
        this.tenCL = ten;
    }

    @Override
    public String toString() {
        return tenCL;
    }
    
}
