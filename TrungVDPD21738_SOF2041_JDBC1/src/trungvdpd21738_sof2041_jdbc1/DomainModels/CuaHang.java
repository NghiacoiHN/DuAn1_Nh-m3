/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

/**
 *
 * @author TrungVD
 */
public class CuaHang {
    private String iD;
    private String ma;
    private String tenCH;
    private String diaChi;
    private String thanhPho;
    private String quocGia;

    public CuaHang() {
    }

    public CuaHang(String iD) {
        this.iD = iD;
    }
    
    public CuaHang(String ma, String tenCH, String diaChi, String thanhPho, String quocGia) {
        this.ma = ma;
        this.tenCH = tenCH;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
    }
    
    

    public CuaHang(String iD, String ma, String tenCH, String diaChi, String thanhPho, String quocGia) {
        this.iD = iD;
        this.ma = ma;
        this.tenCH = tenCH;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenCH() {
        return tenCH;
    }

    public void setTenCH(String tenCH) {
        this.tenCH = tenCH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    @Override
    public String toString() {
        return tenCH +" " + thanhPho + " (" +diaChi+ ")";
    }
    
    
    
}
