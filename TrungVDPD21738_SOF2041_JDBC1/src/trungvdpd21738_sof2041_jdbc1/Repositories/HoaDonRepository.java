/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.HoaDon;
import trungvdpd21738_sof2041_jdbc1.ViewModels.HoaDonBHModelView;

/**
 *
 * @author TrungVD
 */
public interface HoaDonRepository {
    public ArrayList<HoaDon> getListHDDB();
    
    public Boolean themHoaDon(HoaDon hoaDon);
    
    public Boolean suaHoaDon(HoaDon hoaDon, String maHD);
    
    public Boolean xoaHoaDon(String maHD);
    
    public ArrayList<HoaDon> timKiemHD(String maHD);
    
    public ArrayList<HoaDonBHModelView> getListHDBH();
}
