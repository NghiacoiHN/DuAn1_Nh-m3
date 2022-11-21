/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

/**
 *
 * @author TrungVD
 */
public class NhaSanXuat {
    private String iD;
    private String maNSX;
    private String tenNSX;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String maNSX, String tenNSX) {
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
    }

    public NhaSanXuat(String iD, String maNSX, String tenNSX) {
        this.iD = iD;
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    @Override
    public String toString() {
        return tenNSX;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NhaSanXuat){
            NhaSanXuat nSX = (NhaSanXuat) obj;
            if(this.iD.equals(nSX.iD)){
                return true;
            }
        }
        return false;
    }
    
    
}
