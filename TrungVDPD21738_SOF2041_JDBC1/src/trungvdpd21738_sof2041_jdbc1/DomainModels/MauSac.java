/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

/**
 *
 * @author TrungVD
 */
public class MauSac {
    private String iD;
    private String maMauSac;
    private String tenMauSac;

    public MauSac() {
    }

    public MauSac(String maMauSac, String tenMauSac) {
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }

    public MauSac(String iD, String maMauSac, String tenMauSac) {
        this.iD = iD;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    @Override
    public String toString() {
        return tenMauSac;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MauSac){
            MauSac mS = (MauSac) obj;
            if(this.iD.equals(mS.iD)){
                return true;
            }
        }
        return false;
    }
    
    
}
