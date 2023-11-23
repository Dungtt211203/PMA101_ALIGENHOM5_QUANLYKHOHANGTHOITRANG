package ViewModels;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER NQC 112021
 */
public class SanPhamViewmodel {
     private String id;
    private String maSP;
    private String tenSP;

    public SanPhamViewmodel() {
    }

    public SanPhamViewmodel(String id, String ma, String ten) {
        this.id = id;
        this.maSP = ma;
        this.tenSP = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return maSP;
    }

    public void setMa(String ma) {
        this.maSP = ma;
    }

    public String getTen() {
        return tenSP;
    }

    public void setTen(String ten) {
        this.tenSP = ten;
    }

    @Override
    public String toString() {
        return tenSP;
    }
    
}
