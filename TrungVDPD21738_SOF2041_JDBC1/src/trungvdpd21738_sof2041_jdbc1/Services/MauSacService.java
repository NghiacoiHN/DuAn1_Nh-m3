/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.MauSac;

/**
 *
 * @author TrungVD
 */
public interface MauSacService {
    public ArrayList<MauSac> getListMSDB();
    
    public String themMauSac(MauSac mauSac);
    
    public String suaMauSac(MauSac mauSac, String maMSSua);
    
    public String xoaMauSac(String maMSXoa);
    
    public ArrayList<MauSac> timKiemMauSac(String tenMauSac);
}
