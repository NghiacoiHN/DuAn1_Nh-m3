/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.NhaSanXuat;

/**
 *
 * @author TrungVD
 */
public interface NSXService {
    public ArrayList<NhaSanXuat> getListNSXDB();
    
    public String themNSX(NhaSanXuat nhaSanXuat);
    
    public String suaNSX(NhaSanXuat nhaSanXuat, String maNSXSua);
    
    public String xoaNSX(String maNSXXoa);
    
    public ArrayList<NhaSanXuat> timKiemNSX(String tenNSX);
}
