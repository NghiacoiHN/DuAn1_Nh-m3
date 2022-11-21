/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.SanPham;

/**
 *
 * @author TrungVD
 */
public interface SanPhamRepository {
    public ArrayList<SanPham> getListSanPhamDB();
    
    public Boolean themSanPham(SanPham sanPham);
    
    public Boolean suaSanPham(SanPham sanPham, String maSPSua);
    
    public Boolean xoaSanPham(String maSPXoa);
    
    public ArrayList<SanPham> timKiemSanPham(String tenSPSua);
    
    public Boolean kiemTraTenSP(String tenSanPham);
}
