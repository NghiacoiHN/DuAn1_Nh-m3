/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.MauSac;

/**
 *
 * @author TrungVD
 */
public interface MauSacRepository {
    public ArrayList<MauSac> getListMSDB();
    
    public Boolean themMauSac(MauSac mauSac);
    
    public Boolean suaMauSac(MauSac mauSac, String maMSSua);
    
    public Boolean xoaMauSac(String maMSXoa);
    
    public ArrayList<MauSac> timKiemMauSac(String tenMauSac);
}
