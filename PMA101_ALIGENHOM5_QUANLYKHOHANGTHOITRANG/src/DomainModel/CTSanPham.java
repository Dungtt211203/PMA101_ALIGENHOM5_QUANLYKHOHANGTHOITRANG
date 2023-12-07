/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author Lvh9x
 */
public class CTSanPham {

    private String id;
//    private String idSp;
//    private String idSize; // cái này k đẻ string mà để object. sau đó truyền object vào nó sẽ tìm tostring mà totring nó hiển thì Tên
//    private String idMauSac;
//    private String idNhaCungCap;
//    private String idChatLieu;
    private String namBH;
    private String moTa;
    private String soLuongTon;
    private String giaNhap;
    private String giaBan; // thấy sai chua
    

    public CTSanPham() {
    }

    public CTSanPham(String id, String namBH, String moTa, String soLuongTon, String giaBan, String giaNhap) {
        this.id = id;
        this.namBH = namBH;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamBH() {
        return namBH;
    }

    public void setNamBH(String namBH) {
        this.namBH = namBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(String soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(String giaNhap) {
        this.giaNhap = giaNhap;
    }

    @Override
    public String toString() {
        return "CTSanPhamViewModel{" + "id=" + id + ", namBH=" + namBH + ", moTa=" + moTa + ", soLuongTon=" + soLuongTon + ", giaBan=" + giaBan + ", giaNhap=" + giaNhap + '}';
    }

    

    

   
    
    
}
