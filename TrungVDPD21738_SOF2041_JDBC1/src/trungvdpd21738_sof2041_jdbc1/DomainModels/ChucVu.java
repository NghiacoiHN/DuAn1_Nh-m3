/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.DomainModels;

/**
 *
 * @author TrungVD
 */
public class ChucVu {
    private String iD;
    private String maCV;
    private String tenCV;

    public ChucVu() {
    }

    public ChucVu(String iD) {
        this.iD = iD;
    }

    
    public ChucVu(String maCV, String tenCV) {
        this.maCV = maCV;
        this.tenCV = tenCV;
    }

    public ChucVu(String iD, String maCV, String tenCV) {
        this.iD = iD;
        this.maCV = maCV;
        this.tenCV = tenCV;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    @Override
    public String toString() {
        return tenCV;
    }
    
    
}
