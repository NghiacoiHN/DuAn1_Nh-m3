/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.KhachHang;

/**
 *
 * @author TrungVD
 */
public interface KhachHangRepository {

    public ArrayList<KhachHang> getListKHDB();

    public boolean themKH(KhachHang khachHang);

    public boolean suaKH(KhachHang khachHang, String iD);

    public boolean xoaKH(String maKH);

    public ArrayList<KhachHang> timKH(String tenKH);
}
