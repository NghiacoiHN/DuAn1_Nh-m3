/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.SanPham;

/**
 *
 * @author TrungVD
 */
public interface SanPhamService {
    public ArrayList<SanPham> getListSanPhamDB();
    
    public String themSanPham(SanPham sanPham);
    
    public String suaSanPham(SanPham sanPham, String maSPSua);
    
    public String xoaSanPham(String maSPXoa);
    
    public ArrayList<SanPham> timKiemSanPham(String tenSPSua);
}
