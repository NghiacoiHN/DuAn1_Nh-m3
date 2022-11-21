/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trungvdpd21738_sof2041_jdbc1.Repositories;

import java.util.ArrayList;
import trungvdpd21738_sof2041_jdbc1.DomainModels.ChiTietSanPham;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SPBanHangViewModel;
import trungvdpd21738_sof2041_jdbc1.ViewModels.SanPhamTest;

/**
 *
 * @author TrungVD
 */
public interface ChiTietSanPhamRepository {

    public ArrayList<ChiTietSanPham> getListCTSPDB();

    public Boolean themCTSP(ChiTietSanPham chiTietSanPham);

    public Boolean suaCTSP(ChiTietSanPham chiTietSanPham, String iDCTSP);

    public Boolean xoaCTSP(String maCTSPX);
    
    public ArrayList<ChiTietSanPham> timCTSP(String tenCTSP);
    
    public ArrayList<SPBanHangViewModel> getListSP();
    
    public ArrayList<SPBanHangViewModel> timSPBanHang(String tenTimKiem);
    
    public ArrayList<SanPhamTest> getListSPTest();
    
    public Boolean themSanPham(SanPhamTest sanPham);
    
    public Boolean updateSPTest(SanPhamTest sanPham,String iDSP);
}
