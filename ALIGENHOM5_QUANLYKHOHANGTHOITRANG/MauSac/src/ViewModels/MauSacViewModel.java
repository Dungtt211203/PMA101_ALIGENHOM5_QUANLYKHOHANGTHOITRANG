/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

/**
 *
 * @author MyPC
 */
public class MauSacViewModel {

    private String id;
    private String maMS;
    private String tenMS;

    public MauSacViewModel() {
    }

    public MauSacViewModel(String id, String ma, String ten) {
        this.id = id;
        this.maMS = ma;
        this.tenMS = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return maMS;
    }

    public void setMa(String ma) {
        this.maMS = ma;
    }

    public String getTen() {
        return tenMS;
    }

    public void setTen(String ten) {
        this.tenMS = ten;
    }

    @Override
    public String toString() {
        return tenMS;
    }

}
