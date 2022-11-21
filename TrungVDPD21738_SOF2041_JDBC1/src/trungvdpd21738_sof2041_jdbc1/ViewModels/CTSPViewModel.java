/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.ViewModels;

/**
 *
 * @author TrungVD
 */
public class CTSPViewModel {
    private String iDCTSP,iDSP,maSP,tenSP,iDNSX,maNSX,tenNSX,
                   iDMauSac,maMauSac,tenMauSac,iDDSP,maDSP,tenDSP,mota;
    private int soLuongTon;
    private double giaNhap,giaBan;

    public CTSPViewModel() {
    }

    public CTSPViewModel(String iDSP, String maSP, String tenSP, String iDNSX, String maNSX, String tenNSX, String iDMauSac, String maMauSac, String tenMauSac, String iDDSP, String maDSP, String tenDSP, String mota, int soLuongTon, double giaNhap, double giaBan) {
        this.iDSP = iDSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.iDNSX = iDNSX;
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
        this.iDMauSac = iDMauSac;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
        this.iDDSP = iDDSP;
        this.maDSP = maDSP;
        this.tenDSP = tenDSP;
        this.mota = mota;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    
    public CTSPViewModel(String iDCTSP, String iDSP, String maSP, String tenSP, String iDNSX, String maNSX, String tenNSX, String iDMauSac, String maMauSac, String tenMauSac, String iDDSP, String maDSP, String tenDSP, String mota, int soLuongTon, double giaNhap, double giaBan) {
        this.iDCTSP = iDCTSP;
        this.iDSP = iDSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.iDNSX = iDNSX;
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
        this.iDMauSac = iDMauSac;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
        this.iDDSP = iDDSP;
        this.maDSP = maDSP;
        this.tenDSP = tenDSP;
        this.mota = mota;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public String getiDCTSP() {
        return iDCTSP;
    }

    public void setiDCTSP(String iDCTSP) {
        this.iDCTSP = iDCTSP;
    }

    public String getiDSP() {
        return iDSP;
    }

    public void setiDSP(String iDSP) {
        this.iDSP = iDSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getiDNSX() {
        return iDNSX;
    }

    public void setiDNSX(String iDNSX) {
        this.iDNSX = iDNSX;
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getiDMauSac() {
        return iDMauSac;
    }

    public void setiDMauSac(String iDMauSac) {
        this.iDMauSac = iDMauSac;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getiDDSP() {
        return iDDSP;
    }

    public void setiDDSP(String iDDSP) {
        this.iDDSP = iDDSP;
    }

    public String getMaDSP() {
        return maDSP;
    }

    public void setMaDSP(String maDSP) {
        this.maDSP = maDSP;
    }

    public String getTenDSP() {
        return tenDSP;
    }

    public void setTenDSP(String tenDSP) {
        this.tenDSP = tenDSP;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
   
                   
}
