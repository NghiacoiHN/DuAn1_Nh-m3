/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

/**
 *
 * @author TrungVD
 */
public class SanPham {
    private String iD;
    private String maSP;
    private String tenSP;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public SanPham(String iD, String maSP, String tenSP) {
        this.iD = iD;
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
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

    @Override
    public String toString() {
        return tenSP;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SanPham){
            SanPham sP = (SanPham) obj;
            if(this.iD.equals(sP.iD)){
                return true;
            }
        }
        return false;
    }

}
