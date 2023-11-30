/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

/**
 *
 * @author Kieu Oanh
 */
public class NhaCungCapVM {

    private String id;
    private String maNCC;
    private String tenNCC;

    public NhaCungCapVM() {
    }

    public NhaCungCapVM(String id, String maNCC, String tenNCC) {
        this.id = id;
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    @Override
    public String toString() {
        return tenNCC;
    }

}
