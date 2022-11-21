/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.ViewModels;

import java.util.Date;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhanVien;

/**
 *
 * @author TrungVD
 */
public class HoaDonBHModelView {
    private String maHD;
    private Date ngayTao;
    private String tenNV;
    private int tinhTrang;

    public HoaDonBHModelView() {
    }

    public HoaDonBHModelView(String maHD, Date ngayTao, String tenNV, int tinhTrang) {
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.tenNV = tenNV;
        this.tinhTrang = tinhTrang;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }


    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    
}
