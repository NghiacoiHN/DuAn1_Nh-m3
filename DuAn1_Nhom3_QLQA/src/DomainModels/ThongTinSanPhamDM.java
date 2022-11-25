/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.math.BigDecimal;

/**
 *
 * @author longd
 */
public class ThongTinSanPhamDM {
    //SanPham.MaSP, SanPham.TenSp, HoaDonChiTiet.SoLuong,
    //HoaDonChiTiet.DonGia, HoaDon.SoTienGiamGia, HoaDon.ThanhTien, ChiTietSanPham.TrangThai, HoaDonChiTiet.LyDoHuy
    
    String maSP;
    String tenSP;
    Integer soLuong;
    BigDecimal donGia;
    BigDecimal soTienGiamGia;
    BigDecimal thanhTien;
    Integer trangThai;
    String lyDoHuy;

    public ThongTinSanPhamDM() {
    }

    public ThongTinSanPhamDM(String maSP, String tenSP, Integer soLuong, BigDecimal donGia, BigDecimal soTienGiamGia, BigDecimal thanhTien, Integer trangThai, String lyDoHuy) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.soTienGiamGia = soTienGiamGia;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
        this.lyDoHuy = lyDoHuy;
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

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getSoTienGiamGia() {
        return soTienGiamGia;
    }

    public void setSoTienGiamGia(BigDecimal soTienGiamGia) {
        this.soTienGiamGia = soTienGiamGia;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getLyDoHuy() {
        return lyDoHuy;
    }

    public void setLyDoHuy(String lyDoHuy) {
        this.lyDoHuy = lyDoHuy;
    }
    
    
}
