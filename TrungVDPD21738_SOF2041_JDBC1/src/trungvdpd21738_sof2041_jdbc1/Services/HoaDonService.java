/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.HoaDon;
import trungvdpd21738_sof2041_jdbc1.ViewModels.HoaDonBHModelView;

/**
 *
 * @author TrungVD
 */
public interface HoaDonService {
    public ArrayList<HoaDonBHModelView> getListHDBH();
    
    public String themHD(HoaDon hoaDon);
}
