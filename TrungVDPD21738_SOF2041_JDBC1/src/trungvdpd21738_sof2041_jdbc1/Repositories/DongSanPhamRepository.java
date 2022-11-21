/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.DongSanPham;

/**
 *
 * @author TrungVD
 */
public interface DongSanPhamRepository {
    public ArrayList<DongSanPham> getListDSPDB();
    
    public Boolean themDSP(DongSanPham dongSanPham);
    
    public Boolean suaDSP(DongSanPham dongSanPham, String maDSP);
    
    public Boolean xoaDSP(String maDSPXoa);
    
    public ArrayList<DongSanPham> timKiemDSP(String tenDSPTim);
}
