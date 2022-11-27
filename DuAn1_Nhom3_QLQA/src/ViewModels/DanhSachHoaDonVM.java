/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.math.BigDecimal;

/**
 *
 * @author longd
 */
public class DanhSachHoaDonVM {
//    HoaDon.MaHD, NhanVien.TenNV, KhachHang.TenKH, HoaDon.NgayTao, 
//            HoaDon.TrangThai, KhachHang.SDT, KhachHang.DiaChi, HoaDon.ThanhTien, HoaDonChiTiet.LyDoHuy 
    String maHD;
    String maNV;
    String tenNv;
    String tenKH;
    String ngayTao;
    Integer trangThai;
    String sdt;
    String diaChi;
    BigDecimal thanhTien;
    String lyDoHuy;

    public DanhSachHoaDonVM() {
    }

    public DanhSachHoaDonVM(String maHD, String maNV, String tenNv, String tenKH, String ngayTao, Integer trangThai, String sdt, String diaChi, BigDecimal thanhTien, String lyDoHuy) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenNv = tenNv;
        this.tenKH = tenKH;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.thanhTien = thanhTien;
        this.lyDoHuy = lyDoHuy;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
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

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getLyDoHuy() {
        return lyDoHuy;
    }

    public void setLyDoHuy(String lyDoHuy) {
        this.lyDoHuy = lyDoHuy;
    }

    
    
}
