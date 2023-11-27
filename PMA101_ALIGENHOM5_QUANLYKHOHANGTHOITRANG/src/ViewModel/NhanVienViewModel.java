/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

/**
 *
 * @author NamNguyenTien
 */
public class NhanVienViewModel {

    private String id;
    private String manv;
    private String tennv;
    private String tendemNV;
    private String hoNV;
    private String ngaySinh;
    private int gioiTinh;
    private String sdt;
    private String diaChi;
    private int trangThai;

    public NhanVienViewModel() {
    }

    public NhanVienViewModel(String id, String manv, String tennv, String tendemNV, String hoNV, String ngaySinh, int gioiTinh, String sdt, String diaChi, int trangThai) {
        this.id = id;
        this.manv = manv;
        this.tennv = tennv;
        this.tendemNV = tendemNV;
        this.hoNV = hoNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    

    

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTendemNV() {
        return tendemNV;
    }

    public void setTendemNV(String tendemNV) {
        this.tendemNV = tendemNV;
    }

   

    public String getHoNV() {
        return hoNV;
    }

    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    @Override
    public String toString() {
        return tennv;
    }
}
