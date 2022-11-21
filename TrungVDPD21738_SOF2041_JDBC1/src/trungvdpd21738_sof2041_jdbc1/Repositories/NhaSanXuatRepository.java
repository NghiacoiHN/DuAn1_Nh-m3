/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhaSanXuat;

/**
 *
 * @author TrungVD
 */
public interface NhaSanXuatRepository {
    public ArrayList<NhaSanXuat> getListNSXDB();
    
    public Boolean themNSX(NhaSanXuat nhaSanXuat);
    
    public Boolean suaNSX(NhaSanXuat nhaSanXuat, String maNSXSua);
    
    public Boolean xoaNSX(String maNSXXoa);
    
    public ArrayList<NhaSanXuat> timKiemNSX(String tenNSX);
}
