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
public class NhanVien {
    private String iD;
    private String ma;
    private String ten;
    private String tenDem;
    private String ho;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    private String sDT;
    private String matKhau;
    private CuaHang cuaHang;
    private ChucVu chucVu;
    private String iDGuiBC;
    private int TrangThai;

    public NhanVien() {
    }

    public NhanVien(String ma, String ten, String tenDem, String ho, String gioiTinh, String ngaySinh, String diaChi, String sDT) {
        this.ma = ma;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sDT = sDT;
    }

    
    public NhanVien(String ma, String ten, String tenDem, String ho, String gioiTinh, String ngaySinh, String diaChi, String sDT, String matKhau, CuaHang cuaHang, ChucVu chucVu, String iDGuiBC, int TrangThai) {
        this.ma = ma;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.matKhau = matKhau;
        this.cuaHang = cuaHang;
        this.chucVu = chucVu;
        this.iDGuiBC = iDGuiBC;
        this.TrangThai = TrangThai;
    }

    public NhanVien(String iD, String ma, String ten, String tenDem, String ho, String gioiTinh, String ngaySinh, String diaChi, String sDT, String matKhau, CuaHang cuaHang, ChucVu chucVu, String iDGuiBC, int TrangThai) {
        this.iD = iD;
        this.ma = ma;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.matKhau = matKhau;
        this.cuaHang = cuaHang;
        this.chucVu = chucVu;
        this.iDGuiBC = iDGuiBC;
        this.TrangThai = TrangThai;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public CuaHang getCuaHang() {
        return cuaHang;
    }

    public void setCuaHang(CuaHang cuaHang) {
        this.cuaHang = cuaHang;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public String getiDGuiBC() {
        return iDGuiBC;
    }

    public void setiDGuiBC(String iDGuiBC) {
        this.iDGuiBC = iDGuiBC;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
