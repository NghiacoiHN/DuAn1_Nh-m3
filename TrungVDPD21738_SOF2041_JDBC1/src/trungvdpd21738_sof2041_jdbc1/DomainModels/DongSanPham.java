/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

/**
 *
 * @author TrungVD
 */
public class DongSanPham {
    private String iD;
    private String maDSP;
    private String tenDSP;

    public DongSanPham() {
    }

    public DongSanPham(String maDSP, String tenDSP) {
        this.maDSP = maDSP;
        this.tenDSP = tenDSP;
    }

    public DongSanPham(String iD, String maDSP, String tenDSP) {
        this.iD = iD;
        this.maDSP = maDSP;
        this.tenDSP = tenDSP;
    }


    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
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

    @Override
    public String toString() {
        return tenDSP;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DongSanPham){
            DongSanPham dSP = (DongSanPham) obj;
            if(this.iD.equals(dSP.iD)){
                return true;
            }
        }
        return false;
    }
    
    
}
