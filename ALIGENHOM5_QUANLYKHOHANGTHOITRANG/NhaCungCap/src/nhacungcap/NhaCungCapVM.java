/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nhacungcap;

/**
 *
 * @author ctuye
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
        return "NhaCungCapVM{" + "id=" + id + ", maNCC=" + maNCC + ", tenNCC=" + tenNCC + '}';
    }

}
