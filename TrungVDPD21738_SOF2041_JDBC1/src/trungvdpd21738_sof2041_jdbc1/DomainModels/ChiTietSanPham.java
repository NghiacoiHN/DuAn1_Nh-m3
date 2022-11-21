/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

/**
 *
 * @author TrungVD
 */
public class ChiTietSanPham {
    private String iD;
    private SanPham sanPham;
    private NhaSanXuat nhaSanXuat;
    private MauSac mauSac;
    private DongSanPham dongSanPham;
    private int namBH;
    private String moTa;
    private int soLuongTon;
    private double giaNhap;
    private double giaBan;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String iD, SanPham sanPham, NhaSanXuat nhaSanXuat, MauSac mauSac, DongSanPham dongSanPham, int namBH, String moTa, int soLuongTon, double giaNhap, double giaBan) {
        this.iD = iD;
        this.sanPham = sanPham;
        this.nhaSanXuat = nhaSanXuat;
        this.mauSac = mauSac;
        this.dongSanPham = dongSanPham;
        this.namBH = namBH;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public ChiTietSanPham(SanPham sanPham, NhaSanXuat nhaSanXuat, MauSac mauSac, DongSanPham dongSanPham, int namBH, String moTa, int soLuongTon, double giaNhap, double giaBan) {
        this.sanPham = sanPham;
        this.nhaSanXuat = nhaSanXuat;
        this.mauSac = mauSac;
        this.dongSanPham = dongSanPham;
        this.namBH = namBH;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public DongSanPham getDongSanPham() {
        return dongSanPham;
    }

    public void setDongSanPham(DongSanPham dongSanPham) {
        this.dongSanPham = dongSanPham;
    }

    public int getNamBH() {
        return namBH;
    }

    public void setNamBH(int namBH) {
        this.namBH = namBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
