/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.math.BigDecimal;

/**
 *
 * @author TRONG NGHIA
 */
public class BHGioHangDM {

    String IDHDCT;
    String IDHoaDon;
    String IDCTSP;
    Integer soLuong;
    BigDecimal donGia;
    Integer trangThai;

    public BHGioHangDM() {
    }

   


    public String getIDHDCT() {
        return IDHDCT;
    }

    public void setIDHDCT(String IDHDCT) {
        this.IDHDCT = IDHDCT;
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDCTSP() {
        return IDCTSP;
    }

    public void setIDCTSP(String IDCTSP) {
        this.IDCTSP = IDCTSP;
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

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

  

}
