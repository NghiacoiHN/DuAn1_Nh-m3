/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Services;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChiTietSanPham;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SPBanHangViewModel;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SanPhamTest;

/**
 *
 * @author TrungVD
 */
public interface CTSPService {
    public ArrayList<ChiTietSanPham> getListCTSPDB();

    public String themCTSP(ChiTietSanPham chiTietSanPham);

    public String suaCTSP(ChiTietSanPham chiTietSanPham, String iDCTSP);

    public String xoaCTSP(String maCTSPX);
    
    public ArrayList<ChiTietSanPham> timCTSP(String tenCTSP);
    
    public ArrayList<SPBanHangViewModel> getListSP();
    
    public ArrayList<SPBanHangViewModel> timSPBanHang(String tenTimKiem);
    
    public ArrayList<SanPhamTest> getListSPTest();
    
    public String themSanPham(SanPhamTest sanPham);
    
    public String updateSPTest(SanPhamTest sanPham,String iDSP);
}
