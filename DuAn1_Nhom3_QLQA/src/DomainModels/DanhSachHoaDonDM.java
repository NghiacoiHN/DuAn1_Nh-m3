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
public class DanhSachHoaDonDM {
    
    
    String idHD;
    String idKH;
    String idNV;
    String idTichDiemCT;
    String maHD; 
    String maNV;
    String ngayTao;
    String ngayThanhToan;
    BigDecimal soTienGiam;
    BigDecimal thanhTien;
    BigDecimal tienDua;
    BigDecimal tienThua;
    String hinhThucThanhToan;
    String tenKH;
    String sdtKH;
    String tenShip;
    String sdtShip;
    String diaChi;
    String ngayMuonNhan;
    String ngayGiao;
    String ngayGiaoThanhCong;
    Integer trangThai;
    String tenNV;
    String lyDoHuy;

    public DanhSachHoaDonDM() {
    }

    public DanhSachHoaDonDM(String idHD, String idKH, String idNV, String idTichDiemCT, String maHD, String maNV, String ngayTao, String ngayThanhToan, BigDecimal soTienGiam, BigDecimal thanhTien, BigDecimal tienDua, BigDecimal tienThua, String hinhThucThanhToan, String tenKH, String sdtKH, String tenShip, String sdtShip, String diaChi, String ngayMuonNhan, String ngayGiao, String ngayGiaoThanhCong, Integer trangThai, String tenNV, String lyDoHuy) {
        this.idHD = idHD;
        this.idKH = idKH;
        this.idNV = idNV;
        this.idTichDiemCT = idTichDiemCT;
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.soTienGiam = soTienGiam;
        this.thanhTien = thanhTien;
        this.tienDua = tienDua;
        this.tienThua = tienThua;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.tenShip = tenShip;
        this.sdtShip = sdtShip;
        this.diaChi = diaChi;
        this.ngayMuonNhan = ngayMuonNhan;
        this.ngayGiao = ngayGiao;
        this.ngayGiaoThanhCong = ngayGiaoThanhCong;
        this.trangThai = trangThai;
        this.tenNV = tenNV;
        this.lyDoHuy = lyDoHuy;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getIdTichDiemCT() {
        return idTichDiemCT;
    }

    public void setIdTichDiemCT(String idTichDiemCT) {
        this.idTichDiemCT = idTichDiemCT;
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

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public BigDecimal getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(BigDecimal soTienGiam) {
        this.soTienGiam = soTienGiam;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public BigDecimal getTienDua() {
        return tienDua;
    }

    public void setTienDua(BigDecimal tienDua) {
        this.tienDua = tienDua;
    }

    public BigDecimal getTienThua() {
        return tienThua;
    }

    public void setTienThua(BigDecimal tienThua) {
        this.tienThua = tienThua;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getTenShip() {
        return tenShip;
    }

    public void setTenShip(String tenShip) {
        this.tenShip = tenShip;
    }

    public String getSdtShip() {
        return sdtShip;
    }

    public void setSdtShip(String sdtShip) {
        this.sdtShip = sdtShip;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayMuonNhan() {
        return ngayMuonNhan;
    }

    public void setNgayMuonNhan(String ngayMuonNhan) {
        this.ngayMuonNhan = ngayMuonNhan;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getNgayGiaoThanhCong() {
        return ngayGiaoThanhCong;
    }

    public void setNgayGiaoThanhCong(String ngayGiaoThanhCong) {
        this.ngayGiaoThanhCong = ngayGiaoThanhCong;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getLyDoHuy() {
        return lyDoHuy;
    }

    public void setLyDoHuy(String lyDoHuy) {
        this.lyDoHuy = lyDoHuy;
    }

    
    
    
    
}
