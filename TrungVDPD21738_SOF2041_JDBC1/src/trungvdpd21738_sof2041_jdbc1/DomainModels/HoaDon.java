/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

import java.util.Date;

/**
 *
 * @author TrungVD
 */
public class HoaDon {
    private String iD;
    private String maHD;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Date ngayShip;
    private Date ngayNhan;
    private int tinhTrang;
    private String tenNguoinhan;
    private String diaChi;
    private String sDT;

    public HoaDon() {
    }

    public HoaDon(String maHD, KhachHang khachHang, NhanVien nhanVien, Date ngayTao, Date ngayThanhToan, Date ngayShip, Date ngayNhan, int tinhTrang, String tenNguoinhan, String diaChi, String sDT) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.tinhTrang = tinhTrang;
        this.tenNguoinhan = tenNguoinhan;
        this.diaChi = diaChi;
        this.sDT = sDT;
    }

    public HoaDon(String iD, String maHD, KhachHang khachHang, NhanVien nhanVien, Date ngayTao, Date ngayThanhToan, Date ngayShip, Date ngayNhan, int tinhTrang, String tenNguoinhan, String diaChi, String sDT) {
        this.iD = iD;
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.tinhTrang = tinhTrang;
        this.tenNguoinhan = tenNguoinhan;
        this.diaChi = diaChi;
        this.sDT = sDT;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Date getNgayShip() {
        return ngayShip;
    }

    public void setNgayShip(Date ngayShip) {
        this.ngayShip = ngayShip;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTenNguoinhan() {
        return tenNguoinhan;
    }

    public void setTenNguoinhan(String tenNguoinhan) {
        this.tenNguoinhan = tenNguoinhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }
    
    
}
