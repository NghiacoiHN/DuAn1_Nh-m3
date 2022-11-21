/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.KhachHang;

/**
 *
 * @author TrungVD
 */
public interface KhachHangService {
    public ArrayList<KhachHang> getListKHDB();

    public String themKH(KhachHang khachHang);

    public String suaKH(KhachHang khachHang, String iD);

    public String xoaKH(String maKH);

    public ArrayList<KhachHang> timKH(String tenKH);
}
