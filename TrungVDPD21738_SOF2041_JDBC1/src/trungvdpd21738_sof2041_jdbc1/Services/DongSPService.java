/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.DongSanPham;

/**
 *
 * @author TrungVD
 */
public interface DongSPService {
    public ArrayList<DongSanPham> getListDSPDB();
    
    public String themDSP(DongSanPham dongSanPham);
    
    public String suaDSP(DongSanPham dongSanPham, String maDSP);
    
    public String xoaDSP(String maDSPXoa);
    
    public ArrayList<DongSanPham> timKiemDSP(String tenDSPTim);
}
