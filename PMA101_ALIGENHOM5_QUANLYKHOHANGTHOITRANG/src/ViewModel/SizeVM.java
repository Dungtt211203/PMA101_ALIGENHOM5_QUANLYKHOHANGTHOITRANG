/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

/**
 *
 * @author Admin
 */
public class SizeVM {
            
    private String id;
    private String maSIZE;
    private String tenSIZE;

    public SizeVM() {
    }

    public SizeVM(String id, String ma, String ten) {
        this.id = id;
        this.maSIZE = ma;
        this.tenSIZE = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return maSIZE;
    }

    public void setMa(String ma) {
        this.maSIZE = ma;
    }

    public String getTen() {
        return tenSIZE;
    }

    public void setTen(String ten) {
        this.tenSIZE = ten;
    }

    @Override
    public String toString() {
        return tenSIZE;
    }

    
    
}
